package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	private int id;
	private String name;
	private int price;
	
	@ManyToOne
	@JoinColumn(name="cid", insertable=true, updatable=true)
	private Category category;
}
