package com.wsx.dao;
/**
 * 
 * @author 63227
 * 创建数据库连接对象，整个项目与数据库打交道都用这一对象
 */

import java.sql.Connection;
import java.sql.SQLException;

import com.wsx.util.DbUtil;

public class BaseDao {
	public Connection con = new DbUtil().getCon();
	public void closeDao() {
		try {
			con.close();//关闭并空出占用的资源
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
