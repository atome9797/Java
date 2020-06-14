package minigame.DB;

import minigame.client.clientMain;

public class DTOLogin {
	private String id;
	private String password;
	private String name;
	private char sextual;
	private String address;
	private String number1;
	private String nick;
	private coinDTO coin;
	private cardDTO card;
	private BingoDTO bingo;
	
	
	public DTOLogin() {
	}
	
	
	public DTOLogin(String id, String password, String name, char sextual, String address, String number1, String nick) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sextual = sextual;
		this.address = address;
		this.number1 = number1;
		this.nick =nick;
	}

	
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public char getSextual() {
		return sextual;
	}


	public void setSextual(char sextual) {
		this.sextual = sextual;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getNumber1() {
		return number1;
	}


	public void setNumber1(String number1) {
		this.number1 = number1;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}



	public coinDTO getCoin() {
		return coin;
	}


	public void setCoin(coinDTO coin) {
		this.coin = coin;
	}


	public cardDTO getCard() {
		return card;
	}


	public void setCard(cardDTO card) {
		this.card = card;
	}


	public BingoDTO getBingo() {
		return bingo;
	}


	public void setBingo(BingoDTO bingo) {
		this.bingo = bingo;
	}
}
