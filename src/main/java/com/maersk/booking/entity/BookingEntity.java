package com.maersk.booking.entity;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;
import com.maersk.booking.dto.CheckAvailabilityRequest;  // Assuming CheckAvailabilityRequest is in the dto package
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;



@Document(collection = "bookings")
public record BookingEntity(
    @Id String bookingRef,
    @Field("container_size") Integer containerSize,
    @Field("container_type") String containerType,
    @Field("origin") String origin,
    @Field("destination") String destination,
    @Field("quantity") Integer quantity,
    @Field("timestamp") String timestamp
) {}