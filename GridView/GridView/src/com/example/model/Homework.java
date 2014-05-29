package com.example.model;

public class Homework {
	
	private int id;
	private String className;
	private String description;
	private String time;
	public Homework(){
		
	}
	public Homework(int id, String cN, String desc, String nTime){
		
		this.id = id;
		this.className = cN;
		this.description = desc;
		this.time = nTime;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
