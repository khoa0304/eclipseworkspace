package com.topicMe.businessdomain.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topicMe.dao.AbstractBackendDAO;
import com.topicMe.model.businessdomain.Board;
import com.topicMe.model.businessdomain.Topic;

@Service("boardDAOImpl")
public class BoardDAOImpl extends AbstractBackendDAO implements BoardDAO {

	@Autowired
	protected TopicMeBackendDAO<Board, Long> dao;


	public Board findBoard(Class<Board> board,Long id) {
		Board myBoard = dao.findEntity(board,id);
		return myBoard;
	}

	public Board persistBoard(Board board) {
		return dao.persistEntity(board);
	}

    public void deleteBoard(Class<Board> board,Long id) {
    	Board myBoard = dao.findEntity(board,id);
		deleteBoard(myBoard);
	}
    
    public void deleteBoard(Board board){
    	dao.deleteEntity(board);
    }

    @Transactional(readOnly=true)
	public List<Topic> getAllTopicsList(Board board){
    	Query query = getEntityManager().createNamedQuery(Board.GET_ALLTOPICS_BY_BOARDID);  
        query.setParameter("id", board.getId());         
        final List<Topic> list =  (List<Topic>) query.getResultList();     
    	return list;
	}
	
    @Transactional(readOnly=true)
    public long getTotalTopicByBoard(Board board){
    	Query query  = getEntityManager().createNamedQuery(Board.GET_TOTALTOPICS_BY_BOARDID);
    	query.setParameter("id", board.getId());
    	Long totalTopic = (Long) query.getSingleResult();
        return totalTopic.intValue();
    }
    
    @Transactional(readOnly = true)
    public List<Long> getAllBoardIds(){
    	Query query = getEntityManager().createNamedQuery(Board.GET_ALLBOARD_IDS);  
        final List<Long> list =  (List<Long>) query.getResultList();     
    	return list;
    }
    
}
