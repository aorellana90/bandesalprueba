package com.bandesalprueba.sv.dto.exception;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpHeaders httpHeaders;
	private HttpStatus httpStatus;
	private String field;
	private List<String> errors;

}
