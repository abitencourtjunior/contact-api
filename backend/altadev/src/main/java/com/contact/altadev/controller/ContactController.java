package com.contact.altadev.controller;

import com.contact.altadev.model.Contact;
import com.contact.altadev.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/contact")
public class ContactController {
	
	private final ContactService contactService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Contact> show() {
		return contactService.show();
	}
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Contact> findById(@PathVariable long id){
		return contactService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contact create(@RequestBody Contact contact) {
		return contactService.create(contact);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Contact update(@RequestBody Contact contact){
		return contactService.update(contact);
	}
	
	@DeleteMapping(path ={"/{id}"})
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable("id") long id) {
		contactService.deleteById(id);
	}

}
