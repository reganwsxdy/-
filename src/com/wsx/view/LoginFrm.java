package com.wsx.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	private JComboBox userTypeComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setTitle("\u767B\u9646\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u4FE1\u606F\u7CFB\u7EDF\u767B\u9646\u754C\u9762");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/logo.png")));
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/\u7528\u6237\u540D.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 17));
		
		userNameTextField = new JTextField();
		userNameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6    \u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/password.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 17));
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/userType.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		userTypeComboBox = new JComboBox();
		userTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] {UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		
		JButton loginButton = new JButton("\u767B  \u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loginAction(ae);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/\u767B\u5F55.png")));
		loginButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton resetButton = new JButton("\u91CD  \u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(206)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(loginButton)
							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
							.addComponent(resetButton)
							.addGap(27))
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(userNameTextField, 197, 197, 197)
								.addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(userTypeComboBox, 0, 197, Short.MAX_VALUE))))
					.addGap(213))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(lblNewLabel)
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3)
						.addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton)
						.addComponent(resetButton))
					.addGap(33))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void loginAction(ActionEvent ae) {
		// TODO 自动生成的方法存根
		String userName = userNameTextField.getText().toString();
		String password = passwordTextField.getText().toString();
		UserType selectedItem = (UserType)userTypeComboBox.getSelectedItem();
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(this, "用户名不能为空！");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(this, "密码不能为空！");
			return;
		}
		Admin admin = null;
		if("系统管理员".equals(selectedItem.getName())) {
			//系统管理员登录（需要传的数据库对象）
			AdminDao adminDao = new AdminDao();
			Admin adminTmp = new Admin();
			adminTmp.setName(userName);
			adminTmp.setPassword(password);
			admin = adminDao.login(adminTmp);
			adminDao.closeDao();
			if (admin == null) {
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】:"+admin.getName()+"登录本系统！");
			this.dispose();//隐藏登录界面
			new MainFrm(selectedItem, admin).setVisible(true);//登录后跳转到主界面，简单说就是点击后显示主界面，前提是用户名密码正确（setVisible(true)表示界面可见）
		}else if("教师".equals(selectedItem.getName())) {
			//教师登录
			Teacher teacher = null;
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			teacherTmp.setName(userName);
			teacherTmp.setPassword(password);
			teacher = teacherDao.login(teacherTmp);
			teacherDao.closeDao();
			if(teacher == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】"+teacher.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, teacher).setVisible(true);
		}else { 
			//学生登录
			Student student = null; 
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			studentTmp.setName(userName);
			studentTmp.setPassword(password);
			student = studentDao.login(studentTmp);
			studentDao.closeDao();
			if(student == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】"+student.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, student).setVisible(true);
		}
	}

	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根,实现重置文本框功能
		userNameTextField.setText("");
		passwordTextField.setText("");
		userTypeComboBox.setSelectedIndex(0);
		
	}
}
