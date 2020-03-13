package com.devpoc.multiple.db.mongooneconfig;

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

import com.devpoc.multiple.db.mongooneentity.PatientMongo;
import com.devpoc.multiple.db.mongosecondentity.HosptitalMongo;
import com.mongodb.MongoClient;



@Configuration
@EnableMongoRepositories(basePackages = "com.devpoc.multiple.db.mongobonerepo",
        mongoTemplateRef = "primaryMongoTemplate")
public class MongoOneConfig {
	
	 @Primary
	 public @Bean MongoDbFactory getMongoDbFactory() throws UnknownHostException {

	    return new SimpleMongoDbFactory(new MongoClient("localhost",27018),"mongo1");

	  }
	   @Primary
	   public @Bean(name = "primaryMongoTemplate") MongoTemplate getMongoTemplate() throws UnknownHostException {

	      MongoTemplate mongoTemplate1 = new MongoTemplate(getMongoDbFactory());

	       return mongoTemplate1;
	   }
	    
	/*
	
    @Primary
    @ConfigurationProperties("app.datasource.mongo1")
    public MongoProperties mongo1Properties() {
        return new MongoProperties();
    }
	
	@Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(primaryFactory(this.mongo1Properties()));
    }
	
	@SuppressWarnings("deprecation")
	@Bean
    @Primary
    public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
                mongo.getDatabase());
    }
    */
    

}
