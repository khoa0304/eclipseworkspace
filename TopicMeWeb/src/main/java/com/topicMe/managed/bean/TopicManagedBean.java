package com.topicMe.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "topicMB")
@SessionScoped
public class TopicManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
  
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
