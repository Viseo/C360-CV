package com.viseo.c360.cv.models.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "missions")
public class MissionEntity extends BaseEntity {

    @Column
    private int id;

    @Column
    private String title;

    @Column
    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column
    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="client_id")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name="type_id")
    private TypeMissionsEntity typeMissions;

    @ManyToMany
    @JoinTable(
            name="MISSIONS_SKILLS",
            joinColumns=@JoinColumn(name="missions_id"),
            inverseJoinColumns=@JoinColumn(name="skills_id")
    )

    private List <SkillEntity> skills;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClientEntity getClient() {
        return this.client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public TypeMissionsEntity getTypeMissions() {
        return typeMissions;
    }

    public void setTypeMissions(TypeMissionsEntity typeMissions) {
        this.typeMissions = typeMissions;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

}
