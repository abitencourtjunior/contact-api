package com.contact.altadev.service;

import com.contact.altadev.fixture.ContactFixture;
import com.contact.altadev.model.Contact;
import com.contact.altadev.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.contact.altadev.enums.ContactTypeEnum.EMAIL;
import static com.contact.altadev.enums.ContactTypeEnum.PUSH_NOTIFICATION;
import static com.contact.altadev.fixture.ContactFixture.one;
import static com.contact.altadev.fixture.ContactFixture.oneWithId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

	@Mock
	private ContactRepository contactRepository;

	@InjectMocks
	private ContactService contactService;

	@Test
	void shouldShowContactList() {
		when(contactRepository.findAll()).thenReturn(ContactFixture.toList());
		List<Contact> contacts = contactService.show();
		assertEquals(1, contacts.size());
	}

	@Test
	void shouldFindContactByIdThenReturnContact() {
		Contact contactToTest = oneWithId(PUSH_NOTIFICATION, UUID.randomUUID().toString());
		when(contactRepository.findById(contactToTest.getId()))
				.thenReturn(Optional.of(contactToTest));
		Optional<Contact> contact = contactService.findById(contactToTest.getId());
		assertNotNull(contact.get());
	}

	@Test
	void shouldCreateThenReturnNewContact() {
		String value = UUID.randomUUID().toString();
		Contact contactToTest = one(EMAIL, value);
		when(contactRepository.save(contactToTest)).thenReturn(oneWithId(EMAIL, value));
		contactService.create(contactToTest);
	}

	@Test
	void shouldUpdateContactThenReturnContactUpdated() {
		Contact contactToTest = oneWithId(PUSH_NOTIFICATION, UUID.randomUUID().toString());
		when(contactRepository.findById(contactToTest.getId()))
				.thenReturn(Optional.of(contactToTest));
		contactService.update(contactToTest);
	}

	@Test
	void shouldUpdateContactThenReturnContactUpdated2() {
		Contact contactToTest = oneWithId(PUSH_NOTIFICATION, UUID.randomUUID().toString());
		Contact contactToTest2 = oneWithId(PUSH_NOTIFICATION, UUID.randomUUID().toString());
		when(contactRepository.findById(contactToTest.getId()))
				.thenReturn(Optional.of(contactToTest));
		contactService.update(contactToTest2);
	}

	@Test
	void shouldNotUpdateBecauseContactNotFound() {
		Contact contactToTest = oneWithId(PUSH_NOTIFICATION, UUID.randomUUID().toString());
		when(contactRepository.findById(contactToTest.getId()))
				.thenReturn(Optional.empty());
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			contactService.update(contactToTest);
		});
		assertEquals("Contact Not Found", exception.getMessage());
	}

	@Test
	void shouldDeleteContactByIdThenReturnNothing() {
		doNothing().when(contactRepository).deleteById(anyLong());
		contactService.deleteById(1L);
	}
}