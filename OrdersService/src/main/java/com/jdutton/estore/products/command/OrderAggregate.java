package com.jdutton.estore.products.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.jdutton.estore.products.command.model.OrderStatus;
import com.jdutton.estore.products.core.event.OrderCreatedEvent;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String addressId;
	private OrderStatus orderStatus;

	public OrderAggregate() {
		super();
	}

	@CommandHandler
	public OrderAggregate(final CreateOrderCommand createOrderCommand) {
		
		//Validate command
		
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
		
		BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
		
		AggregateLifecycle.apply(orderCreatedEvent);
	}

	@EventSourcingHandler
	public void on(final OrderCreatedEvent orderCreatedEvent) {
		this.orderId = orderCreatedEvent.getOrderId();
		this.userId = orderCreatedEvent.getUserId();
		this.productId = orderCreatedEvent.getProductId();
		this.quantity = orderCreatedEvent.getQuantity();
		this.addressId = orderCreatedEvent.getAddressId();
	}
}
