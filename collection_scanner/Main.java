package java0511_collection_myexam;

import java.util.HashMap;
import java.util.Scanner;
/*
 * 다음처럼 구현 되도록 Scanner와 HaspMap을 사용하여 구현하시오.
 * 찾는 상품입력:삼각김밥
      삼각김밥, GS25, 1100,    2
      찾는 상품입력:라면
      라면,   CU, 1100,    3
      찾는 상품입력:주스
       찾는 상품이 없습니다.
 */
public class Main {
	public static void main(String[] args) {
		Excel ex1=new Excel("삼각김밥","GS25",1100,2);
		Excel ex2=new Excel("라면","CU",1100,3);
		Excel ex3=new Excel("아이스크림","Emart24",1000,5);
		Excel ex4=new Excel("사이다","Seven",1500,7);
		Excel[]bs=new Excel[4];
		bs[0]=ex1;
		bs[1]=ex2;
		bs[2]=ex3;
		bs[3]=ex4;
		display(bs);
	}
	
	public static void display(Excel[]bs) {
		HashMap<String,Excel> map=new HashMap<String, Excel>();
		map.put(bs[0].getMenu(),bs[0]);
		map.put(bs[1].getMenu(),bs[1]);
		map.put(bs[2].getMenu(),bs[2]);
		
		Scanner k=new Scanner(System.in);
		
		System.out.print("찾는 상품입력:");
		String st=k.next();
		
		
		
		if (map.get(st)==null) {
			System.out.println("찾는 상품이 없습니다.");
			
		}else {
			System.out.printf("%s,%5s,%5d,%5d",map.get(st).getMenu(),map.get(st).getStore(),map.get(st).getPrice(),map.get(st).getAmount());
		}
		k.close();
		
		
		
	}
}
