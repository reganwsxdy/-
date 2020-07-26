package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.wsx.dao.CourseDao;
import com.wsx.dao.ScoreDao;
import com.wsx.dao.SelectedCourseDao;
import com.wsx.dao.StudentDao;
import com.wsx.model.Course;
import com.wsx.model.Score;
import com.wsx.model.Student;
import com.wsx.model.Teacher;

public class ManageScoreFrm extends JInternalFrame {
	private JTable scoreListTable;
	private JTextField editScoreTextField;
	private JComboBox studentComboBox;
	private JComboBox courseComboBox;
	private List<Student> studentList;
	private List<Course> courseList;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageScoreFrm frame = new ManageScoreFrm();
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
	public ManageScoreFrm() {
		
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u6210\u7EE9\u7BA1\u7406\u754C\u9762");
		setBounds(100, 100, 792, 658);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentComboBox = new JComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		
		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				courseChangeAct(ie);
			}
		});
		
		JButton searchButton = new JButton("\u67E5  \u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = (Student)studentComboBox.getSelectedItem();
				Course course = (Course)courseComboBox.getSelectedItem();
				Score score = new Score();
				score.setStudent_id(student.getId());
				score.setCourse_id(course.getId());
				setTable(score);
			}
		});
		searchButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		searchButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/image/\u641C\u7D22.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FEE\u6539\u6210\u7EE9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(searchButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(66)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_2 = new JLabel("\u6210\u7EE9\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/image/\u6210\u7EE9.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editScoreTextField = new JTextField();
		editScoreTextField.setColumns(10);
		
		JButton confirmEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		confirmEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editSubmitAct(ae);
			}
		});
		confirmEditButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/image/\u786E\u8BA4.png")));
		confirmEditButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton deleteButton = new JButton("\u5220\u9664\u6210\u7EE9");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteAct(ae);
			}
		});
		deleteButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/image/\u5220\u9664.png")));
		deleteButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(46)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editScoreTextField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(confirmEditButton)
					.addGap(34)
					.addComponent(deleteButton)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(editScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteButton)
						.addComponent(confirmEditButton))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		scoreListTable = new JTable();
		scoreListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				tableItemClick(me);
			}
		});
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
		setCourseCombox();//注意这里，由于是根据老师的课程去找学生，
		//因此需要先将课程导入，然后再根据combox里的课程去找对应报过的学生
		//这里setCourseCombox()和setStudengtCombox()的顺序不能反
		setStudentCombox();
		initTable();

	}
	
	protected void deleteAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		int row = scoreListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择将要删除的成绩！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "确定删除该成绩吗？") == JOptionPane.OK_OPTION){
			int scoreId = Integer.parseInt(scoreListTable.getValueAt(row, 0).toString());
			ScoreDao scoreDao = new ScoreDao();
			if(scoreDao.delete(scoreId)){
				JOptionPane.showMessageDialog(this, "删除成功！");
				editScoreTextField.setText("");
				initTable();
			}else{
				JOptionPane.showMessageDialog(this, "删除失败！");
			}
			scoreDao.closeDao();
		}
	}

	protected void editSubmitAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		int row = scoreListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择将要修改的成绩！");
			return;
		}
		int scoreId = Integer.parseInt(scoreListTable.getValueAt(row, 0).toString());
		int score = Integer.parseInt(editScoreTextField.getText().toString());
		ScoreDao scoreDao = new ScoreDao();
		if(scoreDao.update(scoreId, score)){//上面调用的是删除函数，这里调用修改函数
			JOptionPane.showMessageDialog(this, "修改成功！");
			editScoreTextField.setText("");
			initTable();
		}else{
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		scoreDao.closeDao();
	}

	protected void courseChangeAct(ItemEvent ie) {
		// TODO 自动生成的方法存根
		if(ie.getStateChange() == ItemEvent.SELECTED){
			setStudentCombox();
		}
	}

	protected void tableItemClick(MouseEvent me) {
		// TODO 自动生成的方法存根
		String score = (scoreListTable.getValueAt(scoreListTable.getSelectedRow(), 3).toString());
		editScoreTextField.setText(score);
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
	
	private void initTable(){
		getScoreList(new Score());
	}
	
	private void setTable(Score score){
		getScoreList(score);
	}
	
	private void getScoreList(Score score){
		ScoreDao scoreDao = new ScoreDao();
		List<Score> scoreList = scoreDao.getScoreList(score);
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		for (Score s : scoreList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(getStudentNameById(s.getStudent_id()));
			v.add(getCourseById(s.getCourse_id()));
			v.add(s.getScore());
			dft.addRow(v);
		}
		scoreDao.closeDao();
	}
	
	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
	
	private List<Student> getSelectedCourseStudentList(Course course){//配合学生combobox使用，获取对应学生的课程信息
		SelectedCourseDao scDao = new SelectedCourseDao();
		List<Student> selectedCourseStudentList = scDao.getSelectedCourseStudentList(course);
		return selectedCourseStudentList;
	}
	
	private String getStudentNameById(int id){
		for(Student student :studentList){
			if(student.getId() == id)return student.getName();
		}
		return null;
	}

}
