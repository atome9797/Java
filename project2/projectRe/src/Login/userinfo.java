package Login;


public class userinfo {

	
	String nickname;
	
	
	public userinfo() {
		// TODO Auto-generated constructor stub
	}
	
	private static userinfo dao=new userinfo();
	
	public static userinfo getInstance() {
		return dao;
	}
	

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
	
	
}
