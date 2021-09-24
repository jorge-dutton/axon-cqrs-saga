package com.jdutton.estore.products.core.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jdutton.estore.products.command.model.OrderStatus;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
	
	@Id
	@Column(unique = true)
	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String addressId;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
