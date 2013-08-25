package com.topicMe.businessdomain.dao;

import java.io.Serializable;

import com.topicMe.model.businessdomain.AbstractTopicMeEntity;


public interface TopicMeBackendDAO <T extends AbstractTopicMeEntity, I extends Serializable>{
	
	public T findEntity(Class<T> obj, I id);
    public void deleteEntity(T obj);
    public T persistEntity(T obj);
    public T updateEntity(T obj);
}
