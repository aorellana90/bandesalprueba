package com.bandesalprueba.sv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bandesalprueba.sv.dto.RequestSaveBlogDto;
import com.bandesalprueba.sv.dto.RequestUpdateBlogDto;
import com.bandesalprueba.sv.dto.base.GenericResponseServiceDto;
import com.bandesalprueba.sv.model.Blog;
import com.bandesalprueba.sv.repository.BlogRepository;
import com.bandesalprueba.sv.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepo;

	public BlogServiceImpl(BlogRepository blogRepo) {
		this.blogRepo = blogRepo;
	}

	@Override
	public GenericResponseServiceDto<List<Blog>> getAllBlogs() {
		var response = new GenericResponseServiceDto<List<Blog>>();

		try {
			List<Blog> listBlogs = blogRepo.findAll();

			if (listBlogs.isEmpty()) {
				response.setExito(false);
				response.setMensaje("No se encontro informacion de los blogs");
			} else {
				response.setExito(true);
				response.setDato(listBlogs);
				response.setMensaje("Se obtuvo el listado de blogs con exito");
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogServiceImpl.getAllBlogs");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> saveBlog(RequestSaveBlogDto request) {
		var response = new GenericResponseServiceDto<>();

		try {
			Blog blogSave = Blog.builder().title(request.getTitle()).description(request.getDescription()).build();

			blogRepo.save(blogSave);

			response.setExito(true);
			response.setMensaje("Se registro el blog con exito");
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogServiceImpl.saveBlog");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> updateBlog(RequestUpdateBlogDto request) {
		var response = new GenericResponseServiceDto<>();

		try {
			if (Boolean.TRUE.equals(validarIdBlog(request.getIdBlog()))) {
				Blog blogUpdate = Blog.builder().idBlog(request.getIdBlog()).title(request.getTitle())
						.description(request.getDescription()).build();

				blogRepo.save(blogUpdate);

				response.setExito(true);
				response.setMensaje("Se actualizo el blog con exito");
			} else {
				response.setExito(false);
				response.setMensaje("No se encontro el ID del Blog: " + request.getIdBlog());
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogServiceImpl.updateBlog");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> deleteBlog(Integer idBlog) {
		var response = new GenericResponseServiceDto<>();

		try {
			if (Boolean.TRUE.equals(validarIdBlog(idBlog))) {
				blogRepo.deleteById(idBlog);

				response.setExito(true);
				response.setMensaje("Se elimino el blog con exito");
			} else {
				response.setExito(false);
				response.setMensaje("No se encontro el ID del blog: " + idBlog);
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogServiceImpl.deleteBlog");
		}

		return response;
	}

	private Boolean validarIdBlog(Integer idBlog) {
		Boolean resultado = false;

		try {
			Optional<Blog> blogUpdate = blogRepo.findById(idBlog);

			if (blogUpdate.isEmpty()) {
				resultado = false;
			} else {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();

			resultado = false;
		}

		return resultado;
	}

}
