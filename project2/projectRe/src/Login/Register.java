package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.DAOLogin;
import DTO.DTOLogin;


public class Register extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new Register();
	}

	JPanel p2;
	JPanel p;
	JLabel nameL;
	JLabel idL;
	JLabel passL;
	JLabel addressL;
	JLabel numberL;
	JLabel nicknameL;

	// 로그인 버튼// 취소버튼
	JButton register, cancel;

	JRadioButton manRad, womanRad;

	JTextField nameTxt;
	JTextField idTxt;
	JTextField passTxt;
	JTextField addressTxt;
	JTextField numberTxt;
	JTextField nicknameTxt;
	
	public Register() {
		p = new JPanel();
		nameL = new JLabel("이름: ");
		idL = new JLabel("아이디: ");
		passL = new JLabel("패스워드: ");
		addressL = new JLabel("주소: ");
		numberL = new JLabel("전화번호: ");
		nicknameL=new JLabel("닉네임: ");
		
		manRad = new JRadioButton("남", true);
		womanRad = new JRadioButton("여");

		ButtonGroup bg = new ButtonGroup();
		bg.add(manRad);
		bg.add(womanRad);

		p2 = new JPanel();
		p2.add(new JLabel("성별"));
		p2.add(manRad);
		p2.add(womanRad);

		add(nicknameL);
		add(nameL);
		add(idL);
		add(passL);
		add(addressL);
		add(numberL);

		nameTxt = new JTextField();
		idTxt = new JTextField();
		passTxt = new JTextField();
		addressTxt = new JTextField();
		numberTxt = new JTextField();
		nicknameTxt=new JTextField();
		
		add(nameTxt);
		add(idTxt);
		add(passTxt);
		add(addressTxt);
		add(numberTxt);
		add(nicknameTxt);
		
		register = new JButton("등록");
		cancel = new JButton("재입력");

		add(register);
		add(cancel);
		add(p2);

		JLabel title = new JLabel("<   회원가입    >");
		add(title);
		
		title.setBounds(40, 10, 100, 40);
		nameL.setBounds(40, 60, 40, 40);
		nicknameL.setBounds(40, 100, 60, 40);
		idL.setBounds(40, 140, 70, 40);
		passL.setBounds(40, 180, 60, 40);
		addressL.setBounds(40, 220, 60, 40);
		numberL.setBounds(40, 260, 60, 40);
		
		nameTxt.setBounds(120, 60, 200, 30);
		nicknameTxt.setBounds(120, 100, 200, 30);
		idTxt.setBounds(120, 140, 200, 30);
		passTxt.setBounds(120, 180, 200, 30);
		addressTxt.setBounds(120, 220, 200, 30);
		numberTxt.setBounds(120, 260, 200, 30);
		
		register.setBounds(120, 370, 80, 30);
		cancel.setBounds(240, 370, 80, 30);
		p2.setBounds(70, 300, 300, 100);

		add(p);

		register.addActionListener(this);
		cancel.addActionListener(this);

		///////////////////
		setSize(410, 500);
		setTitle("회원가입");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}// end 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();

		if (obj == register) {
			RegMethod();
		} else if (obj == cancel) {
			cancelMethod();
		}

	}// end 액션

	public void cancelMethod() {
		Reset();
	}

	public void Reset() {
		nameTxt.setText("");// 초기화
		idTxt.setText("");
		passTxt.setText("");
		addressTxt.setText("");
		numberTxt.setText("");
		nicknameTxt.setText("");

		manRad.setSelected(true); // '남'으로 초기화
		nameTxt.requestFocus(); // 커서위치 초기화
	}

	private void RegMethod() {

		
		

		

		DTOLogin dto = new DTOLogin();

		dto.setName(nameTxt.getText().trim());
		dto.setId(idTxt.getText().trim());
		dto.setPassword(passTxt.getText().trim());
		dto.setAddress(addressTxt.getText().trim());
		dto.setSextual((manRad.isSelected() ? '남' : '여'));
		dto.setNumber1(numberTxt.getText().trim());
		dto.setNickname(nicknameTxt.getText().trim());
		
		DAOLogin dao=DAOLogin.getInstance();
		
		if(dao.RegisterMethod(dto,idTxt.getText().trim()) == true) {
		JOptionPane.showMessageDialog(this, "등록되었습니다!");
		}
		else {
			JOptionPane.showMessageDialog(this, "이미 존재하는 아이디 입니다.");
		}
		
		Reset();
	}

}
