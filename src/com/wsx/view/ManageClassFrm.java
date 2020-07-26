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
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.wsx.dao.ClassDao;
import com.wsx.model.StudentClass;
import com.wsx.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectableChannel;

public class ManageClassFrm extends JInternalFrame {
	private JTextField searchClassNameTextField;
	private JTable classListTable;
	private JTextField editClassNameTextField;
	private JTextArea editClassInfoTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageClassFrm frame = new ManageClassFrm();
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
	public ManageClassFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u73ED\u7EA7\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 803, 595);
		
		JLabel lblNewLabel = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/image/\u73ED\u7EA7\u540D\u79F0.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		searchClassNameTextField = new JTextField();
		searchClassNameTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5  \u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentClass sc = new StudentClass();
				sc.setName(searchClassNameTextField.getText().toString());
				setTable(sc);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/image/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/image/\u73ED\u7EA7\u540D\u79F0.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editClassNameTextField = new JTextField();
		editClassNameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u73ED\u7EA7\u4FE1\u606F\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/image/\u73ED\u7EA7\u4ECB\u7ECD.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		editClassInfoTextArea = new JTextArea();
		
		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				submitEditAct(ae);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/image/\u786E\u8BA4.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		JButton submitDeleteButton = new JButton("\u5220\u9664");
		submitDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteClassAct(ae);
			}
		});
		submitDeleteButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/image/\u5220\u9664.png")));
		submitDeleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchClassNameTextField, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(searchButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 654, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(editClassInfoTextArea))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(editClassNameTextField, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
									.addGap(89)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(submitDeleteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(submitEditButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(searchClassNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel)))
					.addGap(50)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(editClassNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(submitEditButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(editClassInfoTextArea, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(submitDeleteButton))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		
		classListTable = new JTable();
		classListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				SelecTableRow(me);
			}
		});
		classListTable.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		classListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u73ED\u7EA7\u7F16\u53F7", "\u73ED\u7EA7\u540D\u79F0", "\u73ED\u7EA7\u4FE1\u606F\u4ECB\u7ECD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		classListTable.getColumnModel().getColumn(0).setPreferredWidth(91);
		classListTable.getColumnModel().getColumn(1).setPreferredWidth(105);
		classListTable.getColumnModel().getColumn(2).setPreferredWidth(477);
		scrollPane.setViewportView(classListTable);
		getContentPane().setLayout(groupLayout);
		setTable(new StudentClass());
	}
	protected void deleteClassAct(ActionEvent ae) {
		// TODO 自动生成的方法存根，删除班级操作
		if (JOptionPane.showConfirmDialog(this, "确定要删除吗？") != JOptionPane.OK_OPTION) {//不是OK就返回，若选了OK则结束if语句，开始删除操作
			return;
		}
		int index = classListTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选中要删除的数据!");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();
		int id = Integer.parseInt(dft.getValueAt(classListTable.getSelectedRow(),0).toString());
		ClassDao classDao = new ClassDao();
		if (classDao.delete(id)) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		classDao.closeDao();
		setTable(new StudentClass());//无论成功与否，均刷新列表里的数据
	}

	protected void submitEditAct(ActionEvent ae) {
		// TODO 自动生成的方法存根，修改班级信息操作
		ClassDao classDao = new ClassDao();
		int index = classListTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的数据");//只改了文本，其余和上面选中操作一样
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();
		String className = dft.getValueAt(classListTable.getSelectedRow(), 1).toString();
		String classInfo = dft.getValueAt(classListTable.getSelectedRow(), 2).toString();
		String editClassName = editClassNameTextField.getText().toString();
		String editClassInfo = editClassInfoTextArea.getText().toString();//修改后的Info可以为空
		if (StringUtil.isEmpty(editClassName)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的名称！");
			return;
		}
		if (className.equals(editClassName) && classInfo.equals(editClassInfo)) {
			JOptionPane.showMessageDialog(this, "您还没有做任何修改！");
			return;
		}
		int id = Integer.parseInt(dft.getValueAt(classListTable.getSelectedRow(),0).toString());
		StudentClass sc = new StudentClass();
		sc.setId(id);
		sc.setName(editClassName);
		sc.setInfo(editClassInfo);
		if (classDao.update(sc)) {
			JOptionPane.showMessageDialog(this, "修改成功！");
		}else {
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		classDao.closeDao();
		setTable(new StudentClass());//无论修改成功与否，均刷新列表里的数据
	}


	protected void SelecTableRow(MouseEvent me) {
		// TODO 自动生成的方法存根,将选中的内容显示到文本框中，主要方法为gerValueAt()
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();
		editClassNameTextField.setText(dft.getValueAt(classListTable.getSelectedRow(), 1).toString());
		editClassInfoTextArea.setText(dft.getValueAt(classListTable.getSelectedRow(), 2).toString());
		
	}

	private void setTable(StudentClass studentClass) {//显示数据库中的班级信息到pane中
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();//获取列表的model
		dft.setRowCount(0);//清空列表
		ClassDao classDao = new ClassDao();
		List<StudentClass> classList = classDao.getClassList(studentClass);
		for (StudentClass sc : classList) {
			Vector v = new Vector();
			v.add(sc.getId());
			v.add(sc.getName());
			v.add(sc.getInfo());
			dft.addRow(v);
		}
		classDao.closeDao();
	}
}
