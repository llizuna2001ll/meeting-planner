package com.zenika.meetingplanner.exceptions;

public class NoRoomsAvailableException extends RuntimeException {
    public NoRoomsAvailableException(String message) {
        super(message);
    }
}
