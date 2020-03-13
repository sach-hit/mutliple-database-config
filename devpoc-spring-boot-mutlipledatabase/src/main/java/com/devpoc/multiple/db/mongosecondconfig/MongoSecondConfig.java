package com.devpoc.multiple.db.mongosecondconfig;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.mongodb.MongoClient;

 @Configuration
 @EnableMongoRepositories(basePackages= "com.devpoc.multiple.db.mongosecondrepo",
		 mongoTemplateRef = "secondaryMongoTemplate")
 
	public class MongoSecondConfig {
	 
	   public @Bean MongoDbFactory getMongoDbFactory1() throws UnknownHostException {

	        return new SimpleMongoDbFactory(new MongoClient("localhost",27019),"mongo2");

	    }

	    public @Bean(name = "secondaryMongoTemplate") MongoTemplate getMongoTemplate() throws UnknownHostException {

	        MongoTemplate mongoTemplate2 = new MongoTemplate(getMongoDbFactory1());

	        return mongoTemplate2;

	    }

	}
	
	/*	
		@Bean
	    @ConfigurationProperties("app.datasource.mongo2")
	    public MongoProperties mongo2Properties() {
	        return new MongoProperties();
	    }
		
		
	    @Bean(name = "secondaryMongoTemplate")
	    public MongoTemplate seconMongoTemplate() throws Exception {
	        return new MongoTemplate(secondaryFactory(this.mongo2Properties()));
	    }
		
		@SuppressWarnings("deprecation")
		@Bean
	    @Primary
	    public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
	        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
	                mongo.getDatabase());
	    }
	    

}*/



