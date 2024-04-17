package com.example.Model;
public class CoupException extends Exception {
	String msg;
	public CoupException(Position coup) {
		if(coup.getPosLigne()==1)
			msg="Colonne "+coup.getPosLigne()+" est rempli";
	}
	public CoupException(String message) {
		msg=message;
	}
	public String getMessage() {
		return msg;
	}
}
