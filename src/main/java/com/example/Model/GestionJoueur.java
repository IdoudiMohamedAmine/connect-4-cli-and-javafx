package com.example.Model;

import com.example.DAO.DAOJoueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestionJoueur {
	List<Joueur> list=new ArrayList<>();
	public GestionJoueur() {
		remplirList();
	}
	public void remplirList(){
		DAOJoueur DAOjoueur=new DAOJoueur();
		list=DAOjoueur.findAll();
	}
	public List<Joueur> listJoueurTrieByScore(){
		Collections.sort(list);
		return list;
	}
	public List<Joueur> listJoueurTrieByNom(){
		Collections.sort(list, new Comparator<Joueur>() {
			@Override
			public int compare(Joueur o1, Joueur o2) {
				return(o1.getNom().compareTo(o2.getNom())*-1);
			}			
		});
		return list;
	}
	public List<Joueur> listJoueurTrieById(){
		Collections.sort(list, (o1, o2) -> {
			if(o1.getId()>o2.getId())
				return 1;
			else
				if(o1.getId()<o2.getId())
					return -1;	
			return 0; 
	      });
		return list;
	}
	public List<Joueur> listFiltreByNom(String nomRecherche){ 
		List<Joueur> resultat = list.stream()
			    .filter(joueur -> joueur.getNom().contains(nomRecherche))
			    .collect(Collectors.toList());
		
		return resultat;
	}
	public List<Joueur> listFiltreByScore(int scoreMin){ 
		List<Joueur> resultat = list.stream()
			    .filter(joueur -> joueur.getScore() > scoreMin)
			    .collect(Collectors.toList());
		
		return resultat;
	}
	public List<Joueur> listFiltreByNomScore(String nomRecherche,int scoreMin){ 
		List<Joueur> resultat;
		resultat=AppPuissance.verifierSi(this.list,j ->{
			Joueur jj=(Joueur)j;
			if(jj.getNom().contains(nomRecherche) && 
			  jj.getScore() > scoreMin)
			  return true;
			return false;
		  });		
		return resultat;
	}
	
	//Getter et setter
	public List<Joueur> getList() {
		return list;
	}
	public void setList(List<Joueur> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "GestionJoueur [list=" + list + "]";
	}
   	
	
	

}
