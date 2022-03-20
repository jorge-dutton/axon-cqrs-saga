package com.jdutton.estore.command.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdutton.estore.command.CreateProductCommand;
import com.jdutton.estore.command.model.CreateProductRequest;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

	private CommandGateway commandGateway;

	public ProductsCommandController(final CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String createProduct(@Valid @RequestBody final CreateProductRequest createProductRequest) {

		CreateProductCommand productCommand = CreateProductCommand.builder()
				.title(createProductRequest.getTitle())
				.price(createProductRequest.getPrice())
				.quantity(createProductRequest.getQuantity())
				.productId(UUID.randomUUID().toString()).build();

		String returnValue;

		returnValue = this.commandGateway.sendAndWait(productCommand);

		return returnValue;
	}

}
