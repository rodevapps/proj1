package com.example.springapp.controller;

import java.util.List;
import java.util.Collections;
import java.lang.reflect.Method;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springapp.model.Test;
import com.example.springapp.repository.TestRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class TestApiController {
	@Autowired
	TestRepository testRepository;

	@GetMapping("/tests/{type}/{column}")
	public ResponseEntity<List<Test>> getReccurrentByColumn(@PathVariable("column") String column, @PathVariable("type") String type) {
		String capitalized = column.substring(0, 1).toUpperCase() + column.substring(1);
		String capitalized2 = type.substring(0, 1).toUpperCase() + type.substring(1);

		try {
  			Method method = testRepository.getClass().getMethod("find" + capitalized2 + "By" + capitalized);

			List<Test> testData = (List<Test>) method.invoke(testRepository);

			System.out.println(testData);

			if (testData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(testData, HttpStatus.OK);
  		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
