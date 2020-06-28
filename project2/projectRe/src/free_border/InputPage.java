package free_border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.PostDAO;
import DTO.FreeBoardDTO;
import Login.userinfo;

public class InputPage extends JFrame implements ActionListener{
	
	private FreeBoard freeBoardPage;
	public JTextField subjectTxt;
	public JTextArea contentTxt;
	public JButton inputBtn;
	int lastRow;
	
	public InputPage() {
		
		subjectTxt=new JTextField();
		contentTxt=new JTextArea();
		inputBtn=new JButton("등록하기");
		
		
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
			addPoster();
			
		}
		
	}
	
	
	private void addPoster() {
		userinfo userInfo = userinfo.getInstance();
		//String nickName = "asd"; 
		String subject = subjectTxt.getText(); //등록페이지 제목 텍스트
		String content = contentTxt.getText(); //등록페이지 내용 텍스트

		FreeBoardDTO dto = new FreeBoardDTO();
		dto.setNinkName(userInfo.getNickname());
		dto.setFreeSubject(subject);
		dto.setFreeContent(content);

		
		// DB에 Insert
		PostDAO dao = PostDAO.getInstance();
		int cnt = dao.insertFreeBoardPost(dto);

		System.out.println(cnt);
		// 등록 실패
		if (cnt == -1) {
			JOptionPane.showMessageDialog(freeBoardPage,"등록 실패했습니다.");
			return;
		}if(cnt>0) {
		// 등록 성공
		JOptionPane.showMessageDialog(freeBoardPage,"등록 성공했습니다.");
		
	
		
		}
	}//end
	
	
	
//	public void addAllPoster() {
//		// DB에서 가져옴
//		PostDAO dao = PostDAO.getInstance();
//		List<FreeBoardDTO> aList = dao.selectFreeBoardPosts();
//		for (int i = 0; i < aList.size(); i++) {//테이블 작성 
//			freeBoardPage.freeBoardModel.setValueAt(aList.get(i).getNinkName(), i, 0);
//			freeBoardPage.freeBoardModel.setValueAt(aList.get(i).getFreeSubject(), i, 1);
//			freeBoardPage.freeBoardModel.setValueAt(aList.get(i).getFreeContent(), i, 2);
//			lastRow = i;
//		}
//	}
	
	
	
	
}
