package com.viseo.c360.cv.models.dto;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class LanguageDto extends BaseDto {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageDto that = (LanguageDto) o;

        if (id != that.id) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
