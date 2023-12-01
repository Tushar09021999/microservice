package com.billing.entity;

import com.billing.payload.Product;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "billings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Billing {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
//
//		@Column(name = "name", length =45, nullable = false )
//		private String name;
//
//
//		@Column(name = "email", length =128, nullable = false, unique= true)
//		private String email;
//
//		@Column(name = "phoneNumber", length =10, nullable = false, unique= true)
//		private String phoneNumber;

	private String name;


	private String email;

	private String phoneNumber;

	@Transient
	private List<Product> products = new ArrayList<>();

	@Transient
	private double totalAmount;

	@Column(name ="lead_source")
	private  String leadSource;

	public double calculateTotalAmount() {
		double totalAmount = 0;

		for (Product product : products) {
			totalAmount += product.getProductPrice() * product.getQuantity();
		}

		return totalAmount;
	}











}
