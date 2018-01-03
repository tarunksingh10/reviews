package com.reviews.app.models;

import java.util.Date;

import lombok.Data;

@Data
public class SearchCriteria {
	private Date fromDate;
	private Date toDate;
	private String source;
	private String city;
	private String property;
	private String category;
}
