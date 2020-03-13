package com.devpoc.multiple.db.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.devpoc.multiple.db.exception.ResourceNotFoundException;
import com.devpoc.multiple.db.mongobonerepo.PatientMongoRepository;
import com.devpoc.multiple.db.mongooneentity.PatientMongo;
import com.devpoc.multiple.db.rdboneentity.Patient;
import com.devpoc.multiple.db.rdbonerepo.PatientRepository;



@Service
public class RdbService {
	
	@Autowired
    private PatientRepository patientRepositry;

	@Autowired
	private PatientMongoRepository patientMongoRepository;
    
    public void saveFilteredPatienttordb(Patient patient) {
    	patientRepositry.save(patient);
    	
    }
    
   public void saveFilteredPatienttomongodb(PatientMongo patient) {
    	patientMongoRepository.save(patient);
    	
    }
   
   public Patient updatePatient(Long patientId,Patient patientDetails) throws ResourceNotFoundException {
	   Patient patient = patientRepositry.findById(patientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + patientId));

	        patient.setEmailAddress(patientDetails.getEmailAddress());
	        patient.setLastName(patientDetails.getLastName());
	        patient.setFirstName(patientDetails.getFirstName());
	        final Patient updatePatient = patientRepositry.save(patient);
	        return updatePatient;
	 }
   
   public Map < String, Boolean > deletePatient(Long patientId)
		    throws ResourceNotFoundException {
	   Patient patient = patientRepositry.findById(patientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + patientId));

	            patientRepositry.delete(patient);
		        Map < String, Boolean > response = new HashMap < > ();
		        response.put("deleted", Boolean.TRUE);
		        return response;
	}
 
}
