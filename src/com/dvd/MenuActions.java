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
 * �˵��Ͱ�ť��Action����
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��
	public static CheckAndAcceptNewDVDAction NEWDVD_CHECK_ACCEPT; 

	public static DVDSearchAction DVD_SEARCH; // DVD�������嶯��
	public static GiveBackAction GIVE_BACK; // DVD�黹���嶯��
	public static BorrowAction BORROW; // DVD���Ĵ��嶯��
	public static DVDOrderAction NEWDVD_ORDER; // ��DVD�������嶯��
	public static DVDTypeModiAction DVDTYPE_MODIFY; // DVD�����޸Ĵ��嶯��
	public static DVDTypeAddAction DVDTYPE_ADD; // DVD������Ӵ��嶯��
	public static ReaderModiAction READER_MODIFY; // �������Ϣ�޸Ĵ��嶯��
	public static ReaderAddAction READER_ADD; // �����Ϣ��Ӵ��嶯��
	public static DVDModiAction DVD_MODIFY; // DVD��Ϣ�޸Ĵ��嶯��
	public static DVDAddAction DVD_ADD; // DVD��Ϣ��Ӵ��嶯��
	public static ExitAction EXIT; // ϵͳ�˳�����

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
			super("DVD����", null);
			putValue(Action.LONG_DESCRIPTION, "��������DVD��Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "DVD����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD��ѯ")||frames.get("DVD��ѯ").isClosed()) {
				DVDSearchIFrame iframe=new DVDSearchIFrame();
				frames.put("DVD��ѯ", iframe);
				DVDshop.addIFame(frames.get("DVD��ѯ"));
			}
		}
	}

	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("DVD�黹", null);
			putValue(Action.LONG_DESCRIPTION, "�黹����DVD");
			putValue(Action.SHORT_DESCRIPTION, "DVD�黹");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD�黹����")||frames.get("DVD�黹����").isClosed()) {
				DVDBackIFrame iframe=new DVDBackIFrame();
				frames.put("DVD�黹����", iframe);
				DVDshop.addIFame(frames.get("DVD�黹����"));
			}
		}
	}

	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("DVD���", null);
			putValue(Action.LONG_DESCRIPTION, "��DVDshop���DVD");
			putValue(Action.SHORT_DESCRIPTION, "DVD����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD������")||frames.get("DVD������").isClosed()) {
				DVDBorrowIFrame iframe=new DVDBorrowIFrame();
				frames.put("DVD������", iframe);
				DVDshop.addIFame(frames.get("DVD������"));
			}
		}
	}

	private static class CheckAndAcceptNewDVDAction extends AbstractAction {
		CheckAndAcceptNewDVDAction() {
			super("������DVD", null);
			putValue(Action.LONG_DESCRIPTION, "���ն�������DVD");
			putValue(Action.SHORT_DESCRIPTION, "������DVD");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("DVD����")||frames.get("DVD����").isClosed()) {
				newDVDCheckIFrame iframe=new newDVDCheckIFrame();
				frames.put("DVD����", iframe);
				DVDshop.addIFame(frames.get("DVD����"));
			}
		}
	}

	private static class DVDOrderAction extends AbstractAction {
		DVDOrderAction() {
			super("��DVD����", null);
			putValue(Action.LONG_DESCRIPTION, "�����µ�DVD");
			putValue(Action.SHORT_DESCRIPTION, "DVD����");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("��DVD��������")||frames.get("��DVD��������").isClosed()) {
				newDVDOrderIFrame iframe=new newDVDOrderIFrame();
				frames.put("��DVD��������", iframe);
				DVDshop.addIFame(frames.get("��DVD��������"));
			}
		}
	}

	private static class DVDTypeModiAction extends AbstractAction {
		DVDTypeModiAction() {
			super("DVD����޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸�DVD�������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "DVD����޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD����޸�")||frames.get("DVD����޸�").isClosed()) {
				DVDTypeModiAndDelIFrame iframe=new DVDTypeModiAndDelIFrame();
				frames.put("DVD����޸�", iframe);
				DVDshop.addIFame(frames.get("DVD����޸�"));
			}
		}
	}

	private static class DVDTypeAddAction extends AbstractAction {
		DVDTypeAddAction() {
			super("DVD������", null);
			putValue(Action.LONG_DESCRIPTION, "ΪDVDshop����µ�DVD���");
			putValue(Action.SHORT_DESCRIPTION, "DVD������");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD������")||frames.get("DVD������").isClosed()) {
				DVDTypeAddIFrame iframe=new DVDTypeAddIFrame();
				frames.put("DVD������", iframe);
				DVDshop.addIFame(frames.get("DVD������"));
			}
		}
	}
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("������޸���ɾ��", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ������ߵĻ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������޸���ɾ��");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("�������Ϣ�޸���ɾ��")||frames.get("�������Ϣ�޸���ɾ��").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("�������Ϣ�޸���ɾ��", iframe);
				DVDshop.addIFame(frames.get("�������Ϣ�޸���ɾ��"));
			}
		}
	}

	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("�������Ϣ���", null);
			putValue(Action.LONG_DESCRIPTION, "ΪDVDshop����µ�����߻�Ա��Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�������Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("����������Ϣ���")||frames.get("����������Ϣ���").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("����������Ϣ���", iframe);
				DVDshop.addIFame(frames.get("����������Ϣ���"));
			}
		}
	}
	//ͼ���޸���ɾ��
	private static class DVDModiAction extends AbstractAction {
		DVDModiAction() {
			super("DVD�޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��DVD��Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "DVD�޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD�޸�")||frames.get("DVD�޸�").isClosed()) {
				DVDModiAndDelIFrame iframe=new DVDModiAndDelIFrame();
				frames.put("DVD�޸�", iframe);
				DVDshop.addIFame(frames.get("DVD�޸�"));
			}
		}
	}
	private static class DVDAddAction extends AbstractAction {				// ͼ����Ϣ��ӣ������Ѿ�ʵ�֣������
		DVDAddAction() {

			super("DVD��Ϣ���", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "ΪDVDshop����µ�DVD��Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "DVD��Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("DVD��Ϣ���")||frames.get("DVD��Ϣ���").isClosed()) {
				DVDAddIFrame iframe = new DVDAddIFrame();
				frames.put("DVD��Ϣ���", iframe);
				DVDshop.addIFame(frames.get("DVD��Ϣ���"));
			}
		}
	}
	private static class ExitAction extends AbstractAction { // �˳�ϵͳ����
		public ExitAction() {
			super("�˳�ϵͳ", null);
			putValue(Action.LONG_DESCRIPTION, "�˳�DVDshop����ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}

}
