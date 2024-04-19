package com.example.Model;

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
		String serveurBD = "jdbc:mysql://127.0.0.1:3306/pussance4?autoReconnect=true&useSSL=false";
		String login="amine";
		String motPasse="amine3349E";
		Connection conn;
		Statement ps;
		ResultSet rs;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(serveurBD, login, motPasse);
			String requete = "select * from joueur ";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				list.add(new Joueur((int)rs.getLong("id"),rs.getString("Nom"),(int)rs.getLong("score"))) ;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Execption");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
