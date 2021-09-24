package com.jdutton.estore.products.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.jdutton.estore.products.core.data.OrderEntity;
import com.jdutton.estore.products.core.data.OrdersRepository;
import com.jdutton.estore.products.core.event.OrderCreatedEvent;

@Component
@ProcessingGroup("orders-processing-group")
public class OrderEventsHandler {

	private OrdersRepository ordersRepository;

	public OrderEventsHandler(OrdersRepository ordersRepository) {
		super();
		this.ordersRepository = ordersRepository;
	}

	@EventHandler
	public void on(final OrderCreatedEvent orderCreatedEvent) {

		OrderEntity orderEntity = new OrderEntity();

		BeanUtils.copyProperties(orderCreatedEvent, orderEntity);

		try {
			// persist into 'read' database
			this.ordersRepository.save(orderEntity);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
