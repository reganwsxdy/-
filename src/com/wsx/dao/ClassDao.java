package com.wsx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.print.attribute.standard.PresentationDirection;

//import com.mysql.jdbc.PreparedStatement;
import com.wsx.model.StudentClass;
import com.wsx.util.StringUtil;

/**
 * 班级信息与数据库的操作
 * @author 63227
 *
 */
public class ClassDao extends BaseDao {
	public boolean addClass(StudentClass scl) {
		String sql = "insert into s_class values(null,?,?)";//values第一个为自增，不用传为空，后两个都需要传。
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, scl.getName());
			preparedStatement.setString(2, scl.getInfo());
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	public List<StudentClass> getClassList(StudentClass studentClass){//实现取出数据库中的列表信息
		List<StudentClass> retList = new ArrayList<StudentClass>();
		String sqlString = "select * from s_class";
		if (!StringUtil.isEmpty(studentClass.getName())) {
			sqlString += " where name like '%"+studentClass.getName()+"%'";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);//这句话复制自21行，但是粘贴后显示的错误是未导入相关包
			//而这个相关包是从java的sql相关中导入，而不是从mysql的jdbc中导入（导入中注释掉的部分），尚不确定两个导入有什么区别，但是为了保证程序的运行，按教程来

			ResultSet executeQuery = preparedStatement.executeQuery();
			while (executeQuery.next()) {
				StudentClass sc = new StudentClass();
				sc.setId(executeQuery.getInt("id"));
				sc.setName(executeQuery.getString("name"));
				sc.setInfo(executeQuery.getString("info"));
				retList.add(sc);
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	public boolean delete(int id) {
		String sql = "delete from s_class where id=?";
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
	public boolean update(StudentClass sc) {
		String sql = "update s_class set name=? , info=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sc.getName());
			preparedStatement.setString(2, sc.getInfo());
			preparedStatement.setInt(3, sc.getId());
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
}
