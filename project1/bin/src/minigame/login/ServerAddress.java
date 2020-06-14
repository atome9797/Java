package minigame.login;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ServerAddress extends JFrame {

	JButton confirmBtn;
	JTextField ipText;
	login login;
	ImageIcon icon;


	public ServerAddress(login login) {
		this.login = login;
		//this.login = new login();
		initialize();
	}

	private void initialize() {

		icon = new ImageIcon("icon2.png");
		this.setIconImage(icon.getImage());// 타이틀바에 이미지넣기

		setTitle("서버 아이피 주소 입력");
		setBounds(100, 100, 306, 95);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 266, 37);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0)); // 텍스트 크기

		ipText = new JTextField();
		ipText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login.getIpBtn().setText(ipText.getText());
					login.setVisible(true);
					dispose();
					login.getIdTxt().requestFocus();
				}
			}
		});
		ipText.setText("172.16.5.11");
		panel.add(ipText, BorderLayout.CENTER);
		ipText.setColumns(10);

		confirmBtn = new JButton("확인");
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.getIpBtn().setText(ipText.getText());
				login.setVisible(true);
				dispose();
				login.getIdTxt().requestFocus();
			}
		});
		panel.add(confirmBtn, BorderLayout.EAST);
		setVisible(true);
	}

}