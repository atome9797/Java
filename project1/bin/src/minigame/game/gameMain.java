package minigame.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import minigame.client.clientMain;
import minigame.client.mini_User;
import minigame.client.minigame_Room;

public class gameMain extends PrGame implements ActionListener {


	JFrame frame;

	String[] cols = { "Nickname", "Count", "Record" };// 테이블 헤드
	JPanel panelNorth; // 상단
	JPanel panelEast; // 정보창
	JPanel panelCenter; // 게임창
	JLabel LabelMessage; // 상단텍스트
	JLabel retryMessage; // 다시시작
	JLabel timeMessage; // 시간출력
	JLabel nameMessage; // 이름출력
	JButton retry;
	JButton[] buttons = new JButton[16];
	String[] images = { "ani01.png", "ani02.png", "ani03.png", "ani04.png", "ani05.png", "ani06.png", "ani07.png",
			"ani08.png", "ani01.png", "ani02.png", "ani03.png", "ani04.png", "ani05.png", "ani06.png", "ani07.png",
			"ani08.png" }; // 이미지파일
	int openCount = 0; // 열린 카드 숫자 : 0, 1, 2
	int buttonIndexSave = 0; // 첫번째 카드 인덱스 : 0 ~ 15
	int buttonIndexSave2 = 0; // 두번째 열린 카드 인덱스 : 0 ~ 15
	Timer timer; // 카드초기화 시간
	int tryCount = 0; // 시도 횟수
	int sucessCount = 0; // 빙고 갯수 0 ~ 8
	int winCount; // 승리 횟수
	int i = 0, j = 0;
	long timer2 = 0, timer3 = 0;
	boolean ch = true, ch2 = true;
//	scorePrint sp;
	long cunTime, cur2;

	public gameMain(clientMain client, minigame_Room room) {

		this.client = client;
		this.room = room;
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(600, 500);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}

		});
		cunTime = System.currentTimeMillis();
