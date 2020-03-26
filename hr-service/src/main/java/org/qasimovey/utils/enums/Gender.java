package org.qasimovey.utils.enums;

public enum Gender {
    MALE(1,"MALE"),
    FEMALE(0,"FEMALE"),
    ALL(3,"ALL");
    public final int code;
    public final String value;
    private Gender(int code,String value){
        this.value=value;
        this.code=code;
    }
}
