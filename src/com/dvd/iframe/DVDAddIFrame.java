package com.dvd.iframe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dvd.JComPz.Item;
import com.dvd.dao.Dao;
import com.dvd.model.DVDType;
import com.dvd.util.CreatecdIcon;
import com.dvd.util.MyDocument;
/**
 * ���ƣ�DVD��Ӵ���
 * 
 */
public class DVDAddIFrame extends JInternalFrame {
	private JComboBox publisher;
	private JTextField   price;
	private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField writer;
	private JTextField ISBN;
	private JTextField dvdName;
	private JComboBox dvdType;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel dvdTypeModel;
	
	Map map=new HashMap();
	public DVDAddIFrame()  {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// ���ô������С��
		setClosable(true);								// ���ô���ɹر�
		setTitle("DVD��Ϣ���");						// ���ô������
		setBounds(100, 100, 400, 300);					// ���ô���λ�úʹ�С

		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));//�հױ߽�
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_2 = new JLabel();
		label_2.setText("DVD��ţ�");
		panel.add(label_2);

		ISBN = new JTextField("������13λdvd��",13);
		ISBN.setDocument(new MyDocument(13)); //����DVD���ı����������ֵΪ13
		
		ISBN.setColumns(13);
		ISBN.addKeyListener(new ISBNkeyListener());
		ISBN.addFocusListener(new ISBNFocusListener());
		panel.add(ISBN);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("���");
		panel.add(label);

		dvdType = new JComboBox();
		dvdTypeModel= (DefaultComboBoxModel)dvdType.getModel();
		
		//���ļ���ȡ��DVD���
		ArrayList<DVDType> list=Dao.selectDVDCategory();
		for(int i=0;i<list.size();i++){
			DVDType dvdtype=(DVDType)list.get(i);
			Item item=new Item();
			item.setId((String)dvdtype.getId());
			item.setName((String)dvdtype.getTypeName());
			dvdTypeModel.addElement(item);
		}
		panel.add(dvdType);

		final JLabel label_1 = new JLabel();
		label_1.setText("DVD����");
		panel.add(label_1);

		dvdName = new JTextField();
		panel.add(dvdName);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("���ݣ�");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("���й�˾��");
		panel.add(label_2_1);

		publisher = new JComboBox();
		String[]array=new String[]{"***���й�˾","**���ͷ��й�˾","**���ͷ��й�˾","***С�ͷ��й�˾"};
		publisher.setModel(new DefaultComboBoxModel(array));
		panel.add(publisher);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("���ݣ�");
		panel.add(label_4);

		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		panel.add(translator);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("�������ڣ�");
		panel.add(label_1_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		pubDate= new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		panel.add(pubDate);
		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("���ۣ�");
		panel.add(label_3_1);
		  price=   new   JTextField();
		  price.setDocument(new MyDocument(5));
		  price.addKeyListener(new NumberListener());
		panel.add(price);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(new addBookActionListener());
		buttonadd.setText("���");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("�ر�");
		panel_1.add(buttonclose);

		final JLabel label_5 = new JLabel();
		ImageIcon dvdAddIcon=CreatecdIcon.add("dvdadd.jpg");
		label_5.setIcon(dvdAddIcon);
		label_5.setPreferredSize(new Dimension(300, 50));
		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(label_5, BorderLayout.NORTH);
		label_5.setText("��DVD����(LOGOͼƬ)");
		
		setVisible(true);											// ��ʾ����ɹر�
	}
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!Dao.selectDVDInfo(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "���DVD���ظ���");
				return;
			}
		}
	}
	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13){
				buttonadd.doClick();
			}
		
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class addBookActionListener implements ActionListener {		// ��Ӱ�ť�ĵ����¼�������
		public void actionPerformed(final ActionEvent e) {
			// ��DVDҵ��
			

			if(ISBN.getText().length()==0){
				JOptionPane.showMessageDialog(null, "DVD���ı��򲻿���Ϊ��");
				return;
			}
			if(ISBN.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "DVD���ı�������λ��Ϊ13λ");
				return;
			}
			if(dvdName.getText().length()==0){
				JOptionPane.showMessageDialog(null, "DVD�����ı��򲻿���Ϊ��");
				return;
			}
			if(writer.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if(pubDate.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿���Ϊ��");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}

			
			String ISBNs=ISBN.getText().trim();
			
			//����
			Object selectedItem = dvdType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			String dvdTypes=item.getId();
			
			String translators=translator.getText().trim();
			String dvdNames=dvdName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=(String)publisher.getSelectedItem();
			String pubDates=pubDate.getText().trim();
			String prices=price.getText().trim();
			int i=Dao.Insertdvd(ISBNs,dvdTypes, dvdNames, writers, translators, publishers,Date.valueOf(pubDates),Double.parseDouble(prices));
				
			if(i==1){
			
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				doDefaultCloseAction();
			}
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}

}
