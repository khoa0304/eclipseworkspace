package com.topicMe.model.businessdomain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table
public class Topic extends AbstractTopicMeEntity {

	
	private static final long serialVersionUID = -385617521337644887L;
	private String name;
	private int totalPages;
	private String categoryName;
	private List<WebPage> webPageList = new ArrayList<WebPage>();
	private Board board;
	
	/**
	 * @return the totalPages
	 */
	@Column
	public int getTotalPages() {
		return totalPages;
	}
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * @return the categoryName
	 */
	@Column(length=100,nullable=true)
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the webPages
	 */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="topic",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	public List<WebPage> getWebPageList() {
		return webPageList;
	}
	/**
	 * @param webPages the webPages to set
	 */
	public void setWebPageList(List<WebPage> webPageList) {
		this.webPageList = webPageList;
	}
	
	@ManyToOne
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@PrePersist
	@PreUpdate
	public void prePersist(){
		setTotalPages(getWebPageList().size());
	}
}
