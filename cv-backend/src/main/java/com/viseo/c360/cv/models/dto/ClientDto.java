package com.viseo.c360.cv.models.dto;


/**
 * Created by ELE3653 on 07/08/2017.
 */
public class ClientDto extends BaseDto{

    private String label;

    private String domain;

    private String description;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        ClientDto clientDto = (ClientDto) o;

        if (id != clientDto.id) return false;
        if (label != null ? !label.equals(clientDto.label) : clientDto.label != null) return false;
        if (domain != null ? !domain.equals(clientDto.domain) : clientDto.domain != null) return false;
        return (description != null ? !description.equals(clientDto.description) : clientDto.description != null);
        //return mission != null ? mission.equals(clientDto.mission) : clientDto.mission == null;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        //result = 31 * result + (mission != null ? mission.hashCode() : 0);
        return result;
    }
}
