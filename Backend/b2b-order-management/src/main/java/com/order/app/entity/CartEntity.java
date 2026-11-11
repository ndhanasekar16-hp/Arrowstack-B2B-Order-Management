package com.order.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="carts")
public class CartEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
	
	@NotNull
	@ManyToOne 
	@JoinColumn(name = "user_id")
	 private UserEntity user;
	
	@NotNull
	@ManyToOne 
	@JoinColumn(name = "product_id")
	 private ProductEntity product;
	
	@NotNull
	@Positive
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	 
	 

}
