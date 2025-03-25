package com.kh.spring.api.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeFowardController {

	
	@GetMapping("infection")
	public String heart() {
		
		return "api/home"; 
	}
	 
}


