package com.bandesalprueba.sv.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateBlogDto {

	@NotNull(message = "message.idblog.null")
	@Min(value = 1, message = "message.idblog.size")
	private int idBlog;

	@NotBlank(message = "message.title.blank")
	@NotNull(message = "message.title.null")
	@Size(max = 50, message = "message.title.order.size")
	private String title;

	@NotBlank(message = "message.description.blank")
	@NotNull(message = "message.description.null")
	@Size(max = 4000, message = "message.description.order.size")
	private String description;

}
