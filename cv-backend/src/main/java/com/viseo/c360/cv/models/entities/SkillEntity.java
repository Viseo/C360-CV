package com.viseo.c360.cv.models.entities;

import javax.persistence.*;

/**
 * Created by ELE3653 on 19/07/2017.
 */
@Entity
@Table(name = "skills")
public class SkillEntity extends BaseEntity {
    @Column
    private String label;

    @ManyToOne
    @JoinColumn(name="domain_id")
    private SkillDomainEntity domain;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SkillDomainEntity getDomain() {
        return domain;
    }

    public void setDomain(SkillDomainEntity domain) {
        this.domain = domain;
    }
}
