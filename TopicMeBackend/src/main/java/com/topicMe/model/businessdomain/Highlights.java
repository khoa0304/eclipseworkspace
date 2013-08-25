/**
 * 
 */
package com.topicMe.model.businessdomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * @author Khoa
 *
 */
@Entity
@Table(name="Highlights")
public class Highlights extends AbstractTopicMeEntity{

	@Transient
	private static final long serialVersionUID = -4620829000331041495L;
	
	private String highlightedText;
	private WebPage webPage;

	
	@Column(nullable=false)
	public String getHighlightedText() {
		return highlightedText;
	}
	public void setHighlightedText(String highlightedText) {
		this.highlightedText = highlightedText;
	}
	
	@ManyToOne
	//@JoinColumn(name="pageId")
	public WebPage getWebPage() {
		return webPage;
	}
	public void setWebPage(WebPage webPage) {
		this.webPage = webPage;
	}
	

}
