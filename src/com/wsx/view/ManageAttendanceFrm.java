package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.AttendanceDao;
import com.wsx.dao.CourseDao;
import com.wsx.dao.SelectedCourseDao;
import com.wsx.dao.StudentDao;
import com.wsx.model.Attendance;
import com.wsx.model.Course;
import com.wsx.model.Student;
import com.wsx.model.Teacher;
import com.wsx.util.DateFormatUtil;

public class ManageAttendanceFrm extends JInternalFrame {
	private JTable attendancedListTable;
	private JComboBox studentComboBox;
	private JComboBox courseComboBox;
	private List<Student> studentList = new ArrayList<Student>();
	private List<Course> courseList = new ArrayList<Course>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAttendanceFrm frame = new ManageAttendanceFrm();
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
	public ManageAttendanceFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u7B7E\u5230\u8003\u52E4\u7BA1\u7406");
		setBounds(100, 100, 805, 604);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageAttendanceFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentComboBox = new JComboBox();
		studentComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				studentChangedAct(ie);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageAttendanceFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				courseChangeAct(ie);
			}
		});
		
		JButton attendanceAddButton = new JButton("\u786E\u8BA4\u7B7E\u5230");
		attendanceAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addAttendanceAct(ae);
			}
		});
		attendanceAddButton.setIcon(new ImageIcon(ManageAttendanceFrm.class.getResource("/image/\u786E\u8BA4.png")));
		attendanceAddButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5DF2\u7B7E\u5230\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(attendanceAddButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 639, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(attendanceAddButton))
					.addGap(28)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton cancelButton = new JButton("\u6DFB\u52A0\u7F3A\u5E2D");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cancelAttendanceAct(ae);
			}
		});
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		cancelButton.setIcon(new ImageIcon(ManageAttendanceFrm.class.getResource("/image/\u5220\u9664.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(cancelButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(cancelButton)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		attendancedListTable = new JTable();
		attendancedListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7B7E\u5230ID", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u7B7E\u5230\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(attendancedListTable);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		setStudentCombox();

	}

	protected void cancelAttendanceAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		if(JOptionPane.showConfirmDialog(this, "是否添加缺席？") != JOptionPane.OK_OPTION)return;
		int row = attendancedListTable.getSelectedRow();//返回第一个选定行的索引，如果没有选择行，则返回-1
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择缺席的学生！");
			return;
		}
		int attendanceId = Integer.parseInt(attendancedListTable.getValueAt(row, 0).toString());//将字符串参数解析为带符号的十进制整数
		AttendanceDao attendanceDao = new AttendanceDao();
		if(attendanceDao.delete(attendanceId)){
			JOptionPane.showMessageDialog(this, "添加缺席成功！");
		}else{
			JOptionPane.showMessageDialog(this, "添加缺席失败！");
		}
		setTable();
	}

	protected void addAttendanceAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		Student student = (Student)studentComboBox.getSelectedItem();
		Course course = (Course)courseComboBox.getSelectedItem();
		String dateString = DateFormatUtil.getDateString(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		Attendance attendance = new Attendance();
		attendance.setAttendance_date(dateString);
		attendance.setStudent_id(student.getId());
		attendance.setCourse_id(course.getId());
		AttendanceDao attendanceDao = new AttendanceDao();
		if(attendanceDao.isAttendanced(attendance)){
			JOptionPane.showMessageDialog(this, "该学生已签到！");
			return;
		}
		if(attendanceDao.addAttendance(attendance)){
			JOptionPane.showMessageDialog(this, "签到成功！");
		}else{
			JOptionPane.showMessageDialog(this, "签到失败！");
		}
		attendanceDao.closeDao();
		setTable();
	}

	
	protected void studentChangedAct(ItemEvent ie) {
		// TODO 自动生成的方法存根
		if(ie.getStateChange() == ItemEvent.SELECTED){
			setTable();
		}
	}
	
	protected void courseChangeAct(ItemEvent ie) {
		// TODO 自动生成的方法存根
		if(ie.getStateChange() == ItemEvent.SELECTED){
			setStudentCombox();
		}
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
	
	private List<Student> getSelectedCourseStudentList(Course course){//配合学生combobox使用，获取对应学生的课程信息
		SelectedCourseDao scDao = new SelectedCourseDao();
		List<Student> selectedCourseStudentList = scDao.getSelectedCourseStudentList(course);
		return selectedCourseStudentList;
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
	
	private void setTable(){//刷新attendanceeListTable 
		Student student = (Student)studentComboBox.getSelectedItem();
		DefaultTableModel dft = (DefaultTableModel) attendancedListTable.getModel();
		dft.setRowCount(0);
		AttendanceDao attendanceDao = new AttendanceDao();
		Attendance attendance = new Attendance();
		attendance.setStudent_id(student.getId());
		List<Attendance> attendanceList = attendanceDao.getAttendancedList(attendance);
		for (Attendance a : attendanceList) {
			Vector v = new Vector();
			v.add(a.getId());
			v.add(student.getName());
			v.add(getCourseById(a.getCourse_id()));
			v.add(a.getAttendance_date());
			dft.addRow(v);
		}
		attendanceDao.closeDao();
	}
	
	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
}
