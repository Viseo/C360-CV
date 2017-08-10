package com.viseo.c360.cv.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientsEntity extends BaseEntity {

    @Column
    private String label;

    @Column
    private String description;

    /*@OneToMany (mappedBy = "clientId",fetch = FetchType.LAZY)
    private List <MissionsEntity> mission;*/

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public List<MissionsEntity> getMission() {
        return mission;
    }

    public void setMission(List<MissionsEntity> mission) {
        this.mission = mission;
    }*/
}
