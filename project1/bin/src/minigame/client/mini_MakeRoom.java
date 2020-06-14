package minigame.client;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import minigame.game.BingoFrame;
import minigame.game.CoinEat;
import minigame.game.gameMain;

public class mini_MakeRoom extends JFrame implements ActionListener {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private JButton btn_game1;
	private JButton btn_game2;
	private JButton btn_game3;
	JButton btnNewButton;

	clientMain client;
	public minigame_Room room;
	int gamestate = 0;

	private JLabel label1;
	private JTextArea textArea;

	public final int game11 = 1; // 로그인
	public final int game22 = 2; // 로그아웃
	public final int game33 = 3; // 회원가입

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public mini_MakeRoom(clientMain client, minigame_Room room) {
		this.client = client;
		this.room = room;
		initialize();
		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setLocationRelativeTo(null);// 중앙에서 시작되라
		frame.setResizable(true);// 사이즈 변경x
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		btn_game3 = new JButton("coin");
		btn_game3.addActionListener(this);
		btn_game3.setBounds(63, 175, 97, 23);
		frame.getContentPane().add(btn_game3);

		btn_game2 = new JButton("card");
		btn_game2.addActionListener(this);
		btn_game2.setBounds(63, 124, 97, 23);
		frame.getContentPane().add(btn_game2);

		btn_game1 = new JButton("bingo");
		btn_game1.addActionListener(this);
		btn_game1.setBounds(63, 73, 97, 23);
		frame.getContentPane().add(btn_game1);

		JLabel lblNewLabel = new JLabel("Games");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setBounds(81, 20, 57, 15);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(23, 35, 182, 255);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Game_Img");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_1.setBounds(321, 20, 88, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Game_Info");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_2.setBounds(321, 145, 88, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("방 제목");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_3.setBounds(23, 325, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("게임");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_4.setBounds(23, 358, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("비밀번호");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_5.setBounds(23, 391, 57, 15);
		frame.getContentPane().add(lblNewLabel_5);

		textField_3 = new JTextField();
		textField_3.setBounds(81, 322, 249, 21);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(81, 355, 223, 21);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(81, 388, 116, 21);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		btnNewButton = new JButton("게임만들기");
		btnNewButton.setBounds(223, 408, 107, 33);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);

		JButton button = new JButton("나가기");
		button.setBounds(365, 408, 107, 33);
		frame.getContentPane().add(button);

		label1 = new JLabel();
		label1.setBounds(293, 35, 200, 110);
		frame.getContentPane().add(label1);

		textArea = new JTextArea();
		textArea.setFont(new Font("HY중고딕", Font.BOLD, 15));
		textArea.setLineWrap(true);
		textArea.setBounds(293, 175, 160, 139);
		frame.getContentPane().add(textArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == btn_game1) {
			game1();
		} else if (obj == btn_game2) {
			game2();
		} else if (obj == btn_game3) {
			game3();
		} else if (obj == btnNewButton) {

			gamestart();

		}

	}

	void game1() {
		textArea.setText("숫자나 특정 주제의 단어 등을 아무렇게나 써넣은 후 가로, 세로 대각선으로 한 줄을 만들면 이기는 게임");
//setText 넘겨주는 매소드
		textField_4.setText("bingo");

		String imgPath = "src\\test\\bingo.png";
		ImageIcon originIcon = new ImageIcon(imgPath);
		Image originImg = originIcon.getImage();
		Image changeImg = originImg.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(changeImg);

		label1.setIcon(Icon);
		gamestate = game11;

	}

	void game2() {
		textArea.setText("제한시간 동안 똑같은 카드를 맞추는게임");

		textField_4.setText("coin");
		String imgPath = "src\\test\\coin.png";
		ImageIcon originIcon = new ImageIcon(imgPath);
		Image originImg = originIcon.getImage();
		Image changeImg = originImg.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
		ImageIcon Icon1 = new ImageIcon(changeImg);

		label1.setIcon(Icon1);
		gamestate = game22;

	}

	void game3() {
		textArea.setText("제한시간 동안 많은 동전을 먹는게임");
		textField_4.setText("card");
		String imgPath = "src\\test\\card.png";
		ImageIcon originIcon = new ImageIcon(imgPath);
		Image originImg = originIcon.getImage();
		Image changeImg = originImg.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
		ImageIcon Icon2 = new ImageIcon(changeImg);

		label1.setIcon(Icon2);
		gamestate = game33;

	}

	public void newRoom(String name) {

		if (room == null) {
			room = new minigame_Room(name);
			room.setRoomNum(client.waitRoom.getLastRoomNum());
			client.getUser().getRoomArray().add(room);
		
		}

	}

	public void gamestart() {
		switch (gamestate) {
		case game11:
			newRoom("game1");
			room.setBGame(new BingoFrame(client, room));
			frame.dispose();

			break;
		case game22:
			newRoom("game2");
			room.setBGame(new gameMain(client, room));
			frame.dispose();

			break;
		case game33:
			newRoom("game3");
			room.setBGame(new CoinEat(client, room));
			frame.dispose();
			break;

		default:
			break;
		}
		
		System.out.println("여기가 일번");
		try {
			client.getOs().writeUTF(mini_User.CREATE_ROOM + "/" + room.toProtocol()+ "/"+ gamestate);
			System.out.println("여기가 이번");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("나나");
		}
		
	}
}
