package com.maersk.booking.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.maersk.booking.entity.Booking;



@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
}