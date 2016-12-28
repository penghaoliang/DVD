package com.dvd.dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.dvd.model.Back;
import com.dvd.model.Borrow;
import com.dvd.model.DVDInfo;
import com.dvd.model.DVDType;
import com.dvd.model.Order;
import com.dvd.model.OrderAndDVDInfo;
import com.dvd.model.Reader;
import com.sun.jmx.snmp.Timestamp;

public class Dao {
	 static ArrayList<DVDInfo> al = new ArrayList<DVDInfo>();
	 static ArrayList<DVDType> bl= new ArrayList<DVDType>();
	 static ArrayList<Borrow> cl= new ArrayList<Borrow>();
	 static ArrayList<Reader> dl= new ArrayList<Reader>();
	 static ArrayList<Order> el= new ArrayList<Order>();
	BufferedReader bin;
	static File file=null;
	static File file1=null;
	static File file2=null;
	static File file3=null;
	static File file4=null;
	static ObjectInputStream fin=null;
	ObjectOutputStream fout=null;
	static int position;
	static DVDInfo firstProduct;
	static DVDInfo tmpProduct;
	static DVDType firstType;
	static DVDType tmpType;
	static Borrow firstBorrow;
	static Borrow tmpBorrow;
	static Reader firstReader;
	static Reader tmpReader;
	static Order firstOrder;
	static Order tmpOrder;
	public Dao() {
		try {
			file = new File("D:\\Product.txt");
			fout = new ObjectOutputStream(new FileOutputStream(file, true));
			firstProduct = new DVDInfo("1111111111111", "122424","斯特瓦特·亨德尔","本·温切尔","大型发行商",Date.valueOf("2016-01-02"),60.0,"钢铁骑士");
			al.add(firstProduct);
			firstProduct = new DVDInfo("2222222222222", "122424","林超贤"," 张涵予","大型发行商",Date.valueOf("2016-11-02"),50.0,"湄公河行动");
			al.add(firstProduct);
			firstProduct = new DVDInfo("6666666666666", "353874","东方卫视","孟非","大型发行商",Date.valueOf("2013-06-02"),50.0,"四大名助");
			al.add(firstProduct);
			firstProduct = new DVDInfo("7777777777777", "353874","张刚","张刚","大型发行商",Date.valueOf("2015-06-02"),50.0,"JAVA教程");
			al.add(firstProduct);
			firstProduct = new DVDInfo("3333333333333", "122424","加里斯·爱德华斯","亚伦·泰勒-约翰逊","大型发行商",Date.valueOf("2014-05-07"),50.0,"哥斯拉");
			al.add(firstProduct);
			firstProduct = new DVDInfo("4444444444444", "122424","乔纳森·里贝斯曼","乔纳森·里贝斯曼","大型发行商",Date.valueOf("2012-03-07"),50.0,"诸神之怒");
			al.add(firstProduct);
			firstProduct = new DVDInfo("5555555555555", "122424","吉尔莫·德尔·托罗","查理·汉纳姆","大型发行商",Date.valueOf("2013-06-02"),50.0,"环太平洋");
			al.add(firstProduct);
			fout.writeObject(al);
			fout.flush();
			fout.close();
			
			
			file1 = new File("D:\\DVDType.txt");
			fout = new ObjectOutputStream(new FileOutputStream(file1, true));
			firstType = new DVDType("122424","电影","30","30");
			bl.add(firstType);
			firstType = new DVDType("353874","节目","40","40");
			bl.add(firstType);
			fout.writeObject(bl);
			fout.flush();
			fout.close();
			
			 file2=new File("D:\\Borrow.txt");
			 fout = new ObjectOutputStream(new FileOutputStream(file2, true));
			 
				fout.writeObject(cl);
				fout.flush();
				fout.close();
				
				
				file3 = new File("D:\\Reader.txt");
				fout = new ObjectOutputStream(new FileOutputStream(file3, true));
				firstReader = new Reader("Tom","1","20","650204199701121219",Date.valueOf("2020-01-02"),"20","18930332129",5000.0,0,"学生","1111111111111",Date.valueOf("2016-01-02"));
			
				dl.add(firstReader);
				fout.writeObject(dl);
				fout.flush();
				fout.close();
				
				 file4=new File("D:\\Order.txt");
				 fout = new ObjectOutputStream(new FileOutputStream(file4, true));
				 
					fout.writeObject(el);
					fout.flush();
					fout.close();
				
				
				
			 
				
		
	
		
		
				
			
			
			
			
			
			
			bin = new BufferedReader(new InputStreamReader(System.in));
			} catch (IOException e) {

			}
		
	}
	
