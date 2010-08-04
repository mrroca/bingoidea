package com.nurse.service;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {

	private static final long serialVersionUID = 2208127802105835746L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() {
		name = "Hello , " + name + " !";
		return SUCCESS;
	}

}
