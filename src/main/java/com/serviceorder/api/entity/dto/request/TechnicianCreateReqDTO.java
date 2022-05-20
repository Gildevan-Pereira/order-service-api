package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
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
public class TechnicianCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^([ a-zA-ZÀ-ú' ]*$)", message = Messages.INVALID) // Letters, spaces and accented letters
	@NotBlank(message = Messages.REQUIRED)
	private String role;
	
	@PositiveOrZero(message = Messages.INVALID) // Numbers Zero to Nine
	@NotNull(message = Messages.REQUIRED)
	private Double commission;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.INVALID) // Letters, spaces and accented letters
	@NotBlank(message = Messages.REQUIRED)
	private String fullname;
	
	@CPF(message = Messages.INVALID)
	@NotBlank(message = Messages.REQUIRED)
	private String identity;
	
	@Size(max = 11, min = 10)
	@Pattern(regexp = "^([\\d]{2})([\\d]{4,5})([\\d]{4})$", //E.g: (XX) X XXXX XXXX  ||  (XX) XXXX XXXX
			message = Messages.INVALID) 
	@NotBlank(message = Messages.REQUIRED)
	private String phone;
	
	@Email
	@Pattern(regexp = "^([a-z]){1,}([a-z0-9._-]){1,}([@]){1}([a-z]){2,}([.]){1}([a-z]){2,}([.]?){1}([a-z]?){3,}$",
			message = Messages.INVALID)
	@NotBlank(message = Messages.REQUIRED)
	private String email;
	
	@Valid
	@NotNull(message = Messages.REQUIRED)
	private AddressCreateReqDTO address;
	
}
