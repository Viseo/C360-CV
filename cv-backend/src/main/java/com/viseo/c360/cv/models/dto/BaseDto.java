package com.viseo.c360.cv.models.dto;

/**
 * Created by YGU3747 on 05/01/2018
 */

public abstract class BaseDto {
    protected long id;

    protected long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
