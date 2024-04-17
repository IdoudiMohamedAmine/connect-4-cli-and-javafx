package com.example.Model;
public class Position {
	private int numLigne;
	private int numColonne;

	public Position(int ligne, int colonne) {
		numLigne = ligne;
		numColonne = colonne;
	}
	public Position(){
		
	}

	public String affiche() {
		String ch = "(" + numLigne + "," + numColonne + ")";
		return ch;
	}

	public int getPosLigne(){
		return numLigne;
	}
	public void setPosLigne(int ligne){
		numLigne=ligne;
	}
	public int getPosColonne(){
		return numColonne;
	}
	public void setPosColonne(int colonne){
		numColonne=colonne;
	}
	@Override
	public String toString() {
		return "Position ("+numLigne + ", " + numColonne + ")";
	}		
}
