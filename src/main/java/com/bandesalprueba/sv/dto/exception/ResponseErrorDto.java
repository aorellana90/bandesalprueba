package com.bandesalprueba.sv.dto.exception;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.bandesalprueba.sv.component.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String object;
	private HttpStatus status;
	private String field;
	private String errorCode;
	private String message;
	private List<String> errors;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss:SSS", timezone = "America/El_Salvador")
	private final String timestamp = DateUtils.formaterDate();

}
