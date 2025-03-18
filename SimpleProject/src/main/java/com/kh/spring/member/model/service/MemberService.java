package com.kh.spring.member.model.service;

import com.kh.spring.member.model.dto.MemberDTO;

public interface MemberService {
	
	MemberDTO login(MemberDTO member);

	
	MemberDTO signUp(MemberDTO member);
	
	
	
	MemberDTO update(MemberDTO member);
	
	int delete(MemberDTO member);
	
}

