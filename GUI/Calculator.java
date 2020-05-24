package GUI;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends Frame implements ActionListener{
	JTextField tf;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;
	
	
	Color buttonColor =Color.LIGHT_GRAY; //버튼 색깔 (회색)
	
	JPanel panel;
	
	
	String val1=null;//?
	String val2=null;
	String sign=null;
	
	Double result=0.0; //연산결과 저장
	
	
	
	
	
	
	public Calculator() {
		setTitle("자바계산기");
		
		//버튼과 텍스트필드 제조
		tf=new JTextField("0",100); //초기값출력:0 크기 20
		tf.setHorizontalAlignment(JTextField.RIGHT);//텍스트필드의 지역을 오른쪽정렬
		b1 =new JButton("<-");
		b2 =new JButton("ce");
		b3 =new JButton("c");
		b4 =new JButton("/");
		b5 =new JButton("7");
		b6 =new JButton("8");
		b7 =new JButton("9");
		b8 =new JButton("*");
		b9 =new JButton("4");
		b10 =new JButton("5");
		b11 =new JButton("6");
		b12 =new JButton("-");
		b13 =new JButton("1");
		b14 =new JButton("2");
		b15 =new JButton("3");
		b16 =new JButton("+");
		b17 =new JButton("0");
		b18 =new JButton("00");
		b19 =new JButton(".");
		b20 =new JButton("=");
		
		panel =new JPanel();
		
		//페널의 레이아웃 지정
		panel.setLayout(new GridLayout(5,4,2,2)); //5행4열 가로 세로2크기
		
		
		//패널에 버튼 부착
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		panel.add(b8);
		panel.add(b9);
		panel.add(b10);
		panel.add(b11);
		panel.add(b12);
		panel.add(b13);
		panel.add(b14);
		panel.add(b15);
		panel.add(b16);
		panel.add(b17);
		panel.add(b18);
		panel.add(b19);
		panel.add(b20);
		
		
		//프레임에 부착(tf텍스트의 방향을 지정할수있다. border없이)
		this.add(tf,"North");
		this.add(panel);
		
		//버튼에 이벤트부착
		//리스너에 frame에있는 b1을 넣겠다는 뜻
		
		b1.addActionListener(this); 
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		b16.addActionListener(this);
		b17.addActionListener(this);
		b18.addActionListener(this);
		b19.addActionListener(this);
		b20.addActionListener(this);
		
		
		
		this.setSize(255,300);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
}	//end calcuator
	
	
	//이벤트에 따른 액션지정
	public void actionPerformed(ActionEvent e) {
		String data=e.getActionCommand(); 
		//이벤트 실행후 받은값을 data로지정
		//그 값에 따른 액션실행을 위해  
		//임의의 문자열 메모리 생성후 필드에 넣어줌
		
		
		 if(data.equals("0") || data.equals("1") || data.equals("2") ||
				 data.equals("3") || data.equals("4") || data.equals("5") ||
				 data.equals("6") || data.equals("7") || data.equals("8") || 
				 data.equals("9") || data.equals("00")){
			   if((tf.getText()).equals("0") || (tf.getText()).equals("00"))
			    tf.setText(""); // 최초 0이아닌 다른 숫자를 누르면 공백으로 초기화
			   val1 = tf.getText() + data; // 숫자를 이어서 붙여줌
			   tf.setText(val1); // 해당 값을 TextField에 set
			  }
		
		
		
		
		
		if (data.equals(".")) {
			if (tf.getText().equals("0")||tf.getText().equals("00")) {
				tf.setText("0"); //0이나 00둘다 텍스트에 0으로 표시하기
				
			}
			val1 =tf.getText() +data; //append해줌
			tf.setText(val1); //append한 값을 필드에 보여줌
		}
		
		if (data.equals("+")||data.equals("-")||data.equals("*")||data.equals("/")||data.equals("=")) {
			if (data.equals("+")) {
				tf.setText(""); //+는 텍스트에 표시안됨
				if (val1 !=null) {
					val2=val1; //val1데이터 받음
					val1=null; //val1리셋해줌
				}
				sign="+";
			}
			
			if (data.equals("-")) {
				tf.setText("");
				if (val1 !=null) {
					val2=val1;
					val1=null;
				
				}
				sign="-";
			}
			
			if (data.equals("*")) {
				tf.setText("");
				if (val1 !=null) {
					val2=val1;
					val1=null;
				
				}
				sign="*";
			}
			
			if (data.equals("/")) {
				tf.setText("");
				if (val1 !=null) {
					val2=val1;
					val1=null;
				
				}
				sign="/";
			}
			
			
			
			if (data.equals("=")) {
				if (sign.equals("+")) {
					//이전값에 새로운값을 더해줌
					result=Double.parseDouble(val2)+Double.parseDouble(val1);
				}
				if (sign.equals("-")) {
					//이전값에 새로운값을 빼줌
					result=Double.parseDouble(val2)-Double.parseDouble(val1);
				}
				if (sign.equals("*")) {
					//이전값에 새로운값을 곱해줌
					result=Double.parseDouble(val2)*Double.parseDouble(val1);
				}
				if (sign.equals("/")) {
					//이전값에 새로운값을 나눠줌
					result=Double.parseDouble(val2)/Double.parseDouble(val1);
				}
				
				tf.setText("");
				val2=String.valueOf(result); //숫자를 문자열로 반환하겠다는뜻
				tf.setText(val2);
				val2=String.format("%.2f", result);//result를 소수점 두번째자리까지로
				
				val1=null;
			}
		}//end if(연산)
		
		if (data.equals("ce")) { //텍스트0으로 초기화후 val1초기화한다.
			tf.setText("0");
			val1=null;
		}
		
		if (data.equals("c")) {
			tf.setText("0");
			val1=null;
		}
		if (data.equals("<-")) {
			val1=tf.getText(); //문자열을 가져옴
			char[]ch =val1.toCharArray(); //배열로 받음 
			String temp;
			
			ch[ch.length-1] ='\n'; //가장 최근입력값을 null로 바꾼다.
			String str=new String(ch); //배열값을 문자열로
			
			temp =str.substring(0,ch.length-1);//0부터 null전까지
			
			val1=temp;
			tf.setText(temp);//텍스트에 표시
			
		}
		
	}//end method
	
}