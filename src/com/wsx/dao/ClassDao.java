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
 * �༶��Ϣ�����ݿ�Ĳ���
 * @author 63227
 *
 */
public class ClassDao extends BaseDao {
	public boolean addClass(StudentClass scl) {
		String sql = "insert into s_class values(null,?,?)";//values��һ��Ϊ���������ô�Ϊ�գ�����������Ҫ����
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, scl.getName());
			preparedStatement.setString(2, scl.getInfo());
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	public List<StudentClass> getClassList(StudentClass studentClass){//ʵ��ȡ�����ݿ��е��б���Ϣ
		List<StudentClass> retList = new ArrayList<StudentClass>();
		String sqlString = "select * from s_class";
		if (!StringUtil.isEmpty(studentClass.getName())) {
			sqlString += " where name like '%"+studentClass.getName()+"%'";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);//��仰������21�У�����ճ������ʾ�Ĵ�����δ������ذ�
			//�������ذ��Ǵ�java��sql����е��룬�����Ǵ�mysql��jdbc�е��루������ע�͵��Ĳ��֣����в�ȷ������������ʲô���𣬵���Ϊ�˱�֤��������У����̳���

			ResultSet executeQuery = preparedStatement.executeQuery();
			while (executeQuery.next()) {
				StudentClass sc = new StudentClass();
				sc.setId(executeQuery.getInt("id"));
				sc.setName(executeQuery.getString("name"));
				sc.setInfo(executeQuery.getString("info"));
				retList.add(sc);
				
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
}
