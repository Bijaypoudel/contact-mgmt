package com.stone.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="PHONE")
@Getter
@Setter
public class PhoneEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String number;
	private String type;
	
	@ManyToOne
	private ContactEntity contactDTO;

}
