package com.devpoc.multiple.db.mongooneentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PatientMongo")
public class PatientMongo {

	    @Id
	    private long id;
	 
	    private String firstName;
	   
	    private String lastName;
	   
	    private String emailAddress;
	   
	    private String address;
	   
	    private String country;
	  
	    private String state;
	  
	    private String phoneNumber;
}

