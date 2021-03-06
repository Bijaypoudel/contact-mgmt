package com.stone.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.stone.dtos.AddressDto;
import com.stone.dtos.CallListDto;
import com.stone.dtos.ContactDto;
import com.stone.dtos.NameDto;
import com.stone.dtos.PhoneDto;
import com.stone.entities.AddressEntity;
import com.stone.entities.ContactEntity;
import com.stone.entities.PhoneEntity;

public class ContactMapper {

	public static Function<AddressEntity, AddressDto> addressEntityToDto = addressEntity -> {
		if (addressEntity != null) {
			AddressDto addressDto = new AddressDto();
			addressDto.setStreet(addressEntity.getStreet());
			addressDto.setState(addressEntity.getState());
			addressDto.setCity(addressEntity.getCity());
			addressDto.setZip(addressEntity.getZip());
			return addressDto;
		}

		return null;
	};

	public static Set<PhoneDto> mapPhoneFromEntity(Set<PhoneEntity> phoneEntityList) {

		if (phoneEntityList != null && phoneEntityList.size() > 0) {

			Set<PhoneDto> phoneDtos = new HashSet<>();

			for (PhoneEntity p : phoneEntityList) {
				PhoneDto phoneDto = new PhoneDto();
				phoneDto.setNumber(p.getNumber());
				phoneDto.setType(p.getType());
				phoneDtos.add(phoneDto);
			}
			return phoneDtos;
		}

		return null;
	}

	public static Function<ContactDto, ContactEntity> contactDtotoEntity = contactDetails -> {

		if (contactDetails != null) {

			AddressEntity address = mapAddressToEntity(contactDetails.getAddress());
			Set<PhoneEntity> phone = mapPhoneToEntity(contactDetails.getPhone());
			NameDto nameDTO = contactDetails.getName();
			ContactEntity contact = new ContactEntity();
			contact.setAddress(address);
			contact.setPhone(phone);
			contact.setFirstName(nameDTO.getFirst());
			contact.setLastName(nameDTO.getLast());
			contact.setMiddleName(nameDTO.getMiddle());
			contact.setEmail(contactDetails.getEmail());
			contact.setId(contactDetails.getId());
			return contact;

		}

		return null;
	};

	public static AddressEntity mapAddressToEntity(AddressDto addressDto) {

		if (addressDto != null) {
			AddressEntity address = new AddressEntity();
			address.setStreet(addressDto.getStreet());
			address.setCity(addressDto.getCity());
			address.setZip(addressDto.getZip());
			address.setState(addressDto.getState());
			return address;
		}

		return null;

	}

	public static Set<PhoneEntity> mapPhoneToEntity(Set<PhoneDto> phoneDtos) {

		if (phoneDtos != null && phoneDtos.size() > 0) {

			Set<PhoneEntity> phones = new HashSet<>();

			for (PhoneDto p : phoneDtos) {
				PhoneEntity phone = new PhoneEntity();
				phone.setNumber(p.getNumber());
				phone.setType(p.getType());
				phones.add(phone);
			}
			return phones;
		}

		return null;
	}

	public static ContactDto mapContactFromEntity(ContactEntity contact) {

		if (contact != null) {
			ContactDto contactDetails = new ContactDto();

			AddressDto address = addressEntityToDto.apply(contact.getAddress());
			Set<PhoneDto> phones = mapPhoneFromEntity(contact.getPhone());
			contactDetails.setAddress(address);
			contactDetails.setPhone(phones);

			NameDto name = new NameDto();
			name.setFirst(contact.getFirstName());
			name.setMiddle(contact.getMiddleName());
			name.setLast(contact.getLastName());

			contactDetails.setName(name);
			contactDetails.setEmail(contact.getEmail());
			contactDetails.setId(contact.getId());
			return contactDetails;
		}

		return null;
	}
	
	public static Function<ContactEntity, CallListDto > mapCallListDto =  contact -> {

		if (contact != null) {
			CallListDto callList = new CallListDto();


			NameDto name = new NameDto();
			name.setFirst(contact.getFirstName());
			name.setMiddle(contact.getMiddleName());
			name.setLast(contact.getLastName());

			callList.setName(name);

			PhoneEntity homePhone = contact.getPhone().stream()
					.filter(t -> "home".equals(t.getType())).collect(Collectors.toList()).get(0);
			
			callList.setPhone(homePhone.getNumber());

			return callList;
		}
		return null;
	};

}
