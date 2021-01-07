package kr.co.ch17.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.ch17.vo.EmployeeVo;

// Dao ��ü
//  - Model2���� �����ͺ��̽� ������ ������Ʈ
//  - �Ϲ������� Singleton ��ü�� ����
public class EmployeeDao {

	private static EmployeeDao instance = new EmployeeDao();
	
	public static EmployeeDao getInstance() {
		return instance;
	}
	
	private EmployeeDao() {}
	
	//DB����
	private final String HOST = "jdbc:mysql://192.168.10.114:3306/ksr";
	private final String USER = "ksr";
	private final String PASS = "1234";
	
	public void insertEmployee(EmployeeVo vo) {
		
		try {
			// 1�ܰ�
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2�ܰ�
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			
			// 3�ܰ�
			Statement stmt = conn.createStatement();
			
			// 4�ܰ�
			String sql  = "INSERT INTO `Employee` SET ";
			       sql += "`uid`='"+vo.getUid()+"',";
			       sql += "`name`='"+vo.getName()+"',";
			       sql += "`gender`="+vo.getGender()+",";
			       sql += "`hp`='"+vo.getHp()+"',";
			       sql += "`email`='"+vo.getEmail()+"',";
			       sql += "`pos`='"+vo.getPos()+"',";
			       sql += "`dep`="+vo.getDep()+",";
			       sql += "`rdate`=NOW()";
			
			stmt.executeUpdate(sql);
			
			// 5�ܰ�
			// 6�ܰ�
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public EmployeeVo selectEmployee(String uid) {
		
		EmployeeVo vo = null;
		
		try {
			// 1�ܰ�
			Class.forName("com.mysql.jdbc.Driver");
			// 2�ܰ�
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			// 3�ܰ�
			Statement stmt = conn.createStatement();
			// 4�ܰ�
			String sql = "SELECT * FROM `Employee` WHERE `uid`='"+uid+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			// 5�ܰ�
			vo = new EmployeeVo();
			
			if(rs.next()) {
				vo.setUid(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setHp(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setPos(rs.getString(6));
				vo.setDep(rs.getString(7));
				vo.setRdate(rs.getString(8));
			}
			
			// 6�ܰ�
			rs.close();
			stmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public List<EmployeeVo> selectEmployees() {
		
		List<EmployeeVo> list = null;
		
		try {
			// 1�ܰ�
			Class.forName("com.mysql.jdbc.Driver");
			// 2�ܰ�
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			// 3�ܰ�
			Statement stmt = conn.createStatement();
			// 4�ܰ�
			String sql = "SELECT * FROM `Employee`";
			ResultSet rs = stmt.executeQuery(sql);
			
			// 5�ܰ�
			list = new ArrayList<>();
			
			while(rs.next()) {
				EmployeeVo vo = new EmployeeVo();
				
				vo.setUid(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getInt(3));
				vo.setHp(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setPos(rs.getString(6));
				vo.setDep(rs.getInt(7));
				vo.setRdate(rs.getString(8));
				
				list.add(vo);				
			}
			
			// 6�ܰ�
			rs.close();
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	public void updateEmployee(String uid, String name, String hp, String pos, String dep) {
		
		try {
			// 1�ܰ�
			Class.forName("com.mysql.jdbc.Driver");
			// 2�ܰ�
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			// 3�ܰ�
			Statement stmt = conn.createStatement();
			// 4�ܰ�
			String sql  = "UPDATE `Employee` SET ";
			       sql += "`name`='"+name+"',";
			       sql += "`hp`='"+hp+"',";
			       sql += "`pos`='"+pos+"',";
			       sql += "`dep`='"+dep+"' ";
			       sql += "WHERE `uid`='"+uid+"'";
			
			stmt.executeUpdate(sql);       
			
			// 5�ܰ�
			// 6�ܰ�
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteEmployee(String uid) {
		
		try {
			// 1�ܰ�
			Class.forName("com.mysql.jdbc.Driver");
			// 2�ܰ�
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			
			// 3�ܰ�
			Statement stmt = conn.createStatement();
			
			// 4�ܰ�
			String sql = "DELETE FROM `Employee` WHERE `uid`='"+uid+"'";
			stmt.executeUpdate(sql);
			
			// 5�ܰ�
			// 6�ܰ�			
			stmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}












