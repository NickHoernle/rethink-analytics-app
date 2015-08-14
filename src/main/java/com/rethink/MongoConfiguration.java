package com.rethink;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {
	

	/*--spring.data.mongodb.host=ds039251.mongolab.com --spring.data.mongodb.port=39251 --spring.data.mongodb.username=rethinkuser --spring.data.mongodb.password=rethinkuser --spring.data.mongodb.database=heroku_app37401020*/
    @Override
    protected String getDatabaseName() {
    	if ( System.getenv("spring.data.mongodb.database") != null )
        	return System.getenv("spring.data.mongodb.database");
    	return "test";
    }

    @Override
    public Mongo mongo() throws Exception {
    	if ( System.getenv("spring.data.mongodb.database") != null )
    		return new MongoClient( System.getenv("spring.data.mongodb.host"), Integer.parseInt( System.getenv("spring.data.mongodb.port") ) );
    	return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.rethink";
    }
    
    protected UserCredentials getUserCredentials() {
    	if ( System.getenv("spring.data.mongodb.username") != null )
    		return new UserCredentials( System.getenv("spring.data.mongodb.username"), System.getenv("spring.data.mongodb.password") );
		return null;
	}
    
    @Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(mongo(), getDatabaseName(), getUserCredentials(), getAuthenticationDatabaseName());
	}
}
