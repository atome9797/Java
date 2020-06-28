package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.DTOLogin;
import Login.JdbcTemplate;
import Login.userinfo;


public class DAOLogin {
	
	private Connection conn;
	private Statement stmt;// 실행 시켜줄 메소드 가지고있음
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static DAOLogin dao = new DAOLogin();// 싱글톤 패턴 작성위한 객체생성

	public static DAOLogin getInstance() {

		return dao;

	}

	public DAOLogin() {}
	


	public boolean loginPass(DTOLogin dto) { // 서버로 연결할때 참인지 거짓인지 판단해주는 메소드

		boolean a = false;
	//	DTOLogin dto = null;// try에서 예외값출현시 catch로 넘어감으로 ,return시에는 dto를 초기화 시켜줘야한다.
		try {
			
			conn = JdbcTemplate.getInit();
			String sql = "select * from userboard where user_id=? AND user_password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			rs =pstmt.executeQuery();
			if (rs.next()) {
				a = true;
				userinfo dto1=userinfo.getInstance();
				
				dto1.setNickname(rs.getString("nickname"));
				
				
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(rs);
		}
		return a;

	}

////////////////////////////////////////////

	public boolean RegisterMethod(DTOLogin dto,String id) {

		boolean a=false;
		try {
			
			conn = JdbcTemplate.getInit();
			
			String sql="select * from userboard where user_id='" + id + "'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(!rs.next()) {
			conn = JdbcTemplate.getInit();
			
			sql = "insert into userboard values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			// DB엔 스트링으로 저장됨
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, String.valueOf(dto.getSextual()));
			pstmt.setString(6, dto.getNumber1());
			pstmt.setString(7, dto.getNickname());
			pstmt.executeUpdate();
			
				a=true;
			
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);

		}
		return a;
	}

}
