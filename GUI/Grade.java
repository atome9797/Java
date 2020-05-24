package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Grade extends Frame implements ActionListener{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	int e;
	int m;
	
	Button bt1,bt2;
	
	TextField tf1,tf2,tf3,tf4;
	
	Label label_g1,label_g2,label_eng,label_math,label_eng2,label_math2,label_sum;
	
	Panel p1,p2;
	
	
	public Grade() {
		
		p1=new Panel();
		p2=new Panel();
		
		
		tf1=new TextField("",4);//�ʱⰪ ,ũ��
		tf2=new TextField("",4);
		tf3=new TextField("",4);
		tf4=new TextField("",4);
		
		label_g1=new Label("�����Է����ּ���.");
		label_g1.setBackground(Color.yellow);
		label_g2=new Label("�����Է����ּ���.");
		label_g2.setBackground(Color.yellow);
		label_eng=new Label("����");
		label_math=new Label("����");
		label_eng2=new Label("����");
		label_math2=new Label("����");
		
		bt1=new Button("g1 ���� ����");
		bt2=new Button("g2 ���� ����");
		
		//frame���� ��ư�� �׼��� �ްڴ�.(�̺�Ʈ �߻���)
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		
		p1.add(label_eng);
		p1.add(tf1);
		p1.add(label_math);
		p1.add(tf2);
		p1.add(label_g1);
		p1.add(bt1);
		
		p2.add(label_eng2);
		p2.add(tf3);
		p2.add(label_math2);
		p2.add(tf4);
		p2.add(label_g2);
		p2.add(bt2);
		
		add(p1);
		add(p2);
		
		//////////////////////////////////////
		this.setLayout(new GridLayout(2,1));
		this.setBounds(700,300,500,120);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		
	}//������
	
	public void actionPerformed(ActionEvent e) {
		String str =e.getActionCommand();//��ư�� ���ڿ� ��ȯ
		int score;
		String s,s2,math,eng;
		
		if (str.equals("g1 ���� ����")) {
			try {
			eng=tf1.getText();
			math=tf2.getText();
			int a=Integer.parseInt(eng);
			int b=Integer.parseInt(math);
			
			this.input_Grade(a,b);
			score=this.sum_Grade();
			s=Integer.toString(score);
			label_g1.setText(s);
			} catch (Exception ev){
				label_g1.setText("���ڸ� �Է��ϼ���");
			}
			
		}else if (str.equals("g2 ���� ����")) {
			
			try {
				eng=tf3.getText();
				math=tf4.getText();
				int a=Integer.parseInt(eng);
				int b=Integer.parseInt(math);
				
				this.input_Grade(a,b);
				score=this.sum_Grade();
				s2=Integer.toString(score);
				label_g2.setText(s2);
				} catch (Exception ev){
					label_g2.setText("���ڸ� �Է��ϼ���");
				}
		}else {
			s="�����Դϴ�.";
			label_g1.setText(s);
			label_g2.setText(s);
		}
		
	}//end �׼�
	
	DTO dto;
	public void input_Grade(int a,int b) {
		e=a;
		m=b;
		dto=new DTO();
		dto.setNumA(e);
		dto.setNumB(m);
	}
	
	public int sum_Grade() {
		dto=new DTO();
		dto.setSum(e+m);
		
		return e+m;
		
	}
	
	//list
	
	private static Grade dao=new Grade();
	
	public static Grade getInit() {
		return dao;
	}
	
	
	public int listMethod(){
		DTO dto=new DTO();
		int cnt=-1;
		
		try {
			conn=Template.Init();
			String sql="insert into cal1 values (?,?,?)";
			//rs=stmt.executeQuery(sql);//��� ��½� ���
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNumA());
			pstmt.setInt(2, dto.getNumB());
			pstmt.setInt(3, dto.getSum());
			cnt=pstmt.executeUpdate();
		
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Template.close(conn);
			Template.close(pstmt);
			Template.close(rs);
		}
		
		
		return cnt;
		
	}
	
}

