package com.dvd.model;

import java.io.Serializable;

import com.sun.jmx.snmp.Timestamp;

public class Borrow implements Serializable{
	private int id;
	private String dvdISBN;
	private String readerISBN;
	private String num;
	private String borrowDate;
	private String backDate;
	private String readerName;
	private String dvdName;
	private int typeId;
	public Borrow(String dvdISBN,String readerISBN,java.sql.Timestamp borrowDate,java.sql.Timestamp backDate
			,String dvdName,String readerName,int typeId){
		this.dvdISBN=dvdISBN;
		this.readerISBN=readerISBN;
		this.borrowDate=borrowDate.toString();
		this.backDate=backDate.toString();
		this.dvdName=dvdName;
		this.readerName=readerName;
		this.typeId=typeId;
		
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getDVDName() {
		return dvdName;
	}
	public String getDVDname() {
		return dvdName;
	}
	public void setDVDName(String bookName) {
		this.dvdName = bookName;
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
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getReaderISBN() {
		return readerISBN;
	}
	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
