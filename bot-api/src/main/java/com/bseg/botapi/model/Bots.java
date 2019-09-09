package com.bseg.botapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="bots")
public class Bots implements Serializable {
	
	private static final long serialVersionUID = -2528137020213494714L;
	
	@javax.persistence.Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name;
	
	public Bots() {
		// TODO Auto-generated constructor stub
	}

	public Bots(String name) {
		this.name = name;
	}
	public Long getId() {
		return Id;
	}	
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = this.name;
	}	

}