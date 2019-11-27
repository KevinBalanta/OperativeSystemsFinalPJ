package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;

public class ProcessSH implements Serializable{
	
	public ProcessSH(String name, String id) {
		// TODO Auto-generated constructor stub
	this.name = name;
	this.id = id;
	}
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
