package com.jdutton.estore.products.core.event;

import com.jdutton.estore.products.command.model.OrderStatus;

import lombok.Data;

@Data
public class OrderCreatedEvent {
	
	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String addressId;
	private OrderStatus orderStatus;

}
