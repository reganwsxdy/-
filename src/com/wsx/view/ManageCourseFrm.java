package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.CourseDao;
import com.wsx.dao.TeacherDao;
import com.wsx.model.Course;
import com.wsx.model.Teacher;
import com.wsx.util.StringUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageCourseFrm extends JInternalFrame {
	private JTextField searchCourseNameTextField;
	private JTable courseListTable;
	private JTextField editCourseTextField;
	private JTextField editCourseStudentNumTextField;
	private JComboBox searchTeacherComboBox;
	private List<Teacher> teacherList = new ArrayList<Teacher>();
	private JTextArea editCourseInfoTextArea;
	private JComboBox editCourseTeacherComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCourseFrm frame = new ManageCourseFrm();
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
	public ManageCourseFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u8BFE\u7A0B\u7BA1\u7406");
		setBounds(100, 100, 808, 716);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		searchCourseNameTextField = new JTextField();
		searchCourseNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u8001\u5E08.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		searchTeacherComboBox = new JComboBox();
		
		JButton searchButton = new JButton("\u67E5  \u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchCourse(ae);
			}
		});
		searchButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		searchButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u641C\u7D22.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7F16\u8F91\u8BFE\u7A0B\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchCourseNameTextField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(searchButton)))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchCourseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(33)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel courseNameLabel = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		courseNameLabel.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		courseNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editCourseTextField = new JTextField();
		editCourseTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u8001\u5E08.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editCourseTeacherComboBox = new JComboBox();
		
		JLabel lblNewLabel_4 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_4.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u4EBA\u6570.png")));
		
		editCourseStudentNumTextField = new JTextField();
		editCourseStudentNumTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_5.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u73ED\u7EA7\u4ECB\u7ECD.png")));
		
		editCourseInfoTextArea = new JTextArea();
		
		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editCourseSubmit(ae);
			}
		});
		submitEditButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		submitEditButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u786E\u8BA4.png")));
		
		JButton deleteCourseButton = new JButton("\u5220\u9664\u8BFE\u7A0B");
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteCourse(ae);
			}
		});
		deleteCourseButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/image/\u5220\u9664.png")));
		deleteCourseButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editCourseStudentNumTextField))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(courseNameLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editCourseTeacherComboBox, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editCourseInfoTextArea))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(162)
							.addComponent(submitEditButton)
							.addGap(81)
							.addComponent(deleteCourseButton)))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseNameLabel)
						.addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editCourseTeacherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(editCourseStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(editCourseInfoTextArea, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitEditButton)
						.addComponent(deleteCourseButton))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		courseListTable = new JTable();
		courseListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				selectedCourse(me);
			}
		});
		courseListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u7F16\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u8001\u5E08", "\u8BFE\u7A0B\u4EBA\u6570", "\u5DF2\u9009\u4EBA\u6570", "\u8BFE\u7A0B\u4ECB\u7ECD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		courseListTable.getColumnModel().getColumn(0).setPreferredWidth(84);
		courseListTable.getColumnModel().getColumn(1).setPreferredWidth(122);
		courseListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		courseListTable.getColumnModel().getColumn(3).setPreferredWidth(99);
		courseListTable.getColumnModel().getColumn(4).setPreferredWidth(89);
		courseListTable.getColumnModel().getColumn(5).setPreferredWidth(283);
		scrollPane.setViewportView(courseListTable);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();
		setCourseListTable(new Course());

	}

	protected void deleteCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
		int row = courseListTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要删除的课程！");
			return;
		}
		int course_Id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		CourseDao courseDao = new CourseDao();
		if (courseDao.delete(course_Id)) {
			JOptionPane.showMessageDialog(this, "删除课程成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除课程失败！");
		}
		courseDao.closeDao();
		setCourseListTable(new Course());
	}
	

	protected void editCourseSubmit(ActionEvent ae) {
		// TODO 自动生成的方法存根
		int row = courseListTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的课程信息！");
			return;
		}
		int course_Id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		Teacher teacher = (Teacher)editCourseTeacherComboBox.getSelectedItem();
		String courseName = editCourseTextField.getText().toString();
		if (StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "请输入课程名！");
		}
		
		int max_student_num = 0;
		try {
			max_student_num = Integer.parseInt(editCourseStudentNumTextField.getText().toString());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(this, "人数只允许输入数字！");
			return;
		}
		
		
		if(max_student_num <= 0){
			JOptionPane.showMessageDialog(this, "请输入正确学生人数！");
			return;
		}
		String courseInfo = editCourseInfoTextArea.getText().toString();
		Course course = new Course();
		course.setId(course_Id);
		course.setName(courseName);
		course.setTeacher_Id(teacher.getId());
		course.setMax_student_num(max_student_num);
		course.setInfo(courseInfo);
		CourseDao courseDao = new CourseDao();
		if(courseDao.update(course)){
			JOptionPane.showMessageDialog(this, "课程修改成功！");
		}else{ 
			JOptionPane.showMessageDialog(this, "课程修改失败！");
		}
		
		courseDao.closeDao();
		setCourseListTable(new Course());//刷新列表
	}

	protected void searchCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
		String searchCourseName = searchCourseNameTextField.getText().toString();
		Teacher teacher = (Teacher)searchTeacherComboBox.getSelectedItem();
		Course course = new Course();
		course.setName(searchCourseName);
		course.setTeacher_Id(teacher.getId());
		setCourseListTable(course);
	}
	
	private void setCourseListTable(Course course){
		CourseDao courseDao = new CourseDao();
		List<Course> courseList = courseDao.getCourseList(course);
		DefaultTableModel dft = (DefaultTableModel) courseListTable.getModel();
		dft.setRowCount(0);
		for (Course c : courseList) {
			Vector v = new Vector();
			v.add(c.getId());
			v.add(c.getName());
			v.add(getTeacherNameById(c.getTeacher_Id()));
			v.add(c.getMax_student_num());
			v.add(c.getSelected_num());
			v.add(c.getInfo());
			dft.addRow(v);
		}
		courseDao.closeDao();
	}
	
	private void setTeacherCombox(){//显示教师的相关信息，因为和teacher有关联，因此单独写一个方法
		TeacherDao teacherDao = new TeacherDao();
		teacherList = teacherDao.getTeacherList(new Teacher());
		teacherDao.closeDao();
		for (Teacher teacher : teacherList) {
			editCourseTeacherComboBox.addItem(teacher);
			searchTeacherComboBox.addItem(teacher);
		}
	}
	
	private String getTeacherNameById(int teacher_Id){
		String retString = "";
		for (Teacher teacher : teacherList) {
			if(teacher.getId() == teacher_Id){
				retString = teacher.getName();
				break;
			}
		}
		return retString;
	}
	
	private int getTeacherIdByName(String teacher_name){
		int retId = -1;
		for (Teacher teacher : teacherList) {
			if(teacher_name.equals(teacher.getName())){
				retId = teacher.getId();
				break;
			}
		}
		return retId;
	}
	
	protected void selectedCourse(MouseEvent me) {
		// TODO Auto-generated method stub
		int row = courseListTable.getSelectedRow();
		String couseName = courseListTable.getValueAt(row, 1).toString();
		int teacher_id = getTeacherIdByName(courseListTable.getValueAt(row, 2).toString());
		int max_student_num = Integer.parseInt(courseListTable.getValueAt(row, 3).toString());
		String couseInfo = courseListTable.getValueAt(row, 5).toString();
		editCourseTextField.setText(couseName);
		editCourseStudentNumTextField.setText(max_student_num+"");
		editCourseInfoTextArea.setText(couseInfo);
		for(int i=0;i<editCourseTeacherComboBox.getItemCount();i++){
			Teacher t = (Teacher) editCourseTeacherComboBox.getItemAt(i);
			if(t.getId() == teacher_id){
				editCourseTeacherComboBox.setSelectedIndex(i);
				break;
			}
		}
	}
}
