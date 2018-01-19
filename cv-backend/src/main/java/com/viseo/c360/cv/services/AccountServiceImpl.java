package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.repositories.AccountDAO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;



@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private AccountDAO accountDAO;

    public UsersEntity findByCredential(String mail, String password){
        try{
            em.createQuery("SELECT U FROM UsersEntity U " +
                    "LEFT JOIN FETCH U.missions M " +
                    "LEFT JOIN FETCH M.skills " +
                    "WHERE U.mail = ?1 AND U.password = ?2",UsersEntity.class)
                    .setParameter(1,mail)
                    .setParameter(2,password)
                    .getSingleResult();
            return em.createQuery("SELECT U FROM UsersEntity U " +
                    "LEFT JOIN FETCH U.languages " +
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
            em.createQuery("SELECT U FROM UsersEntity U " +
                    "LEFT JOIN FETCH U.missions M " +
                    "LEFT JOIN FETCH M.skills " +
                    "WHERE U.mail =?1", UsersEntity.class)
                    .setParameter(1, mail).getSingleResult();
            return em.createQuery("SELECT U FROM UsersEntity U " +
                    "LEFT JOIN FETCH U.languages " +
                    "WHERE U.mail = ?1", UsersEntity.class)
                    .setParameter(1, mail).getSingleResult();

//            return  em.createQuery("SELECT U FROM UsersEntity U LEFT JOIN FETCH U.missions M " +
//                    "LEFT JOIN FETCH U.languages " +
//                    "LEFT JOIN FETCH M.skills WHERE U.mail = ?1", UsersEntity.class)
//                    .setParameter(1, mail).getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because as per our logic this is ok!
        }
        return null;
    }

    public UsersEntity findById(long id){
        try{
            //
            //n+1 probleme pour les codes ci-dessous
//            em.createQuery("SELECT U FROM UsersEntity U " +
//                    "LEFT JOIN FETCH U.missions M " +
//                    "WHERE U.id = ?1", UsersEntity.class)
//                    .setParameter(1,id).getSingleResult();
//
//            UsersEntity usersEntity = em.createQuery("SELECT U FROM UsersEntity U LEFT JOIN FETCH U.languages " +
//                    "WHERE U.id = ?1", UsersEntity.class)
//                    .setParameter(1,id).getSingleResult();
//            return usersEntity;
            em.createQuery("SELECT M FROM UsersEntity U " +
                    "JOIN U.missions M " +
                    "LEFT JOIN FETCH M.skills " +
                    "WHERE U.id = ?1", MissionEntity.class)
                    .setParameter(1,id).getResultList();
            return em.createQuery("SELECT U FROM UsersEntity U " +
                    "LEFT JOIN FETCH U.languages " +
                    "WHERE U.id = ?1", UsersEntity.class)
                    .setParameter(1,id).getSingleResult();

//            return em.createQuery("SELECT U FROM UsersEntity U LEFT JOIN FETCH U.missions M " +
//                    "LEFT JOIN FETCH U.languages " +
//                    "LEFT JOIN FETCH M.skills WHERE U.id = ?1", UsersEntity.class)
//                    .setParameter(1,id).getSingleResult();



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

    public UsersEntity getUserById(long id) {
        return this.findById(id);
    }

    @Override
    public List<UsersEntity> getAll(){
         em.createQuery("SELECT U FROM UsersEntity U " +
                "LEFT JOIN FETCH U.missions M " +
                "LEFT JOIN FETCH M.skills",
                UsersEntity.class).getResultList();
         return em.createQuery("SELECT U FROM UsersEntity U " +
                        "LEFT JOIN FETCH U.languages ",
                UsersEntity.class).getResultList();
    }

    @Override
    public UsersEntity updateUser(UsersEntity user){
        user.setLastUpdateDate(new Date());
        return accountDAO.save(user);
    }

//    @Override
//    @Transactional
//    public UsersEntity updateOnlyUserProfile(UsersEntity user) {
//        user = em.merge(user);
//        em.flush();
//        return user;
////        String request = "UPDATE UsersEntity SET " +
////                "firstName = :firstName, lastName = :lastName, " +
////                "position = :position, birth_day = :birthday, " +
////                "experience = :experience, telephone = :telephone, hobbies = :hobbies, " +
////                "languages = :languages";
////        Query query = em.createQuery(request);
////        query.setParameter("firstName", user.getFirstName())
////                .setParameter("lastName", user.getLastName())
////                .setParameter("position", user.getPosition())
////                .setParameter("birthday", user.getBirth_date())
////                .setParameter("experience", user.getExperience())
////                .setParameter("telephone", user.getExperience())
////                .setParameter("hobbies", user.getHobbies())
////                .setParameter("languages", user.getLanguages());
////        query.executeUpdate();
////        em.flush();
////        return this.findById(user.getId());
//    }

}
