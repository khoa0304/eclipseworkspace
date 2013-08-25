package com.topicMe.businessdomain.dao;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.topicMe.model.businessdomain.Board;
import com.topicMe.model.businessdomain.Topic;
import com.topicMe.test.util.AbstractBackendTest;

@Test(suiteName = "BoardDAO")
public class BoardDAOImplTest extends AbstractBackendTest {

	BoardDAO boardDAO;
	TopicDAO topicDAO;
	
	@BeforeClass
	public void init() {
		boardDAO = (BoardDAO) context.getBean(BoardDAO.class);
		assert boardDAO != null;
		
		topicDAO = (TopicDAO) context.getBean(TopicDAO.class);
		assert topicDAO != null;
	}

	public void testPersistBoard() {
		Board board = new Board();
		board.setName("Java");
		board.setBoardCategory("Technology");
		board.setPrivateMode(false);
		boardDAO.persistBoard(board);
		assert board.getId() > 0;
		assert board.getCreatedTime() > 0;
		assert board.getName().equals("Java");
		assert board.getBoardCategory().equals("Technology");
		assert board.isPrivateMode() == false;
		assert board.getModifiedTime() > 0;
		List<Topic> topicList = board.getTopicList();
		assert topicList.size() == 0;
	}

	public void testPersistBoardWithTopic() {

		Board board = new Board();
		board.setName("Java");
		board.setBoardCategory("Technology");
		board.setPrivateMode(true);
		Topic topic = new Topic();
		topic.setCategoryName("Java and Hibernate Technology");
		topic.setName("Java and Hibernate Integration");
		board.addTopic(topic);
		topic.setBoard(board);
		board = boardDAO.persistBoard(board);
		Board board2 = boardDAO.findBoard(Board.class, board.getId());
		List<Topic> topicList = boardDAO.getAllTopicsList(board2);

		assert topicList.size() == 1;

		Topic topic2 = topicList.get(0);

		assert topic2.getModifiedTime() > 0;
		assert boardDAO.getTotalTopicByBoard(board2) == 1;
		assert board2.getTotalFollowers() == 0;
	}

	
	public void testMultipleTopicsWithSameBoard() {

		Board board = new Board();
		board.setName("Java");
		board.setBoardCategory("Technology");
		board.setPrivateMode(true);
		board = boardDAO.persistBoard(board);
		Board board2 = boardDAO.findBoard(Board.class, board.getId());
		
		assert board2.getName().equals("Java");
		Topic topic = new Topic();
		topic.setCategoryName("Java and Hibernate Technology");
		topic.setName("Java and Hibernate Integration");
		topic.setBoard(board);
		
		topicDAO.persistTopic(topic);
		long totalTopic = boardDAO.getTotalTopicByBoard(board2);
		
		assert totalTopic == 1;
		
		Topic topic2 = new Topic();
		topic2.setCategoryName("Java and Hibernate Technology");
		topic2.setName("Java and Hibernate Integration");
		topic2.setBoard(board);
		topicDAO.persistTopic(topic2);
		
        totalTopic = boardDAO.getTotalTopicByBoard(board2);
		
		assert totalTopic == 2;
		
		
		Topic topic3 = topicDAO.findTopic(topic);
		assert topic3.getId() > 0 ;
		assert topic3.getModifiedTime() > 0 ; 
		assert topic3.getCreatedTime() > 0;
		
		
		topic3 = topicDAO.findTopic(topic2);
		assert topic3.getId() > 0 ;
		assert topic3.getModifiedTime() > 0 ; 
		assert topic3.getCreatedTime() > 0;
		
		Board board3 = topic3.getBoard();
		
		assert board3.getId() == board2.getId();
		
		
	
	}

}
