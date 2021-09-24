package com.jdutton.estore.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.jdutton.estore.core.data.ProductEntity;
import com.jdutton.estore.core.data.ProductsRepository;
import com.jdutton.estore.core.event.ProductCreatedEvent;
import com.jdutton.estore.core.events.ProductReservedEvent;

@Component
@ProcessingGroup("product-processing-group")
public class ProductEventsHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);
	
	private ProductsRepository productsRepository;

	public ProductEventsHandler(ProductsRepository productsRepository) {
		super();
		this.productsRepository = productsRepository;
	}
	
	@ExceptionHandler(resultType=Exception.class)
	public void handle(Exception ex) throws Exception {
		throw ex;
		
	}
	
	@ExceptionHandler(resultType=IllegalArgumentException.class)
	public void handle(IllegalArgumentException	ex) {
		
	}
	
	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
		var productEntity = new ProductEntity();
		BeanUtils.copyProperties(productCreatedEvent, productEntity);
		
		try {
		productsRepository.save(productEntity);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
	}
	
	@EventHandler
	public void on(ProductReservedEvent productReservedEvent) {
		var productEntity = this.productsRepository.findByProductId(productReservedEvent.getProductId());
		
		if (productEntity != null) {
			productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
			this.productsRepository.save(productEntity);
			
			LOGGER.info("ProductReservedEvent handle for OrderId: {} and ProductId: {}", 
					productReservedEvent.getOrderId(),
					productReservedEvent.getProductId());
		}
	}
}
