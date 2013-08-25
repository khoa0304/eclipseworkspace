package com.topicMe.dao;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.topicMe.model.businessdomain.AbstractTopicMeEntity;


public class TopicMeEntityListener {
	
	@PreUpdate
	@PrePersist
	public void touchTime(AbstractTopicMeEntity entity){
		entity.setModifiedTime(System.currentTimeMillis());
	}



}
