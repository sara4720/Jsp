package kr.co.jboard1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kr.co.jboard1.bean.ArticleBean;
import kr.co.jboard1.bean.FileBean;
import kr.co.jboard1.db.DBConfig;

public class ArticleDao {
	
	// �̱��� ��ü
	private static ArticleDao instance = new ArticleDao();
	private ArticleDao() {}
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	// DB���� ���� �����ü 
	private Connection        conn = null;
	private PreparedStatement psmt = null;
	private Statement         stmt = null;
	private ResultSet         rs   = null;
	
	// ����Ʈ ������ó�� ���� �޼���
	public int[] getPageGroup(int currentPg, int lastPageNum) {
		int groupCurrent = (int)Math.ceil(currentPg/10.0);
		int groupStart   = (groupCurrent - 1) * 10 + 1;
		int groupEnd     = groupCurrent * 10 ;
		
		if(groupEnd > lastPageNum){
			groupEnd = lastPageNum;
		}
		
		int[] groups = {groupStart, groupEnd};
		
		return groups;
		
	}	
	public int getCurrentStartNum(int total, int limitStart) {
		return total - limitStart;
	}
	public int getCurrentPg(String pg) {
		int currentPg = 1;
		
		if(pg != null){
			currentPg = Integer.parseInt(pg);
		}
		
		return currentPg;
	}
	public int getLimitStart(int currentPg) {
		int limitStart = (currentPg - 1) * 10;
		
		return limitStart;
	}	
	public int getLastPgNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 ==0){
			lastPageNum = total / 10;
		}else{
			lastPageNum = total/10 + 1;
		}
		
		return lastPageNum;
	}	
	public int selectCountArticle() throws Exception {
		
		// 1 ~ 2 �ܰ�
		conn = DBConfig.getInstance(). getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT COUNT(*) FROM `JBOARD_ARTICLE` WHERE `parent`= 0";
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		int total = 0;
		if(rs.next()){
			total = rs.getInt(1);
		}
		
		// 6�ܰ�
		close();
		
		return total;
	}
	
	// ÷������ ���� �޼���
	public void updateFileDownload(String seq) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "UPDATE `JBOARD_FILE` SET `download`=`download`+1 ";
	           sql += "WHERE `seq`="+seq;
		
		stmt.executeUpdate(sql);
		
		// 6�ܰ�
		close();
		
	}
	public void insertFile(FileBean fb) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "INSERT INTO `JBOARD_FILE` SET ";
				// parent �� �����̱� ������ ''ó�� ����
		       sql += "`parent`="+fb.getParent()+",";
		       sql += "`oldName`='"+fb.getOldName()+"',";
		       sql += "`newName`='"+fb.getNewName()+"',";
		       sql += "`rdate`= NOW();"; 
		       
		stmt.executeUpdate(sql);
		
		// 6�ܰ�
		close();
	}
	public FileBean selectFile(String seq) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
				
	    // 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT * FROM `JBOARD_FILE` WHERE `seq` ="+seq;
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		FileBean fb = new FileBean();
		
		if(rs.next()) {
			fb.setSeq(rs.getInt(1));
			fb.setParent(rs.getInt(2));
			fb.setOldName(rs.getString(3));
			fb.setNewName(rs.getString(4));
			fb.setDownload(rs.getInt(5));
			fb.setRdate(rs.getString(6));
		}
		
		// 6�ܰ�
		close();
		
		return fb;
		
	}
	
	// �Խñ� ���� �޼���
	public int insertArticle(ArticleBean ab) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "INSERT INTO `JBOARD_ARTICLE` SET ";
			   sql += "`title`='"+ab.getTitle()+"',";
			   sql += "`content`='"+ab.getContent()+"',"; 
			   sql += "`file`="+ab.getFile()+","; 
			   sql += "`uid`='"+ab.getUid()+"',";
			   sql += "`regip`='"+ab.getRegip()+"',";
			   sql += "`rdate`=NOW();"; 
			    
		stmt.executeUpdate(sql);

		// 6�ܰ�
		close();
		
		return selectMaxseq();
	}
	public int selectMaxseq() throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT MAX(`seq`) FROM `JBOARD_ARTICLE`;";
		
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		int parent = 0;
		
		if(rs.next()) {
			parent = rs.getInt(1);
		}
		
		// 6�ܰ�
		close();
		
		return parent;
	}
	public ArticleBean selectArticle(String seq) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "SELECT a.*, b.seq, b.oldName, b.download FROM `JBOARD_ARTICLE` AS a ";
		       sql += "LEFT JOIN `JBOARD_FILE` AS b ";
		       sql += "ON a.seq = b.parent ";
		       sql += "WHERE a.seq ="+seq;
		
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		ArticleBean ab = new ArticleBean();
		
		if(rs.next()){
			ab.setSeq(rs.getInt(1));
			ab.setParent(rs.getInt(2));
			ab.setComment(rs.getInt(3));
			ab.setCate(rs.getString(4));
			ab.setTitle(rs.getString(5));
			ab.setContent(rs.getString(6));
			ab.setFile(rs.getInt(7));
			ab.setHit(rs.getInt(8));
			ab.setUid(rs.getString(9));
			ab.setRegip(rs.getString(10));
			ab.setRdate(rs.getString(11));
			ab.setFileSeq(rs.getInt(12));
			ab.setOldName(rs.getString(13));
			ab.setDownload(rs.getInt(14));
		}
		
		// 6�ܰ�
		close();
		
		return ab;
	}
	public List<ArticleBean> selectArticles(int limitStart) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "SELECT a.*, b.nick FROM `JBOARD_ARTICLE` AS a ";
			   sql += "JOIN `JBOARD_MEMBER` AS b ";
			   sql += "ON a.uid = b.uid ";	
			   sql += "WHERE `parent`= 0 ";	
		       sql += "ORDER BY `seq` DESC ";
		       sql += "LIMIT "+limitStart+", 10;";
		       
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		List<ArticleBean> articles = new ArrayList<>();
		
		while(rs.next()){
			
			ArticleBean ab = new ArticleBean();
			
			ab.setSeq(rs.getInt(1));
			ab.setParent(rs.getInt(2));
			ab.setComment(rs.getInt(3));
			ab.setCate(rs.getString(4));
			ab.setTitle(rs.getString(5));
			ab.setContent(rs.getString(6));
			ab.setFile(rs.getInt(7));
			ab.setHit(rs.getInt(8));
			ab.setUid(rs.getString(9));
			ab.setRegip(rs.getString(10));
			ab.setRdate(rs.getString(11));
			ab.setNick(rs.getString(12));
			
			articles.add(ab);
		}
		
		// 6�ܰ�
		close();
		
		return articles;
	}
	public void updateArticle(String seq, String title, String content) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		String sql  = "UPDATE `JBOARD_ARTICLE` SET `title`=?, `content`=? WHERE `seq`=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, seq);
		
		// 4�ܰ�
		psmt.executeUpdate();

		// 6�ܰ�
		close();
	}
	public void updateArticleComment(String parent)  throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� 
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql   = "UPDATE `JBOARD_ARTICLE` SET `comment`=`comment`-1 ";
			   sql  += "WHERE `seq`="+parent;
			   
		stmt.executeUpdate(sql);
		
		// 6�ܰ�
		close();
		
	}
	public void updateHit(String seq) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� 
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sqlUpdate = "UPDATE `JBOARD_ARTICLE` SET `hit`=`hit`+1 WHERE `seq`="+seq;
		stmt.executeUpdate(sqlUpdate);
		
		// 6�ܰ�
		close();
	}	
	public void deleteArticle(String seq) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		String sql = "DELETE FROM `JBOARD_ARTICLE` WHERE `seq`=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, seq);
		
		// 4�ܰ�
		psmt.executeUpdate();

		// 6�ܰ�
		close();
	}
	
	// ��� ���� �޼���
	public void updateCommentCount(String seq) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
				
		// 3�ܰ� 
		stmt = conn.createStatement();
				
		// 4�ܰ�
		String sql = "UPDATE `JBOARD_ARTICLE` SET `comment`=`comment`+1 ";
			   sql += "WHERE `seq`="+seq;
			   
		stmt.executeUpdate(sql);
			   
		// 6�ܰ�
		close();
		
	}
	public void insertComment(ArticleBean ab) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� 
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "INSERT INTO `JBOARD_ARTICLE` SET ";
	       sql += "`parent`='"+ab.getParent()+"',";
	       sql += "`content`='"+ab.getContent()+"',";
	       sql += "`uid`='"+ab.getUid()+"',";
	       sql += "`regip`='"+ab.getRegip()+"',";
	       sql += "`rdate`=NOW();";
			   
	    stmt.executeUpdate(sql);

	    // 6�ܰ�
		close();
		
	}
	public void selectComment()  throws Exception {}
	public List<ArticleBean> selectComments(String parent) throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� 
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql  = "SELECT a.*, b.nick FROM `JBOARD_ARTICLE` AS a ";
		   sql += "JOIN `JBOARD_MEMBER` AS b "; 
		   sql += "ON a.uid=b.uid "; 
	       sql += "WHERE `parent`="+parent+" ";
	       sql += "ORDER BY `seq` ASC;";
			   
		rs = stmt.executeQuery(sql);

		// 5�ܰ�
		List<ArticleBean> comments = new ArrayList<>();
		while(rs.next()) {
			ArticleBean ab = new ArticleBean();
			ab.setSeq(rs.getInt(1));
			ab.setParent(rs.getInt(2));
			ab.setComment(rs.getInt(3));
			ab.setCate(rs.getString(4));
			ab.setTitle(rs.getString(5));
			ab.setContent(rs.getString(6));
			ab.setFile(rs.getInt(7));
			ab.setHit(rs.getInt(8));
			ab.setUid(rs.getString(9));
			ab.setRegip(rs.getString(10));
			ab.setRdate(rs.getString(11));	
			ab.setNick(rs.getString(12));	
			
			comments.add(ab);
		}
		
		// 6�ܰ�
		close();
		return comments;
		
	}
	public int updateComment(String content, String seq) throws Exception {
	
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� 
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "UPDATE `JBOARD_ARTICLE` SET `content` = '"+content+"' WHERE `seq`="+seq;
		int result = stmt.executeUpdate(sql);
		
		// 6�ܰ�
		close();
		
		return result;
		
	}
	public void deleteComment(String seq)  throws Exception {
		
		// 1~2�ܰ�
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� 
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "DELETE FROM `JBOARD_ARTICLE` WHERE `seq`="+seq;
		stmt.executeUpdate(sql);
		
		// 6�ܰ�
		close();
	}
	
	// DB Access ��ü �޼���
	public void close() throws Exception {
		if(rs   != null) rs.close();		
		if(stmt != null) stmt.close();
		if(psmt != null) psmt.close();
		if(conn != null) conn.close();	
	}
}
