package com.kh.spring.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
// 의존성주입 생손자를 생성해주는 애노테이션 //롬복
public class  MemberController {
	
	//@Autowired
	private final MemberService memberService; 
	 // 필드로 타입만 선언해놓고 스프링 bean 이 객체를 주입시키는 애노테이션작성
	
	
	// setter를 만들어서 하는 방법 2번
		 /* @Autowired
		 *  public void setMemberService(memberService memberService) {
		 * this.memberService = memberService; }
		 */
	
	// 생성자를 만들어서 하는 방법 (권장) 3번
	/*
	 * @Autowired public MemberController(MemberService memberService) {
	 * this.memberService = memberService; }
	 */
	 
	
	/*
	 * @RequestMapping(value="login") public String login(HttpServletRequest
	 * request) { System.out.println("나는 로그인 요청오면 충돌함"); String id =
	 * request.getParameter("id"); String pw = request.getParameter("pw");
	 * //System.out.printf("id : %s, pw: %s", id, pw); log.info("id : {}, pw : {}",
	 * id,pw); return "main_page"; }
	 */
	
	/*
	 * @RequestMapping("login") public String login(@RequestParam(value="id"
	 * defaultValue="abvde") String id,
	 * 
	 * @RequestParam(value="pw" )String pw) {
	 * log.info("이렇게도 넘어오나요? id : {} /pd : p{}", id,pw); return "main_page"; }
	 */

	
	/*
	 * @PostMapping("login") public String login() {
	 * 
	 * log.info("넘어오나 id:{} / pw: {}", id,pw);
	 * 
	 * MemberDTO member = new MemberDTO(); member.setMemberId(id);
	 * member.setMemberPw(pw);
	 * 
	 * 
	 * return "main_page"; }
	 * 
	 */
	
	/** 커맨드 객체 방식
	 * 
	 * 1. 매개변수 자료형에 반드시 기본생성자가 존재할 것
	 * 2. 전달되는 킥밧과 객체의 필드명이 동일할 것
	 * 3. setter메서드가 반드시 존재할 것
	 * 
	 * 스프링에서 해당 객체를 기본생성자를 통해서 생성한 후 내부적으로 setter메서드를 찾아서
	 * 요청 시 전달값을 해당 필드에 대입해줌
	 * (Setter Injection)
	 */
	
	
	/*
	 * @PostMapping("login") public String login(MemberDTO member, HttpSession
	 * session, Model model) {
	 */
		
		//log.info("이건안돼 {}", member);
	
		/* 데이터가공 => 스프링이 대신해줌 패스
		 * 요청처리  => 
		 * 응답화면지정
		 * 
		 */
		//MemberDTO loginMember = memberService.login(member);
		/*
		 * if(loginMember != null) { log.info("로그인 성공~");
		 * 
		 * }else { log.info("로그인실패"); }
		 */
		
		/*if(loginMember != null) { // 성공했을때
			// sessionScope에 로그인정보를 담아줌
			session.setAttribute("login", member);
			// 그다음에 main 페이지로 이동
			// /WEB-INF/views/
			// .jps
			// 포워딩 => X
			// sendRedirect
			
			// localhost/spring  / 
			
			return "redirect:/";
			
		} else { // 실패했을떄
			
		}	// error_page;
			// requestScope에 에러문구를 담아서 포워딩
			// spring에서는 Model 객체를 이용해서 RequestScope에 값을 담음
			model.addAttribute("message","로그인실패!");
			
			// forwarding
			// /WEB-INF/views/
			// include/error_page
			// .jsp
		
			return "include/error_page";
			
	//	return "main_page";
	
	}*/
	//}
	// 두 번째 방법 반환타입 ModelandView로 들어가기	
		
	
	@PostMapping("login")
	public ModelAndView login(MemberDTO member,
								HttpSession session,
								ModelAndView mv) {
		
		MemberDTO loginMember = memberService.login(member);
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("message", "로그인실패!")
				.setViewName("include/error_page");
		}
		
		return mv;
	}
	// jsp a태그 -> get
	// 로그아웃은 세션에값이 있으니 세션으로받음
	@GetMapping("logout")	
	public ModelAndView logout(HttpSession session,
								ModelAndView mv) {
		
		session.removeAttribute("loginMember");
		mv.setViewName("redirect:/");
		return mv;
	}	
		
	@GetMapping("signup-form")
	public String signupForm() {
		// WEB-INF/views/    member/signup-form     .jsp
		return"member/signup-form";
		
	}
	
	/**
	 * @param member id~
	 *
	 *@return ember 성공시 main~ 실패하면 err담아서 errpage~
	 */
	
	// 새로운값 post -> 인코딩
	@PostMapping("signup")
	public String join(MemberDTO member) {
			
		/*
		 * try { request.setCharacterEncoding("UTF-8"); }
		 *  catch
		 * (UnsupportedEncodingException e) 
		 * { e.printStackTrace(); }
		 */
		
		//log.info("멤버 필드 찍어보자 : {} ", member);
		
		memberService.signUp(member);
		return "main_page";
		
		
		
		
		
		
	}
	
}
