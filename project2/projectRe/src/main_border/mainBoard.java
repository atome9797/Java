package main_border;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import alarm_border.alarm;
import free_border.FreeBoard;//클래스에 있는 보드
import oneToOne_border.OneToOneBoard;

public class mainBoard extends JFrame implements ActionListener{
	
	public FreeBoard freeboard;
	public JButton prevBtn,nextBtn,addBtn;
	//게시판 이동 링크
	public JButton freePageBtn, oneToOnePageBtn, alarmPageBtn;
	public JLabel titlelabel;
	//테이블
	public JLabel mainlabel;
	
	
	public mainBoard() {
		main();
		
	}
	
	public void main() {
		
		
		// 이미지 재설정
		String imgPath = "src/main_border/board.jpg";
		ImageIcon originIcon = new ImageIcon(imgPath);
		Image originImg = originIcon.getImage();
		Image changeImg = originImg.getScaledInstance(320, 250, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(changeImg);
		
		
		prevBtn = new JButton("이전페이지");
		nextBtn = new JButton("다음페이지");
		prevBtn.setBounds(20, 20, 100, 30);
		nextBtn.setBounds(140, 20, 100, 30);
		
		
		//링크 달아줌
		freePageBtn = new JButton("자유게시판");
		oneToOnePageBtn = new JButton("1:1문의");
		alarmPageBtn = new JButton("공지사항");
		freePageBtn.setBounds(245, 130, 100, 40);
		oneToOnePageBtn.setBounds(355, 130, 100, 40);
		alarmPageBtn.setBounds(465, 130, 100, 40);
		
		//페이지 제목 달아줌
		titlelabel=new JLabel("메인화면");
		titlelabel.setBounds(347,30,300,50);
		Font font=new Font("돋움", Font.PLAIN, 30);
		titlelabel.setFont(font);
		mainlabel=new JLabel(Icon);
		mainlabel.setBounds(230, 200, 350, 350);
		
		
		//등록버튼 과 테이블
		addBtn = new JButton("등록");
		
		
		addBtn.addActionListener(this); //등록버튼
		//searchTxt.addActionListener(this); //검색 필드
		freePageBtn.addActionListener(this); //자유게시판
		oneToOnePageBtn.addActionListener(this);//1:1문의
		alarmPageBtn.addActionListener(this);//공지사항
		
		
		add(mainlabel);
		add(titlelabel);
		add(freePageBtn);
		add(oneToOnePageBtn);
		add(alarmPageBtn);
		add(prevBtn);
		add(nextBtn);
		///////////////////////////
		setLayout(null);
		setTitle("메인화면");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//main메소드 끝

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
			
		if (obj==addBtn) {
			
		}else if (obj==freePageBtn) { //자유게시판
			new FreeBoard();
		}else if (obj==oneToOnePageBtn) { //1:1문의
			new OneToOneBoard();
		}else if (obj==alarmPageBtn) {
			new alarm();
		}
		
		
	}
	
	
	
	
	
	
	
	
}
