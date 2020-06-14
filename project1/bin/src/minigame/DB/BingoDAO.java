package minigame.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minigame.client.clientMain;
import minigame.client.minigame_Room;

public class BingoDAO {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	clientMain client;
	minigame_Room room;
	
	private static BingoDAO dao = new BingoDAO();

	public BingoDAO() {
	}

	public static BingoDAO getInstance() {
		return dao;
	}
	
	
	public BingoDTO bingoGetMethod() {
		BingoDTO dto=null;
		try {
			conn=JdbcTemplate.getInit();
			String sql="select nickname,max(win),max(lose),max(draw),max(winningrate)" + 
					" from binggoscore" + 
					" where nickname=?" + 
					" group by nickname";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, client.user.logdto.getNick() );
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				dto=new BingoDTO();
				
				dto.setWin(rs.getInt("max(win)"));
				dto.setLose(rs.getInt("max(lose)"));
				dto.setDraw(rs.getInt("max(draw)"));
				dto.setWinningrate(rs.getDouble("max(winningrate)"));
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	
	
	public void bingoSetMethod() {
		
		try {
			
			conn=JdbcTemplate.getInit();
			String sql="insert into binggoscore values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			client.user.logdto.getBingo().setWin(client.user.logdto.getBingo().getWin()+1);
			
			double a=client.user.logdto.getBingo().getWin() / (client.user.logdto.getBingo().getLose()+
					client.user.logdto.getBingo().getWin()+client.user.logdto.getBingo().getDraw());
			
			pstmt.setString(1, client.user.logdto.getNick());
			pstmt.setInt(2, client.user.logdto.getBingo().getWin());
			pstmt.setInt(3, client.user.logdto.getBingo().getLose());
			pstmt.setInt(4, client.user.logdto.getBingo().getDraw());
			pstmt.setDouble(5, a);
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
