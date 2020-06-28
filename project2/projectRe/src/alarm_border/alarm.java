package alarm_border;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.PostDAO;
import DTO.FreeBoardDTO;
import DTO.alarmBoardDTO;
import free_border.FreeBoard;

import oneToOne_border.OneToOneBoard;

public class alarm extends JFrame implements ActionListener {
	
	
	public JButton addBtn, delBtn, modBtn, refBtn, nextBtn, prevBtn;
	public JButton freePageBtn, oneToOnePageBtn, alarmPageBtn;
	public JTextField searchTxt;
	public DefaultTableModel alarmBoardModel;
	public JTable alarmBoardTable;
	public String[] alarmBoardCol = {"닉네임", "제목", "내용"};
	public JScrollPane alarmBoardScroll;
	public JLabel titlelabel;
	public JButton addBtn2;
	int lastRow;
	public JButton subjectBtn;
	
	public static void main(String[] args) {
		new alarm();
	}
	
	public alarm() {
		
		free();
	}
	
	public void free() {
		
		prevBtn = new JButton("이전페이지");
		nextBtn = new JButton("다음페이지");
		searchTxt = new JTextField();
		addBtn = new JButton("등록");
		addBtn2=new JButton("새로고침");
		
		freePageBtn = new JButton("자유게시판");
		oneToOnePageBtn = new JButton("1:1문의");
		alarmPageBtn = new JButton("공지사항");
		
		
		alarmBoardModel = new DefaultTableModel(alarmBoardCol, 100);
		alarmBoardTable = new JTable(alarmBoardModel);
		alarmBoardScroll = new JScrollPane(alarmBoardTable);
		
		
		alarmBoardTable.setRowHeight(20);
		
		//테이블의 너비 지정
		alarmBoardTable.getColumnModel().getColumn(0).setPreferredWidth(25); 
		alarmBoardTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		alarmBoardTable.getColumnModel().getColumn(2).setPreferredWidth(400);
		
		//칼럼명 이동불가
		alarmBoardTable.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 불가능.
		alarmBoardTable.setRowSelectionAllowed(false); //기본 true
		alarmBoardTable.setColumnSelectionAllowed(false); //기본 false
		
		
		//페이지 제목 달아줌
		titlelabel=new JLabel("공지사항");
		titlelabel.setBounds(347,30,300,50);
		Font font=new Font("돋움", Font.PLAIN, 30);
		titlelabel.setFont(font);
		
		subjectBtn=new JButton("제목 찾기");
		subjectBtn.setBounds(560, 80, 120, 30);
		
		
		alarmBoardTable.setRowHeight(30);
		
		prevBtn.setBounds(20, 20, 100, 30);
		nextBtn.setBounds(140, 20, 100, 30);
		searchTxt.setBounds(250, 80, 300, 30);
		addBtn.setBounds(250, 180, 60, 30);
		alarmBoardScroll.setBounds(40, 220, 700, 300);
		addBtn2.setBounds(330, 180, 120, 30);
		
		freePageBtn.setBounds(245, 130, 100, 40);
		oneToOnePageBtn.setBounds(355, 130, 100, 40);
		alarmPageBtn.setBounds(465, 130, 100, 40);
		
		
		addBtn.addActionListener(this);
		oneToOnePageBtn.addActionListener(this);
		freePageBtn.addActionListener(this);
		alarmPageBtn.addActionListener(this);
		addBtn2.addActionListener(this);
		searchTxt.addActionListener(this);
		subjectBtn.addActionListener(this);
		
		add(addBtn2);
		add(prevBtn);
		add(nextBtn);
		add(searchTxt);
		add(addBtn);
		add(alarmBoardScroll);
		add(freePageBtn);
		add(oneToOnePageBtn);
		add(alarmPageBtn);
		add(titlelabel);
		add(subjectBtn);
		
		/////////////////////////////
		setLayout(null);
		setTitle("공지사항");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == freePageBtn) {
			new FreeBoard();
		} else if (obj == oneToOnePageBtn) {
			new OneToOneBoard();
		} else if (obj == alarmPageBtn) {//등록페이지 등록하기 버튼
			
		}else if (obj== addBtn) {
			new InputPage3();
		} else if (obj==addBtn2) {
			AllPoster();
		} else if(obj==searchTxt) {
			searchPost();
		} else if (obj==subjectBtn) {
			searchPost();
		}
		
	}
	
	
	//새로고침 버튼
	public void AllPoster() {
		// DB에서 가져옴
				PostDAO dao = PostDAO.getInstance();
				List<alarmBoardDTO> aList = dao.selectAlarm();
				for (int i = 0; i < aList.size(); i++) {//테이블 작성
					
					alarmBoardModel.setValueAt(aList.get(i).getNickName(), i, 0);
					alarmBoardModel.setValueAt(aList.get(i).getAlarm_subject(), i, 1);
					alarmBoardModel.setValueAt(aList.get(i).getAlarm_content(), i, 2);
					
					lastRow=i;
				}
	}
	
	
	//제목 찾기
	public void searchPost(){
		
		
		AllPoster();//DB데이터 가져옴
		
		if(searchTxt.getText().equals("")) {
			return;
		}
		
		
		for(int i = 0 ; i <= lastRow; i++) { //열 행을 나타냄
			//해당 열을 제외하고 다사라지게함
			if(!searchTxt.getText().trim().equals(alarmBoardModel.getValueAt(i, 1).toString())) {
				alarmBoardModel.removeRow(i);
				i--;
				lastRow--;
				
			}
			if(alarmBoardModel.getRowCount() < 100)
				alarmBoardModel.setRowCount(100);
		}
	}
	
}
