package com.stone.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stone.dtos.CallListDto;
import com.stone.dtos.ContactDto;
import com.stone.services.ContactService;

@RestController
@RequestMapping(path = "/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/{id}")
	public ResponseEntity<ContactDto> getContactById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Set<ContactDto>> getAllContacts() {
		return new ResponseEntity<>(contactService.getAllEntities(),HttpStatus.OK);
	}
	
	@GetMapping("/call-list")
	public ResponseEntity<Set<CallListDto>> getCallList() {
		Set<CallListDto> list = contactService.getCallList();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ContactDto> saveContacts(@RequestBody ContactDto contact) {
		return new ResponseEntity<>(contactService.saveContact(contact), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContactDto> updateContacts(@PathVariable("id") Long id, @RequestBody ContactDto contact) {
		return new ResponseEntity<>(contactService.updateContact(id, contact), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
		contactService.deleteEntity(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
