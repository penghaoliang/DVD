package com.dvd.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dvd.JComPz.Item;
import com.dvd.dao.Dao;
import com.dvd.model.DVDType;
import com.dvd.util.CreatecdIcon;

public class DVDTypeModiAndDelIFrame extends JInternalFrame {

	private JComboBox comboBox;
	private JTextField days,fk;
	private JTextField DVDTypeId;
	private JTable table;
	private String[] array;
	private String[] columnNames={ "DVD类别编号", "DVD类别名称", "可借天数","罚款"};
	DefaultComboBoxModel dvdTypeModel;
	DefaultTableModel model;
	Map map;
	
	

	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			DVDType dvdtype=(DVDType)list.get(i);
			results[i][0]=dvdtype.getId();
			results[i][1]=dvdtype.getTypeName();
			results[i][2]=dvdtype.getDays();
            results[i][3]=dvdtype.getFk();
		}
		return results;
	         		
	}
	/**
	 * Create the frame
	 */
	public DVDTypeModiAndDelIFrame() {
		super();
		setTitle("DVD类别修改");
		setBounds(100, 100, 500, 350);
		setIconifiable(true);
		setClosable(true);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel logoLabel = new JLabel();
		
		ImageIcon dvdTypeModiAndDelIcon=CreatecdIcon.add("dvdtypemodify.jpg");
		logoLabel.setIcon(dvdTypeModiAndDelIcon);
		
		logoLabel.setPreferredSize(new Dimension(400, 80));
		logoLabel.setText("logo");
		panel.add(logoLabel);

		final JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 130));
		panel_1.add(scrollPane);

		model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectDVDCategory());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);
		

		final JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		final GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(400, 43));

		final JLabel label = new JLabel();
		label.setText("类别编号：");
		panel_2.add(label);

		DVDTypeId = new JTextField();
		DVDTypeId.setFocusable(false);
		panel_2.add(DVDTypeId);

		final JLabel label_1 = new JLabel();
		label_1.setText("类别名称：");
		panel_2.add(label_1);
		
	
		comboBox = new JComboBox();
		dvdTypeModel= (DefaultComboBoxModel)comboBox.getModel();
		
		//从文件中取出图书类别
		List list=Dao.selectDVDCategory();
		for(int i=0;i<list.size();i++){
			DVDType dvdtype=(DVDType)list.get(i);
			Item item=new Item();
			item.setId(dvdtype.getId());
			item.setName(dvdtype.getTypeName());
			
			dvdTypeModel.addElement(item);
		}
		panel_2.add(comboBox);

		final JLabel label_2 = new JLabel();
		label_2.setText("可借天数：");
		panel_2.add(label_2);

		days = new JTextField();
		panel_2.add(days);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("罚款：");
		panel_2.add(label_3);
		
		fk = new JTextField();
		panel_2.add(fk);
		
		final JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		final JButton buttonMod = new JButton();
		buttonMod.setText("修改");
		buttonMod.addActionListener(new ButtonAddListener());
		panel_4.add(buttonMod);

		final JButton buttonExit = new JButton();
		buttonExit.setText("退出");
		buttonExit.addActionListener(new CloseActionListener());
		panel_4.add(buttonExit);
		setVisible(true);
		//
		
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			DVDTypeId.setText(table.getValueAt(selRow, 0).toString().trim());
			dvdTypeModel.setSelectedItem(table.getValueAt(selRow, 1).toString().trim());
			days.setText(table.getValueAt(selRow, 2).toString().trim());
			fk.setText(table.getValueAt(selRow, 3).toString().trim());
			
		}
	}
	class ButtonAddListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object selectedItem = dvdTypeModel.getSelectedItem();
			
			int i=Dao.UpdateDVDType(DVDTypeId.getText().trim(),selectedItem.toString(), days.getText().trim(),fk.getText().trim());
			System.out.println(i);
			if(i!=0){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.selectDVDCategory());
				model.setDataVector(results,columnNames);
				table.setModel(model);
				
			}
		}
	}
	/*class ButtonDelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int i=Dao.DelbookType(BookTypeId.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results=getFileStates(Dao.selectBookCategory());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}*/
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
