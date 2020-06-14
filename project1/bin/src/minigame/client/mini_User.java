package minigame.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import minigame.DB.DTOLogin;




public class mini_User {
	String id, pw, userName, IP;
	boolean online;
	DataInputStream is;
	DataOutputStream os;
	ArrayList<minigame_Room> userooms; 
	public DTOLogin logdto=new DTOLogin();
	
	
	public static final String LOGIN = "EI"; // 로그인
	public static final String LOGOUT = "EO"; // 로그아웃
	public static final String MEMBERSHIP = "EM"; // 회원가입


	public static final String UPDATE_SELECTEDROOM_USERLIST = "ED"; // 대기실에서 선택한 방의 유저리스트 업데이트
	public static final String UPDATE_ROOM_USERLIST = "ES"; // 방의 유저리스트 업데이트
	public static final String UPDATE_USERLIST = "EU"; // 유저리스트 업데이트
	public static final String UPDATE_ROOMLIST = "ER"; // 방리스트 업데이트
	
	
	public static final String CREATE_ROOM = "RC"; // 방 생성
	public static final String GETIN_ROOM = "RI"; // 방 들어옴
	public static final String GETOUT_ROOM = "RO"; // 방 나감
	public static final String ECHO01 = "MM"; // 대기실 채팅
	public static final String ECHO02 = "ME"; // 게임방 채팅
	
	
	public static final String PUTBUTTON = "PB";
	public static final String SENDBUTTON = "SB";
	
	
	
	mini_User() {

	}
	
	public mini_User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	public mini_User(DataInputStream is, DataOutputStream os) {
		this.is = is;
		this.os = os;
		userooms = new ArrayList<minigame_Room>();
	}
	

	public ArrayList<minigame_Room> getRoomArray() {
		return userooms;
	}



	public void setRooms(ArrayList<minigame_Room> userooms) {
		this.userooms = userooms;
	}



	public String toStringforLogin() {
		return id + "/" + pw;
	}
	
	public String toProtocol() {
		return id + "/" + userName;
	}

	public String toString() {
		return userName + "(" + id + ")";
	}
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}


	public DataInputStream getIs() {
		return is;
	}

	public void setIs(DataInputStream is) {
		this.is = is;
	}

	public DataOutputStream getOs() {
		return os;
	}

	public void setOs(DataOutputStream os) {
		this.os = os;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	
	
	
	
}
