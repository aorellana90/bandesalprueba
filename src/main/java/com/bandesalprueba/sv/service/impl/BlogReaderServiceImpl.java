package com.bandesalprueba.sv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bandesalprueba.sv.dto.RequestSaveBlogReaderDto;
import com.bandesalprueba.sv.dto.RequestUpdateBlogReaderDto;
import com.bandesalprueba.sv.dto.base.GenericResponseServiceDto;
import com.bandesalprueba.sv.model.Blog;
import com.bandesalprueba.sv.model.BlogReader;
import com.bandesalprueba.sv.model.Reader;
import com.bandesalprueba.sv.repository.BlogReaderRepository;
import com.bandesalprueba.sv.repository.BlogRepository;
import com.bandesalprueba.sv.repository.ReaderRepository;
import com.bandesalprueba.sv.service.BlogReaderService;

@Service
public class BlogReaderServiceImpl implements BlogReaderService {

	private BlogReaderRepository blogReaderRepo;
	private BlogRepository blogRepo;
	private ReaderRepository readerRepo;

	public BlogReaderServiceImpl(BlogReaderRepository blogReaderRepo, BlogRepository blogRepo,
			ReaderRepository readerRepo) {
		this.blogReaderRepo = blogReaderRepo;
		this.blogRepo = blogRepo;
		this.readerRepo = readerRepo;
	}

	@Override
	public GenericResponseServiceDto<List<BlogReader>> getAllBlogReader() {
		var response = new GenericResponseServiceDto<List<BlogReader>>();

		try {
			List<BlogReader> listBlogsReaders = blogReaderRepo.findAll();

			if (listBlogsReaders.isEmpty()) {
				response.setExito(false);
				response.setMensaje("No se encontro informacion de blogs y lectores");
			} else {
				response.setExito(true);
				response.setDato(listBlogsReaders);
				response.setMensaje("Se obtuvo el listado de blogs y lectores con exito");
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogReaderServiceImpl.getAllBlogReader");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> saveBlogReader(RequestSaveBlogReaderDto request) {
		var response = new GenericResponseServiceDto<>();

		try {
			Blog blog = Blog.builder().idBlog(request.getIdBlog()).build();
			Reader reader = Reader.builder().idReader(request.getIdReader()).build();
			BlogReader blogReaderSave = BlogReader.builder().blog(blog).reader(reader).build();

			blogReaderRepo.save(blogReaderSave);

			response.setExito(true);
			response.setMensaje("Se registro el blog y lector con exito");
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogReaderServiceImpl.saveBlogReader");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> updateBlogReader(RequestUpdateBlogReaderDto request) {
		var response = new GenericResponseServiceDto<>();

		try {
			if (Boolean.TRUE.equals(
					validarIdBlogReader(request.getIdBlogReader(), request.getIdBlog(), request.getIdReader()))) {
				Blog blog = Blog.builder().idBlog(request.getIdBlog()).build();
				Reader reader = Reader.builder().idReader(request.getIdReader()).build();
				BlogReader blogReaderSave = BlogReader.builder().idBlogReader(request.getIdBlogReader()).blog(blog)
						.reader(reader).build();

				blogReaderRepo.save(blogReaderSave);

				response.setExito(true);
				response.setMensaje("Se actualizo el blog lector con exito");
			} else {
				response.setExito(false);
				response.setMensaje("No se encontro el ID blog lector: " + request.getIdBlogReader());
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogReaderServiceImpl.updateBlogReader");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> deleteBlogReader(Integer idBlogReader) {
		var response = new GenericResponseServiceDto<>();

		try {
			Optional<BlogReader> blogReaderDelete = blogReaderRepo.findById(idBlogReader);

			if (!blogReaderDelete.isEmpty()) {
				blogReaderRepo.deleteById(idBlogReader);

				response.setExito(true);
				response.setMensaje("Se elimino el blog lector con exito");
			} else {
				response.setExito(false);
				response.setMensaje("No se encontro el ID blog lector: " + idBlogReader);
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en BlogReaderServiceImpl.deleteBlogReader");
		}

		return response;
	}

	private Boolean validarIdBlogReader(Integer idBlogReader, Integer idBlog, Integer idReader) {
		Boolean resultado = false;

		try {
			Optional<BlogReader> blogReaderUpdate = blogReaderRepo.findById(idBlogReader);
			Optional<Blog> blogUpdate = blogRepo.findById(idBlog);
			Optional<Reader> readerUpdate = readerRepo.findById(idReader);

			if (blogReaderUpdate.isEmpty() || blogUpdate.isEmpty() || readerUpdate.isEmpty()) {
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
