package com.kh.spring.member.model.service;

import java.security.InvalidParameterException;

import org.springframework.stereotype.Component;

import com.kh.spring.exception.TooLageValueException;
import com.kh.spring.member.model.dto.MemberDTO;



@Component
public class MemberValidator {

	private void valdatedLength(MemberDTO member) {
		if(member.getMemberId().length() > 10) {
			throw new TooLageValueException("아이디값이 너무 깁니다. 10자 이내로 작성");
		}
	}
	private void validatedValue(MemberDTO member) {
		if(member == null || 
				member.getMemberId() == null||
				member.getMemberId().trim().isEmpty()||
				member.getMemberPw() == null ||
				member.getMemberPw().trim().isEmpty()) {
			throw new InvalidParameterException("유효하지 않은 입력값 입니다");
		}
	}
	public void validatedLoginMember(MemberDTO member){
	
		valdatedLength(member);
		validatedValue(member);
		
	}
	
	
}
