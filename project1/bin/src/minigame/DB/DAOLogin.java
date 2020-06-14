package minigame.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minigame.client.clientMain;

public class DAOLogin {
	
	private Connection conn;
	private Statement stmt;// 실행 시켜줄 메소드 가지고있음
	private PreparedStatement pstmt;
	private ResultSet rs;

	//private static DAOLogin dao = new DAOLogin();// 싱글톤 패턴 작성위한 객체생성

//	public static DAOLogin getInstance() {
//
//		return dao;
//
//	}

	public DAOLogin() {}
	
	void Coin(DTOLogin dto) {
		try {
			conn= JdbcTemplate.getInit();
			String sql= "insert into coin_score(nickname) values (?)";
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNick());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	void Card(DTOLogin dto) {
		try {
			conn = JdbcTemplate.getInit();
			String sql = "insert into RECORDGAME_SCORE(nickname) values (?)";
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNick());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	void Bingo(DTOLogin dto) {
		try {
			conn = JdbcTemplate.getInit();
			String sql = "insert into binggoscore(nickname) values (?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNick());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//	public DTOLogin nickMethod(DTOLogin dto) {// 로그인 할때 텍스트와 비교후 nickname을 가져와 DTO에 저장한다음-> binggoDTO와 DTOCoin , cardgameDTO에
//									// Nickname을 뿌려주는 역할
//
//		try {
//			
//			conn = JdbcTemplate.getInit();
//			String sql = "select nickname from userset where id=? AND password=?";
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dto.getId());
//			pstmt.setString(2, dto.getPassword());
//			rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				dto.setNick(rs.getString("nickname"));
//
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JdbcTemplate.close(conn);
//			JdbcTemplate.close(pstmt);
//			JdbcTemplate.close(rs);
//		}
//
//		return dto;
//	}
///////////////////////////////////////////////

	public boolean loginPass(DTOLogin dto,clientMain client) { // 서버로 연결할때 참인지 거짓인지 판단해주는 메소드

		boolean a = false;
	//	DTOLogin dto = null;// try에서 예외값출현시 catch로 넘어감으로 ,return시에는 dto를 초기화 시켜줘야한다.
		try {
			
			conn = JdbcTemplate.getInit();
			String sql = "select * from userset where id=? AND password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			rs =pstmt.executeQuery();
			if (rs.next()) {
				a = true;
				dto.setNick(rs.getString("nickname"));
				System.out.println("aaaaaaaaaa");
				client.user.logdto = dto;
				
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

	public void RegisterMethod(DTOLogin dto) {

		try {
			
			conn = JdbcTemplate.getInit();
			String sql = "insert into userset values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			// DB엔 스트링으로 저장됨
			pstmt.setString(4, String.valueOf(dto.getSextual()));
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getNumber1());
			pstmt.setString(7, dto.getNick());

			pstmt.executeUpdate();
			Coin(dto);
			Card(dto);
			Bingo(dto);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);

		}
	}

}
