package com.contact.altadev.service;

import com.contact.altadev.model.Contact;
import com.contact.altadev.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactService {

	private final ContactRepository contactRepository;

	public List<Contact> show() {
		return contactRepository.findAll();
	}

	public Optional<Contact> findById(long idContact) {
		return contactRepository.findById(idContact);
	}

	public Contact create(Contact contact) {
		return contactRepository.save(contact);
	}

	public Contact update(Contact contact) {
		Contact contactDb = findById(contact.getId()).orElseThrow(() -> new IllegalArgumentException("Contact Not Found"));
		if(contactDb.equals(contact)){
			return contactDb;
		}
		contact.setId(contact.getId());
		return contactRepository.save(contact);
	}

	public void deleteById(long id) {
		contactRepository.deleteById(id);
	}
}
