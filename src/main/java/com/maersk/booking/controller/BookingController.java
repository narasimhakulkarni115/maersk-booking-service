package com.maersk.booking.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import com.maersk.booking.dto.BookingResponse;
import com.maersk.booking.dto.CheckAvailabilityRequest;  // Assuming CheckAvailabilityRequest is in the dto package
import com.maersk.booking.dto.CheckAvailabilityResponse;
import com.maersk.booking.dto.CreateBookingRequest;
import com.maersk.booking.dto.CreateBookingResponse;
import com.maersk.booking.service.AvailabilityService;
import com.maersk.booking.service.BookingService;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import java.util.Map;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  //  private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;
    private final AvailabilityService availabilityService;
    public BookingController(BookingService bookingService, AvailabilityService availabilityService) {
        this.bookingService = bookingService;
        this.availabilityService = availabilityService;
    }
    public Mono<CheckAvailabilityResponse> checkAvailability(@Valid @RequestBody CheckAvailabilityRequest request) {
        return availabilityService.checkAvailable(request)
            .map(availableSpace -> {
                Integer qty = request.quantity();
                return new CheckAvailabilityResponse(qty != null && availableSpace >= qty);
            });
    }
    @PostMapping
    public Mono<CreateBookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.createBooking(request)
            .map(CreateBookingResponse::new)
            .onErrorResume(e -> {
               // log.error("Error saving booking", e);
                return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request"));
            });
    }
}