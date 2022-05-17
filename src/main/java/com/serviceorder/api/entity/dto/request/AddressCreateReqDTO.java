package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

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
public class AddressCreateReqDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = Messages.ADDRESS_STREET_IS_REQUIRED)
	private String street;

	@Pattern(regexp = "^(([0-9]{0,6})|([0-9]{0,6})([A-Z]))$", message = Messages.ADDRESS_NUMBER_IS_INVALID)
	@NotNull(message = Messages.ADDRESS_NUMBER_IS_REQUIRED)
	private String number;
	
	@NotBlank(message = Messages.ADDRESS_DISTRICT_IS_REQUIRED)
	private String district;
	
	@Pattern(regexp = "^([\\d]{8})$", message = Messages.ADDRESS_ZIPCODE_IS_INVALID)
	@NotBlank(message = Messages.ADDRESS_ZIPCODE_IS_REQUIRED)
	private String zipcode;
	
	@NotBlank(message = Messages.ADDRESS_CITY_IS_REQUIRED)
	private String city;
	
	@NotNull(message = Messages.ADDRESS_STATE_IS_REQUIRED)
	@Pattern(regexp = "^[A-Z]{2}$", message = Messages.ADDRESS_STATE_IS_INVALID)
	private String state;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú'\\d ]*$", message = Messages.ADDRESS_COMPLEMENT_IS_INVALID) // Letters, spaces and accented letters
	private String complement;

}
