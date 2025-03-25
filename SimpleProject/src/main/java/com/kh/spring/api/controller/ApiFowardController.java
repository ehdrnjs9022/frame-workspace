package com.kh.spring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiFowardController {

	@GetMapping("hospital")
	public String hopstialPage() {
			return "api/api";
		
	}
	
	@GetMapping("sms")
	public String smsPage() {
		return "api/simple-message";
	}
	
	@GetMapping("map")
	public String mapPage() {
		return "api/map";
	}
	
}
