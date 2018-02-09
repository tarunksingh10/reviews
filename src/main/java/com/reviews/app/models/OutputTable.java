package com.reviews.app.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Document(collection = "output_table")
public class OutputTable {

	@Id
	private String id;
	private String category;
	private String city;
	private String sentiment;
	private String sentiment_text;
	private String rating;
	private String review;
	private String sentence;
	private String hotel;
	private String header;
	private String source;
	private String sentence_id;
	private String l2;
	private String l3;
	private String l0;
	private String l1;
	@Getter(AccessLevel.NONE)
	private Date date;
	private String unique_id;
	private String exec_Status;
	private String feedback;
	private boolean correctCategory;

	public String getDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String d = df.format(date);
		return d;
	}

}
