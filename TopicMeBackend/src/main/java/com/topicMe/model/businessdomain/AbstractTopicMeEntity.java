/**
 * 
 */
package com.topicMe.model.businessdomain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.topicMe.dao.TopicMeEntityListener;

/**
 * @author Khoa
 *
 */
@MappedSuperclass
@EntityListeners({TopicMeEntityListener.class})
public abstract class AbstractTopicMeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private long createdTime;
	
	private long modifiedTime;

	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = System.currentTimeMillis();
	}

	@Column
	public long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	} 

	
	
}
