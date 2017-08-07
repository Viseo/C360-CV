package com.viseo.c360.cv.models.dto;

import java.util.List;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class ClientDto {

    private int id;

    private String label;

    private String description;

    private List<MissionDto> mission;

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

    public List<MissionDto> getMission() {
        return mission;
    }

    public void setMission(List<MissionDto> mission) {
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDto clientDto = (ClientDto) o;

        if (!label.equals(clientDto.label)) return false;
        if (!description.equals(clientDto.description)) return false;
        return mission != null ? mission.equals(clientDto.mission) : clientDto.mission == null;
    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (mission != null ? mission.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }
}
