package net.gymbay.baychat.util;

import jakarta.websocket.CloseReason;

public enum CloseCode implements CloseReason.CloseCode {

    UNKNOWN_ERROR(4000),
    UNKNOWN_OPERATION(4001),
    NOT_AUTHENTICATED(4002),
    WRONG_FORMAT(4003),
    AUTHORIZATION_FAILED(4004),
    TIMED_OUT(4005),
    INVALID_SESSION(4006),
    //NOT_SECURE_CONNECTION(4007),
    RECONNECT(4008);

    private final int code;

    CloseCode(int closeCode) {
        this.code = closeCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
