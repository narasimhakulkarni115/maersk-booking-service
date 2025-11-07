package com.maersk.booking;


import com.maersk.booking.controller.BookingController;
import com.maersk.booking.dto.CheckAvailabilityRequest;
import com.maersk.booking.dto.CheckAvailabilityResponse;
import com.maersk.booking.dto.ContainerType;
import com.maersk.booking.service.AvailabilityService;
import com.maersk.booking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BookingController.class)
class BookingControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AvailabilityService availabilityService;

    @MockBean
    private BookingService bookingService;  // ✅ Add this mock

    @Test
    void shouldCheckAvailability() {
        when(availabilityService.checkAvailable(any(CheckAvailabilityRequest.class)))
            .thenReturn(Mono.just(10));  // Mock return value

        CheckAvailabilityRequest request = new CheckAvailabilityRequest(20, ContainerType.DRY, "Southampton", "Singapore", 5);
        webTestClient.post()
        .uri("/api/bookings/check")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus().isOk();
    }
}
