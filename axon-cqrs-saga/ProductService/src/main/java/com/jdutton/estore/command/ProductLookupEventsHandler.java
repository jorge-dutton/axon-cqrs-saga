package com.jdutton.estore.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.jdutton.estore.core.data.ProductLookupEntity;
import com.jdutton.estore.core.data.ProductLookupRepository;
import com.jdutton.estore.core.event.ProductCreatedEvent;

@Component
@ProcessingGroup("product-processing-group")
public class ProductLookupEventsHandler {

	private ProductLookupRepository productLookupRepository;

	public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
		super();
		this.productLookupRepository = productLookupRepository;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) {

		ProductLookupEntity productLookupEntity = 
				new ProductLookupEntity(event.getProductId(), event.getTitle());
		
		productLookupRepository.save(productLookupEntity);
	}
}
