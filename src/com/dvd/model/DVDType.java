package com.dvd.model;

import java.io.Serializable;

public class DVDType implements Serializable{
	private String id;
	private String typeName;
	private String days;
	private String fk;
	public DVDType(String id,String typeName,String days,String fk){
		this.id=id;
		this.typeName=typeName;
		this.days=days;
		this.fk=fk;
					
		}
	
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
