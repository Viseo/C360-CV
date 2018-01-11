package com.viseo.c360.cv.models.dto;

import com.viseo.c360.cv.models.entities.SkillEntity;

import java.util.Set;

/**
 * Created by YGU3747 on 10/01/2018
 */

public class SkillDomainDto extends BaseDto {
    private String label;

    private Set<SkillDto> skills;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDto> skills) {
        this.skills = skills;
    }
}
