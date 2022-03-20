package com.jdutton.estore.products.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.jdutton.estore.products.command.model.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderCommand {
	
	@TargetAggregateIdentifier
	private final String orderId;
	private final String userId;
	private final String productId;
	private final Integer quantity;
	private final String addressId;
	private final OrderStatus orderStatus;

}
