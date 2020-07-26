package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.wsx.dao.CourseDao;
import com.wsx.dao.ScoreDao;
import com.wsx.dao.SelectedCourseDao;
import com.wsx.dao.StudentDao;
import com.wsx.model.Course;
import com.wsx.model.Score;
import com.wsx.model.Student;
import com.wsx.model.Teacher;

public class AddScoreFrm extends JInternalFrame {
	private JTextField scoreTextField;
	private JComboBox studentComboBox;
	private JComboBox courseComboBox;
	private List<Course> courseList;
	private List<Student> studentList;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddScoreFrm frame = new AddScoreFrm();
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
	public AddScoreFrm() {
		
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6210\u7EE9\u5F55\u5165\u754C\u9762");
		setBounds(100, 100, 552, 427);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		
		studentComboBox = new JComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\u4FE1\u606F\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ae) {
				courseChangeAct(ae);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("\u6240\u5F97\u6210\u7EE9\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/image/\u6210\u7EE9.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		scoreTextField = new JTextField();
		scoreTextField.setColumns(10);
		
		JButton submitButton = new JButton("\u5F55\u5165\u6210\u7EE9");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				submitAct(ae);
			}
		});
		submitButton.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/image/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scoreTextField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(142, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(216, Short.MAX_VALUE)
					.addComponent(submitButton)
					.addGap(207))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addComponent(submitButton)
					.addContainerGap(196, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		setStudentCombox();
		
		
	}
	
	
	protected void submitAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		int score = 0;
		try {
			score = Integer.parseInt(scoreTextField.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "请添加课程的分数！");
			return;
		}
		if(score <= 0){
			JOptionPane.showMessageDialog(this, "请添加正确的课程分数！");
			return;
		}
		Student student = (Student) studentComboBox.getSelectedItem();
		Course course = (Course)courseComboBox.getSelectedItem();
		Score scoreObj = new Score();
		scoreObj.setStudent_id(student.getId());
		scoreObj.setCourse_id(course.getId());
		scoreObj.setScore(score);
		ScoreDao scoreDao = new ScoreDao();
		if(scoreDao.isAdd(scoreObj)){
			JOptionPane.showMessageDialog(this, "该分数已经录入！");
			return;
		}
		if(scoreDao.addScore(scoreObj)){
			JOptionPane.showMessageDialog(this, "录入成功！");
			scoreTextField.setText("");
		}else{
			JOptionPane.showMessageDialog(this, "录入失败！");
		}
		scoreDao.closeDao();
	}

	protected void courseChangeAct(ItemEvent ae) {
		// TODO 自动生成的方法存根
		if(ae.getStateChange() == ItemEvent.SELECTED){
			setStudentCombox();
		}
		//JOptionPane.showMessageDialog(this, "changed");
		//setStudentCombox();
	}

	private void setStudentCombox(){//导出学生所选课程
		studentComboBox.removeAllItems();
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		studentDao.closeDao();
		Course course = (Course)courseComboBox.getSelectedItem();
		List<Student> selectedCourseStudentList = getSelectedCourseStudentList(course);
		for (Student student : studentList) {
			for(Student student2 : selectedCourseStudentList){
				if(student.getId() == student2.getId())
					studentComboBox.addItem(student);
			}
		}
		
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
	
	private List<Student> getSelectedCourseStudentList(Course course){//配合学生combobox使用，获取对应学生的课程信息
		SelectedCourseDao scDao = new SelectedCourseDao();
		List<Student> selectedCourseStudentList = scDao.getSelectedCourseStudentList(course);
		return selectedCourseStudentList;
	}
}
