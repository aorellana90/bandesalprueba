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
import com.bandesalprueba.sv.dto.RequestSaveBlogReaderDto;
import com.bandesalprueba.sv.dto.RequestUpdateBlogReaderDto;
import com.bandesalprueba.sv.dto.base.GenericResponseDto;
import com.bandesalprueba.sv.service.BlogReaderService;

@RestController
@RequestMapping("/blogsreaders")
public class BlogReaderController {

	private BlogReaderService blogReaderService;

	public BlogReaderController(BlogReaderService blogReaderService) {
		this.blogReaderService = blogReaderService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<GenericResponseDto<Object>> getAll() {
		var respuesta = blogReaderService.getAllBlogReader();

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

	@PostMapping("/save")
	public ResponseEntity<GenericResponseDto<Object>> save(@RequestBody @Valid RequestSaveBlogReaderDto request) {
		var respuesta = blogReaderService.saveBlogReader(request);

		return ServiceFactory.createResponse(respuesta.getMensaje());
	}

	@PutMapping("/update")
	public ResponseEntity<GenericResponseDto<Object>> update(@RequestBody @Valid RequestUpdateBlogReaderDto request) {
		var respuesta = blogReaderService.updateBlogReader(request);

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

	@DeleteMapping("/delete/{idBlogReader}")
	public ResponseEntity<GenericResponseDto<Object>> delete(@PathVariable Integer idBlogReader) {
		var respuesta = blogReaderService.deleteBlogReader(idBlogReader);

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

}
