package com.lab.manus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.manus.entity.FieldOptions;

@Repository
public interface FieldOptionsRepo  extends JpaRepository<FieldOptions ,Long> {

}
