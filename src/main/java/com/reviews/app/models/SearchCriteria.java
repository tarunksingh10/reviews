package com.reviews.app.models;

import lombok.Data;

@Data
public class SearchCriteria {
	private String fromDate;
	private String toDate;
	private String source;
	private String city;
	private String property;
	private String category;
}
