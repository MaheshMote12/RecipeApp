package com.me.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Notes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String notes;

	@OneToOne(targetEntity=Recipe.class, cascade=CascadeType.ALL, mappedBy="notes")
	@Getter @Setter
	private Recipe recipe;
	
	public Notes(String notes) {
		super();
		this.notes = notes;
	}

	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
