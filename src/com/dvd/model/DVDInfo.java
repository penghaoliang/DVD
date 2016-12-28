package com.dvd.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DVDInfo implements Serializable {
	private String ISBN;
	private String typeid;
	private String writer;
	private String translator;
	private String publisher;
	private Date date;
	private Double price;
	private String dvdname;
	
public DVDInfo(String ISBN,String typeid,String writer,String translator,String publisher,Date date,Double price,String dvdname){

this.ISBN=ISBN;
this.typeid=typeid;
this.writer=writer;
this.translator=translator;
this.publisher=publisher;
this.date=date;
this.price=price;
this.dvdname=dvdname;
	
	
}
	public DVDInfo() {
	
}
	public String getDVDname() {
		return dvdname;
	}
	public void setDVDname(String bookname) {
		this.dvdname = bookname;
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

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
}
