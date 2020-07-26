package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.wsx.dao.AdminDao;
import com.wsx.dao.StudentDao;
import com.wsx.dao.TeacherDao;
import com.wsx.model.Admin;
import com.wsx.model.Student;
import com.wsx.model.Teacher;
import com.wsx.model.UserType;
import com.wsx.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPasswordFrm extends JInternalFrame {//����Ϊ�ڲ���ܣ�ֻ��MainFrm�ڲ����У��رղ��ᵼ������ܹر�

	private JPanel contentPane;
	private JPasswordField oldPasswordTextField;
	private JPasswordField newPasswordTextField;
	private JPasswordField confirmPasswordTextField;
	private JLabel currentUserLabel;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditPasswordFrm frame = new EditPasswordFrm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//  ͬ��һ������ע�ͣ����ڲ��ٵ����򿪣�Ҫ���޸������Ǹ�������������

	/**
	 * Create the frame.
	 */
	public EditPasswordFrm() {
		
		setTitle("\u4FEE\u6539\u5BC6\u7801");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);//�ɹرյĴ��ڹ����ڵ����Ŀ���ʵ�֣�
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/\u5BC6\u7801.png")));
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		oldPasswordTextField = new JPasswordField();
		oldPasswordTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/\u4FEE\u6539\u5BC6\u7801.png")));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		newPasswordTextField = new JPasswordField();
		newPasswordTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/\u4FEE\u6539\u5BC6\u7801.png")));
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		confirmPasswordTextField = new JPasswordField();
		confirmPasswordTextField.setColumns(10);
		
		JButton submitButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitEdit(e);
			}
		});
		submitButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JButton resetButton = new JButton("\u91CD    \u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JLabel lblNewLabel_3 = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_3.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/\u7528\u6237\u540D.png")));
		
		currentUserLabel = new JLabel("");
		currentUserLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(currentUserLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(confirmPasswordTextField)
								.addComponent(newPasswordTextField)
								.addComponent(oldPasswordTextField, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(87)
							.addComponent(submitButton)
							.addGap(26)
							.addComponent(resetButton)))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(currentUserLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(oldPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(newPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(confirmPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		if ("ϵͳ����Ա".equals(MainFrm.userType.getName())) {//��ȡ�������е��û����ͣ�ʹ��������ʾ�ڡ���ǰ�û���������
			
			Admin admin = (Admin)MainFrm.userObject;
			currentUserLabel.setText("��ϵͳ����Ա��"+admin.getName());
		}else if("ѧ��".equals(MainFrm.userType.getName())){
			Student student = (Student)MainFrm.userObject;
			currentUserLabel.setText("��ѧ����" + student.getName());
		}else {
			Teacher teacher = (Teacher)MainFrm.userObject;
			currentUserLabel.setText("����ʦ��" + teacher.getName());
		}
		//currentUserLabel.setText(userObject);
	}


	protected void submitEdit(ActionEvent e) {
		// TODO �Զ����ɵķ������
		String oldPassword = oldPasswordTextField.getText().toString();
		String newPassword = newPasswordTextField.getText().toString();
		String confirmPassword = confirmPasswordTextField.getText().toString();
		if (StringUtil.isEmpty(oldPassword)) {
			JOptionPane.showMessageDialog(this, "����д�����룡");
			return;
		}
		if (StringUtil.isEmpty(newPassword)) {
			JOptionPane.showMessageDialog(this, "����д�����룡");
			return;
		}
		if (StringUtil.isEmpty(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "��ȷ�������룡");
			return;
		}
		if (!newPassword.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "�¾����벻һ�£�");
			return;
		
		}
		if ("ϵͳ����Ա".equals(MainFrm.userType.getName())) {//��ȡ�������е��û�����
			AdminDao adminDao = new AdminDao();
			Admin adminTmp = new Admin();
			Admin admin = (Admin)MainFrm.userObject;
			adminTmp.setName(admin.getName());
			adminTmp.setId(admin.getId());
			adminTmp.setPassword(oldPassword);
 			JOptionPane.showMessageDialog(this, adminDao.editpassword(adminTmp, newPassword));
 			adminDao.closeDao();
			return;
		}
		if ("ѧ��".equals(MainFrm.userType.getName())) {
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			Student student = (Student)MainFrm.userObject;
			studentTmp.setName(student.getName());
			studentTmp.setPassword(oldPassword);
			studentTmp.setId(student.getId());
			JOptionPane.showMessageDialog(this, studentDao.editPassword(studentTmp,newPassword));
			studentDao.closeDao();
			return;
			
		}
		if ("��ʦ".equals(MainFrm.userType.getName())) {
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			Teacher teacher = (Teacher)MainFrm.userObject;
			teacherTmp.setName(teacher.getName());
			teacherTmp.setPassword(oldPassword);
			teacherTmp.setId(teacher.getId());
			JOptionPane.showMessageDialog(this, teacherDao.editPassword(teacherTmp,newPassword));
			teacherDao.closeDao();
			return;
			
		}
 	}


	protected void resetValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		oldPasswordTextField.setText("");
		newPasswordTextField.setText("");
		confirmPasswordTextField.setText("");
		
	}
}
