package minigame.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class cardDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static cardDAO dao = new cardDAO();

	public cardDAO() {
	}

	public static cardDAO getInstance() {
		return dao;
	}
//	
//	public void insertMethod() {
//		try {
//			cardDTO dto = new cardDTO();
//			conn = JdbcTemplate.getInit();
//			conn.setAutoCommit(false);
//			String sql = "INSERT INTO RECORDGAME_SCORE" + 
//					     " values (?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dto.getNickname());
//			pstmt.setInt(2, dto.getCount());
//			pstmt.setInt(3, dto.getRecord());
//			pstmt.executeUpdate();
//			
//			conn.commit();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JdbcTemplate.close(rs);
//			JdbcTemplate.close(stmt);
//			JdbcTemplate.close(pstmt);
//			JdbcTemplate.close(conn);
//		}
//	}
//	
//	
//
//	public List<cardDTO> rangeMethod(HashMap<String, Integer>map) {
//		List<cardDTO> aList = new ArrayList<cardDTO>();
//
//		try {
//			conn = JdbcTemplate.getInit();
//			String sql = "select b.*" + 
//					" from(select rownum rn, a.*" + 
//					" from(select nickname, count, record" + 
//					" from recordgame_score" + 
//					" order by count  asc)a)b" + 
//					" where b.rn >= ? and b.rn<=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, map.get("startRow"));
//			pstmt.setInt(2, map.get("endRow"));
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				cardDTO dto = new cardDTO();
//				dto.setNickname(rs.getString("nickname"));
//				dto.setCount(rs.getInt("count"));
//				dto.setRecord(rs.getInt("record"));
//				aList.add(dto);
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//
//			e.printStackTrace();
//		}finally {
//			JdbcTemplate.close(rs);
//			JdbcTemplate.close(stmt);
//			JdbcTemplate.close(pstmt);
//			JdbcTemplate.close(conn);
//		}
//
//		return aList;
//
//	}

}
