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
	
	
	Color buttonColor =Color.LIGHT_GRAY; //��ư ���� (ȸ��)
	
	JPanel panel;
	
	
	String val1=null;//?
	String val2=null;
	String sign=null;
	
	Double result=0.0; //������ ����
	
	
	
	
	
	
	public Calculator() {
		setTitle("�ڹٰ���");
		
		//��ư�� �ؽ�Ʈ�ʵ� ����
		tf=new JTextField("0",100); //�ʱⰪ���:0 ũ�� 20
		tf.setHorizontalAlignment(JTextField.RIGHT);//�ؽ�Ʈ�ʵ��� ������ ����������
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
		
		//����� ���̾ƿ� ����
		panel.setLayout(new GridLayout(5,4,2,2)); //5��4�� ���� ����2ũ��
		
		
		//�гο� ��ư ����
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
		
		
		//�����ӿ� ����(tf�ؽ�Ʈ�� ������ �����Ҽ��ִ�. border����)
		this.add(tf,"North");
		this.add(panel);
		
		//��ư�� �̺�Ʈ����
		//�����ʿ� frame���ִ� b1�� �ְڴٴ� ��
		
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
	
	
	//�̺�Ʈ�� ���� �׼�����
	public void actionPerformed(ActionEvent e) {
		String data=e.getActionCommand(); 
		//�̺�Ʈ ������ �������� data������
		//�� ���� ���� �׼ǽ����� ����  
		//������ ���ڿ� �޸� ������ �ʵ忡 �־���
		
		
		 if(data.equals("0") || data.equals("1") || data.equals("2") ||
				 data.equals("3") || data.equals("4") || data.equals("5") ||
				 data.equals("6") || data.equals("7") || data.equals("8") || 
				 data.equals("9") || data.equals("00")){
			   if((tf.getText()).equals("0") || (tf.getText()).equals("00"))
			    tf.setText(""); // ���� 0�̾ƴ� �ٸ� ���ڸ� ������ �������� �ʱ�ȭ
			   val1 = tf.getText() + data; // ���ڸ� �̾ �ٿ���
			   tf.setText(val1); // �ش� ���� TextField�� set
			  }
		
		
		
		
		
		if (data.equals(".")) {
			if (tf.getText().equals("0")||tf.getText().equals("00")) {
				tf.setText("0"); //0�̳� 00�Ѵ� �ؽ�Ʈ�� 0���� ǥ���ϱ�
				
			}
			val1 =tf.getText() +data; //append����
			tf.setText(val1); //append�� ���� �ʵ忡 ������
		}
		
		if (data.equals("+")||data.equals("-")||data.equals("*")||data.equals("/")||data.equals("=")) {
			if (data.equals("+")) {
				tf.setText(""); //+�� �ؽ�Ʈ�� ǥ�þȵ�
				if (val1 !=null) {
					val2=val1; //val1������ ����
					val1=null; //val1��������
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
					//�������� ���ο�� ������
					result=Double.parseDouble(val2)+Double.parseDouble(val1);
				}
				if (sign.equals("-")) {
					//�������� ���ο�� ����
					result=Double.parseDouble(val2)-Double.parseDouble(val1);
				}
				if (sign.equals("*")) {
					//�������� ���ο�� ������
					result=Double.parseDouble(val2)*Double.parseDouble(val1);
				}
				if (sign.equals("/")) {
					//�������� ���ο�� ������
					result=Double.parseDouble(val2)/Double.parseDouble(val1);
				}
				
				tf.setText("");
				val2=String.valueOf(result); //���ڸ� ���ڿ��� ��ȯ�ϰڴٴ¶�
				tf.setText(val2);
				val2=String.format("%.2f", result);//result�� �Ҽ��� �ι�°�ڸ�������
				
				val1=null;
			}
		}//end if(����)
		
		if (data.equals("ce")) { //�ؽ�Ʈ0���� �ʱ�ȭ�� val1�ʱ�ȭ�Ѵ�.
			tf.setText("0");
			val1=null;
		}
		
		if (data.equals("c")) {
			tf.setText("0");
			val1=null;
		}
		if (data.equals("<-")) {
			val1=tf.getText(); //���ڿ��� ������
			char[]ch =val1.toCharArray(); //�迭�� ���� 
			String temp;
			
			ch[ch.length-1] ='\n'; //���� �ֱ��Է°��� null�� �ٲ۴�.
			String str=new String(ch); //�迭���� ���ڿ���
			
			temp =str.substring(0,ch.length-1);//0���� null������
			
			val1=temp;
			tf.setText(temp);//�ؽ�Ʈ�� ǥ��
			
		}
		
	}//end method
	
}