//		sp = new scorePrint();
		init(frame); // 메인화면
		mixCard(); // 카드 섞기
		timer2 = System.currentTimeMillis();
		frame.pack(); // 정돈
		timer();

	}

	

	public void close() { // x버튼 메세지

		int chk = JOptionPane.showConfirmDialog(frame, "종료하시겠습니까?", "Game Over", JOptionPane.YES_NO_OPTION);

		if (chk == JOptionPane.YES_OPTION) {

			closed();
			setVisible(false);
			// System.exit(0);
		}

	}

	private void gameclose() { // 게임 완료후 메세지
		int chk = JOptionPane.showConfirmDialog(frame, "다시하시겠습니까?", "Game Over", JOptionPane.YES_NO_OPTION);

		if (chk == JOptionPane.YES_OPTION) {
			for (int i = 0; i < 16; i++) {
				buttons[i].setEnabled(true);
				buttons[i].setIcon(changeImege("back.jpg"));
			}
			mixCard();
			i = 0;
			tryCount = 0;
			sucessCount = 0;
			LabelMessage.setText("같은 이미지를 찾으세요  " + "시도 횟수 : " + tryCount);

		} else if (chk == JOptionPane.NO_OPTION) {
			closed();
			setVisible(false);
			// System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == retry) {
			for (int i = 0; i < 16; i++) {
				buttons[i].setEnabled(true);
				buttons[i].setIcon(changeImege("back.jpg"));
			}
			mixCard();
			tryCount = 0;
			sucessCount = 0;
			i = 0;
			LabelMessage.setText("같은 이미지를 찾으세요  " + "시도 횟수 : " + tryCount);
			return;
		}

		if (openCount == 2) {
			return;
		}

		JButton btn = (JButton) e.getSource();
		int index = getButtonIndex(btn);
		btn.setIcon(changeImege(images[index]));

		openCount++;
		if (openCount == 1) {
			buttonIndexSave = index;
		} else if (openCount == 2) {
			buttonIndexSave2 = index;
			if (buttonIndexSave != buttonIndexSave2)
				tryCount++;
			LabelMessage.setText("같은 이미지를 찾으세요  " + "시도 횟수 : " + tryCount);

			boolean isBingo = checkCard(buttonIndexSave, buttonIndexSave2);
			if (isBingo == true) {
				openCount = 0;
				sucessCount++;
				buttons[buttonIndexSave].setEnabled(false);
				buttons[buttonIndexSave2].setEnabled(false);
				if (sucessCount == 1) {
					LabelMessage.setText("Game Over");
					ch = false;
					j = i;
					gameclose();
				}
			} else {
				backToQuestion();
			}
		}

	}// end actionPerformed()

	void mixCard() {
		Random ran = new Random();
		for (int i = 0; i < 1000; i++) {
			int random = ran.nextInt(15) + 1;
			String temp = images[0];
			images[0] = images[random];
			images[random] = temp;

		}
	}

	public void backToQuestion() {
		Timer timer2 = new Timer();
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {

				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				buttons[buttonIndexSave].setIcon(changeImege("back.jpg"));
				buttons[buttonIndexSave2].setIcon(changeImege("back.jpg"));
				openCount = 0;
				cancel();
			}

		};
		timer2.schedule(task2, 60, 200);
	}

	public void timer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				i++;
				timeMessage.setText(Integer.toString(i));
				if (i == 60) {
					gameclose();
				}
			}
		};
		timer.schedule(task, 60, 1000);

	}

	public boolean checkCard(int index1, int index2) {
		if (index1 == index2) {

			return false;
		}
		if (images[index1].equals(images[index2])) {
			return true;
		} else {
			return false;
		}
	}

	public int getButtonIndex(JButton btn) {
		int index = 0;
		for (int i = 0; i < 16; i++) {
			if (buttons[i] == btn) {
				index = i;
			}
		}
		return index;
	}

	void init(JFrame frame2) {
		panelNorth = new JPanel();
		panelNorth.setPreferredSize(new Dimension(400, 100));
		panelNorth.setBackground(Color.orange);
		LabelMessage = new JLabel("같은 이미지를 찾으세요 " + "시도 횟수 : ");
		LabelMessage.setPreferredSize(new Dimension(400, 100));
		LabelMessage.setFont(new Font("", Font.PLAIN, 20));
		LabelMessage.setHorizontalAlignment(JLabel.CENTER);
		panelNorth.add(LabelMessage);
		frame2.add("North", panelNorth); // 북쪽판넬

		panelEast = new JPanel();
		panelEast.setPreferredSize(new Dimension(300, 400));
		retryMessage = new JLabel("다시 시작");
		retryMessage.setFont(new Font("", Font.BOLD, 15));
		retry = new JButton();
		retry.addActionListener(this);
		retry.setPreferredSize(new Dimension(100, 30));
		retry.add(retryMessage);
		timeMessage = new JLabel();
		timeMessage.setPreferredSize(new Dimension(100, 100));
		timeMessage.setFont(new Font("", Font.PLAIN, 40));
		panelEast.add(timeMessage);
		panelEast.add(retry);
//		nameMessage = new JLabel(sp.name);
//		nameMessage.setPreferredSize(new Dimension(140, 400));
//		nameMessage.setFont(new Font("", Font.PLAIN, 30));
//		panelEast.add(nameMessage);
		frame2.add("East", panelEast); // 동쪽판넬

		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(4, 4));
		panelCenter.setPreferredSize(new Dimension(400, 400));
		for (int i = 0; i < 16; i++) {
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(100, 100));
			buttons[i].setIcon(changeImege("back.jpg"));
			buttons[i].addActionListener(this);
			panelCenter.add(buttons[i]);
		}

		frame2.add("Center", panelCenter);

	}

	static ImageIcon changeImege(String filename) {
		ImageIcon icon = new ImageIcon("./src/minigame/image/" + filename);
		Image originImage = icon.getImage();
		Image changeImege = originImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon icon_new = new ImageIcon(changeImege);

		return icon_new;
	}

}
