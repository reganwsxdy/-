package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.CourseDao;
import com.wsx.dao.ScoreDao;
import com.wsx.dao.SelectedCourseDao;
import com.wsx.model.Course;
import com.wsx.model.Score;
import com.wsx.model.SelectedCourse;
import com.wsx.model.Student;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ViewScoreFrm extends JInternalFrame {
	private JTable scoreListTable;
	private JLabel studentNameLabel;
	private JComboBox courseComboBox;
	private List<Course> courseList = new ArrayList<Course>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewScoreFrm frame = new ViewScoreFrm();
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
	public ViewScoreFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u770B");
		setBounds(100, 100, 730, 537);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ViewScoreFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentNameLabel = new JLabel("");
		studentNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("\u6240\u9009\u8BFE\u7A0B\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(ViewScoreFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				courseChangeAct(ie);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(99)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentNameLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 558, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentNameLabel)
						.addComponent(lblNewLabel_2)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		
		scoreListTable = new JTable();
		scoreListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6210\u7EE9ID", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u6210\u7EE9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(scoreListTable);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		initTable();
	}
	
	protected void courseChangeAct(ItemEvent ie) {
		// TODO 自动生成的方法存根
		setTable();
	}

	private void setCourseCombox(){
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		Student student = (Student)MainFrm.userObject;
		studentNameLabel.setText(student.getName());
		SelectedCourse sc = new SelectedCourse();
		sc.setStudent_id(student.getId());
		SelectedCourseDao scDao = new SelectedCourseDao();
		List<SelectedCourse> selectedCourseList = scDao.getSelectedCourseList(sc);
		for (SelectedCourse selectedCourse : selectedCourseList) {
			courseComboBox.addItem(getCourseById(selectedCourse.getCourse_id()));
		}
	}
	
	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
	
	private void initTable(){//初始化Table
		Student student = (Student)MainFrm.userObject;
		//Course course = (Course)courseComboBox.getSelectedItem();
		Score score = new Score();
		score.setStudent_id(student.getId());
		//score.setCourse_id(course.getId());
		getScoreList(score);
	}
	
	private void setTable(){//刷新Table，与上面相同
		Student student = (Student)MainFrm.userObject;
		Course course = (Course)courseComboBox.getSelectedItem();
		Score score = new Score();
		score.setStudent_id(student.getId());
		score.setCourse_id(course.getId());
		getScoreList(score);
	}
	
	private void getScoreList(Score score){
		Student student = (Student)MainFrm.userObject;
		ScoreDao scoreDao = new ScoreDao();
		List<Score> scoreList = scoreDao.getScoreList(score);
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		for (Score s : scoreList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(student.getName());
			v.add(getCourseById(s.getCourse_id()));
			v.add(s.getScore());
			dft.addRow(v);
		}
		scoreDao.closeDao();
	}
}
