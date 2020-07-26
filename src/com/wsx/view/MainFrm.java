package com.wsx.view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.wsx.model.Admin;
import com.wsx.model.Student;
import com.wsx.model.Teacher;
import com.wsx.model.UserType;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TexturePaint;

import javax.swing.JButton;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	//从private改为静态，是因为其作为主控界面可以提供登录时的用户类型和用户名，后期的小功能包括修改密码、学生添加班级添加等等均可以从这里确定用户角色
	public static UserType userType;
	public static Object userObject;
	private JMenuItem addStudentMenuItem;
	private JMenu manageClassMenu;
	private JMenu manageTeacherMenu;
	private JMenuItem addTeacherMenuItem;
	private JMenu addCourseMenu;
	private JMenu SelectedCourseMenu;
	private JLabel currentUserLabel;
	private JMenuItem studentAttendanceMenuItem;
	private JMenuItem manageAttendanceMenuItem;
	private JMenuItem statsAttendancMenuItem;
	private JMenuItem addScoreMenuItem;
	private JMenuItem viewScoreMenuItem;
	private JMenuItem manageScoreMenuItem;
	private JMenuItem scoreStatsMenuItem;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrm frame = new MainFrm(null,null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
// 注释掉这个部分，测试的时候是可以运行的，但是为了能让登陆界面进入主界面，成为其单一入口，这个部分添加的两个变量（userType和userObject）要作为接口变量，
// 而上面这个部分的代码是用来测试的，是自动生成的，因此注释掉。
	/**
	 * Create the frame.
	 */
	public MainFrm(UserType userType , Object userObject) {
		
		this.userType = userType;
		this.userObject = userObject;
		
		setTitle("\u5B66\u751F\u4FE1\u606F\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1267, 1021);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		mnNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7CFB\u7EDF\u8BBE\u7F6E.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//自动创建的action
				editpassword(ae);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u4FEE\u6539\u5BC6\u7801.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");//退出系统
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(MainFrm.this, "确定退出吗？") == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
				
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u9000\u51FA.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("\u5B66\u751F\u7BA1\u7406");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		menuBar.add(mnNewMenu_1);
		
		addStudentMenuItem = new JMenuItem("\u5B66\u751F\u6DFB\u52A0");
		addStudentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//实现点击班级列表的弹框显示，直接显示到addStudentFrm
				AddStudentFrm addStudentFrm = new AddStudentFrm();
				addStudentFrm.setVisible(true);
				desktopPane.add(addStudentFrm);
			}
		});
		addStudentMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6DFB\u52A0.png")));
		mnNewMenu_1.add(addStudentMenuItem);
		
		JMenuItem studentListMenuItem = new JMenuItem("\u5B66\u751F\u5217\u8868");
		studentListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageStudentFrm studentManageFrm = new ManageStudentFrm();
				studentManageFrm.setVisible(true);
				desktopPane.add(studentManageFrm);
			}
		});
		studentListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7528\u6237\u5217\u8868.png")));
		mnNewMenu_1.add(studentListMenuItem);
		
		manageClassMenu = new JMenu("\u73ED\u7EA7\u7BA1\u7406");
		manageClassMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u73ED\u7EA7\u7BA1\u7406.png")));
		menuBar.add(manageClassMenu);
		
		JMenuItem addClassMenuItem = new JMenuItem("\u73ED\u7EA7\u6DFB\u52A0");
		addClassMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AddStudentClass(ae);
			}
		});
		addClassMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6DFB\u52A0.png")));
		manageClassMenu.add(addClassMenuItem);
		
		JMenuItem classListMenuItem = new JMenuItem("\u73ED\u7EA7\u5217\u8868");
		classListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//实现点击班级列表的弹框显示，直接显示到classManageFrm
				ManageClassFrm classManageFrm = new ManageClassFrm();
				classManageFrm.setVisible(true);
				desktopPane.add(classManageFrm);
			}
		});
		classListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u73ED\u7EA7\u5217\u8868.png")));
		manageClassMenu.add(classListMenuItem);
		
		manageTeacherMenu = new JMenu("\u6559\u5E08\u7BA1\u7406");
		manageTeacherMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u8001\u5E08.png")));
		menuBar.add(manageTeacherMenu);
		
		addTeacherMenuItem = new JMenuItem("\u6DFB\u52A0\u6559\u5E08");
		addTeacherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//所有的打开新pane的操作均是这几句，如下所示
				AddTeacherFrm addTeacherFrm = new AddTeacherFrm();
				addTeacherFrm.setVisible(true);
				desktopPane.add(addTeacherFrm);
			}
		});
		addTeacherMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6DFB\u52A0.png")));
		manageTeacherMenu.add(addTeacherMenuItem);
		
		JMenuItem teacherListMenuItem = new JMenuItem("\u6559\u5E08\u5217\u8868");
		teacherListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageTeacherFrm manageTeacherFrm = new ManageTeacherFrm();
				manageTeacherFrm.setVisible(true);
				desktopPane.add(manageTeacherFrm);
			}
		});
		teacherListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7528\u6237\u5217\u8868.png")));
		manageTeacherMenu.add(teacherListMenuItem);
		
		addCourseMenu = new JMenu("\u8BFE\u7A0B\u7BA1\u7406");
		addCourseMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		menuBar.add(addCourseMenu);
		
		JMenuItem addCourseNameMenuItem = new JMenuItem("\u6DFB\u52A0\u8BFE\u7A0B");
		addCourseNameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourseFrm addCourseFrm = new AddCourseFrm();
				addCourseFrm.setVisible(true);
				desktopPane.add(addCourseFrm);
			}
		});
		addCourseNameMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6DFB\u52A0.png")));
		addCourseMenu.add(addCourseNameMenuItem);
		
		JMenuItem courseListMenuItem = new JMenuItem("\u8BFE\u7A0B\u5217\u8868");
		courseListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageCourseFrm manageCourseFrm = new ManageCourseFrm();
				manageCourseFrm.setVisible(true);
				desktopPane.add(manageCourseFrm);
			}
		});
		courseListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u8BFE\u7A0B\u5217\u8868.png")));
		addCourseMenu.add(courseListMenuItem);
		
		SelectedCourseMenu = new JMenu("\u9009\u8BFE\u7BA1\u7406");
		SelectedCourseMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u9009\u62E9.png")));
		menuBar.add(SelectedCourseMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u9009\u62E9\u8BFE\u7A0B");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageSelectedCourseFrm manageSelectedCourseFrm = new ManageSelectedCourseFrm();
				manageSelectedCourseFrm.setVisible(true);
				desktopPane.add(manageSelectedCourseFrm);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u8BFE\u7A0B.png")));
		SelectedCourseMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("\u7B7E\u5230\u8003\u52E4");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7B7E\u5230.png")));
		menuBar.add(mnNewMenu_2);
		
		studentAttendanceMenuItem = new JMenuItem("\u5B66\u751F\u7B7E\u5230");
		studentAttendanceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttendanceStudentFrm attendanceStudentFrm = new AttendanceStudentFrm();
				attendanceStudentFrm.setVisible(true);
				desktopPane.add(attendanceStudentFrm);
			}
		});
		studentAttendanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
		studentAttendanceMenuItem.setEnabled(false);
		mnNewMenu_2.add(studentAttendanceMenuItem);
		
		manageAttendanceMenuItem = new JMenuItem("\u7B7E\u5230\u7BA1\u7406");
		manageAttendanceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageAttendanceFrm manageAttendanceFrm = new ManageAttendanceFrm();
				manageAttendanceFrm.setVisible(true);
				desktopPane.add(manageAttendanceFrm);
			}
		});
		manageAttendanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7B7E\u5230\u5217\u8868.png")));
		mnNewMenu_2.add(manageAttendanceMenuItem);
		
		statsAttendancMenuItem = new JMenuItem("\u7B7E\u5230\u7EDF\u8BA1");
		statsAttendancMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatsAttendanceFrm statsAttendanceFrm = new StatsAttendanceFrm();
				statsAttendanceFrm.setVisible(true);
				desktopPane.add(statsAttendanceFrm);
			}
		});
		statsAttendancMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7EDF\u8BA1.png")));
		mnNewMenu_2.add(statsAttendancMenuItem);
		
		JMenu mnNewMenu_4 = new JMenu("\u6210\u7EE9\u7BA1\u7406");
		mnNewMenu_4.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6210\u7EE9.png")));
		menuBar.add(mnNewMenu_4);
		
		addScoreMenuItem = new JMenuItem("\u5F55\u5165\u6210\u7EE9");
		addScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScoreFrm addScoreFrm = new AddScoreFrm();
				addScoreFrm.setVisible(true);
				desktopPane.add(addScoreFrm);
			}
		});
		addScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6DFB\u52A0.png")));
		mnNewMenu_4.add(addScoreMenuItem);
		
		viewScoreMenuItem = new JMenuItem("\u6210\u7EE9\u67E5\u770B");
		viewScoreMenuItem.setEnabled(false);
		viewScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewScoreFrm viewScoreFrm = new ViewScoreFrm();
				viewScoreFrm.setVisible(true);
				desktopPane.add(viewScoreFrm);
				
			}
		});
		viewScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u67E5\u770B.png")));
		mnNewMenu_4.add(viewScoreMenuItem);
		
		manageScoreMenuItem = new JMenuItem("\u6210\u7EE9\u7BA1\u7406");
		manageScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageScoreFrm manageScoreFrm = new ManageScoreFrm();
				manageScoreFrm.setVisible(true);
				desktopPane.add(manageScoreFrm);
			}
		});
		manageScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u6210\u7EE9.png")));
		mnNewMenu_4.add(manageScoreMenuItem);
		
		scoreStatsMenuItem = new JMenuItem("\u6210\u7EE9\u7EDF\u8BA1");
		scoreStatsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatsScoreFrm statsScoreFrm = new StatsScoreFrm();
				statsScoreFrm.setVisible(true);
				desktopPane.add(statsScoreFrm);
			}
		});
		scoreStatsMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u7EDF\u8BA1.png")));
		mnNewMenu_4.add(scoreStatsMenuItem);
		
		JMenu mnNewMenu_3 = new JMenu("\u5E2E\u52A9");
		mnNewMenu_3.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u5E2E\u52A9.png")));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AboutUs(ae);
			}
		});
		mntmNewMenuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u5173\u4E8E\u6211\u4EEC.png")));
		mnNewMenu_3.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 128, 128));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel.setBounds(883, 13, 249, 31);
		desktopPane.add(lblNewLabel);
		
		currentUserLabel = new JLabel("");
		currentUserLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		currentUserLabel.setForeground(new Color(255, 255, 255));
		currentUserLabel.setBounds(967, 14, 258, 27);
		desktopPane.add(currentUserLabel);
		
		JButton changeUserButton = new JButton("\u5207\u6362\u7528\u6237");
		changeUserButton.setForeground(new Color(0, 0, 0));
		changeUserButton.setBackground(new Color(0, 128, 128));
		changeUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrm loginFrm = new LoginFrm();
				loginFrm.setVisible(true);
				desktopPane.add(loginFrm);
				
			}
		});
		changeUserButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		changeUserButton.setIcon(new ImageIcon(MainFrm.class.getResource("/image/\u5207\u6362.png")));
		changeUserButton.setBounds(883, 57, 342, 27);
		desktopPane.add(changeUserButton);
		setLocationRelativeTo(null);
		setAuthority();
	}

	protected void AddStudentClass(ActionEvent ae) {
		// TODO 自动生成的方法存根 
		AddStudentClassFrm sca = new AddStudentClassFrm();
		sca.setVisible(true);
		desktopPane.add(sca);
	}

	protected void editpassword(ActionEvent ae) {
		// TODO 自动生成的方法存根，修改密码功能实现
		EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
		editPasswordFrm.setVisible(true);
		desktopPane.add(editPasswordFrm);
	}

	protected void AboutUs(ActionEvent ae) {
		// TODO 自动生成的方法存根，关于我们功能的实现
		String info = "学习专用，模仿转化修改更新\n";
		info += "源码来源：http://programmer.ischoolbar.com \n";
		info += "可学习其他新项目，在网站中找";
		//JOptionPane.showMessageDialog(this, info);
		String[] buttons = {"继续学习","残忍拒绝"};
		int ret = JOptionPane.showOptionDialog(this, info, "关于我们", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(LoginFrm.class.getResource("/image/logo.png")), buttons, null);
		if (ret == 0) {
			//采用java调用系统浏览器打开指定网站
			try {
				URI uri = new URI("http://programmer.ischoolbar.com");
				//Runtime.getRuntime().exec("explorer http://programmer.ischoolbar.com");
				Desktop.getDesktop().browse(uri);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//JOptionPane.showMessageDialog(this, "不忘初心，再接再厉！");
		}else {
			JOptionPane.showMessageDialog(this, "休息是为了更好的学习和工作，而不是玩！");
		}
	}
	
	private void setAuthority() {
		
		if ("学生".equals(userType.getName())) {
			addStudentMenuItem.setEnabled(false);
			manageClassMenu.setEnabled(false);
			manageTeacherMenu.setEnabled(false);
			addCourseMenu.setEnabled(false);
			studentAttendanceMenuItem.setEnabled(true);
			manageAttendanceMenuItem.setEnabled(false);
			statsAttendancMenuItem.setEnabled(false);
			addScoreMenuItem.setEnabled(false);
			viewScoreMenuItem.setEnabled(true);
			manageScoreMenuItem.setEnabled(false);
			
			
		}
		if ("教师".equals(userType.getName())) {
			addTeacherMenuItem.setEnabled(false);
//			SelectedCourseMenu.setEnabled(false);
		}
		
		if ("系统管理员".equals(MainFrm.userType.getName())) {//获取主界面中的用户类型，使得其能显示在“当前用户：”后面
			Admin admin = (Admin)MainFrm.userObject;
			currentUserLabel.setText("【系统管理员】"+admin.getName());
		}else if("学生".equals(MainFrm.userType.getName())){
			Student student = (Student)MainFrm.userObject;
			currentUserLabel.setText("【学生】" + student.getName());
		}else {
			Teacher teacher = (Teacher)MainFrm.userObject;
			currentUserLabel.setText("【教师】" + teacher.getName());
		}
	}
}
