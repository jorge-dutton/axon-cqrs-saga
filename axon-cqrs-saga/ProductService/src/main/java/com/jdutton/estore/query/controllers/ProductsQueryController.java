package com.jdutton.estore.query.controllers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdutton.estore.query.FindProductsQuery;
import com.jdutton.estore.query.model.ProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

	private QueryGateway queryGateway;

	public ProductsQueryController(final QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}

	@GetMapping
	public List<ProductRestModel> getProducts() {

		FindProductsQuery findProductsQuery = new FindProductsQuery();
		
		List<ProductRestModel> products = this.queryGateway
				.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
		
		return products;
	}
}
