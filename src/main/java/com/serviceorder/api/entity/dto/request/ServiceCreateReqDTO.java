package com.serviceorder.api.entity.dto.request;

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
	
	@NotNull(message = Messages.CATEGORY_ID_IS_REQUIRED)
	private UUID categoryId;
	
	@NotNull(message = Messages.SERVICE_TITLE_IS_REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú' ]*$", message = Messages.SERVICE_TITLE_IS_INVALID)
	private String title;
	
	@NotNull(message = Messages.SERVICE_DESCRIPTION_IS_REQUIRED)
	@Pattern(regexp = "^[ a-zA-ZÀ-ú'\\d ]*$", message = Messages.SERVICE_DESCRIPTION_IS_INVALID)
	private String description;
	
	@NotNull(message = Messages.SERVICE_AMOUNT_IS_REQUIRED)
	@DecimalMin(message = Messages.SERVICE_AMOUNT_IS_INVALID, value = "0.0")
	private BigDecimal amount;
	
	@Pattern(regexp = "^[ a-zA-ZÀ-ú'\\d ]*$", message = Messages.SERVICE_REMARKS_IS_INVALID)
	@NotNull(message = Messages.SERVICE_REMARKS_IS_REQUIRED)
	private String remarks;
}
