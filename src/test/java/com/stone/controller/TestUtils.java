package com.stone.controller;

import com.stone.entities.ContactEntity;

public class TestUtils {

	public static String dummyContactresponse() {

		return "{\n" + "\"id\": 1,\n" + "\"name\": {\n" + "\"first\": \"Bijay\",\n" + "\"last\": \"Poudel\",\n"
				+ "\"middle\": \"Raj\"\n" + "},\n" + "\"address\": {\n" + "\"street\": \"123 XYZ dr\",\n"
				+ "\"city\": \"Fairfax\",\n" + "\"state\": \"VA\",\n" + "\"zip\": 22031\n" + "},\n"
				+ "\"phone\": [\n" + "{\n" + "\"number\": \"318-348-2828\",\n" + "\"type\": \"mobile\"\n" + "},\n"
				+ "{\n" + "\"number\": \"123-123-1234\",\n" + "\"type\": \"home\"\n" + "}\n" + "],\n"
				+ "\"email\": \"bijay@outlook.com\"\n" + "}\n";
	}

	public static String dummyContactrequest() {

		return "{\n" + "\"name\": {\n" + "\"first\": \"Bijay\",\n" + "\"last\": \"Poudel\",\n"
				+ "\"middle\": \"Raj\"\n" + "},\n" + "\"address\": {\n" + "\"street\": \"123 XYZ dr\",\n"
				+ "\"city\": \"Fairfax\",\n" + "\"state\": \"VA\",\n" + "\"zip\": 22031\n" + "},\n"
				+ "\"phone\": [\n" + "{\n" + "\"number\": \"318-348-2828\",\n" + "\"type\": \"mobile\"\n" + "},\n"
				+ "{\n" + "\"number\": \"123-123-1234\",\n" + "\"type\": \"home\"\n" + "}\n" + "],\n"
				+ "\"email\": \"bijay@outlook.com\"\n" + "}\n";
	}
	
	public static ContactEntity dummyContact() {

		ContactEntity en = new ContactEntity();
		en.setId(1l);
		en.setFirstName("Bijay");
		en.setLastName("Poudel");
		en.setEmail("bijay@outlook.com");
		en.setPhone(null);
		return en;
	}

}
