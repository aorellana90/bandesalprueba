package com.bandesalprueba.sv.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bandesalprueba.sv.component.util.ServiceFactory;
import com.bandesalprueba.sv.dto.RequestSaveReadersDto;
import com.bandesalprueba.sv.dto.RequestUpdateReaderDto;
import com.bandesalprueba.sv.dto.base.GenericResponseDto;
import com.bandesalprueba.sv.service.ReaderService;

@RestController
@RequestMapping("/readers")
public class ReaderController {

	private ReaderService readerService;

	public ReaderController(ReaderService readerService) {
		this.readerService = readerService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<GenericResponseDto<Object>> getAll() {
		var respuesta = readerService.getAllReaders();

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

	@PostMapping("/save")
	public ResponseEntity<GenericResponseDto<Object>> save(@RequestBody @Valid RequestSaveReadersDto request) {
		var respuesta = readerService.saveReaders(request);

		return ServiceFactory.createResponse(respuesta.getMensaje());
	}

	@PutMapping("/update")
	public ResponseEntity<GenericResponseDto<Object>> update(@RequestBody @Valid RequestUpdateReaderDto request) {
		var respuesta = readerService.updateReader(request);

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

	@DeleteMapping("/delete/{idReader}")
	public ResponseEntity<GenericResponseDto<Object>> delete(@PathVariable Integer idReader) {
		var respuesta = readerService.deleteReader(idReader);

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

}
