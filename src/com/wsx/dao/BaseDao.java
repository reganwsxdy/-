package com.wsx.dao;
/**
 * 
 * @author 63227
 * �������ݿ����Ӷ���������Ŀ�����ݿ�򽻵�������һ����
 */

import java.sql.Connection;
import java.sql.SQLException;

import com.wsx.util.DbUtil;

public class BaseDao {
	public Connection con = new DbUtil().getCon();
	public void closeDao() {
		try {
			con.close();//�رղ��ճ�ռ�õ���Դ
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
