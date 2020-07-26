package com.wsx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsx.model.Student;
import com.wsx.util.StringUtil;

public class StudentDao extends BaseDao {
	public boolean addStudent(Student student) {
		String sql = "insert into s_student values(null,?,?,?,?)";//values��һ��Ϊ���������ô�Ϊ�գ�����������Ҫ����
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getClassId());
			preparedStatement.setString(3, student.getPassword());
			preparedStatement.setString(4, student.getSex());
			
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Student> getStudentList(Student student){//ʵ��ȡ�����ݿ��е��б���Ϣ(���ƺ��޸�,�ⲿ����ѧ����Ϣ��֮ǰ�ǰ༶��Ϣ)
		List<Student> retList = new ArrayList<Student>();
		StringBuffer sqlString = new StringBuffer("select * from s_student");
		if (!StringUtil.isEmpty(student.getName())) {
			sqlString.append(" and name like '%"+student.getName()+"%'");
		}
		if (student.getClassId() != 0) {
			sqlString.append(" and classId ="+student.getClassId());
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));//��仰������21�У�����ճ������ʾ�Ĵ�����δ������ذ�
			//�������ذ��Ǵ�java��sql����е��룬�����Ǵ�mysql��jdbc�е��루������ע�͵��Ĳ��֣����в�ȷ������������ʲô���𣬵���Ϊ�˱�֤��������У����̳���

			ResultSet executeQuery = preparedStatement.executeQuery();
			while (executeQuery.next()) {
				Student s = new Student();
				s.setId(executeQuery.getInt("id"));
				s.setName(executeQuery.getString("name"));
				s.setClassId(executeQuery.getInt("classId"));
				s.setSex(executeQuery.getString("sex"));
				s.setPassword(executeQuery.getString("password"));
				retList.add(s);
				
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
	public boolean delete(int id) {//��ClassDao�еĹ���һ����ֱ�Ӱ����
		String sql = "delete from s_student where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Student student) {
		String sql = "update s_student set name=? , classId=? , sex=? ,password=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getClassId());
			preparedStatement.setString(3, student.getSex());
			preparedStatement.setString(4, student.getPassword());
			preparedStatement.setInt(5, student.getId());
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	public String editPassword(Student student,String newPassword) {
		String sql = "select * from s_student where name=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		
		try {
			prst = con.prepareStatement(sql);
			prst.setString(1, student.getName());
			prst.setString(2, student.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if (!executeQuery.next()) {
				String retString = "���������";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}//��sql��䴫�����ݿ��������
		
		
		String retString = "�޸�ʧ��";
		String sqlString = "update s_student set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if (rst > 0) {
				retString = "�����޸ĳɹ���";
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}//��sql��䴫�����ݿ��������

		return retString;
	}
	
	public Student login(Student student) {
		String sql = "select * from s_student where name=? and password=?";
		Student studentRst = null;

		try {
			PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
			prst.setString(1, student.getName());
			prst.setString(2, student.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if (executeQuery.next()) {
				studentRst = new Student();
				studentRst.setId(executeQuery.getInt("id"));
				studentRst.setClassId(executeQuery.getInt("classId"));
				studentRst.setName(executeQuery.getString("name"));
				studentRst.setPassword(executeQuery.getString("password"));
				studentRst.setSex(executeQuery.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return studentRst;
	}
}
