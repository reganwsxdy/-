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
		String sql = "insert into s_student values(null,?,?,?,?)";//values第一个为自增，不用传为空，后两个都需要传。
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Student> getStudentList(Student student){//实现取出数据库中的列表信息(复制后修改,这部分是学生信息，之前是班级信息)
		List<Student> retList = new ArrayList<Student>();
		StringBuffer sqlString = new StringBuffer("select * from s_student");
		if (!StringUtil.isEmpty(student.getName())) {
			sqlString.append(" and name like '%"+student.getName()+"%'");
		}
		if (student.getClassId() != 0) {
			sqlString.append(" and classId ="+student.getClassId());
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));//这句话复制自21行，但是粘贴后显示的错误是未导入相关包
			//而这个相关包是从java的sql相关中导入，而不是从mysql的jdbc中导入（导入中注释掉的部分），尚不确定两个导入有什么区别，但是为了保证程序的运行，按教程来

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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	
	public boolean delete(int id) {//和ClassDao中的功能一样，直接搬过来
		String sql = "delete from s_student where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
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
			// TODO 自动生成的 catch 块
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
				String retString = "旧密码错误！";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}//把sql语句传给数据库操作对象
		
		
		String retString = "修改失败";
		String sqlString = "update s_student set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if (rst > 0) {
				retString = "密码修改成功！";
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}//把sql语句传给数据库操作对象

		return retString;
	}
	
	public Student login(Student student) {
		String sql = "select * from s_student where name=? and password=?";
		Student studentRst = null;

		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return studentRst;
	}
}
