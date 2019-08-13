package com.lab.manus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.manus.entity.FormEntitiy;

@Repository
public interface FormRepo extends JpaRepository<FormEntitiy ,Long> {

}
