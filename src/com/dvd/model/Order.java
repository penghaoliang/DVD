package com.dvd.model;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable{
	private String ISBN;
	private Date date;
	private String number;
	private String checkAndAccept;
	private String zk;//уш©ш
	public Order(String ISBN,Date date,String number,String checkAndAccept,String zk){
		this.ISBN=ISBN;
		this.date=date;
		this.number=number;
		this.checkAndAccept=checkAndAccept;
		this.zk=zk;
		
	}
	public String getCheckAndAccept() {
		return checkAndAccept;
	}
	public void setCheckAndAccept(String checkAndAccept) {
		this.checkAndAccept = checkAndAccept;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String isbn) {
		ISBN = isbn;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getZk() {
		return zk;
	}
	public void setZk(String zk) {
		this.zk = zk;
	}
	
}
