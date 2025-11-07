package com.maersk.booking.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CheckAvailabilityRequest(
    @NotNull @Min(20) @Max(40) Integer containerSize,
    @NotNull ContainerType containerType,
    @NotBlank @Size(min=5, max=20) String origin,
    @NotBlank @Size(min=5, max=20) String destination,
    @NotNull @Min(1) @Max(100) Integer quantity
) {

	public Integer quantity() {
		// TODO Auto-generated method stub
		return null;
	}}

