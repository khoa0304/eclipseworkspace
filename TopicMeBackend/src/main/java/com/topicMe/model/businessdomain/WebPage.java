package com.topicMe.model.businessdomain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
public class WebPage extends AbstractTopicMeEntity {

	@Transient
	private static final long serialVersionUID = -4882240419704191248L;
	
	private String content;
	private int totalHighlight;
	private int totalVisit;
	private Topic topic;
	private List<Highlights> highlights = new ArrayList<Highlights>();
	
	
	@Lob
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column
	public int getTotalHighlight() {
		return totalHighlight;
	}
	public void setTotalHighlight(int totalHighlight) {
		this.totalHighlight = totalHighlight;
	}
	@Column
	public int getTotalVisit() {
		return totalVisit;
	}
	public void setTotalVisit(int totalVisit) {
		this.totalVisit = totalVisit;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="webPage",cascade={CascadeType.REMOVE})
    public List<Highlights> getHighlights() {
		return highlights;
	}
	public void setHighlights(List<Highlights> highlights) {
		this.highlights = highlights;
	}
	
	/**
	 * @return the category
	 */
	@ManyToOne
	public Topic getTopic() {
		return topic;
	}
	/**
	 * @param category the category to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
