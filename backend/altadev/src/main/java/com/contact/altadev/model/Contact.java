package com.contact.altadev.model;

import com.contact.altadev.enums.ContactTypeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "CONTACT")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	private String name;
	
	@Column
	@NotNull
	private ContactTypeEnum type;
	
	@Column
	@NotBlank
	private String value;

}
