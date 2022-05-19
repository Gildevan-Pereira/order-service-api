package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.Valid;
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
public class ServiceOrderCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Valid
	@NotNull(message = Messages.REQUIRED)
	private ServiceCreateReqDTO service;

	@NotNull(message = Messages.REQUIRED)
	private UUID clientId;

	@NotNull(message = Messages.REQUIRED)
	private UUID technicianId;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú'\\d ]*$", message = Messages.INVALID)
	@NotNull(message = Messages.REQUIRED)
	private String remarks;
}
