package com.dvd.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dvd.dao.Dao;
import com.dvd.util.CreatecdIcon;
import com.dvd.util.MyDocument;

public class DVDTypeAddIFrame extends JInternalFrame {

	private JFormattedTextField days;
	private JTextField dvdTypeName;
	private JTextField fakuan;
	private JTextField id;
	
	/**
	 * Create the frame
	 */
	public DVDTypeAddIFrame() {
		super();
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);
		setTitle("DVD������");
		setBounds(100, 100, 500, 500);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 80));
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel label_4 = new JLabel();
		ImageIcon dvdTypeAddIcon=CreatecdIcon.add("dvdTypeAdd.jpg");
		label_4.setIcon(dvdTypeAddIcon);
		label_4.setPreferredSize(new Dimension(400, 80));
		label_4.setText("DVD���ͼƬ��400*80��");
		panel.add(label_4);

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 0));
		getContentPane().add(panel_2, BorderLayout.WEST);

		final JLabel label = new JLabel();
		panel_2.add(label);

		final JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		getContentPane().add(panel_3, BorderLayout.CENTER);

		final JLabel label_1 = new JLabel();
		label_1.setPreferredSize(new Dimension(390, 50));
		panel_3.add(label_1);

		final JLabel label_9 = new JLabel();
		label_9.setPreferredSize(new Dimension(160, 20));
		label_9.setText("���ͱ�ţ�");
		panel_3.add(label_9);

		id =  new JTextField();
		id.setColumns(30);
		
		panel_3.add(id);
		
		
		final JLabel label_2 = new JLabel();
		label_2.setPreferredSize(new Dimension(160, 20));
		label_2.setText("DVD������ƣ�");
		panel_3.add(label_2);

		dvdTypeName = new JTextField();
		dvdTypeName.setDocument(new MyDocument(20));
		dvdTypeName.setColumns(30);
		panel_3.add(dvdTypeName);

		final JLabel label_3 = new JLabel();
		label_3.setPreferredSize(new Dimension(160, 20));
		label_3.setText("�� �� �� ����");
		panel_3.add(label_3);

		days = new JFormattedTextField(NumberFormat.getIntegerInstance());
		days.setColumns(30);
		days.setValue(3);
		panel_3.add(days);
        
		final JLabel label_5 = new JLabel();
		label_5.setPreferredSize(new Dimension(160, 20));
		label_5.setText("����ٻ�һ��ķ���������");
		panel_3.add(label_5);

		fakuan = new JTextField("��λΪ��");
		fakuan.setColumns(30);
		  fakuan.addKeyListener(new NumberListener());
		  
		
		panel_3.add(fakuan);
		final JLabel label_8 = new JLabel();
		label_8.setPreferredSize(new Dimension(160, 30));
		panel_3.add(label_8);


		final JButton button = new JButton();
		button.setText("����");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				if(id.getText().length()==0){
					JOptionPane.showMessageDialog(null, "DVD���id����Ϊ��");
					return;
				}
				if(dvdTypeName.getText().length()==0){
					JOptionPane.showMessageDialog(null, "DVD����ı��򲻿�Ϊ��");
					return;
				}
				if(days.getText().length()==0){
					JOptionPane.showMessageDialog(null, "�ɽ������ı��򲻿�Ϊ��");
					return;
				}
				if(fakuan.getText().length()==0||fakuan.getText().trim().equals("��λΪ��")){
					JOptionPane.showMessageDialog(null, "�����ı��򲻿�Ϊ��");
					return;
				}
				int i=Dao.InsertDVDType(id.getText().trim(),dvdTypeName.getText().trim(), days.getText().trim(),Double.valueOf(fakuan.getText().trim())/10);
				if(i==1){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				}
			}
		});
		panel_3.add(button);

		final JButton buttonDel = new JButton();
		buttonDel.setText("�ر�");
		buttonDel.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		panel_3.add(buttonDel);
		setVisible(true);
		//
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
