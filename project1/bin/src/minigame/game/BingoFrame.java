package minigame.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import minigame.DB.BingoDAO;
import minigame.DB.BingoDTO;
import minigame.client.clientMain;
import minigame.client.mini_User;
import minigame.client.minigame_Room;

public class BingoFrame extends PrGame implements ActionListener {

	static int i = 0, j = 0;
	static long timer2, timer3 = 0;
	static boolean ch = true, ch2 = true;
	static JLabel jLabel, LabelM;
	// clientBingo client;

	final int size = 4;
	// public JTextArea textArea;
	private JPanel panel;
	private JFrame frame;
	public JTextField textField, timerField;
	int bingoCnt = 0;
	JButton[] btnArr = null; // 버튼 16개를 열로 받음
	boolean[][] bArr = new boolean[size][size];// 4행4열을 참거짓으로 받음
	String[] values = { "사과", "배", "참외", "포도", "망고", "구하바", "무화과", "딸기", "복숭아", "수박", "토마토", "레몬", "두리안", "메론", "바나나",
			"오렌지" };

	public BingoFrame(clientMain client, minigame_Room room) {
		init();
		this.client = client;
		this.room = room;
		time();
		bingo();

		// last
		timer();

	}

	public void init() {
		timerField = new JTextField();

		frame = new JFrame();
		getContentPane().setBackground(new Color(245, 222, 179));
		setBounds(100, 100, 800, 800);

		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(473, 380, 207, 37);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				msgSummit();

			}
		});

		setTextArea(new JTextArea());
		JScrollPane scrollPane = new JScrollPane(getTextArea());
		scrollPane.setBounds(473, 160, 207, 221);

		getContentPane().add(scrollPane);

		panel = new JPanel();
		panel.setBounds(41, 68, 353, 349);
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(41, 454, 112, 37);
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(165, 454, 60, 37);
		getContentPane().add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(41, 517, 185, 182);
		getContentPane().add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(264, 454, 112, 37);
		getContentPane().add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(384, 454, 60, 37);
		getContentPane().add(panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(264, 517, 185, 182);
		getContentPane().add(panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(485, 454, 112, 37);
		getContentPane().add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBounds(620, 454, 60, 37);
		getContentPane().add(panel_8);

		JPanel panel_9 = new JPanel();
		panel_9.setBounds(485, 517, 192, 182);
		getContentPane().add(panel_9);
		// setVisible(true);
	}

	public void time() {
		timer2 = System.currentTimeMillis();

		jLabel = new JLabel();
		jLabel.setBounds(142, 5, 199, 30);
		LabelM = new JLabel();
		LabelM.setBounds(12, 5, 151, 30);
		LabelM.setPreferredSize(new Dimension(100, 30));
		LabelM.setFont(new Font("", Font.PLAIN, 27));
		jLabel.setPreferredSize(new Dimension(100, 30));
		jLabel.setFont(new Font("", Font.PLAIN, 27));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(245, 222, 179));
		panel_10.setBounds(41, 10, 353, 37);
		panel_10.setLayout(null);

		panel_10.add(LabelM);

		panel_10.add(jLabel);

		getContentPane().add(panel_10);

		JPanel panel_11 = new JPanel();
		panel_11.setBounds(473, 25, 207, 125);
		getContentPane().add(panel_11);
		panel_11.setLayout(null);

//		

//		BingoDAO dao = BingoDAO.getInstance();
//		BingoDTO dto = dao.bingoGetMethod();

		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closed();

			}
		});
	}// end time

	public void bingo() {
		btnArr = new JButton[16];// 16개 버튼 생성

		

		int[] chk = new int[size * size];
		for (int i = 0; i < values.length; i++) {// 16개

			Random random = new Random();
			int num = random.nextInt(16);// 1~16까지의 난수 나옴

			if (chk[num] == 1) { // 비교
				i--; // 한칸뒤로 가서 다시 난수 조건비교됨 ->num마다 1조건을 부여하기위함
				continue; // i++로 가서 다시 실행됨
			}
			chk[num] = 1; // 모든 배열을 1로받음
			btnArr[i] = new JButton(values[num]); // 버튼에 넣을 문자열을 난수로 됨 ->버튼을 배열로 받아 난수로 이동되게함
			panel.add(btnArr[i]); // 버튼을 프레임에 넣어줌
			btnArr[i].addActionListener(this); // 버튼에 액션달아줌

		}

		panel.setLayout(new GridLayout(4, 4));
	}// end bingo

	@Override
	public void serverBtn(String bName) {
		for (int i = 0; i < btnArr.length; i++) {
			//System.out.println(btnArr[i].getText());
	
			if (btnArr[i].getText().equals(bName)) {
				bArr[i / size][i % size] = true; // 0123 4567 891011되면 다음 행으로 넘어가게 설정됨(해당 버튼 누르면 true값 넣어줌)
				btnArr[i].setBackground(Color.cyan);
				break; // 버튼 1개만 눌렀을때 작동하도록 설정해줌
			}
		}
		System.out.println(bName+"-----------------------------");
		checkBingo();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		JButton btn = (JButton) e.getSource();

		for (int i = 0; i < btnArr.length; i++) {// 버튼 배열의 열 길이16개
			if (btnArr[i] == btn) { // 해당버튼의 액션이 일어나면
				// 모르겠음
				bArr[i / size][i % size] = true; // 0123 4567 891011되면 다음 행으로 넘어가게 설정됨(해당 버튼 누르면 true값 넣어줌)
				break; // 버튼 1개만 눌렀을때 작동하도록 설정해줌
			}
		}

		btn.setBackground(Color.cyan); // 버튼 눌렀을때의 색깔
		// print(); //(OX나타내주는 )메소드 실행

		if (checkBingo()) { // 빙고 조건 달성시 나오는 문장
			JOptionPane.showMessageDialog(this, "BINGO~!!! 방에서 나가주세요.");
			//try {
				///////////
//
//				client.user.logdto.getBingo().setWin(client.user.logdto.getBingo().getWin() + 1);
//				client.user.logdto.getBingo().setLose(client.user.logdto.getBingo().getLose());
//				client.user.logdto.getBingo().setDraw(client.user.logdto.getBingo().getDraw());
//				client.user.logdto.getBingo().setWinningrate(client.user.logdto.getBingo().getWinningrate());

				//BingoDAO dao = BingoDAO.getInstance();
				// dao.bingoSetMethod(client);

				////////////
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//Thread.sleep(3000);
				closed();
				// System.exit(0);

//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

		}
		System.out.println(btn.getText());
		try {
			client.getOs().writeUTF(mini_User.PUTBUTTON + "/" + room.getRoomNum() + "/" + btn.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // 서버로 전송

	}// end override

	public void print() {
		for (int i = 0; i < bArr.length; i++) {// 4개행
			for (int j = 0; j < bArr.length; j++) { // 4개행
				System.out.print(bArr[i][j] ? "O" : "X"); // true면 O false면 X
			}
			// System.out.println();
		}
		System.out.println("==========================");
	}// end print()

	public boolean checkBingo() {

		bingoCnt = 0;

		int garoCnt = 0;
		int seroCnt = 0;
		int crossCnt1 = 0;
		int crossCnt2 = 0;

		int i, j;

		for (i = 0; i < size; i++) {// 4
			for (j = 0; j < size; j++) {// 2차원 배열의 첫[]배열은 행을 의미 ->그러나 여기선 열로 사용함
				if (bArr[i][j])
					garoCnt++;
				if (bArr[j][i])
					seroCnt++;
				if (bArr[i][j] && i == j)
					crossCnt1++;
				if (bArr[i][j] && i + j == size - 1)
					crossCnt2++;
			}

			// 가로 4개 달성시 -> bingoCnt+1

			if (garoCnt == size) {
				bingoCnt++;
				// for(i=0; i< 4; i++)
				for (j = 0; j < size; j++) {
					if (bArr[i][j]) {
						btnArr[i * size + j].setBackground(Color.BLACK);
					}
				}

			} // garo

			// 세로4개 달성시
			if (seroCnt == size) {
				bingoCnt++;
				// for(i=0; i<4; i++)
				for (j = 0; j < size; j++) {
					if (bArr[j][i]) {
						btnArr[j * size + i].setBackground(Color.BLACK);
					}
				}

			} // sero

			garoCnt = 0;
			seroCnt = 0;

		} // end for

		// 대각선4개 달성시
		if (crossCnt1 == size) {
			bingoCnt++;
			// for(i=0; i<4; i++)
			for (i = 0; i < size; i++) {
				if (bArr[i][i]) {
					btnArr[i * size + i].setBackground(Color.BLACK);
				}
			}

		} // crossCnt1

		// 대각선 만대편4개 달성시
		if (crossCnt2 == size) {
			bingoCnt++;
			// for(i=0; i<size; i++)
			for (i = 0; i < size; i++) {
				if (bArr[i][size - i - 1]) {
					btnArr[i * size + (size - i - 1)].setBackground(Color.BLACK);
				}
			}

		} // crosscnt2

		System.out.println(bingoCnt);

		return bingoCnt >= 3;
	}// end checkbingo



	public void timer() {

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				i++;
				if (i == 60) {
					i = 0;
					j++;

					if (j == 5) {
						timer.cancel();
						JOptionPane.showMessageDialog(frame, "Time Over~! (DRAW) 방에서 나가주세요.");

						//////////////////
						client.user.logdto.getBingo().setWin(client.user.logdto.getBingo().getWin());
						client.user.logdto.getBingo().setLose(client.user.logdto.getBingo().getLose());
						client.user.logdto.getBingo().setDraw(client.user.logdto.getBingo().getDraw() + 1);
						client.user.logdto.getBingo().setWinningrate(client.user.logdto.getBingo().getWinningrate());

						BingoDAO dao = BingoDAO.getInstance();
						dao.bingoSetMethod();
						////////////////////

						try {
							setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							Thread.sleep(3000);
							closed();
							// System.exit(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				// LabelM.setText("경과시간: "+Integer.toString(j));
				// jLabel.setText(" : "+Integer.toString(i)+" (종료 5분)\n");
			}
		};
		timer.schedule(task, 1, 1000);

	}// end timer

	protected void msgSummit() {

		String string = textField.getText();// 메시지전송
		if (!string.equals("")) {

			try {
				// 빙고에서 메시지 보냄
				client.getOs().writeUTF(
						mini_User.ECHO02 + "/" + room.getRoomNum() + "/" + client.getUser().toString() + string);
				textField.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}// end class
