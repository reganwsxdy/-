package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.wsx.dao.TeacherDao;
import com.wsx.model.Teacher;
import com.wsx.util.StringUtil;

public class AddTeacherFrm extends JInternalFrame {
	private JTextField teacherNameTextField;
	private JTextField teacherTitleTextField;
	private JTextField teacherAgeTextField;
	private JRadioButton teacherSexMaleRadioButton;
	private JRadioButton teacherSexFemaleRadioButton;
	private JPasswordField teacherPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTeacherFrm frame = new AddTeacherFrm();
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
	public AddTeacherFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u6DFB\u52A0\u6559\u5E08");
		setBounds(100, 100, 475, 379);
		
		JLabel lblNewLabel = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u8001\u5E08.png")));
		
		teacherNameTextField = new JTextField();
		teacherNameTextField.setColumns(10);
		
		JLabel label = new JLabel("\u6559\u5E08\u6027\u522B\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u6027\u522B.png")));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		teacherSexMaleRadioButton = new JRadioButton("\u7537");
		teacherSexMaleRadioButton.setSelected(true);
		
		teacherSexFemaleRadioButton = new JRadioButton("\u5973");
		
		JLabel lblNewLabel_1 = new JLabel("\u6559\u5E08\u804C\u79F0\uFF1A");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u804C\u79F0\u8BC4\u5B9A.png")));
		
		teacherTitleTextField = new JTextField();
		teacherTitleTextField.setColumns(10);
		buttonGroup.add(teacherSexMaleRadioButton);
		buttonGroup.add(teacherSexFemaleRadioButton);
		
		JLabel lblNewLabel_2 = new JLabel("\u6559\u5E08\u5E74\u9F84\uFF1A");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_2.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u5E74\u9F84.png")));
		
		teacherAgeTextField = new JTextField();
		teacherAgeTextField.setColumns(10);
		
		JButton submitButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addTeacherAct(ae);
			}
		});
		submitButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton resetButton = new JButton("\u91CD  \u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel_3 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/\u5BC6\u7801.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		teacherPasswordField = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(teacherAgeTextField))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(teacherTitleTextField))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(teacherSexMaleRadioButton)
									.addGap(18)
									.addComponent(teacherSexFemaleRadioButton))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(teacherNameTextField, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(teacherPasswordField))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(submitButton)
							.addGap(37)
							.addComponent(resetButton)))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(teacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(teacherSexMaleRadioButton)
						.addComponent(teacherSexFemaleRadioButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(teacherTitleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(teacherAgeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(teacherPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(submitButton)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(resetButton)
							.addGap(14))))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		teacherNameTextField.setText("");
		teacherTitleTextField.setText("");
		teacherAgeTextField.setText("");
		teacherSexMaleRadioButton.setSelected(true);
		teacherPasswordField.setText("");
	}

	protected void addTeacherAct(ActionEvent ae) {
		// TODO 自动生成的方法存根,添加教师信息
		String teacherName = teacherNameTextField.getText().toString();
		String teacherSex =  teacherSexMaleRadioButton.isSelected() ? teacherSexMaleRadioButton.getText().toString() : teacherSexFemaleRadioButton.getText().toString();
		//实现单选
		String teacherTitle = teacherTitleTextField.getText().toString();
		String teacherPassword = teacherPasswordField.getText().toString();
		int teacherAge = 0;
		try {
			teacherAge = Integer.parseInt(teacherAgeTextField.getText().toString());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(this, "年龄只允许输入数字！");
			return;
		}
		if (StringUtil.isEmpty(teacherName)) {
			JOptionPane.showMessageDialog(this, "请输入姓名！");
			return;
		}
		if(StringUtil.isEmpty(teacherTitle)){
			JOptionPane.showMessageDialog(this, "职称不能为空！");
			return;
		}
		if(teacherAge == 0 || teacherAge < 0){
			JOptionPane.showMessageDialog(this, "请输入正确的年龄！");
			return;
		}
		if(StringUtil.isEmpty(teacherPassword)){
			JOptionPane.showMessageDialog(this, "密码不能为空！");
			return;
		}
		Teacher teacher = new Teacher();
		teacher.setName(teacherName);
		teacher.setSex(teacherSex);
		teacher.setTitle(teacherTitle);
		teacher.setAge(teacherAge);
		teacher.setPassword(teacherPassword);
		TeacherDao teacherDao = new TeacherDao();
		if(teacherDao.addTeacher(teacher)){
			JOptionPane.showMessageDialog(this, "教师添加成功！");
		}else{
			JOptionPane.showMessageDialog(this, "教师添加失败！");
		}
		resetValue(ae);
	}

}
