package DTO;

public class FreeBoardDTO {

	private String nickName;
	private String freeSubject;
	private String freeContent;
	
	public FreeBoardDTO() {
	}

	public FreeBoardDTO(String nickName, String freeSubject, String freeContent) {
		super();
		this.nickName = nickName;
		this.freeSubject = freeSubject;
		this.freeContent = freeContent;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNinkName(String nickName) {
		this.nickName = nickName;
	}

	public String getFreeSubject() {
		return freeSubject;
	}

	public void setFreeSubject(String freeSubject) {
		this.freeSubject = freeSubject;
	}

	public String getFreeContent() {
		return freeContent;
	}

	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	
}
