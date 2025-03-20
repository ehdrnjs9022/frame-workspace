package com.kh.spring.board.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// ValueObject == 불변성

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BoardDTO {
	private int boardNo;
	private String boardTitle; 
	private String boardContent;
	private String boardWriter;
	private int	count;
	private String createDate;
	private String ahangeName;
	private String status;

	



}
