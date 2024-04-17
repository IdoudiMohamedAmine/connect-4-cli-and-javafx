package com.example.Model;


public class Game {
	private int[][] grille;
	private final int nbLigne=6;
	private final int nbColonne=7;
	private final int idJ1, idJ2;
	
	public Game(int idJ1,int idJ2) {
		this.idJ1 = idJ1;
		this.idJ2 = idJ2;
		grille = new int[nbLigne][nbColonne];
	}

	public int getNbLigne() {
		return nbLigne;
	}

	public int getNbColonne() {
		return nbColonne;
	}

	public int getValeurPosition(int numLigne, int numColonne) {
		return grille[numLigne][numColonne];
	}

	public boolean estPositionVide(int numLigne, int numColonne) {
		if (grille[numLigne][numColonne] == 0)
			return true;
		return false;
	}

	public boolean setCoup(int posL, int posC, int idJoueur) {
		grille[posL][posC] = idJoueur;
		return estGagnant(posL, posC, idJoueur);

	}

	public boolean setCoup(Position pos, int idJoueur) {
		grille[pos.getPosLigne()][pos.getPosColonne()] = idJoueur;
		return estGagnant(pos, idJoueur);

	}

	public boolean estGagnant(int posL, int posC, int idJoueur) {
		boolean fin = false;
		Position pos = new Position(posL, posC);
		fin = alignementH(pos, idJoueur) || alignementV(pos, idJoueur) || alignementD(pos, idJoueur);
		return fin;
	}

	public boolean estGagnant(Position pos, int idJoueur) {
		boolean fin = false;
		fin = alignementH(pos, idJoueur) || alignementV(pos, idJoueur) || alignementD(pos, idJoueur);
		return fin;
	}

	private boolean alignementD(Position pos, int idJoueur) {
		int k = 0;
		int j = pos.getPosColonne();
		int i = pos.getPosLigne();
		while (j >= 0 && i >= 0 && grille[i][j] == idJoueur) {
			k++;
			j--;
			i--;
		}
		j = pos.getPosColonne() + 1;
		i = pos.getPosLigne() + 1;
		while (j < nbColonne && i < nbLigne && grille[i][j] == idJoueur) {
			k++;
			j++;
			i++;
		}
		if (k >= 4)
			return true;
		////
		k = 0;
		j = pos.getPosColonne();
		i = pos.getPosLigne();
		while (j >= 0 && i < nbLigne && grille[i][j] == idJoueur) {
			k++;
			j--;
			i++;
		}
		j = pos.getPosColonne() + 1;
		i = pos.getPosLigne() - 1;
		while (j < nbColonne && i >= 0 && grille[i][j] == idJoueur) {
			k++;
			j++;
			i--;
		}
		if (k >= 4)
			return true;
		return false;
	}

	private boolean alignementV(Position pos, int idJoueur) {
		int k = 0;
		int i = pos.getPosLigne();
		while (i >= 0 && grille[i][pos.getPosColonne()] == idJoueur) {
			k++;
			i--;
		}
		if (k == 4)
			return true;
		return false;
	}

	private boolean alignementH(Position pos, int idJoueur) {
		int k = 0;
		int j = pos.getPosColonne();
		while (j >= 0 && grille[pos.getPosLigne()][j] == idJoueur) {
			k++;
			j--;
		}
		j = pos.getPosColonne() + 1;
		while (j < 7 && grille[pos.getPosLigne()][j] == idJoueur) {
			k++;
			j++;
		}
		if (k >= 4)
			return true;
		return false;
	}

	public int getLigneVideByColonne(int numColonne) throws CoupException {
		int numLigne = 0;
		if(numColonne>7)
			throw new CoupException("Num�ro de la colonne est incorrect");
		while (numLigne < 6 && ! estPositionVide(numLigne, numColonne))
			numLigne++;
		if(numLigne==6) 
			throw new CoupException(new Position(numLigne,numColonne));
		return numLigne;
	}

	public void initialiseGrille() {
		for (int i = 0; i < nbLigne; i++)
			for (int j = 0; j < nbColonne; j++)
				grille[i][j] = 0;
	}

	public String toString() {
		String ch = "";
		for (int i = nbLigne - 1; i >= 0; i--) {
			ch = ch + (i + 1) + " ";
			for (int j = 0; j < nbColonne; j++) {
				String car = "-";
				if (grille[i][j] == idJ1)
					car = "+";
				else if (grille[i][j] == idJ2)
					car = "*";
				ch = ch + "| " + car;
			}
			ch = ch + "|\n";
		}
		ch = ch + "  ";
		for (int j = 0; j < nbColonne; j++)
			ch = ch + "| " + (j + 1);
		return ch;
	}


	////
	public int getIdJ1() {
		return idJ1;
	}

	public int getIdJ2() {
		return idJ2;
	}
	//
	public boolean estRemplie() {		
		boolean test=true;
		for (int i = 0; i < nbLigne && test; i++)
			for (int j = 0; j < nbColonne && test; j++)
				if(grille[i][j] ==0) 
					test=false;
		return test;
	}
}
