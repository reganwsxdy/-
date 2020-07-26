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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.StudentDao;
import com.wsx.dao.TeacherDao;
import com.wsx.model.Student;
import com.wsx.model.StudentClass;
import com.wsx.model.Teacher;
import com.wsx.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageTeacherFrm extends JInternalFrame {
	private JTable teacherListTable;
	private JTextField searchTeacherNameTextField;
	private JTextField editTeacherNameTextField;
	private JTextField editTeacherTitleTextField;
	private JTextField editTeacherAgeTextField;
	private JPasswordField editTeacherPasswordField;
	private JRadioButton editTeacherSexMaleRadioButton;
	private JRadioButton editTeacherSexFemaleRadioButton;
	private JButton deleteTeacherButton;
	private JButton searchTeacherButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageTeacherFrm frame = new ManageTeacherFrm();
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
	public ManageTeacherFrm() {
		setClosable(true);
		setIconifiable(true);
		
		setTitle("\u6559\u5E08\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 875, 674);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u8001\u5E08.png")));
		
		searchTeacherNameTextField = new JTextField();
		searchTeacherNameTextField.setColumns(10);
		
		searchTeacherButton = new JButton("\u67E5    \u8BE2");
		searchTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeacher(e);
			}
		});
		searchTeacherButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		searchTeacherButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u641C\u7D22.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u6559\u5E08\u4FE1\u606F\u4FEE\u6539", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(137)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(searchTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(searchTeacherButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(84)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchTeacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(searchTeacherButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u8001\u5E08.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editTeacherNameTextField = new JTextField();
		editTeacherNameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u6559\u5E08\u6027\u522B\uFF1A");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_2.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u6027\u522B.png")));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		editTeacherSexMaleRadioButton = new JRadioButton("\u7537");
		editTeacherSexMaleRadioButton.setSelected(true);
		
		editTeacherSexFemaleRadioButton = new JRadioButton("\u5973");
		buttonGroup.add(editTeacherSexMaleRadioButton);
		buttonGroup.add(editTeacherSexFemaleRadioButton);
		
		JLabel lblNewLabel_3 = new JLabel("\u6559\u5E08\u804C\u79F0\uFF1A");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_3.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u804C\u79F0\u8BC4\u5B9A.png")));
		
		editTeacherTitleTextField = new JTextField();
		editTeacherTitleTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u6559\u5E08\u5E74\u9F84\uFF1A");
		lblNewLabel_4.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u5E74\u9F84.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editTeacherAgeTextField = new JTextField();
		editTeacherAgeTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u767B\u9646\u5BC6\u7801\uFF1A");
		lblNewLabel_5.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u5BC6\u7801.png")));
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editTeacherPasswordField = new JPasswordField();
		
		JButton editSubmitButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		editSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editTeacherAct(ae);
			}
		});
		editSubmitButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u786E\u8BA4.png")));
		editSubmitButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		deleteTeacherButton = new JButton("\u5220    \u9664");
		deleteTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteTeacher(ae);
			}
		});
		deleteTeacherButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/image/\u5220\u9664.png")));
		deleteTeacherButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherPasswordField))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherTitleTextField))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editTeacherSexMaleRadioButton)
									.addGap(18)
									.addComponent(editTeacherSexFemaleRadioButton))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(deleteTeacherButton)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNewLabel_4)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(editTeacherAgeTextField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(editSubmitButton)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(editTeacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(editTeacherSexMaleRadioButton)
						.addComponent(editTeacherSexFemaleRadioButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(editTeacherTitleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(editTeacherAgeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(editTeacherPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editSubmitButton)
						.addComponent(deleteTeacherButton))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		teacherListTable = new JTable();
		teacherListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedTableRow(arg0);
			}
		});
		teacherListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6559\u5E08ID", "\u6559\u5E08\u59D3\u540D", "\u6559\u5E08\u6027\u522B", "\u6559\u5E08\u804C\u79F0", "\u6559\u5E08\u5E74\u9F84", "\u767B\u9646\u5BC6\u7801"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(teacherListTable);
		getContentPane().setLayout(groupLayout);
		setTable(new Teacher());
		setAuthority();
	}
	
	protected void searchTeacher(ActionEvent e) {
		// TODO 自动生成的方法存根
		String teacherName = searchTeacherNameTextField.getText().toString();
		Teacher teacher = new Teacher();
		teacher.setName(teacherName);
		setTable(teacher);
		
	}

	protected void editTeacherAct(ActionEvent ae) {
		// TODO 自动生成的方法存根，修改教师信息
		int row = teacherListTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的教师信息");
			return;
		}
		String teacherName = editTeacherNameTextField.getText().toString();
		String teacherSex = editTeacherSexMaleRadioButton.isSelected() ? editTeacherSexMaleRadioButton.getText().toString() : editTeacherSexFemaleRadioButton.getText().toString();
		String teacherTitle = editTeacherTitleTextField.getText().toString();
		
		int teacherAge = 0;
		try {
			teacherAge = Integer.parseInt(editTeacherAgeTextField.getText().toString());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(this, "年龄只允许输入数字！");
			return;
		}
		String teacherPassword = editTeacherPasswordField.getText().toString();
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
		teacher.setId(Integer.parseInt(teacherListTable.getValueAt(row, 0).toString()));
		teacher.setName(teacherName);
		teacher.setSex(teacherSex);
		teacher.setTitle(teacherTitle);
		teacher.setAge(teacherAge);
		teacher.setPassword(teacherPassword);
		TeacherDao teacherDao = new TeacherDao();
		if(teacherDao.update(teacher)){
			JOptionPane.showMessageDialog(this, "教师修改成功！");
		}else{
			JOptionPane.showMessageDialog(this, "教师修改失败！");
		}
		
		teacherDao.closeDao();
		setTable(new Teacher());//刷新列表
	}

	protected void deleteTeacher(ActionEvent ae) {
		// TODO 自动生成的方法存根
		int row = teacherListTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要删除的教师！");
			return;
		}
		if (JOptionPane.showConfirmDialog(this, "确认要删除吗？") != JOptionPane.OK_OPTION) {
			return;
		}
		TeacherDao teacherDao = new TeacherDao();
		if (teacherDao.delete(Integer.parseInt(teacherListTable.getValueAt(row, 0).toString()))) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		teacherDao.closeDao();
		setTable(new Teacher());//删除后刷新列表
		
	}

	private void setTable(Teacher teacher) {//从ClassManageFrm直接拷贝过来
		if ("教师".equals(MainFrm.userType.getName())) {
			Teacher t = (Teacher)MainFrm.userObject;
			teacher.setName(t.getName());//只能查询到本人的信息
			searchTeacherNameTextField.setText(teacher.getName());
		}
		
		DefaultTableModel dft = (DefaultTableModel) teacherListTable.getModel();//获取列表的model
		dft.setRowCount(0);//清空列表
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = teacherDao.getTeacherList(teacher);
		for (Teacher t : teacherList) {
			Vector v = new Vector();
			v.add(t.getId());
			v.add(t.getName());
			v.add(t.getSex());
			v.add(t.getTitle());
			v.add(t.getAge());
			v.add(t.getPassword());
			dft.addRow(v);
		}
		teacherDao.closeDao();
	}
	
	protected void selectedTableRow(MouseEvent me) {
		// TODO Auto-generated method stub，实现将选择的信息显示到教师信息管理pane的对应文本框中
		DefaultTableModel dft = (DefaultTableModel) teacherListTable.getModel();
		editTeacherNameTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 1).toString());
		editTeacherTitleTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 3).toString());
		editTeacherAgeTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 4).toString());
		editTeacherPasswordField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 5).toString());
		String sex = dft.getValueAt(teacherListTable.getSelectedRow(), 2).toString();
		if(sex.equals(editTeacherSexMaleRadioButton.getText()))editTeacherSexMaleRadioButton.setSelected(true);
		if(sex.equals(editTeacherSexFemaleRadioButton.getText()))editTeacherSexFemaleRadioButton.setSelected(true);
	}
	
	private void setAuthority() {
		if ("教师".equals(MainFrm.userType.getName())) {
			deleteTeacherButton.setEnabled(false);
			searchTeacherNameTextField.setEnabled(false);
			
		}
	}
}
