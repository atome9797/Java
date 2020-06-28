package DTO;

public class OneToDTO {

	
	String nickName;
	String userSubject;
	String userContent;
	String adminSubject;
	String adminContent;
	String num;
	
	public OneToDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public OneToDTO(String nickName, String userSubject, String userContent, String adminSubject, String adminContent,
			String num) {
		super();
		this.nickName = nickName;
		this.userSubject = userSubject;
		this.userContent = userContent;
		this.adminSubject = adminSubject;
		this.adminContent = adminContent;
		this.num = num;
	}





	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserSubject() {
		return userSubject;
	}

	public void setUserSubject(String userSubject) {
		this.userSubject = userSubject;
	}

	public String getUserContent() {
		return userContent;
	}

	public void setUserContent(String userContent) {
		this.userContent = userContent;
	}

	public String getAdminSubject() {
		return adminSubject;
	}

	public void setAdminSubject(String adminSubject) {
		this.adminSubject = adminSubject;
	}

	public String getAdminContent() {
		return adminContent;
	}

	public void setAdminContent(String adminContent) {
		this.adminContent = adminContent;
	}



	public String getNum() {
		return num;
	}



	public void setNum(String num) {
		this.num = num;
	}
	
	
	
	
	
}
