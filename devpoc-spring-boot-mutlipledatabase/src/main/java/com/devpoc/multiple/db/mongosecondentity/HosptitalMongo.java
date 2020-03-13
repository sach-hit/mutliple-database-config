package com.devpoc.multiple.db.mongosecondentity;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "HospitalMongo")
public class HosptitalMongo {
	    private long id;
	    private String name;
	    private String hospitalNumber;
	    private String bedInfo;

}
