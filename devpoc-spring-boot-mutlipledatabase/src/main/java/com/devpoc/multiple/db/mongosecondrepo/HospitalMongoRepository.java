package com.devpoc.multiple.db.mongosecondrepo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.devpoc.multiple.db.mongosecondentity.HosptitalMongo;


public interface HospitalMongoRepository extends MongoRepository<HosptitalMongo, Long>{
	

}
