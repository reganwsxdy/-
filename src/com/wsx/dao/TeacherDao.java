package com.wsx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsx.model.Teacher;
import com.wsx.util.StringUtil;

public class TeacherDao extends BaseDao {
	public boolean addTeacher(Teacher teacher) {
		String sql = "insert into s_teacher values(null,?,?,?,?,?)";//values第一个为自增，不用传为空，后面都需要传。
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSex());
			preparedStatement.setString(3, teacher.getTitle());
			preparedStatement.setInt(4, teacher.getAge());
			preparedStatement.setString(5, teacher.getPassword());
			
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	public List<Teacher> getTeacherList(Teacher teacher){//实现取出数据库中的列表信息(复制后修改,这部分是学生信息，之前是班级信息)
		List<Teacher> retList = new ArrayList<Teacher>();
		StringBuffer sqlString = new StringBuffer("select * from s_teacher");
		if (!StringUtil.isEmpty(teacher.getName())) {
			sqlString.append(" where name like '%"+teacher.getName()+"%'");
		}
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString());//这句话复制自21行，但是粘贴后显示的错误是未导入相关包
			//而这个相关包是从java的sql相关中导入，而不是从mysql的jdbc中导入（导入中注释掉的部分），尚不确定两个导入有什么区别，但是为了保证程序的运行，按教程来

			ResultSet executeQuery = preparedStatement.executeQuery();
			while (executeQuery.next()) {
				Teacher t = new Teacher();
				t.setId(executeQuery.getInt("id"));
				t.setName(executeQuery.getString("name"));
				t.setSex(executeQuery.getString("sex"));
				t.setTitle(executeQuery.getString("title"));
				t.setAge(executeQuery.getInt("age"));
				t.setPassword(executeQuery.getString("password"));
				retList.add(t);
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	public boolean delete(int id) {
		// TODO 自动生成的方法存根
		String sql = "delete from s_teacher where id=?";
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
	
	public boolean update(Teacher teacher){
		String sql = "update s_teacher set name=?, sex=?,title=?,age=?,password=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSex());
			preparedStatement.setString(3, teacher.getTitle());
			preparedStatement.setInt(4, teacher.getAge());
			preparedStatement.setString(5, teacher.getPassword());
			preparedStatement.setInt(6, teacher.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Teacher login(Teacher teacher){
		String sql = "select * from s_teacher where name=? and password=?";
		Teacher teacherRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//°ÑsqlÓï¾ä´«¸øÊý¾Ý¿â²Ù×÷¶ÔÏó
			prst.setString(1, teacher.getName());
			prst.setString(2, teacher.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				teacherRst = new Teacher();
				teacherRst.setId(executeQuery.getInt("id"));
				teacherRst.setName(executeQuery.getString("name"));
				teacherRst.setPassword(executeQuery.getString("password"));
				teacherRst.setSex(executeQuery.getString("sex"));
				teacherRst.setAge(executeQuery.getInt("Age"));
				teacherRst.setTitle(executeQuery.getString("title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherRst;
	}
	
	public String editPassword(Teacher teacher,String newPassword) {
		String sql = "select * from s_teacher where name=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		
		try {
			prst = con.prepareStatement(sql);
			prst.setString(1, teacher.getName());
			prst.setString(2, teacher.getPassword());
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
		String sqlString = "update s_teacher set password = ? where id = ?";
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
}
