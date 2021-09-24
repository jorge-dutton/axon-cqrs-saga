package com.jdutton.estore.core.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="PRODUCTS")
@Data
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = -813529077728464618L;
	
	@Id
	@Column(unique=true)
	private String productId;
	
	@Column(unique=true)
	private String title;
	
	private BigDecimal price;
	private Integer quantity;

}
