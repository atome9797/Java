package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.FreeBoardDTO;
import DTO.OneToDTO;
import DTO.numDTO;
import Login.JdbcTemplate;
import Login.userinfo;

public class OneToDAO {

	private Connection conn;
	private Statement stmt;// 실행 시켜줄 메소드 가지고있음
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public OneToDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static OneToDAO dao = new OneToDAO();
	
	public static OneToDAO getInstance() {
		return dao;
	}
	
	
	public int insertOneToBoardPost(OneToDTO dto) {
		int cnt = -1;
		try {
			conn = JdbcTemplate.getInit();
			String sql = "INSERT INTO answerboard(num,nickname,user_subject,user_content) VALUES (answerboard_num_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNickName());
			pstmt.setString(2, dto.getUserSubject());
			pstmt.setString(3, dto.getUserContent());
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
	
	
	public List<OneToDTO> allBoardPosts(){
		List<OneToDTO> aList = new ArrayList<OneToDTO>();
		
		
		
		try {
			conn = JdbcTemplate.getInit();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM answerboard";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				OneToDTO dto = new OneToDTO();
				dto.setNum(rs.getString("num"));
				dto.setNickName(rs.getString("nickname"));
				dto.setUserSubject(rs.getString("user_subject"));
				dto.setUserContent(rs.getString("user_content"));
				dto.setAdminSubject(rs.getString("admin_subject"));
				dto.setAdminContent(rs.getString("admin_content"));
				
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
	
	
	
	public List<OneToDTO> selectOneTOBoardPosts(String nickName){
		List<OneToDTO> aList = new ArrayList<OneToDTO>();
		
		
		
		try {
			conn = JdbcTemplate.getInit();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM answerboard where nickname='"+nickName+"'";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				OneToDTO dto = new OneToDTO();
				dto.setNum(rs.getString("num"));
				dto.setNickName(rs.getString("nickname"));
				dto.setUserSubject(rs.getString("user_subject"));
				dto.setUserContent(rs.getString("user_content"));
				dto.setAdminSubject(rs.getString("admin_subject"));
				dto.setAdminContent(rs.getString("admin_content"));
				
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
	
	
	public int admin(OneToDTO dto,String num) {
		int cnt = -1;
		
		
		try {
			conn = JdbcTemplate.getInit();
			
			String sql = "update answerboard " + 
						 "set admin_subject=? , admin_content=? " + 
						 "where num='" +num+ "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAdminSubject());
			pstmt.setString(2, dto.getAdminContent());
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
	
	
	
	public int update(OneToDTO dto,String num) {
		int cnt = -1;
		
		
		try {
			conn = JdbcTemplate.getInit();
			
			String sql = "update answerboard " + 
						 "set user_subject=? , user_content=? " + 
						 "where num='" +num+ "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserSubject());
			pstmt.setString(2, dto.getUserContent());
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
	
	
	public int adminupdate(OneToDTO dto,String num) {
		int cnt = -1;
		
		
		try {
			conn = JdbcTemplate.getInit();
			
			String sql = "update answerboard " + 
						 "set admin_subject=? , admin_content=? " + 
						 "where num='" +num+ "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAdminSubject());
			pstmt.setString(2, dto.getAdminContent());
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
	
	
	public int deleteOneto() {
		int cnt=-1;
		
		try {

			conn=JdbcTemplate.getInit();
			String sql=" delete from answerboard where num=?";
			pstmt=conn.prepareStatement(sql);
			
			numDTO num=numDTO.getInstance();
			
			pstmt.setString(1, num.getNum());
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
	
	
}
