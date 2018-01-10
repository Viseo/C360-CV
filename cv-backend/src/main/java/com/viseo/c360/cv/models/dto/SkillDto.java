package com.viseo.c360.cv.models.dto;


import com.viseo.c360.cv.models.entities.SkillDomainEntity;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class SkillDto extends BaseDto{

    private String label;

    private SkillDomainDto domain;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SkillDomainDto getDomain() {
        return domain;
    }

    public void setDomain(SkillDomainDto domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDto skillDto = (SkillDto) o;

        if (id != skillDto.id) return false;
        if (label != null ? !label.equals(skillDto.label) : skillDto.label != null) return false;
        return domain != null ? domain.equals(skillDto.domain) : skillDto.domain == null;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        return result;
    }

}
