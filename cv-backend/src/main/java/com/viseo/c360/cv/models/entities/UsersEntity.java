package com.viseo.c360.cv.models.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name="birth_date")
    @Temporal(TemporalType.DATE)
    private Date birth_date;

    @Column(unique = true, nullable = false)
    @NotNull
    @Email
    private String mail;

    @Column(unique = true, nullable = false)
    private String login;

    @Column
    private String hobbies;

    @Column
    private String telephone;

    @Column
    private String password;

    @Column
    private Boolean admin;

    @Column
    private String position;

    @Column
    private String picture;

    @Column
    private String experience;

    @ManyToMany
    @JoinTable(
            name="USERS_LANGUAGES",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="language_id")
    )
    private List <LanguagesEntity> languages;

    @ManyToMany
    @JoinTable(
            name="USERS_MISSIONS",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="missions_id")
    )
    private List <MissionsEntity> missions;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LanguagesEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguagesEntity> languages) {
        this.languages = languages;
    }

    public List<MissionsEntity> getMissions() {
        return missions;
    }

    public void setMissions(List<MissionsEntity> missions) {
        this.missions = missions;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
