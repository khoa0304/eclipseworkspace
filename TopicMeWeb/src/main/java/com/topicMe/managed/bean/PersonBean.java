package com.topicMe.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


@ManagedBean(name = "personBean")
@RequestScoped
public class PersonBean {
private String firstname;
	
	private String surname;

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void savePerson(ActionEvent actionEvent) {
		System.out.println("Save person got called");
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + firstname + " " + surname + "!"));
	}
}
