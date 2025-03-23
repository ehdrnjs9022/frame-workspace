package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kh.spring.member.model.dao.MemberDAO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordEncoder {

	private final BCryptPasswordEncoder passwordEncoder;
		
	public String encode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);

	}


	public boolean matches(String rawPassword, String encodePassword) {
		return passwordEncoder.matches(rawPassword, encodePassword);
	}
















}
