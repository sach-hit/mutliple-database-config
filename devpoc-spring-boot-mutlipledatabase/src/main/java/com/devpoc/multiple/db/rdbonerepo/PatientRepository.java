package com.devpoc.multiple.db.rdbonerepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devpoc.multiple.db.rdboneentity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
