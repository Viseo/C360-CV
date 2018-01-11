package com.viseo.c360.cv.models.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by YGU3747 on 10/01/2018
 */
@Entity
@Table(name = "domain")
public class SkillDomainEntity extends BaseEntity {

    private String label;

    @OneToMany(mappedBy = "skillDomain")
    private Set<SkillEntity> skills;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }
}
