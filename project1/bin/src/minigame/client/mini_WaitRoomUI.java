package minigame.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import minigame.game.BingoFrame;


public class mini_WaitRoomUI{

	public minigame_Room room;
	JFrame frame;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	JTextArea textArea;
	DefaultListModel model;
	clientMain client;
	JTable table;
	JScrollPane scroll;
	ArrayList<mini_User> userArray; 
	int tableX =210,tableY = 175;
	JList roomList;
	int lastRoomNum =1;
	public mini_MakeRoom makeRoom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		new mini_WaitRoomUI(null);
	}

	public int getLastRoomNum() {
		return lastRoomNum;
	}
	

	public void setLastRoomNum(int lastRoomNum) {
		this.lastRoomNum = lastRoomNum;
	}

	/**
	 * Create the application.
	 */
	public mini_WaitRoomUI(clientMain client) {
		initialize();
		this.client=client;
		timer();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("미니게임천국");
		frame.setBounds(100,100,500,500);
		frame.setLocationRelativeTo(null);//중앙에서 시작되라
		frame.setResizable(false);//사이즈 변경x
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel start = new JPanel();
		start.setLayout(null);
		start.setBounds(0, 0, 494, 472);
		frame.getContentPane().add(start);
		
		JPanel before = new JPanel();
		before.setLayout(null);
		before.setBounds(0, 0, 494, 472);
		frame.getContentPane().add(before);
		
		JButton btnMake = new JButton("방 만들기"); // 방 만들기
		btnMake.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRoom();
			}
		});
		btnMake.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 방만들기 버튼 클릭
				
			}
		});
		btnMake.setBounds(54, 413, 145, 49);
		start.add(btnMake);
		
		JButton btnjoin = new JButton("방 참가하기"); // 참가하기
		btnjoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 방 들어가기
				
				getIn();
			}
		});
		
		
		scroll = new JScrollPane();
		scroll.setBounds(tableX, 26, 270, tableY);
		start.add(scroll);
		
		
		roomList = new JList(new DefaultListModel());
		roomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = roomList.getFirstVisibleIndex();
				
				if (i != -1) {
					// 채팅방 목록 중 하나를 선택한 경우,
					// 선택한 방의 방번호를 전송
					String temp = (String) roomList.getSelectedValue();
					if(temp.equals(null)){
						return;
					}

					
				}
			}
		});
		
		model = (DefaultListModel) roomList.getModel();
		scroll.setViewportView(roomList);
		
	
		
		
		btnjoin.setBounds(275, 413, 169, 49);
		start.add(btnjoin);
		
		
	
		
		textField = new JTextField();
		textField.setBounds(26, 81, 153, 100);
		
		
	
		textField.setColumns(10);
		start.add(textField);
		
	

		JButton sendBtn = new JButton("send");
		sendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				msgSummit();
				textField_4.requestFocus();
			}
		});
		sendBtn.setBounds(410, 382,80, 20);
		start.add(sendBtn);
		
		
		JLabel lblNewLabel = new JLabel("유저정보");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setBounds(26, 65, 57, 15);
		start.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(26, 26, 153, 29);
		start.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TIME");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_1.setBounds(26, 10, 57, 15);
		start.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("채팅");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_2.setBounds(26, 224, 57, 15);
		start.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		textArea = new JTextArea(); // 채팅 패널
		scrollPane.setBounds(26, 243, 455, 130);
		textArea.setBounds(28, 245, 450, 126);
		start.add(textArea);// 텍스트 필드 채팅 보이는 패널
		start.add(scrollPane); 
		
		
		JLabel lblNewLabel_3 = new JLabel("대기실");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_3.setBounds(tableX, 10, 70, 15);
		start.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("입력");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(12, 384, 34, 19);
		start.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					msgSummit();
				}
			}

		});
		
		
		textField_4.setBounds(54, 382, 350, 21);
		start.add(textField_4);
		textField_4.setColumns(10);
		
		
		frame.setVisible(true);
		
		
	
	}
	public void timer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm:ss");
				Calendar time = Calendar.getInstance();
			       
				String format_time1 = format1.format(time.getTime());
				textField_1.setText(format_time1);
			}
			
			
		};
		timer.schedule(task, 0, 1000);

	}
	

	protected void msgSummit() {
	
		String string = textField_4.getText();// 메시지전송
		if (!string.equals("")) {
			
				try {
					// 대기실에 메시지 보냄
					client.getOs().writeUTF(mini_User.ECHO01 + "/" + string);
				} catch (IOException e) {
					e.printStackTrace();
				}
				textField_4.setText("");
			
		}
		
		
		
		
		
		
	}

	
	
	protected void getIn() {
		String selectedRoom = (String) roomList.getSelectedValue();
		StringTokenizer token = new StringTokenizer(selectedRoom, "/"); // 토큰 생성
		String rNum = token.nextToken();
		String rName = token.nextToken();
		
		String rGamestate = token.nextToken();

		minigame_Room theRoom = new minigame_Room(rName); // 방 객체 생성
		theRoom.setRoomNum(Integer.parseInt(rNum)); // 방번호 설정
		theRoom.setGamestate(Integer.parseInt(rGamestate));
		
		if(theRoom.getGamestate()==1)
		theRoom.setBGame(new BingoFrame(client, theRoom)); // UI

		// 클라이언트가 접속한 방 목록에 추가
		client.getUser().getRoomArray().add(theRoom);

		try {
			client.getOs().writeUTF(mini_User.GETIN_ROOM + "/" + theRoom.getRoomNum()+"/"+ theRoom.getGamestate());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void createRoom() {
		
		if(makeRoom != null)
			makeRoom.frame.setVisible(true);
		if(makeRoom == null)
			makeRoom = new mini_MakeRoom(client,room);
		
	}
}
