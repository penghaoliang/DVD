package com.dvd.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.dvd.JComPz.Item;
import com.dvd.JComPz.MapPz;
import com.dvd.dao.Dao;
import com.dvd.model.DVDInfo;
import com.dvd.model.DVDType;
import com.dvd.util.CreatecdIcon;
import com.dvd.util.MyDocument;

/**
 * 名称：图书修改窗体
 *
 */
public class DVDModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	private JFormattedTextField   price;//检测 可通过getValue方法的到转化后的数据对象（该对象是一个Object对象，还需要强制转化一下）
	private JFormattedTextField pubDate;
	private JTextField translator;//可以通过getText得到字符串
	private JTextField publisher;
	private JTextField writer;
	private JTextField ISBN;
	private JTextField dvdName;
	private JComboBox dvdType;
	DefaultComboBoxModel dvdTypeModel;
	private Item item;
	Map map=new HashMap();
	private String[] columnNames;
	private Map m=MapPz.getMap();

	//取数据库中图书相关信息放入表格中
	private Object[][] getFileStates(ArrayList<DVDInfo> list){
		String[] columnNames = { "DVD编号", "DVD类别", "DVD名称", "导演", "主演", "发行商",
				"发行日期", "价格" };
		Object[][]results=new Object[list.size()][columnNames.length];
		
		for(int i=0;i<list.size();i++){
			DVDInfo dvdinfo=(DVDInfo)list.get(i);
			results[i][0]=dvdinfo.getISBN();
			String booktypename=String.valueOf(MapPz.getMap().get(dvdinfo.getTypeid()));
			results[i][1]=booktypename;
			results[i][2]=dvdinfo.getDVDname();
			results[i][3]=dvdinfo.getWriter();
			results[i][4]=dvdinfo.getTranslator();
			results[i][5]=dvdinfo.getPublisher();
			results[i][6]=dvdinfo.getDate();
			results[i][7]=dvdinfo.getPrice();
		}
		return results;
	         		
	}
	public DVDModiAndDelIFrame() {
		super();
		
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);
		setClosable(true);
		setTitle("DVD信息修改");
		setBounds(100, 100, 593, 406);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));//线边框
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		final JButton button = new JButton();
		button.addActionListener(new addDVDActionListener());
		button.setText("修改");
		panel_1.add(button);



		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		button_1.setText("关闭");
		panel_1.add(button_1);

		final JLabel headLogo = new JLabel();
		ImageIcon dvdModiAndDelIcon=CreatecdIcon.add("dvdmodify.jpg");
		headLogo.setIcon(dvdModiAndDelIcon);
		headLogo.setOpaque(true);
		headLogo.setBackground(Color.CYAN);
		headLogo.setPreferredSize(new Dimension(400, 80));
		headLogo.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(headLogo, BorderLayout.NORTH);


		final JPanel panel_2 = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();
		borderLayout_1.setVgap(5);
		panel_2.setLayout(borderLayout_1);
		panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(panel_2);

		final JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		Object[][] results=getFileStates( Dao.selectDVDInfo());
		columnNames = new String[]{"DVD编号", "DVD类别", "DVD名称", "导演", "主演", "发行商", "发行日期",
				"价格"};
		table = new JTable(results,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //设置列不可随容器组件大小变化自动调整宽度
		
		//鼠标单击表格中的内容产生事件,将文件中的内容放入文本框中
		table.addMouseListener(new TableListener());

		
		
		
		
		scrollPane.setViewportView(table);

		final JPanel dvdPanel = new JPanel();
		panel_2.add(dvdPanel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		dvdPanel.setLayout(gridLayout);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);//设置文本的水平对齐方式
		label_2.setText("DVD    号：");
		dvdPanel.add(label_2);

		ISBN = new JTextField();
		ISBN.setDocument(new MyDocument(13)); 
		dvdPanel.add(ISBN);
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("类       别：");
		dvdPanel.add(label);

		dvdType = new JComboBox();
		dvdTypeModel= (DefaultComboBoxModel)dvdType.getModel();
		List list=Dao.selectDVDCategory();
		for(int i=0;i<list.size();i++){
			DVDType dvdtype=(DVDType)list.get(i);
			item=new Item();
			item.setId((String)dvdtype.getId());
			item.setName((String)dvdtype.getTypeName());
			dvdTypeModel.addElement(item);
			map.put(item.getId(), item);
			
		}
		dvdPanel.add(dvdType);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("DVD   名：");
		dvdPanel.add(label_1);

		dvdName = new JTextField();
		dvdPanel.add(dvdName);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("导     演：");
		dvdPanel.add(label_3);

		writer = new JTextField();
		dvdPanel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_2_1.setText("发  行  社：");
		dvdPanel.add(label_2_1);

		publisher = new JTextField();
		dvdPanel.add(publisher);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("主    演：");
		dvdPanel.add(label_4);

		translator = new JTextField();
		dvdPanel.add(translator);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_1.setText("发 行 日 期：");
		dvdPanel.add(label_1_1);


		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		pubDate= new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		dvdPanel.add(pubDate);

		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("单      价：");
		dvdPanel.add(label_3_1);

		  price=   new   JFormattedTextField();
		  price.addKeyListener(new NumberListener());
		dvdPanel.add(price);
		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ISBNs, typeids, dvdNames,writers,translators,publishers,dates,prices;
			int selRow = table.getSelectedRow();
			ISBNs = table.getValueAt(selRow, 0).toString().trim();
			typeids = table.getValueAt(selRow, 1).toString().trim();
			dvdNames = table.getValueAt(selRow, 2).toString().trim();
			writers = table.getValueAt(selRow, 3).toString().trim();
			translators = table.getValueAt(selRow, 4).toString().trim();
			publishers = table.getValueAt(selRow, 5).toString().trim();
			dates = table.getValueAt(selRow, 6).toString().trim();
			prices = table.getValueAt(selRow, 7).toString().trim();
			
			ISBN.setText(ISBNs);
			
			
		 
			dvdTypeModel.setSelectedItem(typeids);
			dvdName.setText(dvdNames);
			writer.setText(writers);
			translator.setText(translators);
			publisher.setText(publishers);
			pubDate.setText(dates);
			price.setText(prices);
		}
	}
	class addDVDActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			// 修改DVD信息表
			if(ISBN.getText().length()==0){
				JOptionPane.showMessageDialog(null, "DVD号文本框不可以为空或则输入数字不可以大于13个");
				return;
			}
			if(ISBN.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "DVD号文本框输入位数为13位");
				return;
			}
			if(dvdName.getText().length()==0){
				JOptionPane.showMessageDialog(null, "DVD名称文本框不可以为空");
				return;
			}
			if(writer.getText().length()==0){
				JOptionPane.showMessageDialog(null, "导演文本框不可以为空");
				return;
			}
			if(publisher.getText().length()==0){
				JOptionPane.showMessageDialog(null, "发行人文本框不可以为空");
				return;
			}
			//日期与单价进行数字验证代码？
			if(pubDate.getText().length()==0){
				JOptionPane.showMessageDialog(null, "发行日期文本框不可以为空");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
				return;
			}
			
			
			
			String ISBNs=ISBN.getText().trim();
			
			//分类
			Object selectedItem = dvdTypeModel.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item)selectedItem;
			String dvdTypes=item.getId();
			System.out.println(dvdTypes);

			
			
			
			
			String translators=translator.getText().trim();
			String dvdNames=dvdName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=publisher.getText().trim();
			String pubDates=pubDate.getText().trim();
			String prices=price.getText().trim();
		
			int i=Dao.Updatedvd(ISBN.getText().trim(),ISBNs, dvdTypes, dvdNames, writers, translators,Date.valueOf(pubDates) ,publishers,Double.parseDouble(prices));
			System.out.println(i);
			
			if(i!=0){

				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.selectDVDInfo());
				//注释代码为使用表格模型
				DefaultTableModel model=new DefaultTableModel();
//				DefaultTableModel model = (DefaultTableModel) table.getModel();
				table.setModel(model);
				model.setDataVector(results, columnNames);
				

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
