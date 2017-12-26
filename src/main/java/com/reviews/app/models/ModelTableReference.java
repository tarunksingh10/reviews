package com.reviews.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "model_table_reference")
public class ModelTableReference {

	@Id
	private String nodeId;
	private String category_root;
	private String category_level_1;
	private String category_level_2;
	private String category_level_3;
	private String category_level_4;
	private String category_level_5;
	private String category_level_6;
	private String category_level_7;
	private String category_level_8;
	private String category_level_9;
	private String category_level_10;
	private String model_name;
	private String model_id;

}
