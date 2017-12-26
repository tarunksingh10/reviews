package com.reviews.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "model_table_keywords")
public class ModelTablekeywords {

	@Id
	private String nodeId;
	private String keywords_Sentence_Level;
	private String keywords_Sentence_Level_1;
	private String keywords_Sentence_Level_2;
	private String Keywords_Comment_Level;
	private String Keywords_Comment_Level_1;
	private String Keywords_Comment_Level_2;
	private String exclusion_Sentence_Level;
	private String exclusion_Comment_Level;
	private String attribute_OR_keywords;
	private String attribute_AND_keywords;

}
