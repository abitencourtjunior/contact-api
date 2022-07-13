package com.contact.altadev.fixture;

import com.contact.altadev.enums.ContactTypeEnum;
import com.contact.altadev.model.Contact;
import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;

import java.util.List;

@UtilityClass
public class ContactFixture {

	private final EasyRandom generator = new EasyRandom();

	public static Contact one(ContactTypeEnum type, String value){
		return Contact
				.builder()
				.name("Altamir Bitencourt Junior")
				.type(type)
				.value(value)
				.build();
	}

	public static Contact oneWithId(ContactTypeEnum type, String value){
		return Contact
				.builder()
				.id(1L)
				.name("Altamir Bitencourt Junior")
				.type(type)
				.value(value)
				.build();
	}

	public static List<Contact> toList(){
		return List.of(generator.nextObject(Contact.class));
	}
}
