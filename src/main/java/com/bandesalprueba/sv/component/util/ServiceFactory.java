package com.bandesalprueba.sv.component.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bandesalprueba.sv.component.util.constant.ConstantesGeneral;
import com.bandesalprueba.sv.component.util.log.LogUtil;
import com.bandesalprueba.sv.component.util.log.LogUtilImpl;
import com.bandesalprueba.sv.component.util.log.LogUtil.TYPELOG;
import com.bandesalprueba.sv.dto.base.GenericResponseDto;
import com.bandesalprueba.sv.dto.exception.ResponseErrorDto;

public class ServiceFactory {

	private static LogUtil logs = new LogUtilImpl(ServiceFactory.class);

	private ServiceFactory() {
		throw new IllegalStateException("ServiceFactory class component util");
	}

	public static ResponseEntity<GenericResponseDto<Object>> createResponse(Object result) {
		try {
			var genericResponse = new GenericResponseDto<Object>();

			genericResponse.setMensaje(ConstantesGeneral.STATUS_OK);
			genericResponse.setEstado(HttpStatus.OK.toString());
			genericResponse.setDatos(result);

			return new ResponseEntity<>(genericResponse, HttpStatus.OK);
		} catch (Exception e) {
			logs.write(TYPELOG.ERROR, ConstantesGeneral.LOG_ERROR, "ServiceFactory / createResponse(), " + e);

			var genericResponse = new GenericResponseDto<Object>();

			genericResponse.setMensaje(e.getLocalizedMessage());
			genericResponse.setEstado(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			genericResponse.setDatos(result);

			return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static ResponseEntity<GenericResponseDto<Object>> notFoundResponse(Object result, Object message) {
		ResponseErrorDto baseError;

		try {
			var genericResponse = new GenericResponseDto<Object>();

			List<String> errors = new ArrayList<>();
			errors.add("Not found");

			baseError = ResponseErrorDto.builder().status(HttpStatus.NOT_FOUND).field("404 NOT_FOUND")
					.message(message.toString()).errorCode("ERROR_NO_DATA_FOUND").errors(errors).build();

			genericResponse.setMensaje(message.toString());
			genericResponse.setEstado(HttpStatus.NOT_FOUND.toString());
			genericResponse.setDatos(baseError);

			return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logs.write(TYPELOG.ERROR, ConstantesGeneral.LOG_ERROR, "ServiceFactory / notFoundResponse(), " + e);

			var genericResponse = new GenericResponseDto<Object>();

			genericResponse.setMensaje(e.getLocalizedMessage());
			genericResponse.setEstado(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			genericResponse.setDatos(result);

			return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
