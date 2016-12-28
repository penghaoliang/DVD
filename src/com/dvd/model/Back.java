package com.dvd.model;

import java.io.Serializable;

public class Back implements Serializable{
	private String dvdISBN;
	private String dvdname;
	private String borrowDate;
	private String backDate;
	private String readerName;
	private String readerISBN;
	private int typeId;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public String getDVDISBN() {
		return dvdISBN;
	}
	public void setDVDISBN(String bookISBN) {
		this.dvdISBN = bookISBN;
	}
	public String getDVDname() {
		return dvdname;
	}
	public void setDVDname(String bookname) {
		this.dvdname = bookname;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	
	public String getReaderISBN() {
		return readerISBN;
	}
	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
}
