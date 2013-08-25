package com.topicMe.model.businessdomain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
@NamedQuery(name="getAllTopicsByBoardId", query="FROM Topic WHERE board_id = :id"),
@NamedQuery(name="getTotalTopicsByBoardId", query="SELECT COUNT(id) FROM Topic WHERE board_id = :id"),
@NamedQuery(name="getAllBoardIds", query="SELECT id FROM Board")

})
public class Board extends AbstractTopicMeEntity {

	public static final String GET_ALLTOPICS_BY_BOARDID ="getAllTopicsByBoardId";
	public static final String GET_TOTALTOPICS_BY_BOARDID ="getTotalTopicsByBoardId";
	public static final String GET_ALLBOARD_IDS = "getAllBoardIds";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3719838444916622243L;
	/**
	 * 
	 */
	private String name;
	private String description;
	private long totalFollowers = 0l;
	private boolean privateMode = true;
	private int totalTopics = 0;
	private String boardCategory;
	private List<Topic> topicList = new ArrayList<Topic>();
	
	@Column(length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 200, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column
	public long getTotalFollowers() {
		return totalFollowers;
	}

	public void setTotalFollowers(long totalFollowers) {
		this.totalFollowers = totalFollowers;
	}

	@Column(nullable = false)
	public boolean isPrivateMode() {
		return privateMode;
	}

	public void setPrivateMode(boolean privateMode) {
		this.privateMode = privateMode;
	}

	@Column
	public int getTotalTopics() {
		return totalTopics;
	}

	public void setTotalTopics(int totalTopics) {
		this.totalTopics = totalTopics;
	}

	@Column(length = 100, nullable = true)
	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "board")
	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public void addTopic(Topic topic){
		this.topicList.add(topic);
	}
}
