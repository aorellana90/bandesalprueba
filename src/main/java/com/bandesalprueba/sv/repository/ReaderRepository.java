package com.bandesalprueba.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bandesalprueba.sv.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

}
