package com.example.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppPuissance {
	static int nbChoix = 8;

	public static void main(String[] args) {
		GestionJoueur gestionJoueur = new GestionJoueur();
		List<Joueur>res;
		int choix;
		while ((choix = choix()) != nbChoix) {
			switch (choix) {
			case 1:
				lancerPartie();
				break;
			case 2:
				gestionJoueur.listJoueurTrieByScore();
				System.out.println(gestionJoueur.toString());
				break;
			case 3:
				gestionJoueur.listJoueurTrieByNom();
				System.out.println(gestionJoueur.toString());
				break;
			case 4:
				gestionJoueur.listJoueurTrieById();
				System.out.println(gestionJoueur.toString());
				break;

			case 5:
				res=gestionJoueur.listFiltreByNom("0");
				System.out.println(res);
				break;
			case 6:
				res=gestionJoueur.listFiltreByScore(20);
				System.out.println(res);
				break;
			case 7:
				res=gestionJoueur.listFiltreByNomScore("03",20);
				System.out.println(res);
				break;
			}
		}
	}

	public static void lancerPartie() {
		Joueur j1 = new Joueur(1, "ali", 0);
		Joueur j2 = new Joueur(2, "sonia", 0);
		boolean fin = false;
		Partie partieJeu = new Partie(j1, j2);
		partieJeu.lancerPartie();
	}

	public static int choix() {
		int choix = -1;
		Scanner clavier = new Scanner(System.in);
		while (choix < 0 || choix > nbChoix) {
			menu();
			choix = clavier.nextInt();
		}
		return choix;
	}

	public static void menu() {
		System.out.println("**** Menu ****");
		System.out.println("1. Lancer partie");
		System.out.println("2. list Joueur Trie By Score");
		System.out.println("3. list Joueur Trie By Nom");
		System.out.println("4. list Joueur Trie By Id");
		System.out.println("5. Filtre des joueurs selon nom");
		System.out.println("6. Filtre des joueurs selon score");
		System.out.println("7. Filtre des joueurs selon nom et score");
		System.out.println("8. Quitter");
	}

	public static <T> List<T> verifierSi(List<T> source, CritereSelection<T> critere) {
		List<T> l = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			if (critere.verifier(source.get(i))) {
				l.add(source.get(i));
			}
		}
		return l;
	}

}
