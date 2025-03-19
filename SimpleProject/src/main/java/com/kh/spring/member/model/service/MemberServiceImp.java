package com.kh.spring.member.model.service;

import java.security.InvalidParameterException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring.exception.MemberNotFoundException;
import com.kh.spring.exception.PasswordNotmathchException;
import com.kh.spring.exception.TooLageValueException;
import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service // 서비스객체로 담아서 스프링이 관리하는것?
public class MemberServiceImp implements MemberService {

	// 스프링으로 필드선언
	@Autowired
	private final MemberDAO memberDao;
	@Autowired
	private final SqlSessionTemplate sqlSession;
	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberValidator validator;
	/*
	 * @Autowired 
	 * public MemberServiceImp(MemberDAO memberDao, SqlSessionTemplate
	 * sqlSession) { 
	 * this.memberDao = memberDao; 
	 * this.sqlSession = sqlSession; 
	 * }
	 */
	

	
	@Override
	public MemberDTO login(MemberDTO member) {
		
		validator.validatedLoginMember(member);
		}
		/*	로그인이라는 요청을 처리해줘야함
		 * 1. Table에 아이디가 존재해야겠다.
		 * 2. 비밀번호가 일치해야겠다.
		 * 3. 둘다 통과면 정상적으로 조회할 수 있도록 해주어야겠다.
		 * 
		 * 	SqlSession sqlSession = getSqlSession();
		 * 	MemberDTO member = new MemberDAO().login(sqlSession,member);
		 * 	sqlSession.close();
		 * 	return loginMember;
		 * 
		 * 아이디 10자가 넘으면 안되겠네?
		 * 아이디/비밀번호가 : 빈문자열 또는 null이면 안되겠네?
		 */
		
		MemberDTO loginMember = memberDao.login(sqlSession, member);
		// 아이디만 일치하더라도 행의 정보를 필드에 담아옴 (암호화)
		
		// 1. loginMember가 null값과 동일하다면 아이디가 존재하지 않는다.
		if(loginMember == null) {
			throw new MemberNotFoundException("존재하지 않는 아이디입니다.");
		}
		
		// 2. 아이디만 가지고 조회를 하기 떄문에 비밀번호 검증 과정 필요
		// 비밀번호 검증 후 
		// 비밀번호가 유효하다면 회원의 정보를 session에 담기
		// 유효하지않다면 비밀번호 이상하다고 응답
		if(passwordEncoder.matches(member.getMemberPw(), loginMember.getMemberPw())) {
				return loginMember;
		} else {
			throw new PasswordNotmathchException("비밀번호가 일치하지 않습니다");
		}
		
		//return loginMember;
	
	}
	
	@Override
	public void signUp(MemberDTO member) {
		if(member == null || 
				member.getMemberId() == null||
				member.getMemberId().trim().isEmpty()||
				member.getMemberPw() == null ||
				member.getMemberPw().trim().isEmpty()) {
			
		}
		
	int result = memberDao.checkId(sqlSession, member.getMemberId());
		
	if(result > 0 ) 
		return ;
	
	log.info("사용자가 입력한 비밀번호 평문 : {} " , member.getMemberId());
	// 암호화하는법 .encode()호출
	log.info("평문을 암호문으로 바꾼것 : {}" , passwordEncoder.encode(member.getMemberId()));
	
	// INSERT 해야지~
	String encPwd = passwordEncoder.encode(member.getMemberPw());
	member.setMemberPw(encPwd);
	int consequence = memberDao.signUp(sqlSession,member);
	
	if(consequence > 0) {
		return ;
	} else {
		return ;
	}
	
	//memberDao.singup
		
	}

	@Override
	public MemberDTO update(MemberDTO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(MemberDTO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
