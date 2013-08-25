package com.topicMe.businessdomain.dao;

import com.topicMe.model.businessdomain.Topic;

public interface TopicDAO {
	
	public void persistTopic(Topic topic);
	public Topic findTopic(Topic topic);

}
