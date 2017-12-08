package com.viseo.c360.cv.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ELE3653 on 19/07/2017.
 */
@Entity
@Table(name = "skills")
public class SkillEntity extends BaseEntity {
    @Column
    private String label;

    @Column
    private String domain;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
