package com.kh.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	// 의존성 객체 주입 받기
	
	private final BoardService boardService;
	
	@GetMapping("boards")
	public String toBoardList() {
		return "board/board_list";
	}
	
	
	@GetMapping("form.bo")
	public String goToForm() {
		return "board/insert_board";
	}
	
	
	
	
	
	
	
}
