package com.bandesalprueba.sv.service;

import java.util.List;

import com.bandesalprueba.sv.dto.RequestSaveReadersDto;
import com.bandesalprueba.sv.dto.RequestUpdateReaderDto;
import com.bandesalprueba.sv.dto.base.GenericResponseServiceDto;
import com.bandesalprueba.sv.model.Reader;

public interface ReaderService {

	public GenericResponseServiceDto<List<Reader>> getAllReaders();

	public GenericResponseServiceDto<Object> saveReaders(RequestSaveReadersDto request);

	public GenericResponseServiceDto<Object> updateReader(RequestUpdateReaderDto request);

	public GenericResponseServiceDto<Object> deleteReader(Integer idReader);

}
