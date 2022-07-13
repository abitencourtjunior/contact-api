package com.contact.altadev.controller;

import com.contact.altadev.model.Contact;
import com.contact.altadev.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.contact.altadev.enums.ContactTypeEnum.EMAIL;
import static com.contact.altadev.fixture.ContactFixture.one;
import static com.contact.altadev.fixture.ContactFixture.oneWithId;
import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerConfig extends BaseControllerConfig {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ContactService contactService;

	@Test
	void shouldCreateTheNewContact() throws Exception {
		Contact contact = one(EMAIL, "altamir.bitencourt@dev.com.br");
		mockMvc.perform(
				post("/contact")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(contact))
				)
			.andExpect(status().isCreated());
	}

	@Test
	void shouldUpdateTheContact() throws Exception {
		Contact contact = oneWithId(EMAIL, "altamir.bitencourt@dev.com.br");

		when(contactService.findById(contact.getId()))
				.thenReturn(of(oneWithId(EMAIL, "altamir@com.br")));

		mockMvc.perform(
						put("/contact")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(contact))
				)
				.andExpect(status().isAccepted());
	}

	@Test
	void shouldReturnContactById() throws Exception {
		Contact contact = oneWithId(EMAIL, "altamir@com.br");

		when(contactService.findById(contact.getId()))
				.thenReturn(of(contact));

		ResultActions response = mockMvc
				.perform(
						get("/contact/{id}", contact.getId())
				);

		response
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	void shouldReturnAllContacts() throws Exception {
		Contact contact = oneWithId(EMAIL, "altamir@com.br");

		when(contactService.show())
				.thenReturn(List.of(contact));

		ResultActions response = mockMvc.perform(get("/contact"));

		response
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$[0].name", is(contact.getName())))
				.andDo(print());
	}

	@Test
	void shouldDeleteContactById() throws Exception {

		long contactIdToDelete = 1L;

		doNothing()
				.when(contactService)
				.deleteById(contactIdToDelete);

		ResultActions response = mockMvc
				.perform(delete("/contact/{id}", contactIdToDelete));

		response
				.andExpect(status().isAccepted())
				.andDo(print());
	}


}