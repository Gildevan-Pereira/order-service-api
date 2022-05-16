package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
	
	@NotBlank(message = Messages.CATEGORY_NAME_IS_REQUIRED)
	private String name;
}
