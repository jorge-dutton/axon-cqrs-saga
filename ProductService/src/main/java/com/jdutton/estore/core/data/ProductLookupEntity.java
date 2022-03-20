package com.jdutton.estore.core.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_LOOKUP")
public class ProductLookupEntity implements Serializable {

	private static final long serialVersionUID = -8701883877365062298L;
	
	@Id
	private String productId;
	
	@Column(unique = true)
	private String title;

}
