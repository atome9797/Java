package minigame.client;

import java.util.ArrayList;

import minigame.game.BingoFrame;
import minigame.game.PrGame;

public class minigame_Room {
	int roomNum;
	String roomName;
	ArrayList<mini_User> userArray; // 채팅방에 접속한 사람들
	mini_User maker; // 방장, 방만든사람
	//RoomUI rUI; // 방 UI
	PrGame BGame;
	int gamestate;

	public minigame_Room() {
		userArray = new ArrayList<mini_User>();
	}

	public minigame_Room(String message) {
		userArray = new ArrayList<mini_User>();
		setRoomName(message);
	}

	
	public int getGamestate() {
		return gamestate;
	}

	public void setGamestate(int gamestate) {
		this.gamestate = gamestate;
	}

	public String toProtocol() {
		return roomNum + "/" + roomName;
	}

	public PrGame getBGame() {
		return BGame;
	}

	public void setBGame(PrGame bGame) {
		BGame = bGame;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public ArrayList<mini_User> getUserArray() {
		return userArray;
	}

	public mini_User getMaker() {
		return maker;
	}

	public void setMaker(mini_User user) {
		this.maker = user;
	}

//	public RoomUI getrUI() {
//		return rUI;
//	}
//
//	public void setrUI(RoomUI rUI) {
//		this.rUI = rUI;
//	}
}
