package com.zenika.meetingplanner.services;

public class NameAlreadyExists extends RuntimeException {
    public NameAlreadyExists(String message) {
        super(message);
    }
}
