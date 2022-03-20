package com.jdutton.estore.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.jdutton.estore.core.data.ProductEntity;
import com.jdutton.estore.core.data.ProductsRepository;
import com.jdutton.estore.query.model.ProductRestModel;

@Component
public class ProductsQueryHandler {
	
	private ProductsRepository productsRepository;
	
	public ProductsQueryHandler(final ProductsRepository productsRepository) {
		super();
		this.productsRepository = productsRepository;
	}
	
	@QueryHandler
	public List<ProductRestModel> findProduct(FindProductsQuery query) {
		
		List<ProductRestModel> productsList = new ArrayList<>();
		List<ProductEntity> storedProducts = this.productsRepository.findAll();
		
		storedProducts.forEach(p -> {
			ProductRestModel productRestModel = new ProductRestModel();
			BeanUtils.copyProperties(p, productRestModel);
			productsList.add(productRestModel);
		});
		
		return productsList;
	}
}
