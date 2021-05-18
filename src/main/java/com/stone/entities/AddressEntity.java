package com.stone.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="ADDRESS")
@Getter
@Setter
public class AddressEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String street;
	private String city;
	private String state;
	private int zip;
	
	@OneToOne(mappedBy="address", cascade=CascadeType.ALL)
	private ContactEntity contactDTO;
	
	
}
