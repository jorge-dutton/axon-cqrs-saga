package com.jdutton.estore;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import com.jdutton.estore.command.interceptors.CreateProductCommandInterceptor;
import com.jdutton.estore.core.errorhandling.ProductsServiceEventErrorHandler;

@EnableEurekaClient
@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateProductCommandInterceptor(final ApplicationContext appContext,
			final CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(appContext.getBean(CreateProductCommandInterceptor.class));
	}

	@Autowired
	public void configure(final EventProcessingConfigurer config) {
		config.registerListenerInvocationErrorHandler("product-processing-group",
				conf -> new ProductsServiceEventErrorHandler());
		
//		config.registerListenerInvocationErrorHandler("product-processing-group",
//				conf -> PropagatingErrorHandler.instance());
	}
}
