package com.example.Model;
public class Coup {
	private int id;
	private int numColonneJ1 ;
	private int numColonneJ2 ;
	public Coup(int numColonneJ1) {
		this.numColonneJ1 = numColonneJ1;
	}
	public Coup(int id, int numColonneJ1, int numColonneJ2) {
		this.id = id;
		this.numColonneJ1 = numColonneJ1;
		this.numColonneJ2 = numColonneJ2;
	}
	public int getCoupJ1() {
		return numColonneJ1;
	}
	public void setCoupJ1(int pnumColonneJ1os) {
		this.numColonneJ1 = numColonneJ1;
	}
	//
	public int getCoupJ2() {
		return numColonneJ2;
	}
	public void setCoupJ2(int numColonneJ2) {
		this.numColonneJ2 = numColonneJ2;
	}
	@Override
	public String toString() {
		return "Coup [numColonneJ1=" + numColonneJ1 +", numColonneJ2=" + numColonneJ2 +"]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
