package com.stone.dtos;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ContactDto {
	
	private Long id;
	private NameDto name;
	private AddressDto address;
	private Set<PhoneDto> phone;
	private String email;
	

}

