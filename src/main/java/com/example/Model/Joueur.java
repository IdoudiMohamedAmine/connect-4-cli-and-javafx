package com.example.Model;
import java.util.Scanner;

public class Joueur implements Comparable<Joueur> {
	private  int id ;
	private String nom ;
	private int score;
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void incrementerScore() {
		this.score++;
	}
	public void decrementerScore() {
		this.score--;
	}
	
	public Joueur(int id,String nom,int score ) {
		this.nom = nom;
		this.id = id;
		this.score = score;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "("+nom +"\n "+ " identifiant: "+id+" \nScore: "+score+")\n";
	}
	public int ChoisierCoup() {
		System.out.print("Choisir une colonne: ");
		Scanner clavier = new Scanner(System.in);
		int numColonne=clavier.nextInt();
		return numColonne-1;
		/*int numColonne=(int)(Math.random()*7);
		return numColonne;*/		
	}
	@Override
	public int compareTo(Joueur o) {
		if(this.score>o.score)
			return 1;
		else
			if(this.score<o.score)
				return -1;	
		return 0;
	}	
	
}
