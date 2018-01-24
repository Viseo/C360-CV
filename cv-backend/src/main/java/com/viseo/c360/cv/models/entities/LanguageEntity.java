package com.viseo.c360.cv.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ELE3653 on 19/07/2017.
 */

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

    @Column
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
