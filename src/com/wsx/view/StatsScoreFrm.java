package com.wsx.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.wsx.dao.CourseDao;
import com.wsx.dao.ScoreDao;
import com.wsx.model.Course;
import com.wsx.model.Teacher;

public class StatsScoreFrm extends JInternalFrame {
	private JTextField maxScoreTextField;
	private JTextField minScoreTextField;
	private JTextField midScoreTextField;
	private JTextField studentNumTextField;
	private JComboBox courseComboBox;
	private JPanel viewPanel;
	private List<Course>courseList;
	private JPanel defaultPanel;
	private ChartPanel chartPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsScoreFrm frame = new StatsScoreFrm();
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
	public StatsScoreFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6210\u7EE9\u7EDF\u8BA1\u754C\u9762");
		setBounds(100, 100, 804, 624);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		courseComboBox = new JComboBox();
		
		JButton searchButton = new JButton("\u67E5  \u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchStatsAct(ae);
			}
		});
		searchButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		viewPanel = new JPanel();
		viewPanel.setBorder(new TitledBorder(null, "\u6210\u7EE9\u7EDF\u8BA1\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5207\u6362\u7EDF\u8BA1\u65B9\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(193)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(searchButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(105)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(viewPanel, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(viewPanel, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton barViewButton = new JButton("\u67F1\u72B6\u56FE\u663E\u793A");
		barViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				barViewAct(ae);
			}
		});
		barViewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		barViewButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u67F1\u72B6\u56FE.png")));
		
		JButton defaultViewButton = new JButton("\u9ED8\u8BA4\u663E\u793A");
		defaultViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				defaultViewAct(ae);
			}
		});
		defaultViewButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u9ED8\u8BA4.png")));
		defaultViewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton pieViewButton = new JButton("\u997C\u72B6\u56FE\u663E\u793A");
		pieViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				pieViewAct(ae);
			}
		});
		pieViewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		pieViewButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u997C\u72B6\u56FE.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(66)
					.addComponent(defaultViewButton)
					.addGap(50)
					.addComponent(barViewButton)
					.addGap(44)
					.addComponent(pieViewButton)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(barViewButton)
						.addComponent(defaultViewButton)
						.addComponent(pieViewButton))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		defaultPanel = new JPanel();
		GroupLayout gl_viewPanel = new GroupLayout(viewPanel);
		gl_viewPanel.setHorizontalGroup(
			gl_viewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(defaultPanel, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_viewPanel.setVerticalGroup(
			gl_viewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(defaultPanel, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("\u6700\u9AD8\u5206\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u6700\u9AD8\u5206.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		maxScoreTextField = new JTextField();
		maxScoreTextField.setEditable(false);
		maxScoreTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u6700\u4F4E\u5206\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u6700\u4F4E\u5206.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		minScoreTextField = new JTextField();
		minScoreTextField.setEditable(false);
		minScoreTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u5E73\u5747\u5206\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u5E73\u5747.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		midScoreTextField = new JTextField();
		midScoreTextField.setEditable(false);
		midScoreTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u603B\u4EBA\u6570\uFF1A");
		lblNewLabel_4.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/image/\u4EBA\u6570.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentNumTextField = new JTextField();
		studentNumTextField.setEditable(false);
		studentNumTextField.setColumns(10);
		GroupLayout gl_defaultPanel = new GroupLayout(defaultPanel);
		gl_defaultPanel.setHorizontalGroup(
			gl_defaultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_defaultPanel.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_defaultPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_defaultPanel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentNumTextField))
						.addGroup(gl_defaultPanel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(midScoreTextField))
						.addGroup(gl_defaultPanel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(minScoreTextField))
						.addGroup(gl_defaultPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(maxScoreTextField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		gl_defaultPanel.setVerticalGroup(
			gl_defaultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_defaultPanel.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(maxScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(minScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(midScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		defaultPanel.setLayout(gl_defaultPanel);
		viewPanel.setLayout(gl_viewPanel);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
	}
	
	protected void barViewAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		Course course = (Course)courseComboBox.getSelectedItem();
		ScoreDao scoreDao = new ScoreDao();
		Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
		clearPanel();
		drawBar(Integer.parseInt(statsInfo.get("student_num")), Integer.parseInt(statsInfo.get("max_score")), Integer.parseInt(statsInfo.get("min_score")), Double.valueOf(statsInfo.get("mid_score")), course.getName());
	}
	
	private void drawBar(int studentNum,int maxScore,int minScore,double midScore,String courseName) {
		setLanuage();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();//创建一个数据集
		dataSet.addValue(maxScore,courseName+"(总人数："+studentNum+")", "最高分");//添加数据
		dataSet.addValue(minScore,courseName+"(总人数："+studentNum+")", "最低分");
		dataSet.addValue(midScore,courseName+"(总人数："+studentNum+")", "平均分");
		//创建一个chart对象，把数据集放进去
		JFreeChart chart = ChartFactory.createBarChart3D("学生分数统计柱状图", "分数统计" , "分数", dataSet, PlotOrientation.VERTICAL, true, false, false);
		//创建一个图标panel
		chartPanel = new ChartPanel(chart);
		//将图标panel添加到要显示的panel上
		chartPanel.setPreferredSize(new Dimension(600,330));
		viewPanel.add(chartPanel,BorderLayout.CENTER);
		viewPanel.setLayout(new FlowLayout());
		viewPanel.updateUI();
		viewPanel.repaint();
	}
	
	protected void pieViewAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		Course course = (Course)courseComboBox.getSelectedItem();
		ScoreDao scoreDao = new ScoreDao();
		Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
		clearPanel();
		drawCircle(Integer.parseInt(statsInfo.get("max_score")), Integer.parseInt(statsInfo.get("min_score")), Double.valueOf(statsInfo.get("mid_score")), course.getName());
	}
	
	protected void drawCircle(int maxScore,int minScore,double midScore,String coureName) {
		// TODO 自动生成的方法存根
		setLanuage();
		DefaultPieDataset dataSet = new DefaultPieDataset();//创建数据集
		dataSet.setValue("最高分",maxScore);//设置数据
		dataSet.setValue("最低分",minScore);
		dataSet.setValue("平均分",midScore);
		JFreeChart chart = ChartFactory.createPieChart3D("学生分数统计饼状图", dataSet, true, true, false);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600,330));
		viewPanel.add(chartPanel,BorderLayout.CENTER);
		viewPanel.setLayout(new FlowLayout());
		viewPanel.updateUI();
		viewPanel.repaint();
	}


	protected void defaultViewAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		clearPanel();
		Course course = (Course)courseComboBox.getSelectedItem();
		ScoreDao scoreDao = new ScoreDao();
		Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
		resetText();
		if(statsInfo.size() > 0){
			setDefaultPanel(statsInfo.get("student_num"),statsInfo.get("max_score"),statsInfo.get("min_score"),statsInfo.get("mid_score"));
		}
	}
	
	private void setDefaultPanel(String studentNum,String maxScore, String minScore,String midScore){
		maxScoreTextField.setText(maxScore);
		minScoreTextField.setText(minScore);
		midScoreTextField.setText(midScore);
		studentNumTextField.setText(studentNum);
		//viewPanel.add(maxScoreTextField);
		//viewPanel.add(minScoreTextField);
		//viewPanel.add(middScoreTextField);
		//viewPanel.add(studentNumTextField);
		viewPanel.add(defaultPanel);
		viewPanel.updateUI();
		viewPanel.repaint();
	}

	protected void searchStatsAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		defaultViewAct(ae);
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
	
	private void resetText(){
		maxScoreTextField.setText("");
		minScoreTextField.setText("");
		midScoreTextField.setText("");
		studentNumTextField.setText("");
	}
	
	private void clearPanel(){
		viewPanel.removeAll();
		viewPanel.updateUI();
		viewPanel.repaint();
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
}
