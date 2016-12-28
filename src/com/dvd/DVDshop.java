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
 * 主窗体
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
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}
	public DVDshop() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setLocationByPlatform(true);
		setSize(1000, 800);
		setTitle("DVD租赁系统");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

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
	 * 创建工具栏
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // 创建工具栏的方法
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		JButton dvdAddButton=new JButton(MenuActions.DVD_ADD);
		//ImageIcon icon=CreatecdIcon.add("bookAdd.bmp");//创建图标方法
		ImageIcon icon=new ImageIcon(DVDshop.class.getResource("/dvdAddtb.jpg"));//添加菜单栏图标	
		dvdAddButton.setIcon(icon);
		dvdAddButton.setHideActionText(true);
		
		
		toolBar.add(dvdAddButton);
	
		
		//在工具栏中添加DVD修改与删除图标
		JButton dvdModiAndDelButton=new JButton(MenuActions.DVD_MODIFY);
		ImageIcon bookmodiicon=CreatecdIcon.add("dvdModiAndDeltb.jpg");//创建图标方法
		dvdModiAndDelButton.setIcon(bookmodiicon);
		dvdModiAndDelButton.setHideActionText(true);
		toolBar.add(dvdModiAndDelButton);
		
		
		
		
		JButton dvdTypeAddButton=new JButton(MenuActions.DVDTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("dvdTypeAddtb.jpg");//创建图标方法
		dvdTypeAddButton.setIcon(bookTypeAddicon);
		dvdTypeAddButton.setHideActionText(true);
		toolBar.add(dvdTypeAddButton);
		
		
		JButton dvdBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("dvdBorrowtb.jpg");//创建图标方法
		dvdBorrowButton.setIcon(bookBorrowicon);
		dvdBorrowButton.setHideActionText(true);
		toolBar.add(dvdBorrowButton);
		
		JButton dvdOrderButton=new JButton(MenuActions.NEWDVD_ORDER);
		ImageIcon bookOrdericon=CreatecdIcon.add("dvdOrdertb.jpg");//创建图标方法
		dvdOrderButton.setIcon(bookOrdericon);
		dvdOrderButton.setHideActionText(true);
		toolBar.add(dvdOrderButton);
		
		
		JButton dvdCheckButton=new JButton(MenuActions.NEWDVD_CHECK_ACCEPT);
		ImageIcon bookCheckicon=CreatecdIcon.add("newdvdChecktb.jpg");//创建图标方法
		dvdCheckButton.setIcon(bookCheckicon);
		dvdCheckButton.setHideActionText(true);
		toolBar.add(dvdCheckButton);
		
		
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("readerAddtb.jpg");//创建图标方法
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("readerModiAndDeltb.jpg");//创建图标方法
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
		
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("exittb.jpg");//创建图标方法
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	/**
	 * 创建菜单栏
	 */
	private JMenuBar createMenu() { // 创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();

		JMenu dvdOrderMenu = new JMenu(); // 初始化新书订购管理菜单
		dvdOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
		dvdOrderMenu.add(MenuActions.NEWDVD_ORDER);
		dvdOrderMenu.add(MenuActions.NEWDVD_CHECK_ACCEPT);

		 
		JMenu baseMenu = new JMenu();// 初始化基础数据维护菜单
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
		{
			JMenu readerManagerMItem = new JMenu("租借人信息管理");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_MODIFY);

			JMenu dvdTypeManageMItem = new JMenu("DVD类别管理");
			dvdTypeManageMItem.add(MenuActions.DVDTYPE_ADD);
			dvdTypeManageMItem.add(MenuActions.DVDTYPE_MODIFY);

			JMenu menu = new JMenu("DVD信息管理");
			menu.add(MenuActions.DVD_ADD);
			menu.add(MenuActions.DVD_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(dvdTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		JMenu borrowManageMenu = new JMenu(); // 租借管理
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowManageMenu.add(MenuActions.BORROW); // 租借
		borrowManageMenu.add(MenuActions.GIVE_BACK); // 归还
		borrowManageMenu.add(MenuActions.DVD_SEARCH); // 搜索

	
		
	

		menuBar.add(baseMenu); // 添加基础数据维护菜单到菜单栏
		menuBar.add(dvdOrderMenu); // 添加DVD订购管理菜单到菜单栏
		menuBar.add(borrowManageMenu); // 添加租借管理菜单到菜单栏
	
		return menuBar;
	}
}
