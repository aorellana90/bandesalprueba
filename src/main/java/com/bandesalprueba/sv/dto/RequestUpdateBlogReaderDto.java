package com.bandesalprueba.sv.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateBlogReaderDto {

	@NotNull(message = "message.idblogreader.null")
	@Min(value = 1, message = "message.idblogreader.size")
	private Integer idBlogReader;

	@NotNull(message = "message.idreader.null")
	@Min(value = 1, message = "message.idreader.size")
	private int idReader;

	@NotNull(message = "message.idblog.null")
	@Min(value = 1, message = "message.idblog.size")
	private int idBlog;

}
