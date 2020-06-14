package minigame.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import minigame.game.BingoFrame;
import minigame.login.login;

public class clientMain implements Runnable {
	static int PORT = 7777; // 서버포트번호
	static String IP = "127.0.0.1"; // 서버아이피주소
	Socket socket; // 소켓
	String username;
	public mini_User user;
	mini_WaitRoomUI waitRoom;
	private DataInputStream Is;
	private DataOutputStream Os;
	Thread th;
	boolean ready = false;

	login lo;

	public static void main(String[] args) {
		new clientMain();

	}

	public clientMain() {

		// waitRoom = new mini_WaitRoomUI(this);
		lo = new login(this);
		// serverAccess();
		Thread thread = new Thread(this);
		thread.start();

	}

	public mini_WaitRoomUI getWaitRoom() {
		return waitRoom;
	}

	public void setWaitRoom(mini_WaitRoomUI waitRoom) {
		this.waitRoom = waitRoom;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!ready) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		user = new mini_User(Is, Os);
		user.setIP(socket.getInetAddress().getHostAddress());

		while (true) {
			try {

				String receivedMsg = Is.readUTF(); // 메시지 받기(대기)
				System.out.println(receivedMsg);
				dataParsing(receivedMsg); // 메시지 해석
			} catch (IOException e) {
				e.printStackTrace();
				try {
					user.getIs().close();
					user.getOs().close();
					socket.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		waitRoom.frame.dispose();

	}

	public boolean serverAccess() {
		if (!ready) {
			// 소켓이 연결이 이루어지지 않은 경우에만 실행
			// 처음 연결시에만 실행
			socket = null;
			IP = lo.getIpBtn().getText();

			try {
				// 서버접속
				InetSocketAddress inetSockAddr = new InetSocketAddress(InetAddress.getByName(IP), PORT);
				socket = new Socket();

				// 지정된 주소로 접속 시도 (3초동안)
				socket.connect(inetSockAddr, 3000);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 접속이 되면 실행
			if (socket.isBound()) {
				// 입력, 출력 스트림 생성
				try {
					Is = new DataInputStream(socket.getInputStream());
					Os = new DataOutputStream(socket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				ready = true;
			}
		}

		return ready;
	}

	private void dataParsing(String data) {
		StringTokenizer token = new StringTokenizer(data, "/"); // 토큰 생성
		String protocol = token.nextToken(); // 토큰으로 분리된 스트링
		String id, pw, rNum, nick, rName, msg, result, bName;
		System.out.println("받은 데이터 : " + data);
		System.out.println("--------------------------------");
		System.out.println(protocol);

		switch (protocol) {
		case mini_User.LOGIN: // 로그인
			// 사용자가 입력한(전송한) 아이디와 패스워드
			result = token.nextToken();

			if (result.equals("OK")) {
				alarmMsg("로그인에 성공했습니다!");
				nick = token.nextToken();
				login(nick);
			} else {
				msg = token.nextToken();
				alarmMsg(msg);
			}
			break;
		case mini_User.LOGOUT:
			logout();
			break;
		case mini_User.MEMBERSHIP: // 회원가입 승인
			result = token.nextToken();
			break;

		case mini_User.UPDATE_ROOMLIST: // 방 목록
			roomList(token);
			break;
		case mini_User.UPDATE_SELECTEDROOM_USERLIST: // 대기실에서 선택한 방의 사용자 목록
			selectedRoomUserList(token);
			break;

		case mini_User.ECHO01: // 대기실 에코
			msg = token.nextToken();
			echoMsg(msg);
			break;
		case mini_User.UPDATE_USERLIST: // 대기실 사용자 목록
			userList(token);
			break;
		case mini_User.UPDATE_ROOM_USERLIST: // 채팅방 사용자 목록
			// 방번호읽기
			rNum = token.nextToken();
			userList(rNum, token);
			break;
		case mini_User.ECHO02: // 게임방 에코
			rNum = token.nextToken();
			msg = token.nextToken();
			echoMsgToRoom(rNum, msg);
			break;
		case mini_User.SENDBUTTON:
			rNum = token.nextToken();
			bName = token.nextToken();
			send(rNum,bName);
		}

	}
	
	private void send(String rNum, String bName) {
		for (int i = 0; i < user.getRoomArray().size(); i++) {
			if (Integer.parseInt(rNum) == user.getRoomArray().get(i).getRoomNum()) {
				user.getRoomArray().get(i).BGame.serverBtn(bName);
		

				
			}
		}
		
	}

	private void userList(StringTokenizer token) {
		if (waitRoom == null) {
			return;
		}

		// if (!waitRoom.level3.isLeaf()) {
		// 리프노드가 아니고, 차일드가 있다면 모두 지움
		// waitRoom.level3.removeAllChildren();
		// }
		while (token.hasMoreTokens()) {
			// 아이디와 닉네임을 읽어서 유저 객체 하나를 생성
			String id = token.nextToken();
			String nick = token.nextToken();
			mini_User tempUser = new mini_User(id, nick);

			for (int i = 0; i < waitRoom.userArray.size(); i++) {
				if (tempUser.getId().equals(waitRoom.userArray.get(i))) {
				}
				if (i == waitRoom.userArray.size()) {
					// 배열에 유저가 없으면 추가해줌
					waitRoom.userArray.add(tempUser);
				}
			}
			// 대기실 사용자노드에 추가
			// waitRoom.level3.add(new DefaultMutableTreeNode(tempUser.toString()));
		}
		// waitRoom.userTree.updateUI();

	}

	private void userList(String rNum, StringTokenizer token) {
		for (int i = 0; i < user.getRoomArray().size(); i++) {
			if (Integer.parseInt(rNum) == user.getRoomArray().get(i).getRoomNum()) {

				// 기존에 리스트가 있을 경우 지워줌
				// if (user.getRoomArray().get(i).getrUI().model != null)
				// user.getRoomArray().get(i).getrUI().model.removeAllElements();

				while (token.hasMoreTokens()) {
					// 아이디와 닉네임을 읽어서 유저 객체 하나를 생성
					String id = token.nextToken();
					String nick = token.nextToken();
					mini_User tempUser = new mini_User(id, nick);

					// user.getRoomArray().get(i).getrUI().model.addElement(tempUser.toString());
				}
			}
		}

	}

	private void selectedRoomUserList(StringTokenizer token) {

	}

	private void echoMsgToRoom(String rNum, String msg) {
		for (int i = 0; i < user.getRoomArray().size(); i++) {
			if (Integer.parseInt(rNum) == user.getRoomArray().get(i).getRoomNum()) {
				// 사용자 -> 방배열 -> 유아이 -> 텍스트에어리어
				// 커서 위치 조정

				user.getRoomArray().get(i).getBGame().getTextArea()
						.setCaretPosition(user.getRoomArray().get(i).getBGame().getTextArea().getText().length());
				// 에코
				user.getRoomArray().get(i).getBGame().getTextArea().append(msg + "\n");
			}
		}

	}

	private void echoMsg(String msg) {

		if (waitRoom != null) {
			waitRoom.textArea.setCaretPosition(waitRoom.textArea.getText().length());
			waitRoom.textArea.append(msg + "\n");
		}
	}

	private void roomList(StringTokenizer token) {

		String rNum, rName, rGamestate;
		minigame_Room room = new minigame_Room();

		// 기존에 리스트가 있을 경우 지워줌
		if (waitRoom.model != null) {
			waitRoom.model.removeAllElements();
		}

		while (token.hasMoreTokens()) {
			rNum = token.nextToken();
			rName = token.nextToken();
			rGamestate = token.nextToken();
			int num = Integer.parseInt(rNum);

			// 라스트룸넘버를 업데이트 (최대값+1)
			if (num >= waitRoom.lastRoomNum) {
				waitRoom.lastRoomNum = num + 1;
			}
			room.setRoomNum(num);
			room.setRoomName(rName);
			room.setGamestate(Integer.parseInt(rGamestate));

			waitRoom.model.addElement(room.toProtocol() + "/" + room.getGamestate());
		}
	}

	private void logout() {
		try {
			alarmMsg("채팅 프로그램을 종료합니다!");
			waitRoom.frame.dispose();
			user.getIs().close();
			user.getOs().close();
			socket.close();
			waitRoom = null;
			user = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void login(String nick) {

	}

	private void alarmMsg(String string) {
		int i = JOptionPane.showConfirmDialog(null, string, "메시지", JOptionPane.CLOSED_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		// 확인 누르면 종료
		if (i == 0) {
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public mini_User getUser() {
		return user;
	}

	public void setUser(mini_User user) {
		this.user = user;
	}

	public DataInputStream getIs() {
		return Is;
	}

	public void setIs(DataInputStream Is) {
		this.Is = Is;
	}

	public DataOutputStream getOs() {
		return Os;
	}

	public void setOs(DataOutputStream Os) {
		this.Os = Os;
	}

}
