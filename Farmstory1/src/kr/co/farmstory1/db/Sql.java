package kr.co.farmstory1.db;

public class Sql {

	// ȸ������ SQL
	public static final String SELECT_TERMS = "SELECT * FROM `JBOARD_TERMS`";
	public static final String INSERT_USER = "INSERT INTO `JBOARD_MEMBER` SET "
										   + "`uid`=?,"
										   + "`pass`=PASSWORD(?),"
										   + "`name`=?,"
										   + "`nick`=?,"
										   + "`email`=?,"
										   + "`hp`=?,"
										   + "`zip`=?,"
										   + "`addr1`=?,"
										   + "`addr2`=?,"
										   + "`regip`=?,"
										   + "`rdate`=NOW();";
	
	public static final String SELECT_USER = "SELECT * FROM `JBOARD_MEMBER` "
										   + "WHERE "
										   + "`uid`=? AND `pass`=PASSWORD(?)";
			                               
	
	// �Խ��ǰ��� SQL
	
	
	// ��Ÿ SQL
	
}