package minigame.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import minigame.DB.DTOLogin;
import minigame.DB.coinDAO;
import minigame.DB.coinDTO;
import minigame.client.clientMain;
import minigame.client.mini_User;
import minigame.client.minigame_Room;


public class CoinEat extends PrGame implements KeyListener, ActionListener, WindowListener {
	
	private Image bufferImage;
	private Graphics screenGraphic;

//	private Image backgroundImage = new ImageIcon(
//			"C:\\study\\workspace\\javademo\\src\\java0527_network\\test22\\cardgame\\Images\\background.jpg")
//					.getImage();
//	private Image player = new ImageIcon(
//			"C:\\study\\workspace\\javademo\\src\\java0527_network\\test22\\cardgame\\Images\\player.png").getImage();
//	private Image coin = new ImageIcon(
//			"C:\\study\\workspace\\javademo\\src\\java0527_network\\test22\\cardgame\\Images\\coin.png").getImage();
	private Image backgroundImage = new ImageIcon("src/minigame/game/Images/background.jpg").getImage();
	private Image player = new ImageIcon("src/minigame/game/Images/player.png").getImage();
	private Image coin = new ImageIcon("src/minigame/game/Images/coin.png").getImage();
	
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int coinX, coinY;
	private int coinWidth = coin.getWidth(null);
	private int coinHeight = coin.getHeight(null);
	private int score;
	private int i = 5;
	private int scs;
	private String nick;
	// private DTOCoinScore dto;
	// private DAOCoinScore dao;

	private boolean up, down, left, right;

	public CoinEat(clientMain client, minigame_Room room) {
		this.client = client;
		this.room = room;

		setTitle("동전 먹기 게임");
		setVisible(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
		// sp = new scorePrint();
		// System.out.println(sp.name);
		setResizable(false);

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		addKeyListener(this);
		// add(BorderLayout.SOUTH,startbtn.start);
		// startbtn.start.addActionListener(this);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				i--;
				if (i == 0) {
					if(isVisible())
						score();
					close();
					// timer.cancel();
				}
			}
		};
		timer.schedule(task, 60, 1000);
		Timer timer2 = new Timer();
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				keyProcess();
				crashCheck();
			}
		};
		timer2.schedule(task2, 1000, 10);
		init();
		// while(true) {

//				try {
//					//Thread.sleep(20);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}

		// }
	}

	public void init() {
		score = 0;
		playerX = (500 - playerWidth) / 2;
		playerY = (500 - playerHeight) / 2;

		coinX = (int) (Math.random() * (501 - playerWidth));
		coinY = (int) (Math.random() * (501 - playerHeight - 30)) + 30;

	}

	public void keyProcess() {
		if (up && playerY - 3 > 30)
			playerY -= 3;
		if (down && playerY + playerHeight + 3 < 500)
			playerY += 3;
		if (left && playerX - 3 > 0)
			playerX -= 3;
		if (right && playerX + playerWidth + 3 < 500)
			playerX += 3;
	}

	public void crashCheck() {
		if (playerX + playerWidth > coinX && coinX + coinWidth > playerX && playerY + playerHeight > coinY
				&& coinY + coinHeight > playerY) {
			score += 100;
			coinX = (int) (Math.random() * (501 - playerWidth));
			coinY = (int) (Math.random() * (501 - playerHeight - 30)) + 30;
		}
	}

	public void paint(Graphics g) {
		bufferImage = createImage(500, 500);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);

	}

	public void screenDraw(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(coin, coinX, coinY, null);
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE : " + score, 30, 80);
		g.drawString("Time : " + Integer.toString(i), 400, 80);
		g.drawString("Key:W,A,S,D", 200, 80);
		g.drawString("User:"+client.user.logdto.getNick(), 30, 450);
		this.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void restart() {
		if (i == 0) {
			i = 60;
			score = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼 메소드
//		Object obj = e.getSource();
//		if(obj == startbtn.start) {
//			// 메소드 넣어야함
//		//	restart();
//		}
//			
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}
	
	public void score() {
		coinDAO dao = coinDAO.getInstance();
		coinDTO dto = dao.coinscore(score, nick);
	}
	
	public void close() {
		int chk = JOptionPane.showConfirmDialog(this, "다시 시작하시겠습니까?", "Game Over", JOptionPane.YES_NO_OPTION);
		if (chk == JOptionPane.YES_OPTION) {
			restart();
		} else {
			
			closed();
			setVisible(false);
			// System.exit(0);}
		}

	}
}
