package com.stone.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "CONTACT")
@Getter
@Setter
public class ContactEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;
	private String lastName;
	private String middleName;
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private AddressEntity address;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<PhoneEntity> phone;

}
