package com.jdutton.estore.products.command.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdutton.estore.products.command.CreateOrderCommand;
import com.jdutton.estore.products.command.model.CreateOrderRequest;
import com.jdutton.estore.products.command.model.OrderStatus;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

	private CommandGateway commandGateway;

	public OrdersCommandController(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	@PostMapping
	String createOrder(@Valid @RequestBody final CreateOrderRequest createOrderRequest) {

		CreateOrderCommand orderCommand = CreateOrderCommand.builder()
				.orderId(UUID.randomUUID().toString())
				.userId("27b95829-4f3f-4ddf-8993-151ba010e35b")
				.productId(createOrderRequest.getProductId())
				.quantity(createOrderRequest.getQuantity())
				.addressId(createOrderRequest.getAddressId())
				.orderStatus(OrderStatus.CREATED).build();
		

		return this.commandGateway.sendAndWait(orderCommand);
	}

}
