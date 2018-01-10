package com.reviews.app.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "output_table")
public class OutputTable {

	@Id
	private String id;
	private String category;
	private String city;
	private String sentiment_y;
	private String sentiment_x;
	private String verbatim;
	private String sentence;
	private String source;
	private String sentence_id;
	private String l2;
	private String l3;
	private String l0;
	private String l1;
	private Date date;
	private String property;
	private String unique_id;
	private String feedback;
	private boolean correctCategory;

}
