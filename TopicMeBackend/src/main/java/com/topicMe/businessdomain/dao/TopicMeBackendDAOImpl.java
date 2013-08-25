package com.topicMe.businessdomain.dao;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topicMe.dao.AbstractBackendDAO;
import com.topicMe.model.businessdomain.AbstractTopicMeEntity;


@Service
public class TopicMeBackendDAOImpl <T extends AbstractTopicMeEntity, I extends Serializable> 
                   extends AbstractBackendDAO implements TopicMeBackendDAO<T, I>{

     
    @Transactional(readOnly=true)
    public T findEntity(Class<T> obj, I id) {
        return (T) getEntityManager().find(obj,id);
    }

    @Transactional
    public void deleteEntity(T obj) {
    	getEntityManager().remove(obj);
    }

    @Transactional
    public T persistEntity(T obj) {
	   getEntityManager().persist(obj);
       return obj;
    }
    
    @Transactional
    public T updateEntity(T obj){
    	return getEntityManager().merge(obj);
    }
}
