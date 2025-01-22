package com.unu.examenFinal.beans;

public class rol {
	private int idrol;
	private String namerol;

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

	public rol(int idrol, String namerol) {
		super();
		this.idrol = idrol;
		this.namerol = namerol;
	}

	public rol() {
		this(0, "");
	}

}
