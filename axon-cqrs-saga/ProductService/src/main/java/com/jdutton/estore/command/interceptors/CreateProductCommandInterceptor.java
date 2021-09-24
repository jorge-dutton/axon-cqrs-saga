package com.jdutton.estore.command.interceptors;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;

import com.jdutton.estore.command.CreateProductCommand;
import com.jdutton.estore.core.data.ProductLookupEntity;
import com.jdutton.estore.core.data.ProductLookupRepository;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	public static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

	private ProductLookupRepository productLookupRepository;

	public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
		super();
		this.productLookupRepository = productLookupRepository;
	}

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {

		return (index, command) -> {

			LOGGER.info("intercepted command {}", command.getPayloadType());

			if (CreateProductCommand.class.equals(command.getPayloadType())) {

				CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

				ProductLookupEntity entity = this.productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(),
						createProductCommand.getTitle());
				
				if (entity != null) {
					throw new IllegalStateException("Error, product already exist");
				}
			}
			return command;
		};
	}

}
