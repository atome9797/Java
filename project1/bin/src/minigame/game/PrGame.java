package minigame.game;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import minigame.client.clientMain;
import minigame.client.mini_User;
import minigame.client.minigame_Room;

public class PrGame extends JFrame {

	clientMain client;
	minigame_Room room;
	private JTextArea textArea = new JTextArea();

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	void closed() {

		try {
			client.getUser().getOs().writeUTF(mini_User.GETOUT_ROOM + "/" + room.getRoomNum());
			for (int i = 0; i < client.getUser().getRoomArray().size(); i++) {
				if (client.getUser().getRoomArray().get(i).getRoomNum() == room.getRoomNum()) {
					client.getUser().getRoomArray().remove(i);
				}
			}
			setVisible(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if(client.getWaitRoom().makeRoom.room!=null)
			client.getWaitRoom().makeRoom.room = null;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void serverBtn(String bName) {};

}
