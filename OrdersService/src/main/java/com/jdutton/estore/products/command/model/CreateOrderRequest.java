package com.jdutton.estore.products.command.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateOrderRequest {
	
	@NotBlank(message="ProductId is mandatory")
	private String productId;
	
	@Min(value=1, message="Quantity cannot be lower than 1")
	@Max(value=5, message="Quantity cannot be grater than 5")
	private Integer quantity;
	
	@NotBlank(message="AddressId is mandatory")
	private String addressId;
	

}
