package org.qasimovey.utils.enums;

public enum Profession {
    PROGRAMMER(10, "PROGRAMMER"),
    // Mobile_Developer(20, " Mobile Developer"),
    // Database_Developer(30, "Database Developer"),
    ECONOMIST(40, "ECONOMIST"),
    MANAGER(50, "MANAGER");
    public final int code;
    public final String value;

    Profession(int code, String value) {
        this.code = code;
        this.value = value;
    }
}