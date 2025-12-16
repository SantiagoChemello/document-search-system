package com.IBMproject.documentIngester;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DocumentIngesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentIngesterApplication.class, args);
	}

	@Bean
	GraphQLScalarType uploadScalarType() {
		return ApolloScalars.Upload;
	}

}
