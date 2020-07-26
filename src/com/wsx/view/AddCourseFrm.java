package com.wsx.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.wsx.dao.CourseDao;
import com.wsx.dao.StudentDao;
import com.wsx.dao.TeacherDao;
import com.wsx.model.Course;
import com.wsx.model.Student;
import com.wsx.model.StudentClass;
import com.wsx.model.Teacher;
import com.wsx.util.StringUtil;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddCourseFrm extends JInternalFrame {
	private JTextField CourseNameTextField;
	private JTextField studentNumTextField;
	private JTextArea courseInfoTextArea;
	private JComboBox teacherListComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourseFrm frame = new AddCourseFrm();
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
	public AddCourseFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u6DFB\u52A0\u8BFE\u7A0B");
		setBounds(100, 100, 498, 399);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		CourseNameTextField = new JTextField();
		CourseNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/image/\u8001\u5E08.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		teacherListComboBox = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/image/\u4EBA\u6570.png")));
		
		studentNumTextField = new JTextField();
		studentNumTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_3.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/image/\u4ECB\u7ECD.png")));
		
		courseInfoTextArea = new JTextArea();
		
		JButton addCourseButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addCourseAct(ae);
			}
		});
		addCourseButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/image/\u786E\u8BA4.png")));
		addCourseButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton resetButton = new JButton("\u91CD    \u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
			
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/image/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseInfoTextArea, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentNumTextField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(teacherListComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CourseNameTextField, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
					.addGap(82))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(addCourseButton)
					.addGap(46)
					.addComponent(resetButton)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(CourseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(teacherListComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(courseInfoTextArea, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(addCourseButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();
	}

	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		CourseNameTextField.setText("");
		courseInfoTextArea.setText("");
		studentNumTextField.setText("");
		teacherListComboBox.setSelectedIndex(0);
		
	}

	protected void addCourseAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		String courseName = CourseNameTextField.getText().toString();
		String courseInfo = courseInfoTextArea.getText().toString();
		Teacher selectedTeacher = (Teacher)teacherListComboBox.getSelectedItem();
		int studentMaxNum = 0;
		try {
			studentMaxNum = Integer.parseInt(studentNumTextField.getText().toString());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(this, "只能输入数字！");
			return;
		}
		if (StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "请输入课程名！");
			return;
		}
		if(studentMaxNum <= 0){
			JOptionPane.showMessageDialog(this, "请输入正确的学生数量！");
			return;
		}
		Course course = new Course();
		//以下四个要与CourseDao中的四个相对应
		course.setName(courseName);
		course.setMax_student_num(studentMaxNum);
		course.setInfo(courseInfo);
		course.setTeacher_Id(selectedTeacher.getId());
		CourseDao courseDao = new CourseDao();
		if(courseDao.addCourse(course)){
			JOptionPane.showMessageDialog(this, "课程添加成功！");
		}else{
			JOptionPane.showMessageDialog(this, "课程添加失败！");
		}
		resetValue(ae);
	}
	
	private void setTeacherCombox(){
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = teacherDao.getTeacherList(new Teacher());
		teacherDao.closeDao();
		for(Teacher teacher : teacherList) {
			teacherListComboBox.addItem(teacher);
		}
		
	}
}
