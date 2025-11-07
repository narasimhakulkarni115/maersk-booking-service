package com.maersk.booking.dao;

import com.maersk.booking.dto.CreateBookingRequest;

import reactor.core.publisher.Mono;



public interface BookingDao {
    Mono<String> saveBooking(CreateBookingRequest request);
    Mono<Long> countBookings();

}