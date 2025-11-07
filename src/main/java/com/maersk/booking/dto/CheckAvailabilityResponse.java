package com.maersk.booking.dto;




public class CheckAvailabilityResponse {
    private boolean available;

    public CheckAvailabilityResponse(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}