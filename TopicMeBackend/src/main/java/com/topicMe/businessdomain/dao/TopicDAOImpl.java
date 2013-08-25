package com.topicMe.businessdomain.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topicMe.dao.AbstractBackendDAO;
import com.topicMe.model.businessdomain.Topic;

@Service
public class TopicDAOImpl extends AbstractBackendDAO implements TopicDAO{
	
	@Autowired
	protected TopicMeBackendDAO<Topic, Long> dao;

	@Transactional
	public void persistTopic(Topic topic){
		dao.persistEntity(topic);
	}
	
	@Transactional
	public Topic findTopic(Topic topic){
		return  (Topic) dao.findEntity(Topic.class, topic.getId());
	}

}
