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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dvd.JComPz.Item;
import com.dvd.dao.Dao;
import com.dvd.model.DVDInfo;
import com.dvd.model.DVDType;
import com.dvd.util.MyDocument;

public class newDVDOrderIFrame extends JInternalFrame {

	private JTextField dvdName;
	private JTextField zk;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbs;
	private JTextField price;
	private JComboBox dvdType;
	private JTextField operator;
	private JTextField orderNumber;
	private JTextField ISBN;
	private JFormattedTextField orderDate;
	DefaultComboBoxModel dvdTypeModel;
	DefaultComboBoxModel cbsModel;
	JRadioButton radioButton1;
	JRadioButton radioButton2;
	Map map;
	DefaultTableModel model;
	


	/**
	 * Create the frame
	 */
	public newDVDOrderIFrame() {
		super();
		setTitle("新DVD订购管理");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 500, 320);

		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(0, 240));
		getContentPane().add(panel);

		final JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "订购信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_4.setPreferredSize(new Dimension(480, 120));
		final GridLayout gridLayout_1 = new GridLayout(0, 4);
		gridLayout_1.setVgap(8);
		panel_4.setLayout(gridLayout_1);
		panel.add(panel_4);

		final JLabel label_1 = new JLabel();
		panel_4.add(label_1);
		label_1.setText("订购日期：");

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		orderDate = new JFormattedTextField(myfmt.getDateInstance());
		panel_4.add(orderDate);
		orderDate.setValue(new java.util.Date());
		orderDate.addKeyListener(new DateListener());

		final JLabel label_4 = new JLabel();
		panel_4.add(label_4);
		label_4.setText("订购数量：");

		orderNumber = new JTextField();
		panel_4.add(orderNumber);
		orderNumber.setDocument(new MyDocument(4));
		orderNumber.addKeyListener(new NumberListener());

		final JLabel label_9 = new JLabel();
		panel_4.add(label_9);
		label_9.setText("是否验收：");

		final JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);

		radioButton1 = new JRadioButton();
		radioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup.add(radioButton1);
		panel_3.add(radioButton1);
		radioButton1.setText("是");

		radioButton2 = new JRadioButton();
		radioButton2.setSelected(true);
		buttonGroup.add(radioButton2);
		panel_3.add(radioButton2);
		radioButton2.setText("否");

		final JLabel label = new JLabel();
		panel_4.add(label);
		label.setText("折扣：");

		zk = new JTextField();
		zk.setDocument(new MyDocument(1));
		zk.addKeyListener(new NumberListener());
		panel_4.add(zk);


		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "DVD信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(0, 100));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		final JLabel label_3 = new JLabel();
		label_3.setText("DVD编号：");
		panel_1.add(label_3);

		ISBN = new JTextField();
		ISBN.setDocument(new MyDocument(13));
		//ISBN.addKeyListener(new ISBNListener());
		ISBN.addFocusListener(new ISBNListenerlostFocus());
		panel_1.add(ISBN);

		final JLabel label_2 = new JLabel();
		label_2.setText("DVD名称：");
		panel_1.add(label_2);

		dvdName = new JTextField();
		panel_1.add(dvdName);

		final JLabel label_6 = new JLabel();
		label_6.setText("DVD类别：");
		panel_1.add(label_6);

		dvdType = new JComboBox();
		dvdTypeModel= (DefaultComboBoxModel)dvdType.getModel();
		panel_1.add(dvdType);

		final JLabel label_8 = new JLabel();
		label_8.setText("发行商：");
		panel_1.add(label_8);
		cbs = new JComboBox();
		cbsModel=(DefaultComboBoxModel)cbs.getModel();
		panel_1.add(cbs);

		final JLabel label_7 = new JLabel();
		label_7.setText("DVD价格：");
		panel_1.add(label_7);

		price = new JTextField();
		price.setDocument(new MyDocument(5));
		panel_1.add(price);


		
		map=new HashMap();
		//从文件中取出DVD类别
		List list=Dao.selectDVDCategory();
		for(int i=0;i<list.size();i++){
			DVDType dvdtype=(DVDType)list.get(i);
			Item item=new Item();
			item.setId((String)dvdtype.getId());
			item.setName((String)dvdtype.getTypeName());
			dvdTypeModel.addElement(item);
			map.put(item.getId(), item);
			
		}
		String[] array=new String[]{"***发行公司","**中型发行公司","**大型发行公司","***小型发行公司"};
		cbs.setModel(new DefaultComboBoxModel(array));

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonAdd = new JButton();
		buttonAdd.setText("添加");
		buttonAdd.addActionListener(new ButtonAddLisenter());
		panel_2.add(buttonAdd);

		final JButton ButtonExit = new JButton();
		ButtonExit.setText("退出");
		ButtonExit.addActionListener(new CloseActionListener());
		panel_2.add(ButtonExit);
		setVisible(true);
		//
	}
	class ButtonAddLisenter implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "订DVD日期文本框不可为空");
				return;
			}
			if(ISBN.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "DVD编号文本框不可为空");
				return;
			}
			if(orderNumber.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "订DVD数量文本框不可为空");
				return;
			}
		
			if(price.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "价格文本框不可为空");
				return;
			}
			if(!Dao.selectDVDOrder(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "添加DVD号重复！");
				return;
			}
			
			String checkAndAccept="0";
			if(radioButton2.isSelected()){
				checkAndAccept="1";
			}
			System.out.println(checkAndAccept);
			
			Double zks=Double.valueOf(zk.getText())/10;
			
			try{
				int i=Dao.InsertDVDOrder(ISBN.getText().trim(), java.sql.Date.valueOf(orderDate.getText().trim()), orderNumber.getText().trim(), checkAndAccept,zks);
				System.out.println(i);
				if(i==1){
					JOptionPane.showMessageDialog(null, "添加成功！");
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
			}
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class ISBNListener extends KeyAdapter {//使用回车键进行触发事件方法
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				String ISBNs = ISBN.getText().trim();
				List list = Dao.selectDVDInfo(ISBNs);
				System.out.println(list.isEmpty());
				if(list.isEmpty()){
					new DVDAddIFrame();
				}
				for (int i = 0; i < list.size(); i++) {
					DVDInfo bookinfo = (DVDInfo) list.get(i);
					dvdName.setText(bookinfo.getDVDname());
					dvdType.setSelectedItem(map.get(bookinfo.getTypeid()));
					cbs.setSelectedItem(bookinfo.getPublisher());
					price.setText(String.valueOf(bookinfo.getPrice()));

				}
			}
		}
	}
	class ISBNListenerlostFocus extends FocusAdapter{
		public void focusLost(FocusEvent e){
			String ISBNs = ISBN.getText().trim();
			if(!Dao.selectDVDOrder(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "已经为此编号DVD添加订购信息，请输入其他DVD编号！");
				ISBN.setText("");
				dvdName.setText("");
				price.setText("");
				return;
			}
			List list = Dao.selectDVDInfo(ISBNs);
			if(list.isEmpty()&&!ISBN.getText().isEmpty()){
				ISBN.setText("");
				dvdName.setText("");
				price.setText("");
				JOptionPane.showMessageDialog(null, "DVD信息表中无此书号，请您首先到基础数据维护中进行DVD信息添加操作");
			}
			for (int i = 0; i < list.size(); i++) {
				DVDInfo dvdinfo = (DVDInfo) list.get(i);
				dvdName.setText(dvdinfo.getDVDname());
				dvdType.setSelectedItem(map.get(dvdinfo.getTypeid()));
				cbs.setSelectedItem(dvdinfo.getPublisher());
				price.setText(String.valueOf(dvdinfo.getPrice()));

			}
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
