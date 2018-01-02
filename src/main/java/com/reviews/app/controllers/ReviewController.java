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
import org.springframework.web.bind.annotation.RestController;

import com.reviews.app.models.OutputTable;
import com.reviews.app.models.SearchCriteria;
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
	public List<OutputTable> getAllOutputTable() {
		List<OutputTable> list = reviewRepository.findAll();
		return list;
	}

	@PostMapping("/outputTableEntry")
	public OutputTable createOutputTableEntry(@Valid @RequestBody OutputTable outputTableEntry) {
		return reviewRepository.save(outputTableEntry);
	}

	@GetMapping(value = "/outputTableEntry/{id}")
	public ResponseEntity<OutputTable> getOutputTableEntryById(@PathVariable("id") String id) {
		OutputTable outputTableEntry = reviewRepository.findOne(id);
		if (outputTableEntry == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(outputTableEntry, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/outputTableEntry/{id}")
	public ResponseEntity<OutputTable> updateOutputTableEntry(@PathVariable("id") String id,
			@Valid @RequestBody OutputTable outputTable) {
		OutputTable outputTableEntry = reviewRepository.findOne(id);
		if (outputTableEntry == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		outputTableEntry.setCorrectCategory(outputTable.isCorrectCategory());
		outputTableEntry.setFeedback(outputTable.getFeedback());

		OutputTable updatedOutputTableEntry = reviewRepository.save(outputTableEntry);
		return new ResponseEntity<>(updatedOutputTableEntry, HttpStatus.OK);
	}

	@DeleteMapping(value = "/outputTableEntry/{id}")
	public void deleteTodo(@PathVariable("id") String id) {
		reviewRepository.delete(id);
	}

	@PostMapping(value = "/getFilteredOutputTable")
	public List<OutputTable> getFilteredOutputTable(@Valid @RequestBody SearchCriteria searchCriteria) {
		Query query = new Query();
		if (searchCriteria.getCity() != null) {
			query.addCriteria(Criteria.where("city").exists(true).andOperator(Criteria.where("city").is(searchCriteria.getCity())));
		}
		if (searchCriteria.getProperty() != null) {
			query.addCriteria(Criteria.where("property").exists(true).andOperator(Criteria.where("property").is(searchCriteria.getProperty())));
		}
		if (searchCriteria.getSource() != null) {
			query.addCriteria(Criteria.where("source").exists(true).andOperator(Criteria.where("source").is(searchCriteria.getSource())));
		}
		List<OutputTable> listOutput = mongoTemplate.find(query, OutputTable.class);
		return listOutput;
	}
}