package com.jdutton.estore.command.model;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateProductRequest {
	
	@NotBlank(message="Title is mandatory.")
	private String title;
	
	@Min(value=1, message="Price cannot be lower than 1")
	private BigDecimal price;
	
	@Min(value=1, message="Quantity cannot be lower than 1")
	@Max(value=5, message="Quantity cannot be grater than 5")
	private Integer quantity;
}
