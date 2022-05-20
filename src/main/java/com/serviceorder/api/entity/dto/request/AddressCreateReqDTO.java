package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

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
public class AddressCreateReqDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]+[\\d]*$", message = Messages.INVALID) // Letters, spaces and accented letters
	private String street;

	@NotNull(message = Messages.REQUIRED)
//	@Pattern(regexp = "^(([0-9]{0,6} )|([0-9]{0,6} )([A-Z]))$", message = Messages.INVALID)
	private String number;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]{3,}+[\\d]*$", message = Messages.INVALID) // Letters, spaces and accented letters
	private String district;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^([\\d]{8})$", message = Messages.INVALID)
	private String zipcode;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.INVALID) // Letters, spaces and accented letters
	private String city;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^[A-Z]{2}$", message = Messages.INVALID)
	private String state;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú',. ]+[\\d]*$", message = Messages.INVALID) // Letters, spaces and accented letters
	private String complement;

}
