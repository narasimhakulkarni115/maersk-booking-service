package com.maersk.booking.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maersk.booking.dao.BookingDao;
import com.maersk.booking.dto.CheckAvailabilityResponse;
import com.maersk.booking.dto.CreateBookingRequest;
import com.maersk.booking.entity.Booking;
import reactor.core.publisher.Mono;




import com.maersk.booking.dto.CreateBookingRequest;
import com.maersk.booking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookingService {

	
	    private final BookingDao bookingDao;
	    public BookingService(BookingDao bookingDao) {
	        this.bookingDao = bookingDao;
	    }
	    public Mono<String> createBooking(CreateBookingRequest request) {
	        return bookingDao.saveBooking(request);
	    }
	}