package com.bandesalprueba.sv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blogs_readers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BlogReader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBlogReader")
	private Integer idBlogReader;

	@ManyToOne
	@JoinColumn(name = "idReader")
	private Reader reader;

	@ManyToOne
	@JoinColumn(name = "idBlog")
	private Blog blog;

}
