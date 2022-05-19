package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.serviceorder.api.message.Messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(max = 150)
	@NotBlank(message = Messages.REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.INVALID) //Letters, spaces and accented letters
	private String fullname;
	
	@NotBlank(message = Messages.REQUIRED)
	@CPF(message = Messages.INVALID) // Numbers of Zero to Nine and have eleven digits 
	private String identity;
	
	@Size(max = 11, min = 10)
	@Pattern(regexp = "^([\\d]{2})([\\d]{4,5})([\\d]{4})$", //E.g: (XX) X XXXX XXXX  ||  (XX) XXXX XXXX
			message = Messages.INVALID) 
	@NotBlank(message = Messages.REQUIRED)
	private String phone;
	
	@Pattern(regexp = "^([a-z]){1,}([a-z0-9._-]){1,}([@]){1}([a-z]){2,}([.]){1}([a-z]){2,}([.]?){1}([a-z]?){3,}$",
			message = Messages.REQUIRED)
	@Email(message = Messages.INVALID)
	@Size(max = 75)
	@NotBlank(message = Messages.REQUIRED)
	private String email;
	
	@Valid
	@NotNull(message = Messages.REQUIRED)
	private AddressCreateReqDTO address;
	

}









