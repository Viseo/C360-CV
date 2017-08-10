package com.viseo.c360.cv.models.dto;


/**
 * Created by ELE3653 on 07/08/2017.
 */
public class SkillsDto {

    private String label;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillsDto skillsDto = (SkillsDto) o;

        if (label != null ? !label.equals(skillsDto.label) : skillsDto.label != null) return false;
        return domain != null ? domain.equals(skillsDto.domain) : skillsDto.domain == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        return result;
    }
}
