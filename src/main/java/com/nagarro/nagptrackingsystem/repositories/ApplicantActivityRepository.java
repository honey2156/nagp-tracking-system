package com.nagarro.nagptrackingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.nagptrackingsystem.entity.ApplicantActivity;

@Repository
public interface ApplicantActivityRepository extends JpaRepository<ApplicantActivity, Integer> {

}
