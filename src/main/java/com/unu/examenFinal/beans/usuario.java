package com.unu.examenFinal.beans;

public class usuario {
	private int id;
	private String username;
	private String password;
	private String namerol;
	private int idrol;	
	
	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getNamerol() {
		return namerol;
	}

	public void setNamerol(String namerol) {
		this.namerol = namerol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public usuario(int id, String username, String password, String namerol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.namerol = namerol;
	}
	

	public usuario(int id, String username, String password, int idrol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.idrol = idrol;
	}


	public usuario() {
		this(0, "", "", "");
	}

}
