package com.example.springapp.controller;

import java.util.List;
import java.util.Collections;
import java.lang.reflect.Method;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springapp.model.Test;
import com.example.springapp.repository.TestRepository;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class TestController {
    @Autowired
    TestRepository testRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/")
	public String index(@RequestParam(name = "column", required=true) String column, @RequestParam(name = "type", required=true) String type, Model model) {
		String capitalized = column.substring(0, 1).toUpperCase() + column.substring(1);
		String capitalized2 = type.substring(0, 1).toUpperCase() + type.substring(1);

		try {
  			Method method = testRepository.getClass().getMethod("find" + capitalized2 + "By" + capitalized);

  			List<Test> testData = (List<Test>) method.invoke(testRepository);

			System.out.println(testData);

    	    if (!testData.isEmpty()) {
				model.addAttribute("rows", testData);
        	}
  		} catch (Exception e) {
			  System.out.println(e);
		}

		return "index";
	}
}
