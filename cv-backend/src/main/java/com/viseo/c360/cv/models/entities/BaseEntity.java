package com.viseo.c360.cv.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
    @Column
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

}
