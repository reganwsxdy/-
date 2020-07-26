package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.ClassDao;
import com.wsx.dao.StudentDao;
import com.wsx.model.Student;
import com.wsx.model.StudentClass;
import com.wsx.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageStudentFrm extends JInternalFrame {
	private JTextField searchStudentNameTextField;
	private JTable studentListTable;
	private JTextField editStudentNameTextField;
	private JPasswordField editStudentPasswordField;
	private JComboBox searchStudentComboBox;
	private List<StudentClass> studentClassList;
	private JComboBox editStudentClassComboBox;
	private ButtonGroup editSexButtonGroup;
	private JRadioButton editStudentSexMaleRadioButton;
	private JRadioButton editStudentSexFemaleRadioButton;
	private JRadioButton editStudentSexUnKnowRadioButton;
	private JButton deleteStudentButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStudentFrm frame = new ManageStudentFrm();
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
	public ManageStudentFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 926, 611);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		
		searchStudentNameTextField = new JTextField();
		searchStudentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u73ED\u7EA7\u540D\u79F0.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		searchStudentComboBox = new JComboBox();
		
		JButton searchButton = new JButton("\u67E5  \u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchStudent(ae);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_2.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		
		editStudentNameTextField = new JTextField();
		editStudentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u73ED\u7EA7\u540D\u79F0.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editStudentClassComboBox = new JComboBox();
		
		JLabel lblNewLabel_4 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		lblNewLabel_4.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u6027\u522B.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editSexButtonGroup = new ButtonGroup();
		editStudentSexMaleRadioButton = new JRadioButton("\u7537");
		editStudentSexMaleRadioButton.setSelected(true);
		
		editStudentSexFemaleRadioButton = new JRadioButton("\u5973");
		
		editStudentSexUnKnowRadioButton = new JRadioButton("\u4FDD\u5BC6");
		
		editSexButtonGroup.add(editStudentSexMaleRadioButton);
		editSexButtonGroup.add(editStudentSexFemaleRadioButton);
		editSexButtonGroup.add(editStudentSexUnKnowRadioButton);
		
		JLabel lblNewLabel_5 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		lblNewLabel_5.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u5BC6\u7801.png")));
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editStudentPasswordField = new JPasswordField();
		
		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				submitEditAct(ae);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u786E\u8BA4.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		deleteStudentButton = new JButton("\u5220\u9664\u5B66\u751F");
		deleteStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteStudent(ae);
			}
		});
		deleteStudentButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/\u5220\u9664.png")));
		deleteStudentButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(78)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(searchStudentNameTextField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
									.addGap(50)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(searchButton))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editStudentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editStudentNameTextField, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editStudentSexMaleRadioButton)
									.addGap(18)
									.addComponent(editStudentSexFemaleRadioButton)
									.addGap(18)
									.addComponent(editStudentSexUnKnowRadioButton))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editStudentPasswordField)))
							.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(deleteStudentButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(submitEditButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchStudentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(59)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(editStudentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(editStudentSexMaleRadioButton)
						.addComponent(editStudentSexFemaleRadioButton)
						.addComponent(editStudentSexUnKnowRadioButton)
						.addComponent(submitEditButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(editStudentClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(editStudentPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteStudentButton))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		studentListTable = new JTable();
		studentListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SelecTableRow(arg0);
			}
		});
		studentListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751F\u7F16\u53F7", "\u5B66\u751F\u59D3\u540D", "\u6240\u5C5E\u73ED\u7EA7", "\u5B66\u751F\u6027\u522B", "\u767B\u5F55\u5BC6\u7801"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(studentListTable);
		getContentPane().setLayout(groupLayout);
		setStudentClassInfo();
		setTable(new Student());
		setAuthority();
	}
	protected void submitEditAct(ActionEvent ae) {
		// TODO 自动生成的方法存根,实现修改学生信息功能
		int row = studentListTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的学生信息");
			return;
		}
		String studentName = editStudentNameTextField.getText().toString();
		String studentPassword = editStudentPasswordField.getText().toString();
		if (StringUtil.isEmpty(studentName)) {
			JOptionPane.showMessageDialog(this, "请修改学生姓名！");
			return;
		}
		if (StringUtil.isEmpty(studentPassword)) {
			JOptionPane.showMessageDialog(this, "请修改密码！");
			return;
		}
		
		Student student = new  Student();
		student.setName(studentName);
		student.setPassword(studentPassword);
		StudentClass sc = (StudentClass)editStudentClassComboBox.getSelectedItem();
		student.setClassId(sc.getId());
		student.setId(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()));
		if(editStudentSexMaleRadioButton.isSelected())student.setSex(editStudentSexMaleRadioButton.getText().toString());
		if(editStudentSexFemaleRadioButton.isSelected())student.setSex(editStudentSexFemaleRadioButton.getText().toString());
		if(editStudentSexUnKnowRadioButton.isSelected())student.setSex(editStudentSexUnKnowRadioButton.getText().toString());
		StudentDao studentDao = new StudentDao();
		if (studentDao.update(student)) {
			JOptionPane.showMessageDialog(this, "修改成功！");
		}
		else {
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		studentDao.closeDao();
		setTable(new Student());
	}

	protected void deleteStudent(ActionEvent ae) {
		// TODO 自动生成的方法存根,实现删除学生操作
		int row = studentListTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要删除的学生！");
			return;
		}
		if (JOptionPane.showConfirmDialog(this, "确认要删除吗？") != JOptionPane.OK_OPTION) {
			return;
		}
		StudentDao studentDao = new StudentDao();
		if (studentDao.delete(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()))) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		studentDao.closeDao();
		setTable(new Student());//删除后刷新列表
		
	}
	
	protected void SelecTableRow(MouseEvent me) {
		// TODO 自动生成的方法存根,将选中的内容显示到文本框中，主要方法为gerValueAt()
		DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();
		editStudentNameTextField.setText(dft.getValueAt(studentListTable.getSelectedRow(), 1).toString());
		editStudentPasswordField.setText(dft.getValueAt(studentListTable.getSelectedRow(), 4).toString());
		String className = dft.getValueAt(studentListTable.getSelectedRow(), 2).toString();
		for (int i = 0; i < editStudentClassComboBox.getItemCount(); i++) {
			StudentClass sc = (StudentClass)editStudentClassComboBox.getItemAt(i);
			if (className.equals(sc.getName())) {
				editStudentClassComboBox.setSelectedIndex(i);
			}
			
		}
		String sex = dft.getValueAt(studentListTable.getSelectedRow(), 3).toString();
		editSexButtonGroup.clearSelection();
		if(sex.equals(editStudentSexMaleRadioButton.getText()))editStudentSexMaleRadioButton.setSelected(true);
		if(sex.equals(editStudentSexFemaleRadioButton.getText()))editStudentSexFemaleRadioButton.setSelected(true);
		if(sex.equals(editStudentSexUnKnowRadioButton.getText()))editStudentSexUnKnowRadioButton.setSelected(true);
		
	}

	protected void searchStudent(ActionEvent ae) {
		// TODO 自动生成的方法存根,实现搜索功能
		Student student = new Student();
		student.setName(searchStudentNameTextField.getText().toString());
		StudentClass sc = (StudentClass)searchStudentComboBox.getSelectedItem();
		student.setClassId(sc.getId());
		setTable(student);
	}

	private void setTable(Student student) {//从ClassManageFrm直接拷贝过来,再进行修改，显示数据库中的学生信息到pane中
		if ("学生".equals(MainFrm.userType.getName())) {
			Student s = (Student)MainFrm.userObject;
			student.setName(s.getName());//只能查询到本人的信息
		}
		DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();//获取列表的model
		dft.setRowCount(0);//清空列表
		StudentDao studentDao = new StudentDao();
		List<Student> studentList = studentDao.getStudentList(student);
		for (Student s : studentList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(s.getName());
			v.add(getClassNameById(s.getClassId()));
			v.add(s.getSex());
			v.add(s.getPassword());
			dft.addRow(v);
		}
		studentDao.closeDao();
	}
	private void setStudentClassInfo() {//实现把班级编号放入学生列表中
		ClassDao classDao = new ClassDao();
		studentClassList = classDao.getClassList(new StudentClass());
		for (StudentClass sc : studentClassList) {
			searchStudentComboBox.addItem(sc);
			editStudentClassComboBox.addItem(sc);
		} 
		classDao.closeDao();
	}
	private String getClassNameById(int id){//把表中的id（编号）改为班级名称
		for (StudentClass sc : studentClassList) {
			if(sc.getId() == id)return sc.getName();
		}
		return "";
	}
	private void setAuthority() {
		if ("学生".equals(MainFrm.userType.getName())) {
			Student s = (Student)MainFrm.userObject;
			searchStudentNameTextField.setText(s.getName());
			searchStudentNameTextField.setEnabled(false);
			deleteStudentButton.setEnabled(false);
			for(int i=0;i<searchStudentComboBox.getItemCount();i++){
				StudentClass sc = (StudentClass) searchStudentComboBox.getItemAt(i);
				if(sc.getId() == s.getClassId()){
					searchStudentComboBox.setSelectedIndex(i);
					break;
				}
			}
			searchStudentComboBox.setEnabled(false);
			for(int i=0;i<editStudentClassComboBox.getItemCount();i++){
				StudentClass sc = (StudentClass) editStudentClassComboBox.getItemAt(i);
				if(sc.getId() == s.getClassId()){
					editStudentClassComboBox.setSelectedIndex(i);
					break;
				}
			}
			editStudentClassComboBox.setEnabled(false);
		}
	}
}
