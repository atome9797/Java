package DTO;

public class numDTO {

	String num;
	
	public numDTO() {
		// TODO Auto-generated constructor stub
	}
	
	private static numDTO dto=new numDTO();
	
	public static numDTO getInstance() {
		return dto;
	}
	
	
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
	
	
}
