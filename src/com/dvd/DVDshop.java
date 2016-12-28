package com.dvd;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.dvd.dao.Dao;
import com.dvd.util.CreatecdIcon;
/**
 * ������
 * 
 */
public class DVDshop extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	public static void main(String[] args) {
		
		try {
			Dao dao=new Dao();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			DVDshop frame = new DVDshop();
			frame.setVisible(true);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);
	}
	public DVDshop() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setLocationByPlatform(true);
		setSize(1000, 800);
		setTitle("DVD����ϵͳ");
		JMenuBar menuBar = createMenu(); // ���ô����˵����ķ���
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // ���ô����������ķ���
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // ���屳��

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/backImg.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}
	/**
	 * ����������
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // �����������ķ���
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		JButton dvdAddButton=new JButton(MenuActions.DVD_ADD);
		//ImageIcon icon=CreatecdIcon.add("bookAdd.bmp");//����ͼ�귽��
		ImageIcon icon=new ImageIcon(DVDshop.class.getResource("/dvdAddtb.jpg"));//��Ӳ˵���ͼ��	
		dvdAddButton.setIcon(icon);
		dvdAddButton.setHideActionText(true);
		
		
		toolBar.add(dvdAddButton);
	
		
		//�ڹ����������DVD�޸���ɾ��ͼ��
		JButton dvdModiAndDelButton=new JButton(MenuActions.DVD_MODIFY);
		ImageIcon bookmodiicon=CreatecdIcon.add("dvdModiAndDeltb.jpg");//����ͼ�귽��
		dvdModiAndDelButton.setIcon(bookmodiicon);
		dvdModiAndDelButton.setHideActionText(true);
		toolBar.add(dvdModiAndDelButton);
		
		
		
		
		JButton dvdTypeAddButton=new JButton(MenuActions.DVDTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("dvdTypeAddtb.jpg");//����ͼ�귽��
		dvdTypeAddButton.setIcon(bookTypeAddicon);
		dvdTypeAddButton.setHideActionText(true);
		toolBar.add(dvdTypeAddButton);
		
		
		JButton dvdBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("dvdBorrowtb.jpg");//����ͼ�귽��
		dvdBorrowButton.setIcon(bookBorrowicon);
		dvdBorrowButton.setHideActionText(true);
		toolBar.add(dvdBorrowButton);
		
		JButton dvdOrderButton=new JButton(MenuActions.NEWDVD_ORDER);
		ImageIcon bookOrdericon=CreatecdIcon.add("dvdOrdertb.jpg");//����ͼ�귽��
		dvdOrderButton.setIcon(bookOrdericon);
		dvdOrderButton.setHideActionText(true);
		toolBar.add(dvdOrderButton);
		
		
		JButton dvdCheckButton=new JButton(MenuActions.NEWDVD_CHECK_ACCEPT);
		ImageIcon bookCheckicon=CreatecdIcon.add("newdvdChecktb.jpg");//����ͼ�귽��
		dvdCheckButton.setIcon(bookCheckicon);
		dvdCheckButton.setHideActionText(true);
		toolBar.add(dvdCheckButton);
		
		
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("readerAddtb.jpg");//����ͼ�귽��
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("readerModiAndDeltb.jpg");//����ͼ�귽��
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
		
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("exittb.jpg");//����ͼ�귽��
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	/**
	 * �����˵���
	 */
	private JMenuBar createMenu() { // �����˵����ķ���
		JMenuBar menuBar = new JMenuBar();

		JMenu dvdOrderMenu = new JMenu(); // ��ʼ�����鶩������˵�
		dvdOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
		dvdOrderMenu.add(MenuActions.NEWDVD_ORDER);
		dvdOrderMenu.add(MenuActions.NEWDVD_CHECK_ACCEPT);

		 
		JMenu baseMenu = new JMenu();// ��ʼ����������ά���˵�
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
		{
			JMenu readerManagerMItem = new JMenu("�������Ϣ����");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_MODIFY);

			JMenu dvdTypeManageMItem = new JMenu("DVD������");
			dvdTypeManageMItem.add(MenuActions.DVDTYPE_ADD);
			dvdTypeManageMItem.add(MenuActions.DVDTYPE_MODIFY);

			JMenu menu = new JMenu("DVD��Ϣ����");
			menu.add(MenuActions.DVD_ADD);
			menu.add(MenuActions.DVD_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(dvdTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		JMenu borrowManageMenu = new JMenu(); // ������
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowManageMenu.add(MenuActions.BORROW); // ���
		borrowManageMenu.add(MenuActions.GIVE_BACK); // �黹
		borrowManageMenu.add(MenuActions.DVD_SEARCH); // ����

	
		
	

		menuBar.add(baseMenu); // ��ӻ�������ά���˵����˵���
		menuBar.add(dvdOrderMenu); // ���DVD��������˵����˵���
		menuBar.add(borrowManageMenu); // ���������˵����˵���
	
		return menuBar;
	}
}
