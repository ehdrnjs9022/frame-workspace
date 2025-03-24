package com.kh.spring.member.model.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring.exception.AuthenticationException;
import com.kh.spring.exception.PasswordNotmathchException;
import com.kh.spring.member.model.dao.MemberMapper;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service // 서비스객체로 담아서 스프링이 관리하는것?
public class MemberServiceImp implements MemberService {

    // 스프링으로 필드선언
   // private final MemberDAO memberDao;
    //private final SqlSessionTemplate sqlSession;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberValidator validator;
    private final MemberMapper memberMapper;

    @Override
    public MemberDTO login(MemberDTO member) {
        // 아이디만 일치하더라도 행의 정보를 필드에 담아옴 (암호화)

        // 1. loginMember가 null값과 동일하다면 아이디가 존재하지 않는다.
    	
    	//MemberDTO loginMember = memberMapper.login(memberMapper, member);
    	
       // if (loginMember == null) {
       //     throw new MemberNotFoundException("존재하지 않는 아이디입니다.");
        //}
        // 2. 아이디만 가지고 조회를 하기 떄문에 비밀번호 검증 과정 필요
        // 비밀번호 검증 후 
        // 비밀번호가 유효하다면 회원의 정보를 session에 담기
        // 유효하지 않다면 비밀번호 이상하다고 응답
    	validator.validatedLoginMember(member);
    	System.out.println(passwordEncoder.encode("1234"));
    	MemberDTO loginMember = validator.validateMemberExists(member);
        if (passwordEncoder.matches(member.getMemberPw(), loginMember.getMemberPw())) {
            return loginMember;
        } else {
            throw new PasswordNotmathchException("비밀번호가 일치하지 않습니다");
        }
    }

    //----------------------------------------------------------------------------

    @Override
    public void signUp(MemberDTO member) {
        // 유효성 검사
        /*
    	if (member == null ||
                member.getMemberId() == null ||
                member.getMemberId().trim().isEmpty() ||
                member.getMemberPw() == null ||
                member.getMemberPw().trim().isEmpty()) {
            throw new InvalidParameterException("아이디나 비밀번호가 비어있습니다.");
        }
         */
    	
    		//validator.validatedLoginMember(member);
    	
    		//memberMapper.login(member);
    		

        // 평문 비밀번호를 암호문으로 바꿔서 저장
        /*
        log.info("사용자가 입력한 비밀번호 평문 : {}", member.getMemberPw());
        String encPwd = passwordEncoder.encode(member.getMemberPw());
        member.setMemberPw(encPwd);
	    */
       
        // 회원가입 처리
    	validator.validatedJoinMember(member);
    	member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
    	memberMapper.signUp(member);
        /*if (consequence > 0) {
            log.info("회원가입 성공: {}", member.getMemberId());
        } else {
            throw new RuntimeException("회원가입에 실패했습니다.");
        }*/
    }

    //----------------------------------------------------------------------------
    
    @Override
    public void update(MemberDTO member, HttpSession session) {
       MemberDTO sessionMember = ((MemberDTO)session
   			.getAttribute("loginMember"));
    	 
    	//사용자 검증
    	if(member.getMemberId()
    			.equals(sessionMember.getMemberId())){
    		throw new AuthenticationException("권한없는 접근입니다.");
    		// 예외발생시킬떄 런타임익셉션 자식들이여야함
    	}
    	
    	// 존재하는 아이디인지 검증
    	validator.validateMemberExists(member);
    	int result = memberMapper.update(member);
    	//SQL문 수행 결과 검증
    	if(result !=1) {
    		throw new AuthenticationException("문제가 일어났어요. 다시 시도해주세요");
    	}
    	
    	sessionMember.setMemberName(member.getMemberName());
    	sessionMember.setEmail(member.getEmail());
    
    
    }

    
    @Override
    public int delete(MemberDTO member) {
        // TODO Auto-generated method stub
        return 0;


    
    
    }

    @Override
    public String idCheck(String memberId) {
    	
    	String result = memberMapper.idCheck(memberId) !=null ? "NNNNY" : "NNNNN";
    	
    	return null;
    }

	@Override
	public String idCheck(MemberDTO member) {
		// TODO Auto-generated method stub
		return null;
	}



}







