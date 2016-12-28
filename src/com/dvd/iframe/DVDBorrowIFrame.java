package com.dvd.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.dvd.JComPz.MapPz;
import com.dvd.dao.Dao;
import com.dvd.model.DVDInfo;
import com.dvd.model.DVDType;
import com.dvd.model.Reader;
import com.dvd.util.MyDocument;

public class DVDBorrowIFrame extends JInternalFrame {
	
	
	private JTextField operator;


	private JTextField todaydate;

	private JTable table;

	private JTextField price;

	private JTextField dvdType;

	private JTextField dvdName;

	private JTextField dvdISBN;

	private JTextField keepMoney;

	private JTextField number;

	private JTextField readerName;

	private JTextField readerISBN;

	private String[] columnNames = { "DVD编号", "借DVD日期", "应还日期", "租借者编号" };

	private Map map = MapPz.getMap();

	//private static int i = 1;

	DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * Create the frame
	 */
	public final void add() {
		String str[] = new String[4];
		str[0] = dvdISBN.getText().trim();
		str[1] = String.valueOf(myfmt.format(new java.util.Date()));
		str[2] = getBackTime().toLocaleString();
		str[3] = readerISBN.getText().trim();
		model.addRow(str);
	}
	public Date getBackTime() {	//取还书时间
		String days = "0";
		List list2 = Dao.selectDVDCategory(dvdType.getText().trim());
		for (int j = 0; j < list2.size(); j++) {
			DVDType type = (DVDType) list2.get(j);
			days = type.getDays();
		}
		java.util.Date date = new java.util.Date();
		date.setDate(date.getDate() + Integer.parseInt(days));
		return date;
	}
	
	
	public DVDBorrowIFrame() {
		super();
//		addInternalFrameListener(new InternalFrameAdapter() {
//			public void internalFrameClosing(InternalFrameEvent e) {
//			}
//		});//关闭窗口时候引发的事件
		
		setTitle("DVD借阅管理");
		setIconifiable(true); // 设置窗体可最小化
		setClosable(true); // 设置窗体可关闭
		setBounds(100, 100, 500, 375);

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 100));
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columnNames);

		table.setModel(model);

		final JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 120));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		final JSplitPane splitPane = new JSplitPane();
		panel_1.add(splitPane);

		final JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(240, 110));
		splitPane.setLeftComponent(panel_3);

		final JPanel panel_5 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(2);
		gridLayout.setVgap(10);
		panel_5.setLayout(gridLayout);
		panel_5.setPreferredSize(new Dimension(150, 100));
		panel_3.add(panel_5);

		final JLabel label = new JLabel();
		label.setText("租借者编号：");
		panel_5.add(label);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		readerISBN.addKeyListener(new ISBNListenerlostFocus());
		panel_5.add(readerISBN);

		final JLabel label_1 = new JLabel();
		label_1.setText("租借者姓名：");
		panel_5.add(label_1);

		readerName = new JTextField();
		readerName.setEditable(false);
		panel_5.add(readerName);

		final JLabel label_2 = new JLabel();
		label_2.setText("可借数量：");
		panel_5.add(label_2);

		number = new JTextField();
		number.setEditable(false);
		panel_5.add(number);

		final JLabel label_4 = new JLabel();
		label_4.setText("押    金：");
		panel_5.add(label_4);

		keepMoney = new JTextField();
		keepMoney.setEditable(false);
		panel_5.add(keepMoney);
		Icon icon = new ImageIcon("1.gif");

		final JPanel panel_4 = new JPanel();
		final GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(10);
		panel_4.setLayout(gridLayout_1);
		panel_4.setPreferredSize(new Dimension(240, 110));
		splitPane.setRightComponent(panel_4);

		final JLabel label_5 = new JLabel();
		label_5.setText("DVD编号：");
		panel_4.add(label_5);

		dvdISBN = new JTextField();
		dvdISBN.setDocument(new MyDocument(13));
		dvdISBN.addKeyListener(new bookISBNListenerlostFocus());
		panel_4.add(dvdISBN);

		final JLabel label_6 = new JLabel();
		label_6.setText("DVD名称：");
		panel_4.add(label_6);

		dvdName = new JTextField();
		dvdName.setEditable(false);
		panel_4.add(dvdName);

		final JLabel label_7 = new JLabel();
		label_7.setText("DVD类别：");
		panel_4.add(label_7);

		dvdType = new JTextField();
		dvdType.setEditable(false);
		panel_4.add(dvdType);

		final JLabel label_8 = new JLabel();
		label_8.setText("DVD价格：");
		panel_4.add(label_8);

		price = new JTextField();
		price.setEditable(false);
		panel_4.add(price);

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 100));
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_7 = new JPanel();
		final GridLayout gridLayout_2 = new GridLayout(0, 2);
		gridLayout_2.setVgap(10);
		panel_7.setLayout(gridLayout_2);
		panel_7.setPreferredSize(new Dimension(280, 50));
		panel_2.add(panel_7);

		final JLabel label_9 = new JLabel();
		label_9.setText("当前时间：");
		panel_7.add(label_9);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		todaydate.addActionListener(new TimeActionListener());
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

	
	
		final JPanel panel_8 = new JPanel();
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 60));
		panel_2.add(panel_8);

		final JButton buttonBorrow = new JButton();
		buttonBorrow.setText("借出当前DVD");
		buttonBorrow.addActionListener(new BorrowActionListener());
		panel_8.add(buttonBorrow);

		final JButton buttonClear = new JButton();
		buttonClear.setText("清除所有记录");
		buttonClear.addActionListener(new ClearActionListener(model));
		panel_8.add(buttonClear);

		setVisible(true);
		//
	}

	class bookISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				if (readerISBN.getText().trim().length()!=0
						&& dvdISBN.getText().trim().length()!=0) {
					String ISBNs = dvdISBN.getText().trim();
					List list = Dao.selectDVDInfo(ISBNs);
					for (int i = 0; i < list.size(); i++) {
						DVDInfo book = (DVDInfo) list.get(i);
						dvdName.setText(book.getDVDname());
						dvdType.setText(String.valueOf(map.get(book
								.getTypeid())));
						price.setText(String.valueOf(book.getPrice()));
					}
					String days = "0";
					List list2 = Dao.selectDVDCategory(dvdType.getText()
							.trim());
					for (int j = 0; j < list2.size(); j++) {
						DVDType type = (DVDType) list2.get(j);
						days = type.getDays();
					}
					String readerISBNs = readerISBN.getText().trim();
					List list5 = Dao.selectReader(readerISBNs);// 此租借者是否在reader表中
					List list4 = Dao.selectDVDInfo(ISBNs);// 此DVD是否在DVDInfo表中
					if (!readerISBNs.isEmpty() && list5.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"此租借者编号没有注册，查询输入租借者编号是否有误！");
						return;
					}
					if (list4.isEmpty() && !ISBNs.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"本DVDshop没有此书，查询输入DVD编号是否有误！");
						return;
					}
					if (Integer.parseInt(number.getText().trim()) <= 0) {
						JOptionPane.showMessageDialog(null, "借DVD量已经超过最大借书量！");
						return;
					}

					add();
					number.setText(String.valueOf(Integer.parseInt(number
							.getText().trim()) - 1));
				}

				else
					JOptionPane.showMessageDialog(null, "请输入租借者条形码！");
			}

		}
	}

	class ISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				String ISBNs = readerISBN.getText().trim();

				List list = Dao.selectReader(ISBNs);
				if (list.isEmpty() && !ISBNs.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"此租借者编号没有注册，查询输入租借者编号是否有误！");
				}
				for (int i = 0; i < list.size(); i++) {
					Reader reader = (Reader) list.get(i);
					readerName.setText(reader.getName());
					number.setText(reader.getMaxNum());
					keepMoney.setText(reader.getKeepMoney() + "");
					System.out.println("租借者可借书量" + number.getText().trim());
				}
			}
		}
	}

	class BorrowActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {

			String dvdISBNs=dvdISBN.getText().trim();
			String readerISBNs=readerISBN.getText().trim();
			String dvdNames=dvdName.getText().trim();
			          
			int typeid=Dao.getDVDTypeid(dvdISBNs);
			String readerNames=readerName.getText().trim();
			             
			             
			  String borrowDate=myfmt.format(new java.util.Date());
			String backDate=myfmt.format(getBackTime());
			//System.out.println(borrowDate);
			//System.out.println(java.sql.Timestamp.valueOf(backDate));
			int i=Dao.InsertDVDBorrow(dvdISBNs, readerISBNs, Timestamp.valueOf(borrowDate),Timestamp.valueOf(backDate),dvdNames,readerNames,typeid);
			if(i==1){
				JOptionPane.showMessageDialog(null, "图书借阅完成！");
				doDefaultCloseAction();
			}
		}
	}

	class ClearActionListener implements ActionListener {
		private final DefaultTableModel model;

		ClearActionListener(DefaultTableModel model) {
			this.model = model;
		}
		
		public void actionPerformed(final ActionEvent e) {
			System.out.println(table.getRowCount());
			if(table.getRowCount()!=0){
				model.removeRow(table.getRowCount()-1);
				dvdISBN.setText("");
				dvdType.setText("");
				dvdName.setText("");
				price.setText("");
				readerISBN.setText("");
				readerName.setText("");
				number.setText("");
				keepMoney.setText("");
			}
			else {
				JOptionPane.showMessageDialog(null, "表格中暂时没有数据，请进行租借操作");
			}
		}
	}
	class TimeActionListener implements ActionListener{
		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		public void actionPerformed(ActionEvent ae){
			todaydate.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
}
