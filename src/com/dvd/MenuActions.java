package com.dvd;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import com.dvd.iframe.DVDAddIFrame;
import com.dvd.iframe.DVDBackIFrame;
import com.dvd.iframe.DVDBorrowIFrame;
import com.dvd.iframe.DVDModiAndDelIFrame;
import com.dvd.iframe.DVDSearchIFrame;
import com.dvd.iframe.DVDTypeAddIFrame;
import com.dvd.iframe.DVDTypeModiAndDelIFrame;
import com.dvd.iframe.ReaderAddIFrame;
import com.dvd.iframe.ReaderModiAndDelIFrame;
import com.dvd.iframe.newDVDCheckIFrame;
import com.dvd.iframe.newDVDOrderIFrame;
import com.dvd.util.*;
/**
 * 菜单和按钮的Action对象
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // 子窗体集合
	public static CheckAndAcceptNewDVDAction NEWDVD_CHECK_ACCEPT; 

	public static DVDSearchAction DVD_SEARCH; // DVD搜索窗体动作
	public static GiveBackAction GIVE_BACK; // DVD归还窗体动作
	public static BorrowAction BORROW; // DVD借阅窗体动作
	public static DVDOrderAction NEWDVD_ORDER; // 新DVD定购窗体动作
	public static DVDTypeModiAction DVDTYPE_MODIFY; // DVD类型修改窗体动作
	public static DVDTypeAddAction DVDTYPE_ADD; // DVD类型添加窗体动作
	public static ReaderModiAction READER_MODIFY; // 租借者信息修改窗体动作
	public static ReaderAddAction READER_ADD; // 租借信息添加窗体动作
	public static DVDModiAction DVD_MODIFY; // DVD信息修改窗体动作
	public static DVDAddAction DVD_ADD; // DVD信息添加窗体动作
	public static ExitAction EXIT; // 系统退出动作

	static {
		frames = new HashMap<String, JInternalFrame>();

		NEWDVD_CHECK_ACCEPT=new CheckAndAcceptNewDVDAction();
	
		DVD_SEARCH = new DVDSearchAction();
		GIVE_BACK = new GiveBackAction();
		BORROW = new BorrowAction();
		NEWDVD_ORDER = new DVDOrderAction();
		DVDTYPE_MODIFY = new DVDTypeModiAction();
		DVDTYPE_ADD = new DVDTypeAddAction();
		READER_MODIFY = new ReaderModiAction();
		READER_ADD = new ReaderAddAction();
		DVD_MODIFY = new DVDModiAction();
		DVD_ADD = new DVDAddAction();
		EXIT = new ExitAction();
	}


	

	

	private static class DVDSearchAction extends AbstractAction {
		DVDSearchAction() {
			super("DVD搜索", null);
			putValue(Action.LONG_DESCRIPTION, "搜索入库的DVD信息");
			putValue(Action.SHORT_DESCRIPTION, "DVD搜索");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD查询")||frames.get("DVD查询").isClosed()) {
				DVDSearchIFrame iframe=new DVDSearchIFrame();
				frames.put("DVD查询", iframe);
				DVDshop.addIFame(frames.get("DVD查询"));
			}
		}
	}

	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("DVD归还", null);
			putValue(Action.LONG_DESCRIPTION, "归还租借的DVD");
			putValue(Action.SHORT_DESCRIPTION, "DVD归还");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD归还管理")||frames.get("DVD归还管理").isClosed()) {
				DVDBackIFrame iframe=new DVDBackIFrame();
				frames.put("DVD归还管理", iframe);
				DVDshop.addIFame(frames.get("DVD归还管理"));
			}
		}
	}

	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("DVD租借", null);
			putValue(Action.LONG_DESCRIPTION, "从DVDshop租借DVD");
			putValue(Action.SHORT_DESCRIPTION, "DVD借阅");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD租借管理")||frames.get("DVD租借管理").isClosed()) {
				DVDBorrowIFrame iframe=new DVDBorrowIFrame();
				frames.put("DVD租借管理", iframe);
				DVDshop.addIFame(frames.get("DVD租借管理"));
			}
		}
	}

	private static class CheckAndAcceptNewDVDAction extends AbstractAction {
		CheckAndAcceptNewDVDAction() {
			super("验收新DVD", null);
			putValue(Action.LONG_DESCRIPTION, "验收订购的新DVD");
			putValue(Action.SHORT_DESCRIPTION, "验收新DVD");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("DVD验收")||frames.get("DVD验收").isClosed()) {
				newDVDCheckIFrame iframe=new newDVDCheckIFrame();
				frames.put("DVD验收", iframe);
				DVDshop.addIFame(frames.get("DVD验收"));
			}
		}
	}

	private static class DVDOrderAction extends AbstractAction {
		DVDOrderAction() {
			super("新DVD定购", null);
			putValue(Action.LONG_DESCRIPTION, "定购新的DVD");
			putValue(Action.SHORT_DESCRIPTION, "DVD定购");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("新DVD订购管理")||frames.get("新DVD订购管理").isClosed()) {
				newDVDOrderIFrame iframe=new newDVDOrderIFrame();
				frames.put("新DVD订购管理", iframe);
				DVDshop.addIFame(frames.get("新DVD订购管理"));
			}
		}
	}

	private static class DVDTypeModiAction extends AbstractAction {
		DVDTypeModiAction() {
			super("DVD类别修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改DVD的类别信息");
			putValue(Action.SHORT_DESCRIPTION, "DVD类别修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD类别修改")||frames.get("DVD类别修改").isClosed()) {
				DVDTypeModiAndDelIFrame iframe=new DVDTypeModiAndDelIFrame();
				frames.put("DVD类别修改", iframe);
				DVDshop.addIFame(frames.get("DVD类别修改"));
			}
		}
	}

	private static class DVDTypeAddAction extends AbstractAction {
		DVDTypeAddAction() {
			super("DVD类别添加", null);
			putValue(Action.LONG_DESCRIPTION, "为DVDshop添加新的DVD类别");
			putValue(Action.SHORT_DESCRIPTION, "DVD类别添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD类别添加")||frames.get("DVD类别添加").isClosed()) {
				DVDTypeAddIFrame iframe=new DVDTypeAddIFrame();
				frames.put("DVD类别添加", iframe);
				DVDshop.addIFame(frames.get("DVD类别添加"));
			}
		}
	}
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("租借者修改与删除", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除租借者的基本信息");
			putValue(Action.SHORT_DESCRIPTION, "租借者修改与删除");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("租借者信息修改与删除")||frames.get("租借者信息修改与删除").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("租借者信息修改与删除", iframe);
				DVDshop.addIFame(frames.get("租借者信息修改与删除"));
			}
		}
	}

	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("租借者信息添加", null);
			putValue(Action.LONG_DESCRIPTION, "为DVDshop添加新的租借者会员信息");
			putValue(Action.SHORT_DESCRIPTION, "租借者信息添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("租借者相关信息添加")||frames.get("租借者相关信息添加").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("租借者相关信息添加", iframe);
				DVDshop.addIFame(frames.get("租借者相关信息添加"));
			}
		}
	}
	//图书修改与删除
	private static class DVDModiAction extends AbstractAction {
		DVDModiAction() {
			super("DVD修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除DVD信息");
			putValue(Action.SHORT_DESCRIPTION, "DVD修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD修改")||frames.get("DVD修改").isClosed()) {
				DVDModiAndDelIFrame iframe=new DVDModiAndDelIFrame();
				frames.put("DVD修改", iframe);
				DVDshop.addIFame(frames.get("DVD修改"));
			}
		}
	}
	private static class DVDAddAction extends AbstractAction {				// 图书信息添加－－－已经实现，请参照
		DVDAddAction() {

			super("DVD信息添加", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "为DVDshop添加新的DVD信息");
			putValue(Action.SHORT_DESCRIPTION, "DVD信息添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD信息添加")||frames.get("DVD信息添加").isClosed()) {
				DVDAddIFrame iframe = new DVDAddIFrame();
				frames.put("DVD信息添加", iframe);
				DVDshop.addIFame(frames.get("DVD信息添加"));
			}
		}
	}
	private static class ExitAction extends AbstractAction { // 退出系统动作
		public ExitAction() {
			super("退出系统", null);
			putValue(Action.LONG_DESCRIPTION, "退出DVDshop管理系统");
			putValue(Action.SHORT_DESCRIPTION, "退出系统");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}

}
