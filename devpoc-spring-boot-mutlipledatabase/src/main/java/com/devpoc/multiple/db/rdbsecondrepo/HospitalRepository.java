package com.devpoc.multiple.db.rdbsecondrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devpoc.multiple.db.rdbsecondentity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{

}
