package java0513_inner;

class Outer{
	private int x;
	static private int y;
	
	static class Inner{
		int z;
		void prn() {
			y=20;
			z=40;
			System.out.printf("%dx%d=%d\n",y,z,y*z);
		}
	}
}

//스태틱 클래스를 하위 클래스로 만들어 다음결과값이 나오도록 구현하시오.

public class Inner_exam {
	public static void main(String[] args) {

		Outer.Inner inn=new Outer.Inner();
		inn.prn();
		
	}
}
