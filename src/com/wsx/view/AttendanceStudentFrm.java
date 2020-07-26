package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.AttendanceDao;
import com.wsx.dao.CourseDao;
import com.wsx.dao.SelectedCourseDao;
import com.wsx.model.Attendance;
import com.wsx.model.Course;
import com.wsx.model.SelectedCourse;
import com.wsx.model.Student;
import com.wsx.util.Chooser;
import com.wsx.util.DateFormatUtil;
import java.awt.event.ActionListener;

public class AttendanceStudentFrm extends JInternalFrame {
	private JTextField searchAttendanceDateTextField;
	private JTable attendancedListTable;
	private JComboBox addSelectedCourseComboBox;
	private JComboBox searchSelectedComboBox;
	private List<Course> courseList = new ArrayList<Course>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceStudentFrm frame = new AttendanceStudentFrm();
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
	public AttendanceStudentFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u5B66\u751F\u7B7E\u5230\u9762\u677F");
		setBounds(100, 100, 732, 576);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		
		addSelectedCourseComboBox = new JComboBox();
		
		JButton addAttendanceButton = new JButton("\u786E\u8BA4\u7B7E\u5230");
		addAttendanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addAttendanceAct(ae);
			}
		});
		addAttendanceButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		addAttendanceButton.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/image/\u786E\u8BA4.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5DF2\u7B7E\u5230\u8BFE\u7A0B\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(100)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(addSelectedCourseComboBox, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
									.addGap(89)
									.addComponent(addAttendanceButton))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addAttendanceButton)
						.addComponent(addSelectedCourseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		searchSelectedComboBox = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("\u65E5\u671F\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/image/\u65E5\u671F.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		searchAttendanceDateTextField = new JTextField();
		searchAttendanceDateTextField.setColumns(10);
		
		JButton searchAttendanceButton = new JButton("\u67E5\u8BE2");
		searchAttendanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchAct(ae);
			}
		});
		searchAttendanceButton.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/image/\u641C\u7D22.png")));
		searchAttendanceButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchSelectedComboBox, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchAttendanceDateTextField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchAttendanceButton)))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(searchSelectedComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(searchAttendanceDateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchAttendanceButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		attendancedListTable = new JTable();
		attendancedListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7B7E\u5230ID", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u7B7E\u5230\u65E5\u671F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(attendancedListTable);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		initTable();
		Chooser.getInstance().register(searchAttendanceDateTextField);

	}
	
	protected void searchAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		Student student = (Student)MainFrm.userObject;
		Course course = (Course)searchSelectedComboBox.getSelectedItem();
		String dateString = searchAttendanceDateTextField.getText().toString();
		Attendance attendance = new Attendance();
		attendance.setAttendance_date(dateString);
		attendance.setStudent_id(student.getId());
		attendance.setCourse_id(course.getId());
		getAttendancedList(attendance);
	}

	protected void addAttendanceAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		Student student = (Student)MainFrm.userObject;
		Course course = (Course)addSelectedCourseComboBox.getSelectedItem();
		String dateString = DateFormatUtil.getDateString(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		Attendance attendance = new Attendance();
		attendance.setAttendance_date(dateString);
		attendance.setStudent_id(student.getId());
		attendance.setCourse_id(course.getId());
		AttendanceDao attendanceDao = new AttendanceDao();
		if(attendanceDao.isAttendanced(attendance)){
			JOptionPane.showMessageDialog(this, "已经签到，请勿重复签到！");
			return;
		}
		if(attendanceDao.addAttendance(attendance)){
			JOptionPane.showMessageDialog(this, "签到成功！");
		}else{
			JOptionPane.showMessageDialog(this, "签到失败！");
		}
		attendanceDao.closeDao();
		initTable();
	}
	
	private void setCourseCombox(){//显示课程信息
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		Student student = (Student)MainFrm.userObject;
		SelectedCourse sc = new SelectedCourse();
		sc.setStudent_id(student.getId());
		SelectedCourseDao scDao = new SelectedCourseDao();
		List<SelectedCourse> selectedCourseList = scDao.getSelectedCourseList(sc);
		for (SelectedCourse selectedCourse : selectedCourseList) {
			addSelectedCourseComboBox.addItem(getCourseById(selectedCourse.getCourse_id()));
			searchSelectedComboBox.addItem(getCourseById(selectedCourse.getCourse_id()));
		}
	}
	
	private Course getCourseById(int id){//因为courseList是根据Course创建的列表变量，因此声明函数类型为course
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
	
	private void initTable(){
		Student student = (Student)MainFrm.userObject;
		Attendance attendance = new Attendance();
		attendance.setStudent_id(student.getId());
		getAttendancedList(attendance);
	}
	private void getAttendancedList(Attendance attendance){
		Student student = (Student)MainFrm.userObject;
		AttendanceDao attendanceDao = new AttendanceDao();
		List<Attendance> attendancedList = attendanceDao.getAttendancedList(attendance);
		DefaultTableModel dft = (DefaultTableModel) attendancedListTable.getModel();
		dft.setRowCount(0);
		for (Attendance a : attendancedList) {
			Vector v = new Vector();
			v.add(a.getId());
			v.add(student.getName());
			v.add(getCourseById(a.getCourse_id()));
			v.add(a.getAttendance_date());
			dft.addRow(v);
		}
		attendanceDao.closeDao();
	}
	
}
