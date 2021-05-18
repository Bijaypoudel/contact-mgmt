package com.stone.dtos;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {
	private String street;
	private String city;
	private String state;
	private int zip;
	
	
}

