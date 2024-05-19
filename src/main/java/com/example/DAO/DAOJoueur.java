package com.example.DAO;

import com.example.Model.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOJoueur extends  DAO<Joueur>{
    @Override
    public List<Joueur> findAll() {
        List<Joueur> list = new ArrayList<>();
        Statement ps;
        ResultSet rs;
        try {
            String requete = "select * from joueur ";
            ps = conn.createStatement();
            rs = ps.executeQuery(requete);
            while (rs.next()) {
                list.add(new Joueur((int)rs.getLong("id"),rs.getString("Nom"),(int)rs.getLong("score"))) ;
            }
            rs.close();
            ps.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return list;
    }

    @Override
    public Joueur findById(Joueur id) {
        Joueur j=null;
        ResultSet rs;
        try {
            String requete = "select * from joueur where id=? ";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+id.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                j=new Joueur((int)rs.getLong("id"),rs.getString("nom"),(int) rs.getLong("score"));
            }
            rs.close();
            ps.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return j;
    }

    @Override
    public Joueur create(Joueur joueur) {
        Joueur j=null;
        ResultSet rs;
        try {
            String requete = "INSERT INTO `joueur`( `nom`, `score`) VALUES (?,?)";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,joueur.getNom());
            ps.setLong(2,joueur.getScore());
            rs = ps.executeQuery();
            j=new Joueur(joueur.getId(), joueur.getNom(), joueur.getScore());
            rs.close();
            ps.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return j;
    }

    @Override
    public void delete(Joueur joueur) {
        ResultSet rs;
        try {
            String requete = "DELETE FROM `joueur` WHERE id=? ";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+joueur.getId());
            rs = ps.executeQuery();
            rs.close();
            ps.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
    }

    @Override
    public void update(Joueur joueur) {
        ResultSet rs;
        try {
            String requete = "UPDATE `joueur` SET `id`=?,`nom`=?,`score`=? WHERE id=?";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+joueur.getId());
            ps.setString(2,joueur.getNom());
            ps.setLong(3,joueur.getScore());
            ps.setLong(4,joueur.getId());
            rs = ps.executeQuery();
            rs.close();
            ps.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
    }
    }