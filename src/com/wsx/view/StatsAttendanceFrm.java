package com.wsx.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.wsx.dao.AttendanceDao;
import com.wsx.dao.CourseDao;
import com.wsx.model.Course;
import com.wsx.model.Teacher;
import com.wsx.util.Chooser;
import com.wsx.util.StringUtil;

public class StatsAttendanceFrm extends JInternalFrame {
	private JTextField dateTextField;
	private JTable statsListTable;
	private List<Course> courseList = new ArrayList<Course>();
	private JComboBox courseComboBox;
	private JScrollPane statsListScrollPane;
	private JPanel statsListPanel;
	private ChartPanel chartPanel;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsAttendanceFrm frame = new StatsAttendanceFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StatsAttendanceFrm() {
		
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u7B7E\u5230\u4FE1\u606F\u7EDF\u8BA1\u60C5\u51B5");
		setBounds(100, 100, 794, 657);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {//项目状态改变活动
				courseChangeAct(ie);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u65E5\u671F\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/image/\u65E5\u671F.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		dateTextField = new JTextField();
		dateTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5  \u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchAttendanceAct(ae);
			}
		});
		searchButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		searchButton.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/image/\u641C\u7D22.png")));
		
		statsListPanel = new JPanel();
		statsListPanel.setBorder(new TitledBorder(null, "\u7B7E\u5230\u4FE1\u606F\u7EDF\u8BA1\u60C5\u51B5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5207\u6362\u663E\u793A\u65B9\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(statsListPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(searchButton)))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(18)
					.addComponent(statsListPanel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("\u5217\u8868\u663E\u793A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//列表显示
				clearPanel();
				statsListPanel.setLayout(new BorderLayout());
				statsListPanel.add(statsListScrollPane);
			}
		});
		btnNewButton.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/image/\u5217\u8868.png")));
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("\u67F1\u72B6\u56FE\u663E\u793A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateString = dateTextField .getText().toString();
				if (StringUtil.isEmpty(dateString)) {
					JOptionPane.showMessageDialog(StatsAttendanceFrm.this, "请选择日期！");
					return;
				}
				//柱状图显示
				clearPanel();
				Course course = (Course)courseComboBox.getSelectedItem();//Jcombox类，即Object类，强转为CourseDao类
				drawBar(getAttendanceNum(course, dateString),course.getSelected_num(),dateString);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/image/\u67F1\u72B6\u56FE.png")));
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_2 = new JButton("\u997C\u72B6\u56FE\u663E\u793A");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateString = dateTextField .getText().toString();
				if (StringUtil.isEmpty(dateString)) {
					JOptionPane.showMessageDialog(StatsAttendanceFrm.this, "请选择日期！");
					return;
				}
				//饼状图显示
				clearPanel();
				Course course = (Course)courseComboBox.getSelectedItem();//Jcombox类，即Object类，强转为CourseDao类
				drawCircle(getAttendanceNum(course, dateString),course.getSelected_num(),dateString);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/image/\u997C\u72B6\u56FE.png")));
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(79)
					.addComponent(btnNewButton)
					.addGap(50)
					.addComponent(btnNewButton_1)
					.addGap(44)
					.addComponent(btnNewButton_2)
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		statsListScrollPane = new JScrollPane();
		GroupLayout gl_statsListPanel = new GroupLayout(statsListPanel);
		gl_statsListPanel.setHorizontalGroup(
			gl_statsListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statsListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(statsListScrollPane, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_statsListPanel.setVerticalGroup(
			gl_statsListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statsListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(statsListScrollPane, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		statsListTable = new JTable();
		statsListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u540D\u79F0", "\u7B7E\u5230\u4EBA\u6570", "\u7F3A\u5E2D\u4EBA\u6570", "\u9009\u8BFE\u4EBA\u6570", "\u65E5\u671F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		statsListScrollPane.setViewportView(statsListTable);
		statsListPanel.setLayout(gl_statsListPanel);
		getContentPane().setLayout(groupLayout);
		Chooser.getInstance().register(dateTextField);
		setCourseCombox();
		setTable();
	}

	protected void courseChangeAct(ItemEvent ie) {
		// TODO 自动生成的方法存根
		if (ie.getStateChange() == ItemEvent.SELECTED) {
			setTable();
		}
	}

	protected void searchAttendanceAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		setTable();
	}

	private void setCourseCombox(){//将教师信息添加到复选框中
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for (Course course : courseList) {
			if("教师".equals(MainFrm.userType.getName())){
				Teacher teacher = (Teacher)MainFrm.userObject;
				if(course.getTeacher_Id() == teacher.getId()){
					courseComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			courseComboBox.addItem(course);
		}
		
	}
	
	private void setTable() {//填充/刷新签到信息统计情况表
		Course course = (Course)courseComboBox.getSelectedItem();//Jcombox类，即Object类，强转为CourseDao类
		String dateString = dateTextField.getText().toString();
		AttendanceDao attendanceDao = new AttendanceDao();
		List<HashMap<Integer,String>> attendanceStatsList = attendanceDao.getAttendanceStatsList(course.getId(), dateString);
		DefaultTableModel dft = (DefaultTableModel)statsListTable.getModel();
		dft.setRowCount(0);
		for (HashMap<Integer, String> attenancdStats : attendanceStatsList) {
			Set<Entry<Integer,String>> entrySet = attenancdStats.entrySet();
			int attendanceNum = 0;
			String dateString2 = "";
			for (Entry<Integer, String> entry : entrySet) {
				attendanceNum = entry.getKey();
				dateString2 = entry.getValue();
			}
			Vector v = new Vector();
			v.add(course.getName());
			v.add(attendanceNum);
			v.add(course.getSelected_num()-attendanceNum);
			v.add(course.getSelected_num());
			v.add(dateString2);
			dft.addRow(v);
		}
	}
	
	private void clearPanel() {
		// TODO 自动生成的方法存根
		statsListPanel.removeAll();
		statsListPanel.updateUI();
		statsListPanel.repaint();
	}
	
	private void drawBar(int attendanceNum,int studentNum,String date) {
		setLanuage();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();//创建一个数据集
		dataSet.addValue(attendanceNum, date, "出席人数");//添加数据
		dataSet.addValue(studentNum-attendanceNum, date, "缺勤人数");
		dataSet.addValue(studentNum, date, "总人数");
		//创建一个chart对象，把数据集放进去
		JFreeChart chart = ChartFactory.createBarChart3D("学生签到统计情况", "出席类别", "天数", dataSet, PlotOrientation.VERTICAL, true, false, false);
		//创建一个图标panel
		chartPanel= new ChartPanel(chart);
		//将图标panel添加到要显示的panel上
		chartPanel.setPreferredSize(new Dimension(650,370));
		statsListPanel.add(chartPanel,BorderLayout.CENTER);
		statsListPanel.setLayout(new FlowLayout());
		statsListPanel.updateUI();
		statsListPanel.repaint();
	}
	
	private void setLanuage(){
		//创建主题样式
		   StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		   //设置标题字体  
		   standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
		   //设置图例字体  
		   standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
		   //设置轴向字体  
		   standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
		   //应用主题样式  
		   ChartFactory.setChartTheme(standardChartTheme);
	}
	
	protected int getAttendanceNum(Course course, String dateString) {//获取学生签到的信息，从setTable修改而来
		// TODO 自动生成的方法存根
		AttendanceDao attendanceDao = new AttendanceDao();
		List<HashMap<Integer, String>> attendanceStatsList = attendanceDao.getAttendanceStatsList(course.getId(), dateString);
		int attendanceNum = 0 ;
		for(HashMap<Integer, String> attendanceStats : attendanceStatsList){
			Set<Entry<Integer, String>> entrySet = attendanceStats.entrySet();
			for(Entry<Integer, String> entry : entrySet){
				attendanceNum = entry.getKey();
			}
		}
		return attendanceNum;
	}
	
	protected void drawCircle(int attendanceNum,int selected_num,String date) {
		// TODO 自动生成的方法存根
		setLanuage();
		DefaultPieDataset dataSet = new DefaultPieDataset();//创建数据集
		dataSet.setValue("出席人数",attendanceNum);//设置数据
		dataSet.setValue("缺勤人数",selected_num-attendanceNum);
		dataSet.setValue("总人数",selected_num);
		JFreeChart chart = ChartFactory.createPieChart3D("学生签到考勤统计", dataSet, true, true, false);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(650,370));
		statsListPanel.add(chartPanel,BorderLayout.CENTER);
		statsListPanel.setLayout(new FlowLayout());
		statsListPanel.updateUI();
		statsListPanel.repaint();
	}
}
