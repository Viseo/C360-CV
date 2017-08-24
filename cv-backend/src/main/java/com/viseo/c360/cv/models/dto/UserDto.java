package com.viseo.c360.cv.models.dto;

import com.viseo.c360.cv.models.entities.MissionsEntity;

import java.util.Date;
import java.util.List;

public class UserDto {

    private int id;

    private String login;

    private String firstName;

    private String lastName;

    private Date birth_date;

    private String mail;

    private String hobbies;

    private String telephone;

    private String password;

    private String picture;

    private String position;

    private String experience;

    private Boolean admin;

    private List<LanguageDto> languages;

    private List<MissionsEntity> missions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public List<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDto> languages) {
        this.languages = languages;
    }

    public List<MissionsEntity> getMissions() {
        return missions;
    }

    public void setMissions(List<MissionsEntity> missions) {
        this.missions = missions;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (id != userDto.id) return false;
        if (login != null ? !login.equals(userDto.login) : userDto.login != null) return false;
        if (firstName != null ? !firstName.equals(userDto.firstName) : userDto.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userDto.lastName) : userDto.lastName != null) return false;
        if (birth_date != null ? !birth_date.equals(userDto.birth_date) : userDto.birth_date != null) return false;
        if (mail != null ? !mail.equals(userDto.mail) : userDto.mail != null) return false;
        if (hobbies != null ? !hobbies.equals(userDto.hobbies) : userDto.hobbies != null) return false;
        if (telephone != null ? !telephone.equals(userDto.telephone) : userDto.telephone != null) return false;
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) return false;
        if (picture != null ? !picture.equals(userDto.picture) : userDto.picture != null) return false;
        if (position != null ? !position.equals(userDto.position) : userDto.position != null) return false;
        if (experience != null ? !experience.equals(userDto.experience) : userDto.experience != null) return false;
        if (admin != null ? !admin.equals(userDto.admin) : userDto.admin != null) return false;
        if (languages != null ? !languages.equals(userDto.languages) : userDto.languages != null) return false;
        return missions != null ? missions.equals(userDto.missions) : userDto.missions == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birth_date != null ? birth_date.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (hobbies != null ? hobbies.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (missions != null ? missions.hashCode() : 0);
        return result;
    }
}
