package com.wsx.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.wsx.dao.ClassDao;
import com.wsx.model.StudentClass;
import com.wsx.util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AddStudentClassFrm extends JInternalFrame {
	private JTextField classNameTextField;
	private JTextArea classInfoTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentClassFrm frame = new AddStudentClassFrm();
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
	public AddStudentClassFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u6DFB\u52A0\u73ED\u7EA7\u4FE1\u606F");
		setBounds(100, 100, 493, 361);
		
		JLabel lblNewLabel = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/image/\u73ED\u7EA7\u540D\u79F0.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		classNameTextField = new JTextField();
		classNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u73ED\u7EA7\u4FE1\u606F\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/image/\u73ED\u7EA7\u4ECB\u7ECD.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		JButton submitButton = new JButton("\u63D0  \u4EA4");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				submitClass(ae);
			}
		});
		submitButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		submitButton.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/image/\u786E\u8BA4.png")));
		
		JButton resetButton = new JButton("\u91CD  \u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/image/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		classInfoTextArea = new JTextArea();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(61)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(classInfoTextArea, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(classNameTextField))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(107)
							.addComponent(submitButton)
							.addGap(56)
							.addComponent(resetButton)))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(classInfoTextArea, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addGap(39))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void submitClass(ActionEvent ae) {
		// TODO 自动生成的方法存根
		String className = classNameTextField.getText().toString();
		String classInfo = classInfoTextArea.getText().toString();
		if (StringUtil.isEmpty(className)) {
			JOptionPane.showMessageDialog(this, "班级名称不能为空！");
			return;
		}
		StudentClass scl = new StudentClass();
		scl.setName(className);
		scl.setInfo(classInfo);
		ClassDao classDao = new ClassDao();
		if (classDao.addClass(scl)) {
			JOptionPane.showMessageDialog(this, "班级添加成功！");
		}else {
			JOptionPane.showMessageDialog(this, "班级添加失败！");
		}
		classDao.closeDao();
		resetValue(ae);
	}

	protected void resetValue(ActionEvent e) {
		// TODO 自动生成的方法存根
		classNameTextField.setText("");
		classInfoTextArea.setText("");
	}
}
