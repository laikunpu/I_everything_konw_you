package com.lkp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lkp.entity.HtmlLevelOne;
import com.lkp.entity.HtmlLevelThree;
import com.lkp.entity.HtmlLevelThreeNotCollect;
import com.lkp.entity.HtmlLevelTwo;
import com.lkp.entity.Htmlpagecollect;

public class DaoImp {
	
	
	public void insertPage(Htmlpagecollect html) {

		Connection conn = getConnection(); // 首先要获取连接，即连接到数据库

		try {
			String sql = "INSERT INTO htmlpagecollect(name, imageurl)" + " VALUES (?, ?)"; // 插入数据的sql语句

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, html.getName());
			pstm.setString(2, html.getImageurl());

			int count = pstm.executeUpdate(); // 执行插入操作的sql语句，并返回插入数据的个数

		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
	}

	public Htmlpagecollect findPage(String name) {
		Htmlpagecollect page = null;
		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from htmlpagecollect where name = ?"; // 查询数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();// 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) {
				page = new Htmlpagecollect(rs.getString("name"), rs.getString("imageurl"));
			}

		} catch (SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
		return page;
	}

	public void deletePage(String name) {

		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "delete from htmlpagecollect  where name = ?";// 删除数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);

			int count = pstm.executeUpdate();// 执行sql删除语句，返回删除数据的数量

		} catch (SQLException e) {
			System.out.println("删除数据失败");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}

	}
	

	public void insertLevelThree(HtmlLevelThree html) {

		Connection conn = getConnection(); // 首先要获取连接，即连接到数据库

		try {
			String sql = "INSERT INTO htmllevelthree(name, content)" + " VALUES (?, ?)"; // 插入数据的sql语句

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, html.getName());
			pstm.setString(2, html.getContent());

			int count = pstm.executeUpdate(); // 执行插入操作的sql语句，并返回插入数据的个数

		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
	}

	public HtmlLevelThree findLevelThree(String name) {
		HtmlLevelThree three = null;
		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from htmllevelthree where name = ?"; // 查询数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();// 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) {
				three = new HtmlLevelThree(rs.getString("name"), rs.getString("content"));
			}

		} catch (SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
		return three;
	}

	public void deleteLevelThree(String name) {

		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "delete from htmllevelthree  where name = ?";// 删除数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);

			int count = pstm.executeUpdate();// 执行sql删除语句，返回删除数据的数量

		} catch (SQLException e) {
			System.out.println("删除数据失败");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}

	}

	public void insertLevelThreeNotCollect(HtmlLevelThreeNotCollect html) {

		Connection conn = getConnection(); // 首先要获取连接，即连接到数据库

		try {
			String sql = "INSERT INTO htmllevelthreenotcollect(name)" + " VALUES (?)"; // 插入数据的sql语句

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, html.getName());

			int count = pstm.executeUpdate(); // 执行插入操作的sql语句，并返回插入数据的个数

		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
	}

	public HtmlLevelThreeNotCollect findLevelThreeNotCollect(String name) {

		HtmlLevelThreeNotCollect notCollect = null;
		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from htmllevelthreenotcollect where name = ?"; // 查询数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();// 执行sql查询语句，返回查询数据的结果集
			while (rs.next()) {
				notCollect = new HtmlLevelThreeNotCollect(rs.getString("name"));
			}

		} catch (SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
		return notCollect;
	}
	
	public List<HtmlLevelThreeNotCollect> findAllLevelThreeNotCollect() {

		List<HtmlLevelThreeNotCollect> pages=new ArrayList<HtmlLevelThreeNotCollect>();
		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from htmllevelthreenotcollect"; // 查询数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();// 执行sql查询语句，返回查询数据的结果集
			while (rs.next()) {
				HtmlLevelThreeNotCollect notCollect  = new HtmlLevelThreeNotCollect(rs.getString("name"));
				pages.add(notCollect);
			}

		} catch (SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
		return pages;
	}

	public void deleteLevelThreeNotCollect(String name) {

		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "delete from htmllevelthreenotcollect  where name = ?";// 删除数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);

			int count = pstm.executeUpdate();// 执行sql删除语句，返回删除数据的数量

		} catch (SQLException e) {
			System.out.println("删除数据失败");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}

	}

	public void insertLevelOne(HtmlLevelOne html) {

		Connection conn = getConnection(); // 首先要获取连接，即连接到数据库

		try {
			String sql = "INSERT INTO htmllevelone(name, content)" + " VALUES (?, ?)"; // 插入数据的sql语句

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, html.getName());
			pstm.setString(2, html.getContent());

			int count = pstm.executeUpdate(); // 执行插入操作的sql语句，并返回插入数据的个数

		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
	}

	public HtmlLevelOne findLevelOne(String name) {

		HtmlLevelOne one = null;
		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from htmllevelone where name = ?"; // 查询数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();// 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) {
				one = new HtmlLevelOne(rs.getString("name"), rs.getString("content"));
			}
		} catch (SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
		return one;
	}

	public void deleteLevelOne(String name) {

		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "delete from htmllevelone  where name = ?";// 删除数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);

			int count = pstm.executeUpdate();// 执行sql删除语句，返回删除数据的数量

		} catch (SQLException e) {
			System.out.println("删除数据失败");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}

	}

	public void insertLevelTwo(HtmlLevelTwo html) {

		Connection conn = getConnection(); // 首先要获取连接，即连接到数据库

		try {
			String sql = "INSERT INTO htmlleveltwo(name, content)" + " VALUES (?, ?)"; // 插入数据的sql语句

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, html.getName());
			pstm.setString(2, html.getContent());

			int count = pstm.executeUpdate(); // 执行插入操作的sql语句，并返回插入数据的个数

		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
	}

	public HtmlLevelTwo findLevelTwo(String name) {
		HtmlLevelTwo two = null;
		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from htmlleveltwo where name = ?"; // 查询数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();// 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) {
				two = new HtmlLevelTwo(rs.getString("name"), rs.getString("content"));
			}
		} catch (SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}
		return two;
	}

	public void deleteLevelTwo(String name) {

		Connection conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "delete from htmlleveltwo  where name = ?";// 删除数据的sql语句
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);

			int count = pstm.executeUpdate();// 执行sql删除语句，返回删除数据的数量

		} catch (SQLException e) {
			System.out.println("删除数据失败");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭数据库连接
		}

	}

	public static Connection getConnection() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tn3", "root", "6256332");// 创建数据连接

		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con; // 返回所建立的数据库连接
	}
}
