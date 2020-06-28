package oneToOne_border;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.OneToDAO;
import DAO.PostDAO;
import DTO.OneToDTO;
import DTO.numDTO;
import Login.userinfo;
import alarm_border.alarm;
import free_border.FreeBoard;




public class OneToOneBoard extends JFrame implements ActionListener,MouseListener{

	
	public JButton addBtn, delBtn, modBtn, refBtn, nextBtn, prevBtn;
	public JButton freePageBtn, oneToOnePageBtn, alarmPageBtn;
	public JTextField searchTxt,numTxt;
	public DefaultTableModel OneToBoardModel;
	public JTable OneToBoardTable;
	public String[] OneToBoardCol = {"번호","닉네임", "제목", "내용","제목","관리자 메세지 내용"};
	public JScrollPane OneToBoardScroll;
	public JLabel titlelabel;
	public JButton addBtn2;
	int lastRow;
	public JButton subjectBtn;
	public JButton deleteBtn;
	public JButton updateBtn;
	
	int crow=-1;
	
	public OneToOneBoard() {
		one();
	}
	
	public void one() {
		
		
		prevBtn = new JButton("이전페이지");
		nextBtn = new JButton("다음페이지");
		numTxt =new JTextField();
		searchTxt = new JTextField();
		addBtn = new JButton("등록");
		addBtn2=new JButton("새로고침");
		deleteBtn=new JButton("삭제");
		updateBtn=new JButton("수정");
		
		freePageBtn = new JButton("자유게시판");
		oneToOnePageBtn = new JButton("1:1문의");
		alarmPageBtn = new JButton("공지사항");
		
		
		OneToBoardModel = new DefaultTableModel(OneToBoardCol, 100) { //테이블을 위한 모델  5열 50행 생성
			public boolean isCellEditable(int row, int col) { //cell건들지 못하게 함
				return false;
			}
		};
		OneToBoardTable = new JTable(OneToBoardModel);
		OneToBoardScroll = new JScrollPane(OneToBoardTable);
		
		
		OneToBoardTable.setRowHeight(20);
		
		//테이블의 너비 지정
		OneToBoardTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		OneToBoardTable.getColumnModel().getColumn(1).setPreferredWidth(12); 
		OneToBoardTable.getColumnModel().getColumn(2).setPreferredWidth(12);
		OneToBoardTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		OneToBoardTable.getColumnModel().getColumn(4).setPreferredWidth(12);
		OneToBoardTable.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		//칼럼명 이동불가
		OneToBoardTable.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 불가능.
		OneToBoardTable.setRowSelectionAllowed(false); //기본 true
		OneToBoardTable.setColumnSelectionAllowed(false); //기본 false
		
		OneToBoardTable.addMouseListener(this);
		
		//페이지 제목 달아줌
		titlelabel=new JLabel("1:1게시판");
		titlelabel.setBounds(347,30,300,50);
		Font font=new Font("돋움", Font.PLAIN, 30);
		titlelabel.setFont(font);
		
		subjectBtn=new JButton("제목 찾기");
		subjectBtn.setBounds(560, 80, 120, 30);
		
		
		OneToBoardTable.setRowHeight(30);
		
		prevBtn.setBounds(20, 20, 100, 30);
		nextBtn.setBounds(140, 20, 100, 30);
		numTxt.setBounds(220,80,20,30);
		searchTxt.setBounds(250, 80, 300, 30);
		addBtn.setBounds(250, 180, 60, 30);
		OneToBoardScroll.setBounds(40, 220, 700, 300);
		addBtn2.setBounds(330, 180, 120, 30);
		updateBtn.setBounds(460,180,70,30);
		deleteBtn.setBounds(530,180,70,30);
		
		freePageBtn.setBounds(245, 130, 100, 40);
		oneToOnePageBtn.setBounds(355, 130, 100, 40);
		alarmPageBtn.setBounds(465, 130, 100, 40);
		
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		addBtn.addActionListener(this);
		oneToOnePageBtn.addActionListener(this);
		freePageBtn.addActionListener(this);
		alarmPageBtn.addActionListener(this);
		addBtn2.addActionListener(this);
		searchTxt.addActionListener(this);
		subjectBtn.addActionListener(this);
		numTxt.addActionListener(this);
		
		add(addBtn2);
		add(prevBtn);
		add(nextBtn);
		add(numTxt);
		add(searchTxt);
		add(addBtn);
		add(OneToBoardScroll);
		add(freePageBtn);
		add(oneToOnePageBtn);
		add(alarmPageBtn);
		add(titlelabel);
		add(subjectBtn);
		add(updateBtn);
		add(deleteBtn);
		
		/////////////////////////////
		setLayout(null);
		setTitle("1:1게시판");
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
			
		} else if (obj == alarmPageBtn) {//등록페이지 등록하기 버튼
			new alarm();
		}else if (obj== addBtn) {
			userinfo info=userinfo.getInstance();
			
			if (info.getNickname().equals("p")) {
				
				numDTO dto=numDTO.getInstance();
				dto.setNum(numTxt.getText().trim());
				
				System.out.println(numTxt.getText().trim());
				new adminInput();
			}else {
				new InputPage();
			}
		} else if (obj==addBtn2) {
			userinfo info=userinfo.getInstance();
			
			if (info.getNickname().equals("p")) {
				AllPoster();
			}else {
				userAllposter();
			}
			
		} else if(obj==searchTxt) {
			searchPost();
		} else if (obj==subjectBtn) {
			searchPost();
		} else if(obj==numTxt){
			searchPost();
		} else if (obj==updateBtn) {
			userinfo info=userinfo.getInstance();
			if (!info.getNickname().equals("p")) {
				
				numDTO dto=numDTO.getInstance();
				dto.setNum(numTxt.getText().trim());
				new update();
			}else {
				numDTO dto=numDTO.getInstance();
				dto.setNum(numTxt.getText().trim());
				new adminupdate();
				
			}
			
		} else if (obj==deleteBtn) {
			userinfo info=userinfo.getInstance();
			if (!info.getNickname().equals("p")) {
				if (numTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(this,"삭제할 메세지를 선택해주세요.");
				}else {
					int chk=JOptionPane.showConfirmDialog(this,"정말로 삭제하시겠습니까?", "삭제" , JOptionPane.YES_NO_CANCEL_OPTION);
					
					if (chk==JOptionPane.YES_OPTION) {
						numDTO dto=numDTO.getInstance();
						dto.setNum(numTxt.getText().trim());
						
						OneToDAO dao=OneToDAO.getInstance();
						int cnt=dao.deleteOneto();
						
						if (cnt>0) {
							JOptionPane.showMessageDialog(this,"삭제 되었습니다.");
						}else {
							JOptionPane.showMessageDialog(this,"삭제불가");
						}
					}
				}
				
			}else {
				JOptionPane.showMessageDialog(this,"관리자는 메세지 삭제가 불가능합니다.");
			}
			
		}//end else if 
		
	}//end action
	
	
	
	
	//새로고침 버튼
	public void AllPoster() {
		// DB에서 가져옴
		userinfo userInfo = userinfo.getInstance();
			
				OneToDAO dao = OneToDAO.getInstance();
				List<OneToDTO> aList = dao.allBoardPosts();
				for (int i = 0; i < aList.size(); i++) {//테이블 작성
					
					OneToBoardModel.setValueAt(aList.get(i).getNum(), i, 0);
					OneToBoardModel.setValueAt(aList.get(i).getNickName(), i, 1);
					OneToBoardModel.setValueAt(aList.get(i).getUserSubject(), i, 2);
					OneToBoardModel.setValueAt(aList.get(i).getUserContent(), i, 3);
					OneToBoardModel.setValueAt(aList.get(i).getAdminSubject(), i, 4);
					OneToBoardModel.setValueAt(aList.get(i).getAdminContent(), i, 5);
					
					lastRow=i;
				}
	}
	
	
	public void userAllposter() {
		userinfo userInfo = userinfo.getInstance();
		
		OneToDAO dao = OneToDAO.getInstance();
		List<OneToDTO> aList = dao.selectOneTOBoardPosts(userInfo.getNickname());
		for (int i = 0; i < aList.size(); i++) {//테이블 작성
			
			OneToBoardModel.setValueAt(aList.get(i).getNum(), i, 0);
			OneToBoardModel.setValueAt(aList.get(i).getNickName(), i, 1);
			OneToBoardModel.setValueAt(aList.get(i).getUserSubject(), i, 2);
			OneToBoardModel.setValueAt(aList.get(i).getUserContent(), i, 3);
			OneToBoardModel.setValueAt(aList.get(i).getAdminSubject(), i, 4);
			OneToBoardModel.setValueAt(aList.get(i).getAdminContent(), i, 5);
			
			lastRow=i;
		}
	}
	
	
	
	//제목 찾기
	public void searchPost(){
		
		
		AllPoster();//DB데이터 가져옴
		
		if(searchTxt.getText().equals("") && numTxt.getText().equals("")) {
			return;
		}
		
		
		for(int i = 0 ; i <= lastRow; i++) { //열 행을 나타냄
			//해당 열을 제외하고 다사라지게함
			if(!numTxt.getText().trim().equals(OneToBoardModel.getValueAt(i, 0).toString())) {
				OneToBoardModel.removeRow(i);
				i--;
				lastRow--;
				
			}
			
			
			if(OneToBoardModel.getRowCount() < 100) {
				OneToBoardModel.setRowCount(100);
				}
			
			
		}
		
	}

	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj=e.getSource();
		if (obj==OneToBoardTable && e.getClickCount()==2) {
			int row=OneToBoardTable.getSelectedRow();// 행의 인덱스 번호를 가져옴
			
			//  행은 0부터 시작이기때문에 
			//  행이 없을경우(-1반환됨)       등록되지 않은 행을 선택했을경우
			if (row < 0 || OneToBoardTable.getValueAt(row, 2)==null) { //인덱스는 0 123...진행되므로 , 그리고  
				JOptionPane.showMessageDialog(this,"없는 제목입니다.");
				return;
			} 
			
			crow=row; //crow에 넣어준후  다른 메소드에서 사용하기위해 설정해줌
			
			searchTxt.setText(OneToBoardTable.getValueAt(row, 2).toString()); 
			numTxt.setText(OneToBoardTable.getValueAt(row, 0).toString());
		}
	}
	
	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
