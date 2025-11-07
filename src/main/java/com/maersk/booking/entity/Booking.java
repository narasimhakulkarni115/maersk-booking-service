package com.maersk.booking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String bookingRef;
    private int containerSize;
    private String containerType;
    private String origin;
    private String destination;
    private int quantity;
    private Instant timestamp;

    // ✅ Constructors
    public Booking() {}

    public Booking(String bookingRef, int containerSize, String containerType,
                   String origin, String destination, int quantity, Instant timestamp) {
        this.bookingRef = bookingRef;
        this.containerSize = containerSize;
        this.containerType = containerType;
        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    // ✅ Getters and Setters
    public String getId() {
        return id;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public int getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(int containerSize) {
        this.containerSize = containerSize;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    // ✅ toString() — useful for logs
    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", bookingRef='" + bookingRef + '\'' +
                ", containerSize=" + containerSize +
                ", containerType='" + containerType + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                '}';
    }
}