package com.bandesalprueba.sv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bandesalprueba.sv.dto.RequestSaveReadersDto;
import com.bandesalprueba.sv.dto.RequestUpdateReaderDto;
import com.bandesalprueba.sv.dto.base.GenericResponseServiceDto;
import com.bandesalprueba.sv.model.Reader;
import com.bandesalprueba.sv.repository.ReaderRepository;
import com.bandesalprueba.sv.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService {

	private ReaderRepository readerRepo;

	public ReaderServiceImpl(ReaderRepository readerRepo) {
		this.readerRepo = readerRepo;
	}

	@Override
	public GenericResponseServiceDto<List<Reader>> getAllReaders() {
		var response = new GenericResponseServiceDto<List<Reader>>();

		try {
			List<Reader> listReaders = readerRepo.findAll();

			if (listReaders.isEmpty()) {
				response.setExito(false);
				response.setMensaje("No se encontro informacion de lectores");
			} else {
				response.setExito(true);
				response.setDato(listReaders);
				response.setMensaje("Se obtuvo el listado de lectores con exito");
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en ReaderServiceImpl.getAll");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> saveReaders(RequestSaveReadersDto request) {
		var response = new GenericResponseServiceDto<>();

		try {
			Reader readerSave = Reader.builder().name(request.getName()).build();

			readerRepo.save(readerSave);

			response.setExito(true);
			response.setMensaje("Se registro el nombre del lector con exito");
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en ReaderServiceImpl.saveReaders");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> updateReader(RequestUpdateReaderDto request) {
		var response = new GenericResponseServiceDto<>();

		try {
			if (Boolean.TRUE.equals(validarIdReader(request.getIdReader()))) {
				Reader readerUpdate = Reader.builder().idReader(request.getIdReader()).name(request.getName()).build();

				readerRepo.save(readerUpdate);

				response.setExito(true);
				response.setMensaje("Se actualizo el nombre del lector con exito");
			} else {
				response.setExito(false);
				response.setMensaje("No se encontro el ID Reader: " + request.getIdReader());
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en ReaderServiceImpl.updateReader");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> deleteReader(Integer idReader) {
		var response = new GenericResponseServiceDto<>();

		try {
			if (Boolean.TRUE.equals(validarIdReader(idReader))) {
				readerRepo.deleteById(idReader);

				response.setExito(true);
				response.setMensaje("Se elimino el lector con exito");
			} else {
				response.setExito(false);
				response.setMensaje("No se encontro el ID Reader: " + idReader);
			}
		} catch (Exception e) {
			e.printStackTrace();

			response.setExito(false);
			response.setMensaje("Ocurrio un error en ReaderServiceImpl.deleteReader");
		}

		return response;
	}

	private Boolean validarIdReader(Integer idReader) {
		Boolean resultado = false;

		try {
			Optional<Reader> readerUpdate = readerRepo.findById(idReader);

			if (readerUpdate.isEmpty()) {
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
