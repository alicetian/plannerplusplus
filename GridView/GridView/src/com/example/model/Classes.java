package com.example.model;

public class Classes {

	private int id;
	private String className;
	private String description;
	private String days;
	private String time;
	
	public Classes(){
		
	}
	public Classes(int newId, String newCN, String desc, String newDays, String newTime){
		
		this.id = newId;
		this.className = newCN;
		this.description = desc;
		this.days = newDays;
		this.time = newTime;
		
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
    public String toString() {
      return id + " " + className + " " + description + " " + days + " " + time;
    }
	
}
