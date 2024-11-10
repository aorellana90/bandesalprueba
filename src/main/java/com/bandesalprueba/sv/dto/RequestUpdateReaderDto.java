package com.bandesalprueba.sv.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateReaderDto {

	@NotNull(message = "message.idreader.null")
	@Min(value = 1, message = "message.idreader.size")
	private int idReader;

	@NotBlank(message = "message.name.blank")
	@NotNull(message = "message.name.null")
	@Size(max = 8, message = "message.name.order.size")
	private String name;

}
