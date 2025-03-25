package com.kh.spring.api.home.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.api.home.model.service.HomeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeSafetyController {

	private final HomeService homeService;

	@GetMapping(value= "infection", produces="application/jason; charset=UTF-8")
	public String requestInfectionApi() throws IOException {
		String responseDate = homeService.requestInfectionApi();
		 return responseDate;
	}
	
	
	
}
