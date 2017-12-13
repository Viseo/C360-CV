package com.viseo.c360.cv.services;

import com.viseo.c360.cv.converters.UserEntityToDtoConverter;
import com.viseo.c360.cv.converters.UserDtoToEntityConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private AccountDAO accountDAO;

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




    @Override
    public UsersEntity exist(String mail, String password) {

        return this.findByCredential(mail, password);
    }

    @Override
    public UsersEntity add(UsersEntity user) {
        user.setLastUpdateDate(new Date());
        return accountDAO.save(user);
    }

    public UsersEntity mailExist(String mail) {
        return this.findByMail(mail);
    }

    public UsersEntity getUserById(int id) {
        return this.findById(id);
    }

    @Override
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

    public UsersEntity updateUser(UsersEntity user){
        user.setLastUpdateDate(new Date());
        return accountDAO.save(user);
    }
}
