package com.viseo.c360.cv.models.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ELE3653 on 19/07/2017.
 */

@Entity
@Table(name = "type_missions")
public class TypeMissionsEntity extends BaseEntity {

    @Column
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
