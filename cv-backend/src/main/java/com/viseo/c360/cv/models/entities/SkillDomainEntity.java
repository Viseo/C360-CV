package com.viseo.c360.cv.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by YGU3747 on 10/01/2018
 */
@Entity
@Table(name = "domain")
public class SkillDomainEntity extends BaseEntity {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
