package com.reviews.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.reviews.app.repositories.ReviewRepository;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

	@Autowired
	ReviewRepository reviewRepository;

	@GetMapping("/outputTableEntry")
	public List<OutputTable> getAllOutputTable() {
		List<OutputTable>  list = reviewRepository.findAll();
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
		outputTableEntry.setCategory(outputTable.getCategory());
		outputTableEntry.setProperty(outputTable.getProperty());
		outputTableEntry.setSource(outputTable.getSource());
		outputTableEntry.setSentiment_y(outputTable.getSentiment_y());
		OutputTable updatedOutputTableEntry = reviewRepository.save(outputTableEntry);
		return new ResponseEntity<>(updatedOutputTableEntry, HttpStatus.OK);
	}

	@DeleteMapping(value = "/outputTableEntry/{id}")
	public void deleteTodo(@PathVariable("id") String id) {
		reviewRepository.delete(id);
	}
}