package com.mkyong.core;

import com.mkyong.model.DocumentWithTextPages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
//import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");


		String vendor = "gms";
		String model = "acadia";
		Integer year =2014;
		String keyword = "qwerty";
		Query search = new Query(Criteria.where("vendor").is(vendor)
							.and("model").is(model)
							.and("year").is(year)
							.and("pages.text").regex(keyword, "i"));


		List<DocumentWithTextPages> saved = mongoOperation.find(search, DocumentWithTextPages.class);
		System.out.println("find - saved : " + saved);


		Query findBookmarks = new Query(Criteria.where("vendor").is(vendor)
				.and("model").is(model)
				.and("year").is(year));
		saved = mongoOperation.find(findBookmarks, DocumentWithTextPages.class);

		System.out.println("find - saved : " + saved);

	}

}