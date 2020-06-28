package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.FreeBoardDTO;
import DTO.alarmBoardDTO;
import Login.JdbcTemplate;

public class PostDAO {
	private Connection conn;
	private Statement stmt;// 실행 시켜줄 메소드 가지고있음
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private PostDAO() {
	}
	private static PostDAO dao = new PostDAO();
	public static PostDAO getInstance() {
		return dao;
	}
	
	public List<FreeBoardDTO> selectFreeBoardPosts(){
		List<FreeBoardDTO> aList = new ArrayList<FreeBoardDTO>();
		
		try {
			conn = JdbcTemplate.getInit();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM freetextboard";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				FreeBoardDTO dto = new FreeBoardDTO();
				dto.setNinkName(rs.getString("nickname"));
				dto.setFreeSubject(rs.getString("free_subject"));
				dto.setFreeContent(rs.getString("free_content"));
				aList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		
		return aList;
	}
	
	public int insertFreeBoardPost(FreeBoardDTO dto) {
		int cnt = -1;
		try {
			conn = JdbcTemplate.getInit();
			String sql = "INSERT INTO freetextboard VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNickName());
			pstmt.setString(2, dto.getFreeSubject());
			pstmt.setString(3, dto.getFreeContent());
			cnt = pstmt.executeUpdate(); //업데이트 되면 1됨
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		
		return cnt;
	}
	
	
	public int insertAlarm(alarmBoardDTO dto) {
		int cnt=-1;
		
		try {
			conn=JdbcTemplate.getInit();
			String sql="insert into alarmboard values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNickName());
			pstmt.setString(2, dto.getAlarm_subject());
			pstmt.setString(3, dto.getAlarm_content());
			cnt=pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(rs);
		}		
		
		return cnt;
	}
	
	
	public List<alarmBoardDTO> selectAlarm(){
		List<alarmBoardDTO> aList = new ArrayList<alarmBoardDTO>();
		
		try {
			conn = JdbcTemplate.getInit();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM alarmboard";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				alarmBoardDTO dto = new alarmBoardDTO();
				dto.setNickName(rs.getString("nickname"));
				dto.setAlarm_subject(rs.getString("alarm_subject"));
				dto.setAlarm_content(rs.getString("alarm_content"));
				aList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		
		return aList;
	}
	
	
	
	
}
