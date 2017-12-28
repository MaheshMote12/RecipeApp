package com.me.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class UnitOfMeasure {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String unit;

	
	public UnitOfMeasure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnitOfMeasure(String unit) {
		super();
		this.unit = unit;
	}

/*	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
*/	
	
}
