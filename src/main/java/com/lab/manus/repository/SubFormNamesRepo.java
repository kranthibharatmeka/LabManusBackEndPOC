package com.lab.manus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.manus.entity.SubFormNames;

@Repository
public interface SubFormNamesRepo extends JpaRepository<SubFormNames ,Long> {

}
