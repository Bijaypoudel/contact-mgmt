package com.stone.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.stone.dtos.ContactDto;
import com.stone.dtos.PhoneDto;
import com.stone.exceptions.ContactValidationException;

public class ContactUtils {
	public final static String WORK_PHONE = "work";
	public final static String HOME_PHONE = "home";
	public final static String MOBILE_PHONE = "mobile";	
	public final static List<String> PHONETYPES =  Arrays.asList(WORK_PHONE, HOME_PHONE, MOBILE_PHONE);	
	
	public static PhoneDto getPhoneFromType(Set<PhoneDto> phoneSet, String type) {
		for (PhoneDto p : phoneSet) {
			if (p.getType().equals(type)) {
				return p;
			}
		}
		return null;

	}

	public static void validatePhone(ContactDto contactDetails) {

		if (contactDetails != null && contactDetails.getPhone() != null) {
			for (PhoneDto p : contactDetails.getPhone()) {
				if (!ContactUtils.PHONETYPES.contains(p.getType())) {
					throw new ContactValidationException("Only mobile|home|work allowed for phone type");

				}
			}
		}

	}
}
