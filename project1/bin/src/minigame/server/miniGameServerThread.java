package minigame.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JTextArea;

import minigame.client.mini_User;
import minigame.client.minigame_Room;

public class miniGameServerThread implements Runnable {

	ArrayList<mini_User> userArray; // 서버에 접속한 사용자들
	ArrayList<minigame_Room> roomArray; // 서버가 열어놓은 채팅방들
	mini_User user; // 현재 스레드와 연결된(소켓이 생성된) 사용자
	JTextArea jta;
	boolean onLine = true;

	private DataOutputStream thisUser;

	miniGameServerThread(JTextArea jta, mini_User person, ArrayList<mini_User> userArray, ArrayList<minigame_Room> roomArray) {

		this.roomArray = roomArray;
		this.userArray = userArray;
		this.userArray.add(person); // 배열에 사용자 추가
		this.user = person;
		this.jta = jta;
		this.thisUser = person.getOs();
	}

	@Override
	public void run() {
		DataInputStream dis = user.getIs(); // 입력 스트림 얻기

		while (onLine) {
			try {
				String receivedMsg = dis.readUTF(); // 메시지 받기(대기)
				dataParsing(receivedMsg); // 메시지 해석
				jta.append("성공 : 메시지 읽음 -" + receivedMsg + "\n");
				jta.setCaretPosition(jta.getText().length());
			} catch (IOException e) {
				try {
					user.getIs().close();
					user.getOs().close();
					user = null;
				} catch (IOException e1) {
					e1.printStackTrace();
					jta.append("에러 : 서버스레드-읽기 실패\n");
				} finally {
					break;
				}
			}
		}
	}
	// 데이터를 구분
	public synchronized void dataParsing(String data) {
		StringTokenizer token = new StringTokenizer(data, "/"); // 토큰 생성
		String protocol = token.nextToken(); // 토큰으로 분리된 스트링을 숫자로
		String id, pw, rNum, rName, msg , rGamestate, Bname;
		System.out.println("서버가 받은 데이터 : " + data);

		switch (protocol) {
		case mini_User.LOGIN: // 로그인
			// 사용자가 입력한(전송한) 아이디와 패스워드
			id = token.nextToken();
			pw = token.nextToken();
			login(id, pw);
			break;
		case mini_User.LOGOUT: // 로그아웃
			logout();
			break;
		case mini_User.MEMBERSHIP: // 회원가입
			id = token.nextToken();
			pw = token.nextToken();
			break;
		case mini_User.UPDATE_USERLIST: // 대기실 사용자 목록
			userList(thisUser);
			break;
		case mini_User.UPDATE_ROOM_USERLIST: // 채팅방 사용자 목록
			// 방번호읽기
			rNum = token.nextToken();
			userList(rNum, thisUser);
			break;
		case mini_User.UPDATE_SELECTEDROOM_USERLIST: // 대기실에서 선택한 채팅방의 사용자 목록
			// 방번호읽기
			rNum = token.nextToken();
			selectedRoomUserList(rNum, thisUser);
			break;
		case mini_User.UPDATE_ROOMLIST: // 방 목록
			roomList(thisUser);
			break;
		case mini_User.CREATE_ROOM: // 방만들기
			rNum = token.nextToken();
			rName = token.nextToken();
			rGamestate = token.nextToken();
			createRoom(rNum, rName,rGamestate);
			break;
		case mini_User.GETIN_ROOM: // 방 들어가기
			rNum = token.nextToken();
			getInRoom(rNum);
			break;
		case mini_User.GETOUT_ROOM: // 방 나오기
			rNum = token.nextToken();
			getOutRoom(rNum);
			break;
		case mini_User.ECHO01: // 대기실 에코
			msg = token.nextToken();
			echoMsg(mini_User.ECHO01 + "/" + user.toString() + msg);
			break;
		case mini_User.ECHO02: // 채팅방 에코
			rNum = token.nextToken();
			msg = token.nextToken();
			echoMsg(rNum, msg);
			break;
		case mini_User.PUTBUTTON:
			rNum = token.nextToken();
			Bname = token.nextToken();
			putbutton(rNum,Bname);
			break;

		}
	}
	
	
	
