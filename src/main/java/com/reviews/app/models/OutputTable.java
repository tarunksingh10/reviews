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
	private String Sentiment_y;
	private String Sentiment_x;
	private String Verbatim;
	private String Sentence;
	private String source;
	private String sentence_id;
	private String L2;
	private String L3;
	private String L0;
	private String L1;
	private Date date;
	private String property;
	private String Unique_id;
	private String feedback;
	private boolean correctCategory;

}
