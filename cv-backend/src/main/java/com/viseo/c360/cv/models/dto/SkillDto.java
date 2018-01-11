package com.viseo.c360.cv.models.dto;


/**
 * Created by ELE3653 on 07/08/2017.
 */
public class SkillDto extends BaseDto{

    private String label;

    private SkillDomainDto skillDomain;

    public SkillDomainDto getSkillDomain() {
        return skillDomain;
    }

    public void setSkillDomain(SkillDomainDto skillDomain) {
        this.skillDomain = skillDomain;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
