package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

	@Pattern(regexp = "^([ a-zA-ZÀ-ú' ]*$)", message = Messages.TECHNICIAN_ROLE_IS_INVALID) // Letters, spaces and accented letters
	@NotNull(message = Messages.TECHNICIAN_ROLE_IS_REQUIRED)
	private String role;
	
	@Pattern(regexp = "^([\\d]*$)", message = Messages.TECHNICIAN_COMMISSION_IS_INVALID) // Numbers Zero to Nine
	@NotBlank(message = Messages.TECHNICIAN_COMMISSION_IS_REQUIRED)
	private Double commission;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.TECHNICIAN_FULLNAME_IS_INVALID) // Letters, spaces and accented letters
	@NotBlank(message = Messages.TECHNICIAN_FULLNAME_IS_REQUIRED)
	private String fullname;
	
	@Pattern(regexp = "^(\\d{11})$", message = Messages.TECHNICIAN_IDENTITY_IS_INVALID) // Numbers of Zero to Nine and have eleven digits 
	@NotBlank(message = Messages.TECHNICIAN_IDENTITY_IS_REQUIRED)
	private String identity;
	
	@Pattern(regexp = "^([\\d]{2})([\\d]{4,5})([\\d]{4})$", //E.g: (XX) X XXXX XXXX  ||  (XX) XXXX XXXX
			message = Messages.TECHNICIAN_PHONE_IS_INVALID) 
	@NotNull(message = Messages.TECHNICIAN_PHONE_IS_REQUIRED)
	private String phone;
	
	@Pattern(regexp = "^([a-z]){1,}([a-z0-9._-]){1,}([@]){1}([a-z]){2,}([.]){1}([a-z]){2,}([.]?){1}([a-z]?){3,}$",
			message = Messages.TECHNICIAN_EMAIL_IS_INVALID)
	@NotNull(message = Messages.TECHNICIAN_EMAIL_IS_REQUIRED)
	private String email;
	
	@Valid
	@NotNull(message = Messages.ADDRESS_IS_REQUIRED)
	private AddressCreateReqDTO address;
	
}
