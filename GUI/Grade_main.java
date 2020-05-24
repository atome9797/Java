package GUI;

public class Grade_main {
	public static void main(String[] args) {
		new Grade();
		
		Grade gd=Grade.getInit();
		DTO dto=new DTO();
		
		int cnt=gd.listMethod();
		
		if (cnt >=0) {
			System.out.printf("%d개 수정\n",cnt); //0개이면 하나도 없다는뜻 (오류아님)
		}else {
			System.out.println("수정 실패"); //예외발생된 경우임(정상 작동에선 수정실패 안나옴)
		}
		
		
	}
}
