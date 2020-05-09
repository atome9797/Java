package java0428_inheritance.prob.part08;

public class Letter {
	String name;//private사용하면 안넘어가짐 default로넘겨줘야함
	String season;
	
	public Letter() {
	}
	
	public Letter(String name,String season) {
		this.name=name;
		this.season=season;
		
	}
	
	void prn(){
		System.out.printf("%s %s",name,season);
	}
	
}
