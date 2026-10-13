package com.piyus.JavaHibernatePractical.model;

import jakarta.persistence.AttributeConverter;

public class BooleanToStringConverter
        implements AttributeConverter<Boolean, String> {


    @Override
    public String convertToDatabaseColumn(Boolean attribute) {

        if (attribute == null) {
            return null;
        }

        if (attribute == true) {
            return "true";
        }
        return "false";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        if(dbData.equals("Yes")){
            return true;
        }
        return false;
    }
}
