package org.qasimovey.utils.enums;

public enum State {
    WAITING_FOR_INTERVIEW(3),
    NOT_WORKING(0),
    WORKING(1);
    public final int code;

    State(final int code) {
        this.code = code;
    }
}
