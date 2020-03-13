package com.devpoc.multiple.db.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devpoc.multiple.db.mongobonerepo.PatientMongoRepository;
import com.devpoc.multiple.db.mongooneentity.PatientMongo;
import com.devpoc.multiple.db.mongosecondentity.HosptitalMongo;
import com.devpoc.multiple.db.mongosecondrepo.HospitalMongoRepository;
import com.devpoc.multiple.db.rdboneentity.Patient;
import com.devpoc.multiple.db.rdbonerepo.PatientRepository;
import com.devpoc.multiple.db.rdbsecondentity.Hospital;
import com.devpoc.multiple.db.rdbsecondrepo.HospitalRepository;
import com.devpoc.multiple.db.services.RdbService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/hospitalinfo")
public class HospitalInfoController {
	
	
	@Autowired
    private PatientRepository patientRepositry;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalMongoRepository hospitalmongorepo ;

    @Autowired
    private PatientMongoRepository patientMongoRepository;
    
    @Autowired
    private RdbService rdbservice;
    
    @RequestMapping(method = RequestMethod.GET, path = "/db1",
            produces = {
            		 MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Page<Patient> getAllPatientDb1(@RequestHeader final HttpHeaders headers, final Pageable pageable) {
        return patientRepositry.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/db2",
            produces = {
            		 MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Page<Hospital> getAllHospitalDb2(@RequestHeader final HttpHeaders headers, final Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mongo1",
            produces = {
            		 MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Page<PatientMongo> getAllPatientMongo1(@RequestHeader final HttpHeaders headers, final Pageable pageable) {
        return patientMongoRepository.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mongo2",
            produces = {
                   MediaType.APPLICATION_JSON_VALUE
           })
    @ResponseBody
    public Page<HosptitalMongo> getAllHospitalMongo1(@RequestHeader final HttpHeaders headers, final Pageable pageable) {
      return hospitalmongorepo.findAll(pageable);
    }
    
    @PostMapping(value = "/addhospitalstordb")
    @ApiOperation(value="save All Hospitlas to rdb", notes="Gets all hospitals in the h2", nickname="getHospitals")
    public Hospital saveHospitals(@RequestBody(required=true)Hospital hospital){
    	return hospitalRepository.saveAndFlush(hospital);
    }
    
    @PostMapping(value = "/addpatientstordb")
    @ApiOperation(value="save All Hospitlas to rdb", notes="Gets all patients in the h2", nickname="adpatients")
    public Patient savePatients(@RequestBody(required=true)Patient patient){
    	return patientRepositry.save(patient);
    }
    
    @PostMapping(value = "/addhospitaltomongo")
    @ApiOperation(value="Get All Hospitlas to mongodb", notes="save all hospitals in the mongodb", nickname="getHospitals")
    public HosptitalMongo saveHospitalstomongo(@RequestBody(required=true)HosptitalMongo hospital){
    	return hospitalmongorepo.save(hospital);
    }
    
    @RequestMapping(value = "/hospitals", method = RequestMethod.GET)
    @ApiOperation(value="Get matching Hospitlas with number", notes="Get matching hospitals in the system using hospitalNumber", nickname="getHospitalswithmatchingnumber")
    public List<HosptitalMongo> getAllHospitals(@RequestParam(name="hospitalNumber", required=false)String hospitalNumber){
        return hospitalmongorepo.findAll().stream().filter(s->s.getHospitalNumber().equalsIgnoreCase(hospitalNumber)).collect(Collectors.toList());
    }
    
    @PostMapping(value = "/addpatientstodb")
    @ApiOperation(value="save All patients to dbs", notes="save all patients to db", nickname="dbsavepatient")
    public void savePatientstodb(@RequestBody(required=true)Patient patient){
    	rdbservice.saveFilteredPatienttordb(patient);
    	
    }
    
    @PostMapping(value = "/addpatientstomongodb")
    @ApiOperation(value="save All patients to mongo", notes="save all patients to mongodb", nickname="mongodbsavepatient")
    public void savePatientstomongodb(@RequestBody(required=true)PatientMongo patient){
    	rdbservice.saveFilteredPatienttomongodb(patient);
    	
    }
    	
}
