package com.bandesalprueba.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bandesalprueba.sv.model.BlogReader;

@Repository
public interface BlogReaderRepository extends JpaRepository<BlogReader, Integer> {

}
