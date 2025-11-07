package com.maersk.booking.dto;



public class BookingResponse {
    private String bookingRef;
    private String status;

    public BookingResponse(String bookingRef, String status) {
        this.bookingRef = bookingRef;
        this.status = status;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public String getStatus() {
        return status;
    }
}