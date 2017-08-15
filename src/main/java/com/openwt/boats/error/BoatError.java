package com.openwt.boats.error;

public enum BoatError {
    INVALID_DATA("Invalid data."),
    MISSING_DATA("Missing data."),
    MISSING_NAME("Missing name."),
    MISSING_DESCRIPTION("Missing description."),
    MISSING_WEIGHT("Missing weight."),
    MISSING_OWNER("Missing owner.")

    ;


    private final String description;

    BoatError(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
