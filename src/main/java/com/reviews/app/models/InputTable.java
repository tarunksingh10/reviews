package com.reviews.app.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "input_table")
public class InputTable {

	@Id
	private String uniqueId;
	private Date data_dt = new Date();
	private String comment;
	private String placeholder_field_1;
	private String placeholder_field_2;
	private String placeholder_field_3;
	private String placeholder_field_4;
	private String placeholder_field_5;
	private String placeholder_field_6;
	private String placeholder_field_7;
	private String placeholder_field_8;
	private String placeholder_field_9;
	private Date placeholder_field_10;

}
