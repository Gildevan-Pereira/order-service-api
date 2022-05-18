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
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.CLIENT_FULLNAME_IS_INVALID) //Letters, spaces and accented letters
	@NotBlank(message = Messages.CLIENT_FULLNAME_IS_REQUIRED)
	private String fullname;
	
	@CPF(message = Messages.CLIENT_IDENTITY_IS_INVALID) // Numbers of Zero to Nine and have eleven digits 
	@NotNull(message = Messages.CLIENT_IDENTITY_IS_REQUIRED)
	private String identity;
	
	@Size(max = 10)
	@Pattern(regexp = "^([\\d]{2})([\\d]{4,5})([\\d]{4})$", //E.g: (XX) X XXXX XXXX  ||  (XX) XXXX XXXX
			message = Messages.CLIENT_PHONE_IS_INVALID) 
	@NotNull(message = Messages.CLIENT_PHONE_IS_REQUIRED)
	private String phone;
	
	@Email(message = Messages.CLIENT_EMAIL_IS_INVALID)
	@Size(max = 75)
	@NotBlank(message = Messages.CLIENT_EMAIL_IS_REQUIRED)
	private String email;
	
	@Valid
	@NotNull(message = Messages.ADDRESS_IS_REQUIRED)
	private AddressCreateReqDTO address;
	
	
//	FIXME: REMOVER ESTE CÓDIGO
//	public static void main(String[] args) {
//		var teste = "81981185639";
//		
//		var correto = teste.matches("^([a-zA-z])");
//		
//		System.out.println(correto);
//	}
}









