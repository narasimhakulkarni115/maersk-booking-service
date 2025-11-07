package com.maersk.booking;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import com.maersk.booking.dto.CheckAvailabilityRequest;
import com.maersk.booking.service.AvailabilityService;
import reactor.test.StepVerifier;



@ExtendWith(MockitoExtension.class)
class AvailabilityServiceTest {
  
}
