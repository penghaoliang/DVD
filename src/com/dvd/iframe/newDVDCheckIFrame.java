package com.dvd.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dvd.JComPz.MapPz;
import com.dvd.dao.Dao;
import com.dvd.model.OrderAndDVDInfo;

public class newDVDCheckIFrame extends JInternalFrame {

	private JTextField dvdType;
	private JTextField orderPrice;
	private JTextField zk;
	private JTable table;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField price;
	private JTextField operator;
	private JTextField orderNumber;
	private JTextField ISBN;
	private JFormattedTextField orderDate;

	
	JRadioButton radioButton2;
	JRadioButton radioButton1;
	private String[] columnNames={ "DVD���", "��������", "��������","�Ƿ�����","�ۿ�","DVD���","DVD����","����","����","������","��������","DVD�۸�"};
	private Map map=MapPz.getMap();
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			OrderAndDVDInfo order=(OrderAndDVDInfo)list.get(i);
			results[i][0]=order.getISBN();
			results[i][1]=order.getOrderdate();
			results[i][2]=order.getNumber();
		
			
			String CheckAndAccepts;
			if(order.getCheckAndAccept().equals("1"))//1����û������
				CheckAndAccepts="��";
			else
				CheckAndAccepts="��";
			results[i][3]=CheckAndAccepts;
			
			results[i][4]=order.getZk();
			
			String bookTypes=String.valueOf(MapPz.getMap().get(order.getTypeId()));
			results[i][5]=bookTypes;
			
			results[i][6]=order.getDVDname();
			results[i][7]=order.getWriter();
			results[i][8]=order.getTraslator();
			results[i][9]=order.getPublisher();
			results[i][10]=order.getDate();
			results[i][11]=order.getPrice();
		}
		return results;
	         		
	}

	/**
	 * Create the frame
	 */
	public newDVDCheckIFrame() {
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("DVD����");
		setBounds(100, 100, 700, 420);
		

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(680, 180));
		panel.add(scrollPane);

		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectDVDOrder());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر����Զ���С
		scrollPane.setViewportView(table);
		table.addMouseListener(new TableListener());



		final JPanel panel_1_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel_1_1.setLayout(gridLayout);
		panel_1_1.setPreferredSize(new Dimension(450, 150));
		panel.add(panel_1_1);

		final JLabel label_1 = new JLabel();
		label_1.setText("�������ڣ�");
		panel_1_1.add(label_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

		orderDate = new JFormattedTextField(myfmt.getDateInstance());
		orderDate.setValue(new java.util.Date());
		orderDate.addKeyListener(new DateListener());

		panel_1_1.add(orderDate);

		final JLabel label_3 = new JLabel();
		label_3.setText("DVD��ţ�");
		panel_1_1.add(label_3);

		ISBN = new JTextField();
		panel_1_1.add(ISBN);

		final JLabel label_4 = new JLabel();
		label_4.setText("����������");
		panel_1_1.add(label_4);

		orderNumber = new JTextField();
		panel_1_1.add(orderNumber);

		

		final JLabel label_6 = new JLabel();
		label_6.setText("DVD���");
		panel_1_1.add(label_6);

		dvdType = new JTextField();
		panel_1_1.add(dvdType);


		final JLabel label_7 = new JLabel();
		label_7.setText("DVDԭ�۸�");
		panel_1_1.add(label_7);

		price = new JTextField();
		panel_1_1.add(price);

		final JLabel label_9 = new JLabel();
		label_9.setText("�Ƿ����գ�");
		panel_1_1.add(label_9);

		final JPanel panel_1 = new JPanel();
		panel_1_1.add(panel_1);

		radioButton1 = new JRadioButton();
		radioButton1.setSelected(true);
		panel_1.add(radioButton1);
		buttonGroup.add(radioButton1);
		radioButton1.setText("��");

		radioButton2= new JRadioButton();
		panel_1.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("��");

		final JLabel label = new JLabel();
		label.setText("�ۿۣ�");
		panel_1_1.add(label);

		zk = new JTextField();
		panel_1_1.add(zk);

		final JLabel label_2 = new JLabel();
		label_2.setText("�����۸�");
		panel_1_1.add(label_2);

		orderPrice = new JTextField();
		panel_1_1.add(orderPrice);
		setVisible(true);

		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("����");
		buttonCheck.addActionListener(new CheckActionListener(model));

		final JButton buttonExit = new JButton();
		panel_2.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener());
		buttonExit.setText("�˳�");
		
		//
	}
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "ʱ���ʽ��ʹ��\"2007-05-10\"��ʽ");
			}
		}
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			ISBN.setText(table.getValueAt(selRow, 0).toString().trim());
			orderDate.setText(table.getValueAt(selRow, 1).toString().trim());
			
			orderNumber.setText(table.getValueAt(selRow, 2).toString().trim());
			
			
			dvdType.setText(table.getValueAt(selRow, 5).toString().trim());
			
			price.setText(table.getValueAt(selRow, 11).toString().trim());
			if(table.getValueAt(selRow, 3).toString().trim().equals("��"))//1����û������
				radioButton2.setSelected(true);
			else
				radioButton1.setSelected(true);
			zk.setText(table.getValueAt(selRow, 4).toString().trim());
			orderPrice.setText(Double.valueOf(table.getValueAt(selRow, 11).toString().trim())*Double.valueOf(table.getValueAt(selRow, 4).toString().trim())+"");
			
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class CheckActionListener implements ActionListener{
		private final DefaultTableModel model;

		CheckActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(final ActionEvent e) {
			if(radioButton2.isSelected()){
				String ISBNs=ISBN.getText();
				int i=Dao.UpdateCheckDVDOrder(ISBNs);
				if(i==1){
					JOptionPane.showMessageDialog(null, "���ճɹ���");
					Object[][] results=getFileStates(Dao.selectDVDOrder());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					radioButton1.setSelected(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "��ѡ���DVD�Ѿ����й����գ���ѡ������DVD��������");
			}
			
			
			
			
		}
	}
}
