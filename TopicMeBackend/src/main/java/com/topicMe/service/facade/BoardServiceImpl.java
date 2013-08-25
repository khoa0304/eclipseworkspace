package com.topicMe.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topicMe.businessdomain.dao.BoardDAO;
import com.topicMe.model.businessdomain.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
    BoardDAO boardDAO;
	
	public Board addBoard(Board board){
		boardDAO.persistBoard(board);
	    return board;
	}
	
}
