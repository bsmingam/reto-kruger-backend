package com.canterita.challenge.backend.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "ORDER_DETAIL_ENTITY")
public class OrderDetailEntity {
	
	public OrderDetailEntity() {		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_order")
	private OrderEntity order;
	
	@Column(name = "detail")
	private String detail;

	@Column(name = "cantidad")
	private Double cantidad;
	
	@Column(name = "precio_unitario")
	private Double precioUnitario;
	
	@Column(name = "total_detail")
	private Double totalDetail;
}