package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Component
public class AccountDAO implements CrudRepository<UsersEntity, Long> {

    @PersistenceContext
    EntityManager em;

    public UsersEntity findByCredential(String mail, String password){
        try{
            return em.createQuery("SELECT U FROM UsersEntity U JOIN FETCH U.missions M " +
                    "JOIN FETCH U.languages " +
                    "JOIN FETCH M.skills " +
                    "WHERE U.mail = ?1 AND U.password = ?2",UsersEntity.class)
                    .setParameter(1,mail)
                    .setParameter(2,password)
                    .getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because as per our logic this is ok!
        }
        return null;
    }

    public UsersEntity findByMail(String mail){
        try{
            return  em.createQuery("SELECT U FROM UsersEntity U JOIN FETCH U.missions M " +
                    "JOIN FETCH U.languages " +
                    "JOIN FETCH M.skills WHERE U.mail = ?1", UsersEntity.class)
                    .setParameter(1, mail).getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because as per our logic this is ok!
        }
        return null;
    }

    public UsersEntity findById(int id){
        try{
            return em.createQuery("SELECT U FROM UsersEntity U JOIN FETCH U.missions M " +
                    "JOIN FETCH U.languages " +
                    "JOIN FETCH M.skills WHERE U.id = ?1", UsersEntity.class)
                    .setParameter(1,id).getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because as per our logic this is ok!
        }
        return null;
    }

    public List<UsersEntity> getAll(){
        try{
            return em.createQuery("SELECT U FROM UsersEntity U JOIN FETCH U.missions M " +
                    "JOIN FETCH U.languages " +
                    "JOIN FETCH M.skills", UsersEntity.class).getResultList();
        }
        catch (NoResultException nre){
            //Ignore this because as per our logic this is ok!
        }
        return null;
    }

    @Override
    public <S extends UsersEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UsersEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UsersEntity> findById(Long aLong) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UsersEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<UsersEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UsersEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends UsersEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
