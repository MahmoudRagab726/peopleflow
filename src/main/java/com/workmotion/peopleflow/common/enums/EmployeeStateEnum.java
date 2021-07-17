package com.workmotion.peopleflow.common.enums;

import lombok.Getter;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
public enum EmployeeStateEnum {

    ADDED("ADDED"),
    IN_CHECK("IN-CHECK"),
    APPROVED("APPROVED"),
    ACTIVE("ACTIVE");

    @Getter
    private final String state;

    public static final String VALUE_LIST = "[ADDED,IN-CHECK,APPROVED,ACTIVE]";

    EmployeeStateEnum(final String state) {
        this.state = state;
    }

    public static boolean contains(String value) {
        try {
            valueOf(value);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}