package GUI;

public class Grade_main {
	public static void main(String[] args) {
		new Grade();
		
		Grade gd=Grade.getInit();
		DTO dto=new DTO();
		
		int cnt=gd.listMethod();
		
		if (cnt >=0) {
			System.out.printf("%d�� ����\n",cnt); //0���̸� �ϳ��� ���ٴ¶� (�����ƴ�)
		}else {
			System.out.println("���� ����"); //���ܹ߻��� �����(���� �۵����� �������� �ȳ���)
		}
		
		
	}
}
