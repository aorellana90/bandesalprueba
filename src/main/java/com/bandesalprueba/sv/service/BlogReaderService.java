package com.bandesalprueba.sv.service;

import java.util.List;

import com.bandesalprueba.sv.dto.RequestSaveBlogReaderDto;
import com.bandesalprueba.sv.dto.RequestUpdateBlogReaderDto;
import com.bandesalprueba.sv.dto.base.GenericResponseServiceDto;
import com.bandesalprueba.sv.model.BlogReader;

public interface BlogReaderService {

	public GenericResponseServiceDto<List<BlogReader>> getAllBlogReader();

	public GenericResponseServiceDto<Object> saveBlogReader(RequestSaveBlogReaderDto request);

	public GenericResponseServiceDto<Object> updateBlogReader(RequestUpdateBlogReaderDto request);

	public GenericResponseServiceDto<Object> deleteBlogReader(Integer idBlogReader);

}
