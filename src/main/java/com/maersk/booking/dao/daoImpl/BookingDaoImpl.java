package com.maersk.booking.dao.daoImpl;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.maersk.booking.dao.BookingDao;
import com.maersk.booking.dto.CreateBookingRequest;
import com.maersk.booking.entity.BookingEntity;

import reactor.core.publisher.Mono;




@Repository
public class BookingDaoImpl implements BookingDao {
    private final ReactiveMongoTemplate mongoTemplate;
    public BookingDaoImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Mono<String> saveBooking(CreateBookingRequest request) {
        return countBookings()
            .map(count -> String.format("957%06d", count + 1))
            .flatMap(bookingRef -> {
                BookingEntity entity = new BookingEntity(
                    bookingRef, request.containerSize(), request.containerType().name(),
                    request.origin(), request.destination(), request.quantity(), request.timestamp()
                );
                return mongoTemplate.save(entity).thenReturn(bookingRef);
            });
    }
    @Override
    public Mono<Long> countBookings() {
        return mongoTemplate.count(new Query(), BookingEntity.class);
    }
}
