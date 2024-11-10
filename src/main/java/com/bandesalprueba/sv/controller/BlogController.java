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
import com.bandesalprueba.sv.dto.RequestSaveBlogDto;
import com.bandesalprueba.sv.dto.RequestUpdateBlogDto;
import com.bandesalprueba.sv.dto.base.GenericResponseDto;
import com.bandesalprueba.sv.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	private BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<GenericResponseDto<Object>> getAll() {
		var respuesta = blogService.getAllBlogs();

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

	@PostMapping("/save")
	public ResponseEntity<GenericResponseDto<Object>> save(@RequestBody @Valid RequestSaveBlogDto request) {
		var respuesta = blogService.saveBlog(request);

		return ServiceFactory.createResponse(respuesta.getMensaje());
	}

	@PutMapping("/update")
	public ResponseEntity<GenericResponseDto<Object>> update(@RequestBody @Valid RequestUpdateBlogDto request) {
		var respuesta = blogService.updateBlog(request);

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

	@DeleteMapping("/delete/{idBlog}")
	public ResponseEntity<GenericResponseDto<Object>> delete(@PathVariable Integer idBlog) {
		var respuesta = blogService.deleteBlog(idBlog);

		if (Boolean.FALSE.equals(respuesta.getExito())) {
			return ServiceFactory.notFoundResponse(null, respuesta.getMensaje());
		}

		return ServiceFactory.createResponse(respuesta.getDato());
	}

}
