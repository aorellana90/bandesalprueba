package com.bandesalprueba.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bandesalprueba.sv.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
