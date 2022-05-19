package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
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
public class CategoryCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.INVALID) //Letters, spaces and accented letters
	@NotBlank(message = Messages.REQUIRED)
	private String name;
}
