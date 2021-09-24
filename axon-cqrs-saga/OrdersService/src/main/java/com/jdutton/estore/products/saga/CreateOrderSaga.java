package com.jdutton.estore.products.saga;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jdutton.estore.core.commands.ReserveProductCommand;
import com.jdutton.estore.core.events.ProductReservedEvent;
import com.jdutton.estore.products.core.event.OrderCreatedEvent;

@Saga
public class CreateOrderSaga {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderSaga.class);

	@Autowired
	private transient CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderCreatedEvent orderCreatedEvent) {

		// @formatter:off

		ReserveProductCommand reservedProductCommand = ReserveProductCommand.builder()
				.orderId(orderCreatedEvent.getOrderId())
				.productId(orderCreatedEvent.getProductId())
				.quantity(orderCreatedEvent.getQuantity())
				.userId(orderCreatedEvent.getUserId()).build();

		LOGGER.info("OrderCreatedEvent handle for OrderId: {} and ProductId: {}", 
				reservedProductCommand.getOrderId(),
				reservedProductCommand.getProductId());
		
		// @formatter:on

		this.commandGateway.send(reservedProductCommand, new CommandCallback<ReserveProductCommand, Object>() {

			@Override
			public void onResult(CommandMessage<? extends ReserveProductCommand> commandMessage,
					CommandResultMessage<? extends Object> commandResultMessage) {

				if (commandResultMessage.isExceptional()) {
					// TODO trigger compensating transaction
				}
			}

		});
	}

	@SagaEventHandler(associationProperty = "orderId")
	public void handle(final ProductReservedEvent productReservedEvent) {
		// process user payment
		LOGGER.info("ProductReservedEvent handle for OrderId: {} and ProductId: {}", 
				productReservedEvent.getOrderId(),
				productReservedEvent.getProductId());
	}

}
