package com.reviews.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reviews.app.models.TestingDisplay;
import com.reviews.app.repositories.ReviewRepository;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping("/outputTableEntry")
	public List<TestingDisplay> getAllOutputTable() {
		List<TestingDisplay> list = reviewRepository.findAll();
		return list;
	}

	@PostMapping("/outputTableEntry")
	public TestingDisplay createOutputTableEntry(@Valid @RequestBody TestingDisplay outputTableEntry) {
		return reviewRepository.save(outputTableEntry);
	}

	@GetMapping(value = "/outputTableEntry/{id}")
	public ResponseEntity<TestingDisplay> getOutputTableEntryById(@PathVariable("id") String id) {
		TestingDisplay outputTableEntry = reviewRepository.findOne(id);
		if (outputTableEntry == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(outputTableEntry, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/outputTableEntry/{id}")
	public ResponseEntity<TestingDisplay> updateOutputTableEntry(@PathVariable("id") String id,
			@Valid @RequestBody TestingDisplay outputTable) {
		TestingDisplay outputTableEntry = reviewRepository.findOne(id);
		if (outputTableEntry == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		outputTableEntry.setCorrectCategory(outputTable.isCorrectCategory());
		outputTableEntry.setFeedback(outputTable.getFeedback());

		TestingDisplay updatedOutputTableEntry = reviewRepository.save(outputTableEntry);
		return new ResponseEntity<>(updatedOutputTableEntry, HttpStatus.OK);
	}

	@DeleteMapping(value = "/outputTableEntry/{id}")
	public void deleteTodo(@PathVariable("id") String id) {
		reviewRepository.delete(id);
	}

	@GetMapping(value = "/getFilteredOutputTable")
	public List<TestingDisplay> getFilteredOutputTable(@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "hotel", required = false) String hotel,
			@RequestParam(value = "source", required = false) String source,
			@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate) {
		Query query = new Query();

		
			
		
		if (fromDate != null && toDate != null) {
			query.addCriteria(Criteria.where("date").exists(true)
					.andOperator(Criteria.where("date").gte(DateMain.getDate( fromDate)).andOperator(Criteria.where("date").lte(DateMain.getDate(toDate)))));
		} else if (fromDate != null && toDate == null) {
			query.addCriteria(Criteria.where("date").exists(true).andOperator(Criteria.where("date").gte(DateMain.getDate(fromDate))));
		} else if (fromDate == null && toDate != null) {
			query.addCriteria(Criteria.where("date").exists(true).andOperator(Criteria.where("date").lte(DateMain.getDate(toDate))));
		}

		if (city != null) {
			query.addCriteria(Criteria.where("city").is(city));
		}
		if (hotel != null) {
			query.addCriteria(Criteria.where("hotel").is(hotel));
		}
		if (source != null) {
			query.addCriteria(Criteria.where("source").is(source));
		}
		List<TestingDisplay> listOutput = mongoTemplate.find(query, TestingDisplay.class);
		return listOutput;
	}
}