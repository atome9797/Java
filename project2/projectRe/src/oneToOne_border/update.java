package oneToOne_border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.OneToDAO;
import DTO.OneToDTO;
import DTO.numDTO;
import Login.userinfo;

public class update extends JFrame implements ActionListener{

	
	public OneToOneBoard OneToBoardPage;
	public JTextField subjectTxt;
	public JTextArea contentTxt;
	public JButton inputBtn;
	int lastRow;
	
	
	public update() {

		subjectTxt=new JTextField();
		contentTxt=new JTextArea();
		inputBtn=new JButton("수정하기");
		
		
		subjectTxt.setBounds(20, 20, 450, 50);
		contentTxt.setBounds(20, 80, 450, 330);
		inputBtn.setBounds(20, 420, 450, 30);
		
		add(subjectTxt);
		add(contentTxt);
		add(inputBtn);
		
		inputBtn.addActionListener(this);//등록하기 버튼
		
		
		setLayout(null);
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}

	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj==inputBtn) {
			updatePoster();
			
		}
		
	}
	
	
	public void updatePoster() {
		userinfo userInfo = userinfo.getInstance();
		//String nickName = "asd"; 
		String subject = subjectTxt.getText(); //등록페이지 제목 텍스트
		String content = contentTxt.getText(); //등록페이지 내용 텍스트

		OneToDTO dto = new OneToDTO();
		dto.setUserSubject(subject);
		dto.setUserContent(content);

		if (content.equals("") || subject.equals("")) {
			JOptionPane.showMessageDialog(this, "입력해주세요.");
			
		}else {
			
			numDTO num=numDTO.getInstance();
			
			// DB에 Insert
			OneToDAO dao = OneToDAO.getInstance();
			
			int cnt = dao.update(dto,num.getNum());
			
			System.out.println(num.getNum());
			System.out.println(cnt);
			
			
			
			// 등록 실패
			if (cnt == -1) {
				JOptionPane.showMessageDialog(OneToBoardPage,"등록 실패했습니다."); //메인페이지에서 오류를 띄울것이므로 사용
				return;
			}if(cnt>0) {
				// 등록 성공
				JOptionPane.showMessageDialog(OneToBoardPage,"등록 성공했습니다.");
				
			}
		}//
		
	}
	
	
}
