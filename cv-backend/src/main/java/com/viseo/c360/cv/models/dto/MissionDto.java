package com.viseo.c360.cv.models.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class MissionDto {

    private String title;

    private Date beginDate;

    private Date endDate;

    private String description;

    private ClientDto client;

    private TypeMissionsDto typeMissions;

    private List<SkillsDto> skills;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public TypeMissionsDto getTypeMissions() {
        return typeMissions;
    }

    public void setTypeMissions(TypeMissionsDto typeMissions) {
        this.typeMissions = typeMissions;
    }

    public List<SkillsDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsDto> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionDto that = (MissionDto) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (typeMissions != null ? !typeMissions.equals(that.typeMissions) : that.typeMissions != null) return false;
        return skills != null ? skills.equals(that.skills) : that.skills == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (typeMissions != null ? typeMissions.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        return result;
    }
}