	private void putbutton(String rNum,String Bname) {
		
		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				try {
					userArray.get(i).getOs().writeUTF(mini_User.SENDBUTTON + "/" + rNum+"/"+ Bname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//
		
		
	}

	private void getOutRoom(String rNum) {
		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				// 방에서 나가기
				// 채팅방의 유저리스트에서 사용자 삭제
				for (int j = 0; j < roomArray.get(i).getUserArray().size(); j++) {
					if (user.equals(roomArray.get(i).getUserArray().get(j))) {
						roomArray.get(i).getUserArray().remove(j);
					}
				}

				// 사용자의 방리스트에서 방을 제거
				for (int j = 0; j < user.getRoomArray().size(); j++) {
					if (Integer.parseInt(rNum) == user.getRoomArray().get(j).getRoomNum()) {
						user.getRoomArray().remove(j);
						roomList();
						System.out.println(j + ": 방을 지웠다");
					}
				}
				echoMsg(roomArray.get(i), user.toString() + "님이 퇴장하셨습니다.");
				userList(rNum);

				if (roomArray.get(i).getUserArray().size() <= 0) {
					roomArray.remove(i);
					roomList();
				}
			
				
			}
		}}
	

	private void getInRoom(String rNum) {
		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				// 방 객체가 있는 경우, 방에 사용자추가
				roomArray.get(i).getUserArray().add(user);
				// 사용자 객체에 방 추가
				user.getRoomArray().add(roomArray.get(i));
				echoMsg(roomArray.get(i), user.toString() + "님이 입장하셨습니다.");
				userList(rNum);
			}
		}
	}

	private void createRoom(String rNum, String rName , String rGamestate) {
		minigame_Room rm = new minigame_Room(rName); // 지정한 제목으로 채팅방 생성
		rm.setMaker(user); // 방장 설정
		rm.setRoomNum(Integer.parseInt(rNum)); // 방번호 설정
		rm.setGamestate(Integer.parseInt(rGamestate));

		rm.getUserArray().add(user); // 채팅방에 유저(본인) 추가
		roomArray.add(rm); // 룸리스트에 현재 채팅방 추가
		user.getRoomArray().add(rm); // 사용자 객체에 접속한 채팅방을 저장

		echoMsg(mini_User.ECHO01 + "/" + user.toString() + "님이 " + rm.getRoomNum() + "번 방을 개설하셨습니다.");
		echoMsg(rm, user.toString() + "님이 입장하셨습니다.");
		roomList();
		userList(rNum, thisUser);
		jta.append("성공 : " + userArray.toString() + "가 방생성\n");
	}

	private void whisper(String id, String msg) {
		
	}

	// 대기실 에코
	private void echoMsg(String msg) {
		//System.out.println("여기가 문제? : "+msg);
		for (int i = 0; i < userArray.size(); i++) {
			try {
				userArray.get(i).getOs().writeUTF(msg);
				jta.append(user.toString() + " - " + msg + "\n");
			} catch (IOException e) {
				e.printStackTrace();
				jta.append("에러 : 에코 실패\n");
			}
		}
	}

	// 방 에코 (방 번호만 아는 경우)
	private void echoMsg(String rNum, String msg) {
		
		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				echoMsg(roomArray.get(i), msg);
			}
		}
	}

	// 방 에코 (방객체가 있는 경우)
	private void echoMsg(minigame_Room room, String msg) {
		for (int i = 0; i < room.getUserArray().size(); i++) {
			try {
				// 방에 참가한 유저들에게 에코 메시지 전송
				room.getUserArray().get(i).getOs().writeUTF(mini_User.ECHO02 + "/" + room.getRoomNum() + "/" + msg);
				jta.append("성공 : 메시지전송 : " + msg + "\n");
			} catch (IOException e) {
				e.printStackTrace();
				jta.append("에러 : 에코 실패\n");
			}
		}
	}

	private void login(String id, String pw) {
		StringBuffer str = new StringBuffer();
		try {

			//DBLogin logdb = new DBLogin();
			//int result = logdb.checkIDPW(id, pw);

			if (true) { // result가 0이면 성공
				for (int i = 0; i < userArray.size(); i++) {
					if (id.equals(userArray.get(i).getId())) {
						try {
							System.out.println("접속중");
							thisUser.writeUTF(mini_User.LOGIN + "/fail/이미 접속 중입니다.");
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
				}
				// 로그인 OK
				user.setId(id);
				user.setPw(pw);
				user.setUserName(id);
				thisUser.writeUTF(mini_User.LOGIN + "/OK/" + user.getUserName());
				this.user.setOnline(true);

				// 대기실에 에코
				echoMsg(mini_User.ECHO01 + "/" + user.toString() + "님이 입장하셨습니다.");
				jta.append(id + " : 님이 입장하셨습니다.\n");

				roomList(thisUser);
				for (int i = 0; i < userArray.size(); i++) {
					userList(userArray.get(i).getOs());
				}

				jta.append("성공 : DB 읽기 : " + id);
			} else { // result가 1이면 실패
				thisUser.writeUTF(mini_User.LOGIN + "/fail/아이디와 비밀번호를 확인해 주세요!");
			}

		} catch (Exception e) {
			try {
				thisUser.writeUTF(mini_User.LOGIN + "/fail/아이디가 존재하지 않습니다!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			jta.append("실패 : DB 읽기\n");
			return;
		}

	}

	private void logout() {
		System.out.println("로그아웃 했음!");

		// 오프라인으로 바꿈
		user.setOnline(false);
		// 사용자배열에서 삭제
		//System.out.println(userArray.size());
		for (int i = 0; i < userArray.size(); i++) {
			
			if (user.equals(userArray.get(i))) {
				System.out.println(userArray.get(i).getId() + "지웠다.");
				userArray.remove(i);
			}
		}
		// room 클래스의 멤버변수인 사용자배열에서 삭제
		for (int i = 0; i < roomArray.size(); i++) {
			for (int j = 0; j < roomArray.get(i).getUserArray().size(); j++) {
				if (user.getId().equals(roomArray.get(i).getUserArray().get(j).getId())) {
					roomArray.get(i).getUserArray().remove(j);
				}
			}
		}
		echoMsg(mini_User.ECHO01 + "/" + user.toString() + "님이 퇴장하셨습니다.");

		for (int i = 0; i < userArray.size(); i++) {
			userList(userArray.get(i).getOs());
		}

		jta.append(user.getId() + " : 님이 퇴장하셨습니다.\n");

		try {
			user.getOs().writeUTF(mini_User.LOGOUT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			user.getIs().close();
			user.getOs().close();
			user = null;
			jta.append("성공 : 스트림 닫기\n");
		} catch (IOException e) {
			e.printStackTrace();
			jta.append("실패 : 스트림 닫기\n");
		}
	}

	// 사용자 리스트 (선택한 채팅방)
	public void selectedRoomUserList(String rNum, DataOutputStream target) {
		String ul = "";

		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				for (int j = 0; j < roomArray.get(i).getUserArray().size(); j++) {
					// 채팅방에 접속되어 있는 유저들의 아이디+닉네임
					ul += "/" + roomArray.get(i).getUserArray().get(j).toProtocol();
				}
			}
		}
		try {
			// 데이터 전송
			target.writeUTF(mini_User.UPDATE_SELECTEDROOM_USERLIST + ul);
			jta.append("성공 : 목록(사용자)-" + ul + "\n");
		} catch (IOException e) {
			jta.append("에러 : 목록(사용자) 전송 실패\n");
		}
	}

	// 사용자 리스트 (대기실)
	public String userList(DataOutputStream target) {
		String ul = "";

		for (int i = 0; i < userArray.size(); i++) {
			// 접속되어 있는 유저들의 아이디+닉네임
			ul += "/" + userArray.get(i).toProtocol();
		}

		try {
			// 데이터 전송
			target.writeUTF(mini_User.UPDATE_USERLIST + ul);
			jta.append("성공 : 목록(사용자)-" + ul + "\n");
		} catch (IOException e) {
			jta.append("에러 : 목록(사용자) 전송 실패\n");
		}
		return ul;
	}

	// 사용자 리스트 (채팅방 내부)
	public void userList(String rNum, DataOutputStream target) {
		String ul = "/" + rNum;

		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				for (int j = 0; j < roomArray.get(i).getUserArray().size(); j++) {
					// 채팅방에 접속되어 있는 유저들의 아이디+닉네임
					ul += "/" + roomArray.get(i).getUserArray().get(j).toProtocol();
				}
			}
		}
		try {
			// 데이터 전송
			target.writeUTF(mini_User.UPDATE_ROOM_USERLIST + ul);
			jta.append("성공 : 목록(사용자)-" + ul + "\n");
		} catch (IOException e) {
			jta.append("에러 : 목록(사용자) 전송 실패\n");
		}
	}

	// 사용자 리스트 (채팅방 내부 모든 사용자들에게 전달)
	public void userList(String rNum) {
		String ul = "/" + rNum;
		minigame_Room temp = null;
		for (int i = 0; i < roomArray.size(); i++) {
			if (Integer.parseInt(rNum) == roomArray.get(i).getRoomNum()) {
				temp = roomArray.get(i);
				for (int j = 0; j < roomArray.get(i).getUserArray().size(); j++) {
					// 채팅방에 접속되어 있는 유저들의 아이디+닉네임
					ul += "/" + roomArray.get(i).getUserArray().get(j).toProtocol();
				}
			}
		}
		for (int i = 0; i < temp.getUserArray().size(); i++) {
			try {
				// 데이터 전송
				temp.getUserArray().get(i).getOs().writeUTF(mini_User.UPDATE_ROOM_USERLIST + ul);
				jta.append("성공 : 목록(사용자)-" + ul + "\n");
			} catch (IOException e) {
				jta.append("에러 : 목록(사용자) 전송 실패\n");
			}
		}
	}

	// 채팅 방리스트
	public void roomList(DataOutputStream target) {
		String rl = "";

		for (int i = 0; i < roomArray.size(); i++) {
			// 만들어진 채팅방들의 제목
			rl += "/" + roomArray.get(i).toProtocol()+"/"+roomArray.get(i).getGamestate();
		}

		jta.append("test\n");

		try {
			// 데이터 전송
			target.writeUTF(mini_User.UPDATE_ROOMLIST + rl);
			jta.append("성공 : 목록(방)-" + rl + "\n");
		} catch (IOException e) {
			jta.append("에러 : 목록(방) 전송 실패\n");
		}
	}

	// 채팅 방리스트
	public void roomList() {
		String rl = "";

		for (int i = 0; i < roomArray.size(); i++) {
			// 만들어진 채팅방들의 제목
			rl += "/" + roomArray.get(i).toProtocol()+"/"+roomArray.get(i).getGamestate();
		}

		jta.append("test\n");

		for (int i = 0; i < userArray.size(); i++) {

			try {
				// 데이터 전송
				userArray.get(i).getOs().writeUTF(mini_User.UPDATE_ROOMLIST + rl);
				jta.append("성공 : 목록(방)-" + rl + "\n");
			} catch (IOException e) {
				jta.append("에러 : 목록(방) 전송 실패\n");
			}
		}
	}
}