	 public static  int searchStudByDVDInfo_ID(String idStr) {
		// 
		try {
		fin = new ObjectInputStream(new FileInputStream(file));
		al = (ArrayList) fin.readObject();

		fin.close();
		} catch (Exception e) {

		}
		for (position = 0; position < al.size(); position++) {
		tmpProduct = (DVDInfo) al.get(position);
		if (tmpProduct.getISBN().equalsIgnoreCase(idStr)) {
		break;
		}
		}// for
		return position;
		}

	 public static  int searchStudByDVDType_Type(String type) {
		// 
		try {
		fin = new ObjectInputStream(new FileInputStream(file1));
		bl = (ArrayList) fin.readObject();

		fin.close();
		} catch (Exception e) {

		}
		for (position = 0; position < al.size(); position++) {
			tmpType = (DVDType) bl.get(position);
		if (tmpType.getTypeName().equalsIgnoreCase(type)) {
		break;
		}
		}// for
		return position;
		}
	

	/*
	 * 查询类别方法
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<DVDType> selectDVDCategory()  {
		ArrayList<DVDType> bl=new ArrayList<DVDType>();
		try {
		ObjectInputStream fin = new ObjectInputStream(new FileInputStream("D:\\DVDType.txt"));
		bl = (ArrayList<DVDType>) fin.readObject();
		fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		return bl;
		
	}
	public static List selectDVDCategory(String bookType) {
		List list=new ArrayList();
		
		try {
			fin = new ObjectInputStream(new FileInputStream(file1));
			bl = (ArrayList) fin.readObject();

			fin.close();
			} catch (Exception e) {

			}
			for (position = 0; position < bl.size(); position++) {
				tmpType = (DVDType) bl.get(position);
			if (tmpType.getTypeName().equalsIgnoreCase(bookType)) {
			list.add(tmpType);
			}
			}
			
			
			
			
		
	
		return list;
		
	}
	/*
	 *DVD类别表相关操作
	 * 
	 */
	public static int InsertDVDType(String id,String dvdTypeName,String days,Double fk){
		int i=0;
		try{
			ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream(file1));
			firstType = new DVDType(id,dvdTypeName,days,fk.toString());
		bl.add(firstType);
		fout.writeObject(bl);
		fout.close();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
	}
	public static int UpdateDVDType(String id,String typeName,String days,String fk){
		int i=0;
		try{
			fin = new ObjectInputStream(new FileInputStream(file1));
			bl = (ArrayList) fin.readObject();

			fin.close();
			
			for (position = 0; position < bl.size(); position++) {
			tmpType = (DVDType) bl.get(position);
			if (tmpType.getId().equalsIgnoreCase(id)) {
			break;
			}
			}
			
			
			DVDType bookType=new DVDType(id,typeName,days,fk);
			bl.set(position,bookType);
			file1.delete();
			 ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream(file1));
			fout.writeObject(bl);
			fout.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
//	public static int DelbookType(String id){
//		int i=0;
//		try{
//			String sql="delete from tb_bookType where id='"+id+"'";
//			//System.out.println(sql);
//			i=Dao.executeUpdate(sql);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		Dao.close();
//		return i;
//	}
	public static List selectDVDTypeFk(String dvdType) {//取每种书超过规定时间罚款金额
		List list=new ArrayList();
		try {
			fin = new ObjectInputStream(new FileInputStream(file1));
			bl = (ArrayList) fin.readObject();

			fin.close();
			} catch (Exception e) {

			}
			for (position = 0; position < bl.size(); position++) {
				tmpType = (DVDType) bl.get(position);
			if (tmpType.getTypeName().equalsIgnoreCase(dvdType)) {
			list.add(tmpType);
			}
			}
		
		return list;
		
	}

	/*
	 * 插入DVD信息方法
	 */
	public static  int Insertdvd(String ISBN,String typeId,String dvdname,String writer,String translator,String publisher,Date date,Double price){
		int i=0;
		try{
		 ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream("D:\\Product.txt"));
			firstProduct = new DVDInfo(ISBN, typeId,writer,translator,publisher,date,price,dvdname);
		al.add(firstProduct);
		fout.writeObject(al);
		fout.close();
		}catch(Exception e){
		
		}
		
		i++;
		
		return i;
	}
	/*
	 * 查询DVD相关信息
	 * 
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static  ArrayList<DVDInfo> selectDVDInfo()  {
	
		 ArrayList<DVDInfo> al = new ArrayList<DVDInfo>();
				
		 try {
			 ObjectInputStream	fin = new ObjectInputStream(new FileInputStream("D:\\Product.txt"));
			al = (ArrayList) fin.readObject();
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		
			
		return al;
	}

	public static  List selectDVDInfo(String ISBN) {
		List list=new ArrayList();
		try {
			fin = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList) fin.readObject();

			fin.close();
			} catch (Exception e) {

			}
			for (position = 0; position < al.size(); position++) {
				tmpProduct = (DVDInfo) al.get(position);
			if (tmpProduct.getISBN().equalsIgnoreCase(ISBN)) {
			list.add(tmpProduct);
			}
			}
		return list;
	}
	public static int getDVDTypeid(String ISBN){
		try {
			fin = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList) fin.readObject();

			fin.close();
			} catch (Exception e) {

			}
		for (position = 0; position < al.size(); position++) {
			tmpProduct = (DVDInfo) al.get(position);
		if (tmpProduct.getISBN().equalsIgnoreCase(ISBN)) {
		 return Integer.parseInt(tmpProduct.getTypeid());
		}
		}

		 return Integer.parseInt(tmpProduct.getTypeid());
		
	}
	/*
	 * 修改DVD信息方法
	 */
	public static int Updatedvd(String ISBNs,String ISBN,String typeId,String dvdname,String writer,String translator,Date date,String publisher,Double price){
		int i=0;
		try{
			int findPosition = searchStudByDVDInfo_ID(ISBNs);
			
			DVDInfo dvdInfo=new DVDInfo(ISBN,typeId,writer,translator,publisher,date,price,dvdname);
			al.set(findPosition,dvdInfo);
			 ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream("D:\\Product.txt"));
			fout.writeObject(al);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		i++;
		return i;
	}
//	/*
//	 * 删除图书信息方法
//	 */
//	public static int Delbook(String ISBN){
//		int i=0;
//		try{
//			String sql="delete from tb_bookInfo where ISBN='"+ISBN+"'";
//			//System.out.println(sql);
//			i=Dao.executeUpdate(sql);
//		}catch(Exception e){
//			e.printStackTrace();
//			
//		}
//		Dao.close();
//		return i;
//	}
	/*
	 * 对读者信息表执行的相关操作
	 */
	public static int InsertReader(String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			 ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream("D:\\Reader.txt"));
				firstReader = new Reader( name, sex, age, identityCard, date, maxNum, tel, keepMoney, Integer.parseInt(zj), zy, ISBN,bztime);
			dl.add(firstReader);
			fout.writeObject(dl);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	i++;
		return i;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Reader> selectReader() {
		ArrayList<Reader> list=new ArrayList<Reader>();
		
		try {
			
			 ObjectInputStream	fin = new ObjectInputStream(new FileInputStream(file3));
				list = (ArrayList<Reader>) fin.readObject();
				fin.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		return list;
	}
	public static List selectReader(String readerISBN) {
		List list=new ArrayList();
		
		try {
			fin = new ObjectInputStream(new FileInputStream(file3));
			dl = (ArrayList) fin.readObject();

			fin.close();
			} catch (Exception e) {

			}
			for (position = 0; position < dl.size(); position++) {
				tmpReader = (Reader) dl.get(position);
			if (tmpReader.getISBN().equalsIgnoreCase(readerISBN)) {
			list.add(tmpReader);
			}
			}
		
		return list;
	}
	public static int UpdateReader(String id,String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			fin = new ObjectInputStream(new FileInputStream(file3));
			dl = (ArrayList) fin.readObject();

			fin.close();
			
			for (position = 0; position < dl.size(); position++) {
			tmpReader = (Reader) dl.get(position);
			if (tmpReader.getISBN().equalsIgnoreCase(ISBN)) {
			break;
			}
			}
			
			
			Reader dvdreader=new Reader( name, sex, age, identityCard, date, maxNum, tel, keepMoney, Integer.parseInt(zj), zy, ISBN,bztime);
			dl.set(position,dvdreader);
			 ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream(file3));
			fout.writeObject(dl);
			fout.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
	}
	public static int DelReader(String ISBN){
		int i=0;
		try{
			
			fin = new ObjectInputStream(new FileInputStream(file3));
			dl = (ArrayList) fin.readObject();

			fin.close();
			
			for (position = 0; position < dl.size(); position++) {
			tmpReader = (Reader) dl.get(position);
			if (tmpReader.getISBN().equalsIgnoreCase(ISBN)) {
			break;
			}
			}
			
			
			dl.remove(position);
			file3.delete();
			ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(file3, true));
			fout.writeObject(dl);
			fout.flush();
			fout.close();
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
	}
/*
 * 对订购信息表操作
 */
	public static int InsertDVDOrder(String ISBN,Date date,String number,String checkAndAccept,Double zk){
		int i=0;
		try{
			 ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream(file4));
				firstOrder = new Order(ISBN, date,number,checkAndAccept,zk.toString());
			el.add(firstOrder);
			fout.writeObject(el);
			fout.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
		
	}
	public static List selectDVDOrder() {
		List list=new ArrayList();
		
		try {
			
			fin = new ObjectInputStream(new FileInputStream(file));
		      al = (ArrayList) fin.readObject();

		     fin.close();
		     fin = new ObjectInputStream(new FileInputStream(file4));
		      el = (ArrayList) fin.readObject();

		     fin.close();
			
			int i;
			
		     for (position = 0; position < el.size(); position++) {
				OrderAndDVDInfo order=new OrderAndDVDInfo();
				
				order.setISBN(el.get(position).getISBN());
				order.setOrderdate(el.get(position).getDate());
				order.setNumber(el.get(position).getNumber());
				order.setCheckAndAccept(el.get(position).getCheckAndAccept());
				order.setZk(Double.parseDouble( el.get(position).getZk()));
				for (i = 0; i< al.size(); i++) {
					tmpProduct = (DVDInfo) al.get(position);
				if (tmpProduct.getISBN().equalsIgnoreCase(el.get(position).getISBN())) {
				 break;
				}
				}
				
	          
				order.setTypeId(tmpProduct.getTypeid());
				order.setDVDname(tmpProduct.getDVDname());
				order.setWriter(tmpProduct.getWriter());
				order.setTraslator(tmpProduct.getTranslator());
				order.setPublisher(tmpProduct.getPublisher());
				order.setDate(tmpProduct.getDate());
				order.setPrice(tmpProduct.getPrice());
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}
	public static List selectDVDOrder(String ISBN) {
		List list=new ArrayList();
	
		try {
			fin = new ObjectInputStream(new FileInputStream(file4));
		      el = (ArrayList) fin.readObject();

		     fin.close();
		     for (position = 0; position < el.size(); position++) {
					tmpOrder = (Order) el.get(position);
				if (tmpOrder.getISBN().equalsIgnoreCase(ISBN)){
				list.add(tmpOrder);
				}
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public static int UpdateCheckDVDOrder(String ISBN){
		int i=0;
		try{
			
			fin = new ObjectInputStream(new FileInputStream(file4));
		      el = (ArrayList) fin.readObject();
		      fin.close();
		    
		     for (position = 0; position < el.size(); position++) {
					tmpOrder = (Order) el.get(position);
				if (tmpOrder.getISBN().equalsIgnoreCase(ISBN)){
				el.get(position).setCheckAndAccept("0");
				}
				}
		     file4.delete();
		     ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream(file4));
				
			fout.writeObject(el);
			fout.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
		
	}
	/*
	 * 对借阅表进行操作
	 */
	public static int InsertDVDBorrow(String dvdISBN,String readerISBN,java.sql.Timestamp timestamp,java.sql.Timestamp timestamp2,String dvdName,String readerName,int typeId){
		int i=0;

		
		try{
			ObjectOutputStream	fout = new ObjectOutputStream(new FileOutputStream("D:\\Borrow.txt"));
			firstBorrow=new Borrow(dvdISBN,readerISBN,timestamp,timestamp2,dvdName,readerName,typeId);
		cl.add(firstBorrow);
		
		fout.writeObject(cl);
		fout.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
		
	}
	public static List selectBorrow(String readerISBN) {
		List list=new ArrayList();
		
		try {
			try {
				fin = new ObjectInputStream(new FileInputStream(file2));
				cl = (ArrayList) fin.readObject();

				fin.close();
				} catch (Exception e) {

				}
				for (position = 0; position < cl.size(); position++) {
					tmpBorrow = (Borrow) cl.get(position);
				if (tmpBorrow.getReaderISBN().equalsIgnoreCase(readerISBN)) {
				list.add(tmpBorrow);
				}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}
	/*
	 * 查询还DVD内容
	 */
	public static List selectDVDBack(String readerISBN) {
		List list=new ArrayList();
		

	
			
			try {
				fin = new ObjectInputStream(new FileInputStream(file2));
				cl = (ArrayList) fin.readObject();

				fin.close();
				} catch (Exception e) {

				}
				for (position = 0; position < cl.size(); position++) {
					tmpBorrow = (Borrow) cl.get(position);
				if (tmpBorrow.getReaderISBN().equalsIgnoreCase(readerISBN)) {
				list.add(tmpBorrow);
				}
				}
			
			
			
			
		
		
		return list;
	}
	public static int UpdateDVDBack(String bookISBN,String readerISBN,int id){//归还DVD操作
		int i=0;
try{
			
			fin = new ObjectInputStream(new FileInputStream(file2));
			cl = (ArrayList) fin.readObject();

			fin.close();
			
			for (position = 0; position < cl.size(); position++) {
			tmpBorrow = (Borrow) cl.get(position);
			if (tmpBorrow.getDVDISBN().equalsIgnoreCase(bookISBN)&&tmpBorrow.getReaderISBN().equalsIgnoreCase(readerISBN)) {
			break;
			}
			}
			
			
			cl.remove(position);
			file2.delete();
			ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(file2, true));
			fout.writeObject(cl);
			fout.flush();
			fout.close();
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		i++;
		return i;
		
	}
	
	
	//new
	public static List selectdvdserch() {
		ArrayList<DVDInfo> list=new ArrayList<DVDInfo>();
		
		try {
			 ObjectInputStream	fin = new ObjectInputStream(new FileInputStream("D:\\Product.txt"));
				list = (ArrayList<DVDInfo>) fin.readObject();
				fin.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		
		return list;
	}
	public static List selectdvdmohu(String dvdname){
		List list=new ArrayList();
	
		try {
			 ObjectInputStream	fin = new ObjectInputStream(new FileInputStream("D:\\Product.txt"));
				al = (ArrayList) fin.readObject();
				fin.close();
				
				for (position = 0; position < al.size(); position++) {
					tmpProduct = (DVDInfo) al.get(position);
					if (tmpProduct.getDVDname().equalsIgnoreCase(dvdname)) {
					list.add(tmpProduct);
					}
					}
			
			
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	public static List selectdvdmohuwriter(String writer){
		List list=new ArrayList();
		try {
			 ObjectInputStream	fin = new ObjectInputStream(new FileInputStream("D:\\Product.txt"));
				al = (ArrayList) fin.readObject();
				fin.close();
				
				for (position = 0; position < al.size(); position++) {
					tmpProduct = (DVDInfo) al.get(position);
					if (tmpProduct.getWriter().equalsIgnoreCase(writer)) {
					list.add(tmpProduct);
					}
					}
			
			
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
		}
	
}
