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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id")
    private SkillDomainEntity skillDomain;

    public SkillDomainEntity getSkillDomain() {
        return skillDomain;
    }

    public void setSkillDomain(SkillDomainEntity skillDomain) {
        this.skillDomain = skillDomain;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
