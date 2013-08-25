package com.topicMe.businessdomain.dao;

import java.util.List;

import com.topicMe.model.businessdomain.Board;
import com.topicMe.model.businessdomain.Topic;

public interface BoardDAO {

	Board findBoard(Class<Board> board,Long id);
	Board persistBoard(Board board);
	void deleteBoard(Class<Board> board,Long id);
	void deleteBoard(Board board);
	List<Topic> getAllTopicsList(Board board);
    long getTotalTopicByBoard(Board board);
    List<Long> getAllBoardIds();
}
