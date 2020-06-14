package minigame.login;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import minigame.DB.DAOLogin;
import minigame.DB.DTOLogin;
import minigame.client.clientMain;
import minigame.client.mini_WaitRoomUI;


public class login extends JFrame implements ActionListener {

	// 메인
	
	///////////////////////////
	JLabel t1, t2, id, pass;
	JTextField idTxt, passTxt;
	JButton login, register;
	JButton ipBtn;
	clientMain client;

	private Image img = null;

	public login(clientMain client) {
		this.client = client;
		ServerAddress sd = new ServerAddress(this);
		init();
	}

	void init() {

		// 이미지 재설정
		String imgPath = "src/login_frame/image.png";
		ImageIcon originIcon = new ImageIcon(imgPath);
		Image originImg = originIcon.getImage();
		Image changeImg = originImg.getScaledInstance(320, 250, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(changeImg);

		// 디자인
		JPanel p = new JPanel();

		p.setLayout(null);

		JLabel label = new JLabel(Icon);

		add(label);

		t1 = new JLabel("~미니게임 천국~");
		add(t1);
		t2 = new JLabel("로그인 해주세요!!");
		add(t2);
		id = new JLabel("아이디: ");
		add(id);
		pass = new JLabel("비밀번호: ");
		add(pass);
		idTxt = new JTextField();
		add(idTxt);
		passTxt = new JTextField();
		add(passTxt);

		// 로그인 버튼
		login = new JButton("로그인");
		add(login);
		// 회원가입
		register = new JButton("회원가입");
		add(register);

		label.setBounds(0, 5, 350, 255);
		t1.setBounds(350, 5, 70, 40);
		t2.setBounds(350, 35, 280, 40);
		id.setBounds(40, 265, 60, 40);
		pass.setBounds(40, 305, 60, 40);
		idTxt.setBounds(150, 265, 200, 30);
		passTxt.setBounds(150, 305, 200, 30);
		login.setBounds(380, 265, 90, 30);
		register.setBounds(380, 305, 90, 30);

		ipBtn = new JButton("");
		ipBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerAddress sd = new ServerAddress(login.this);
				setVisible(false); // true이면 창이 그냥 닫힌다.
			}
		});

		ipBtn.setBounds(380, 230, 90, 23);
		p.add(ipBtn);

		add(p);

		login.addActionListener(this);
		register.addActionListener(this);

		/////////////////////
		setSize(500, 400);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == login) {
			lome();
		} else if (obj == register) {
			new Register();
		}

	}// end

	public void lome() {
		
		
		
//		DTOLogin dto = new DTOLogin( );
//		dto.setId(idTxt.getText());
//		dto.setPassword(passTxt.getText());
//		
//		DAOLogin dao = new DAOLogin();
//		//System.out.println(client.user);
//		boolean a = dao.loginPass(dto,client);
		
		client.serverAccess();//서버연결
		
	if(true)  {
		JOptionPane.showMessageDialog(this, "로그인 되었습니다.");
		client.setWaitRoom(new mini_WaitRoomUI(client)); 
//		DTOLogin dto2 = new DTOLogin();
//		dto2.setId(idTxt.getText());
//		dto2.setPassword(passTxt.getText());
//		DAOLogin dao2 = DAOLogin.getInstance();
		//dao2.nickMethod(dto2);
		
		setVisible(false);
		 }else {
			 JOptionPane.showMessageDialog(this, "로그인 실패했습니다.");
		}
			
		//JOptionPane.showMessageDialog(this, "잘못입력하셨습니다. 다시 입력해주세요.");

	}

	public JTextField getIdTxt() {
		return idTxt;
	}

	public void setIdTxt(JTextField idTxt) {
		this.idTxt = idTxt;
	}

	public JButton getIpBtn() {
		return ipBtn;
	}

	public void setIpBtn(JButton ipBtn) {
		this.ipBtn = ipBtn;
	}

}
