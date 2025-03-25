package com.kh.spring.api.home.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class HomeService {

	public String requestInfectionApi() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.safetydata.go.kr//V2/api/DSSP-IF-10840");
		sb.append("?serviceKey=XWH3PQQK4WI16PE5");
		
		
		URL url = new URL(sb.toString());
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET"); //get방식으로 보내겠다 준비
		
		connection.connect(); // 연결구멍
		
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String responseDate = br.readLine();
		
		br.close();
		is.close();
		connection.disconnect();
		
		return responseDate;
	}
	
	
	
}
