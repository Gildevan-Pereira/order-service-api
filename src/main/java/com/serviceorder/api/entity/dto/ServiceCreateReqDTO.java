package com.serviceorder.api.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
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
public class ServiceCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = Messages.REQUIRED)
	private UUID categoryId;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.INVALID)
	private String title;
	
	@NotNull(message = Messages.REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú'\\d ]*$", message = Messages.INVALID)
	private String description;
	
	@NotNull(message = Messages.REQUIRED)
	@DecimalMin(message = Messages.INVALID, value = "0.0")
	private BigDecimal amount;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú'\\d ]*$", message = Messages.INVALID)
	@NotNull(message = Messages.REQUIRED)
	private String remarks;
}
