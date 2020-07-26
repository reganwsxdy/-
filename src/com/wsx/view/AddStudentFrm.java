package com.wsx.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.wsx.dao.ClassDao;
import com.wsx.dao.StudentDao;
import com.wsx.model.Student;
import com.wsx.model.StudentClass;
import com.wsx.util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudentFrm extends JInternalFrame {
	private JTextField studentNameTextField;
	private JPasswordField studentPasswordField;
	private JComboBox studentClassComboBox;
	private ButtonGroup sexButtonGroup;
	private JRadioButton studentSexMaleRadioButton;
	private JRadioButton studentSexFemaleRadioButton;
	private JRadioButton studentSexUnKnownRadioButton;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentFrm frame = new AddStudentFrm();
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
	public AddStudentFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u6DFB\u52A0\u5B66\u751F");
		setBounds(100, 100, 450, 316);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/\u73ED\u7EA7\u7BA1\u7406.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentClassComboBox = new JComboBox();
		studentClassComboBox.setModel(new DefaultComboBoxModel(new String[] {}));
		
		JLabel lblNewLabel_2 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/\u5BC6\u7801.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentPasswordField = new JPasswordField();
		
		JLabel lblNewLabel_3 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/\u6027\u522B.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentSexMaleRadioButton =  new JRadioButton("\u7537");
		studentSexMaleRadioButton.setSelected(true);
		
		studentSexFemaleRadioButton = new JRadioButton("\u5973");
		
		studentSexUnKnownRadioButton = new JRadioButton("\u4FDD\u5BC6");
		
		sexButtonGroup = new ButtonGroup();//直接代码创建buttongroup对象，将选择项放入，就能自动实现单选
		sexButtonGroup.add(studentSexMaleRadioButton);
		sexButtonGroup.add(studentSexFemaleRadioButton);
		sexButtonGroup.add(studentSexUnKnownRadioButton);
		
		
		
		JButton submitButton = new JButton("\u786E\u8BA4");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				studentAddAct(ae);
			}
		});
		submitButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		submitButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/\u786E\u8BA4.png")));
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				 resetValue(ae);
			}
		});
		resetButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		resetButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/\u91CD\u7F6E.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentPasswordField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(submitButton)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(studentSexMaleRadioButton)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(studentSexFemaleRadioButton)
									.addGap(18)
									.addComponent(studentSexUnKnownRadioButton))
								.addComponent(resetButton))))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(studentClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(studentPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(studentSexMaleRadioButton)
						.addComponent(studentSexFemaleRadioButton)
						.addComponent(studentSexUnKnownRadioButton))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setStudentClassInfo();

	}

	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		studentNameTextField.setText("");
		studentPasswordField.setText("");
		studentClassComboBox.setSelectedIndex(0);
		sexButtonGroup.clearSelection();
		studentSexMaleRadioButton.setSelected(true);
	}

	protected void studentAddAct(ActionEvent ae) {
		// TODO 自动生成的方法存根
		String studenName = studentNameTextField.getText().toString();
		String studentPassword = studentPasswordField.getText().toString();
		if (StringUtil.isEmpty(studenName)) {
			JOptionPane.showMessageDialog(this, "请填写学生姓名！");
			return;
		}
		if (StringUtil.isEmpty(studentPassword)) {
			JOptionPane.showMessageDialog(this, "请填写密码！");
			return;
		}
		StudentClass sc = (StudentClass)studentClassComboBox.getSelectedItem();
		String sex = studentSexMaleRadioButton.isSelected() ? studentSexMaleRadioButton.getText() : (studentSexFemaleRadioButton.isSelected() ? studentSexFemaleRadioButton.getText() : studentSexUnKnownRadioButton.getText());
		Student student = new Student();
		student.setName(studenName);
		student.setClassId(sc.getId());
		student.setPassword(studentPassword);
		student.setSex(sex);
		StudentDao studentDao = new StudentDao();
		if (studentDao.addStudent(student)) {
			JOptionPane.showMessageDialog(this, "添加成功！");
		}else {
			JOptionPane.showMessageDialog(this, "添加失败！");
		}
		resetValue(ae);
	}
	
	private void setStudentClassInfo() {
		ClassDao classDao = new ClassDao();
		List<StudentClass> classList = classDao.getClassList(new StudentClass());
		for (StudentClass sc : classList) {
			studentClassComboBox.addItem(sc);
		} 
		classDao.closeDao();
	}
}
