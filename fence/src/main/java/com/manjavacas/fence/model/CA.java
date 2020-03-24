package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * Actual coordination
 *
 */

@Document(collection = "CA")
public class CA {
	
	@Id
	private ObjectId _id;
	
	private String user1;
	private String user2;
	private String project;
	private double weight;

}
