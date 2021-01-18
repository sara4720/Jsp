package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.farmstory2.config.DBConfig;
import kr.co.farmstory2.config.Sql;
import kr.co.farmstory2.vo.MemberVo;
import kr.co.farmstory2.vo.TermsVo;

public class UserDao {

	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}
	private UserDao() {}
	
	public void insertUser(MemberVo vo) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick());
			psmt.setString(5, vo.getEmail());
			psmt.setString(6, vo.getHp());
			psmt.setString(7, vo.getZip());
			psmt.setString(8, vo.getAddr1());
			psmt.setString(9, vo.getAddr2());
			psmt.setString(10, vo.getRegip());
			
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public TermsVo selectTerms() {
		
		TermsVo vo = null;
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_TERMS);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new TermsVo();
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}

			rs.close();
			psmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public int selectCountUser(String uid) {
		
		int result = 0;
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_USER);
			psmt.setString(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);				
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public MemberVo selectUser(String uid, String pass) {
		
		MemberVo vo = null;
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			ResultSet rs = psmt.executeQuery();
			
			
			if(rs.next()) {
				vo = new MemberVo();
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			
			rs.close();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public void selectUsers() {}
	public void updateUser() {}
	public void deleteUser() {}
	
}











