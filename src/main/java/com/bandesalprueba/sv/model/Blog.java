package com.bandesalprueba.sv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blogs")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBlog")
	private Integer idBlog;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

}
