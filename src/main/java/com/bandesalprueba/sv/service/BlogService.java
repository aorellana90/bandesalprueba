package com.bandesalprueba.sv.service;

import java.util.List;

import com.bandesalprueba.sv.dto.RequestSaveBlogDto;
import com.bandesalprueba.sv.dto.RequestUpdateBlogDto;
import com.bandesalprueba.sv.dto.base.GenericResponseServiceDto;
import com.bandesalprueba.sv.model.Blog;

public interface BlogService {

	public GenericResponseServiceDto<List<Blog>> getAllBlogs();

	public GenericResponseServiceDto<Object> saveBlog(RequestSaveBlogDto request);

	public GenericResponseServiceDto<Object> updateBlog(RequestUpdateBlogDto request);

	public GenericResponseServiceDto<Object> deleteBlog(Integer idBlog);

}
