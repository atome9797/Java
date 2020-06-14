package minigame.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class coinDAO {
	DTOLogin ldto;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static coinDAO dao = new coinDAO();
	
	public coinDAO() {}
	
	public static coinDAO getInstance() {
		return dao;
	}
	
	public DTOLogin coinnick() {
		
		DTOLogin dto = null;
		try {
			conn = JdbcTemplate.getInit();
			String sql = "select nickname , max(score)" + 
					" from coin_score" + 
					" group by nickname";
			
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto=new DTOLogin();
//				coinDTO cdto = new coinDTO();
//				cdto.setNickname(rs.getString("nickname"));
				dto.getCoin().setNickname(rs.getString("nickname"));
				dto.getCoin().setScore(rs.getInt("score"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(rs);
		}
		
		return dto;
	}
	
	public coinDTO coinscore(int a, String b) {
		
		
		try {
		//	coinscore score=new coinscore();
			conn = JdbcTemplate.getInit();
	String sql = "update coin_score set score=? where nickname = '?'";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,a);
			pstmt.setString(2,b);
			pstmt.executeUpdate();
	//		System.out.println(pstmt);
			
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);
		}
		return null;
		
		
		
	}
	
	
}
