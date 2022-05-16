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
	
	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String street;

	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String number;
	
	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String district;
	
	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String zipcode;
	
	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String city;
	
	@Pattern(regexp = "^[A-Z]{2}$", message = Messages.ADDRESS_STATE_IS_INVALID)
	@NotNull(message = Messages.ADDRESS_STATE_IS_REQUIRED)
	private String state;
	
	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String complement;

}
