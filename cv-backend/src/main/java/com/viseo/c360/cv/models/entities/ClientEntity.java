package com.viseo.c360.cv.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity extends BaseEntity {

    @Column
    private String label;

    @Column
    private String domain;

    @Column
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
}
