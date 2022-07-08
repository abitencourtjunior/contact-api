//package com.contact.altadev.controller;
//
//import com.contact.altadev.model.Contact;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ContactController.class)
//class ContactControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	void shouldCreateTheNewContact() throws Exception {
//		mockMvc.perform(post("/contact")
//				                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//	}
//
//	@Test
//	void show() {
//	}
//
//	@Test
//	void findById() {
//	}
//
//	@Test
//	void update() {
//	}
//
//	@Test
//	void delete() {
//	}
//}