package com.example.DAO;

import com.example.Model.Joueur;
import com.example.Model.Partie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DAOPartie extends DAO<Partie> {
    DAOJoueur j=new DAOJoueur();
    @Override
    public List<Partie> findAll() {
        List<Partie> parties = new ArrayList<>();
        Statement ps;
        ResultSet rs;
        try {
            String requete = "select * from Partie";
            ps = conn.createStatement();
            rs = ps.executeQuery(requete);
            while (rs.next()) {
                parties.add(new Partie(rs.getInt("idPartie"),j.findById(new Joueur(rs.getInt("IdJoueur1"),null,0)),j.findById(new Joueur(rs.getInt("IdJoueur2"),null,0))));
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return parties;
    }
    @Override
    public Partie findById(Partie id) {
        Partie partie = null;
        ResultSet rs;
        try {
            String requete = "select * from Partie where idPartie = ? ";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+id.getId());
            rs = ps.executeQuery(requete);
            partie=new Partie(rs.getInt("idPartie"),j.findById(new Joueur(rs.getInt("IdJoueur1"),null,0)),j.findById(new Joueur(rs.getInt("IdJoueur2"),null,0)));
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return partie;
    }

    @Override
    public Partie create(Partie partie) {
        Partie p = null;
        ResultSet rs;
        try {
            String requete = "INSERT INTO `Partie`(`IdJoueur1`, `IdJoueur2`, `ScoreJoueur1`, `ScoreJoueur2`) VALUES (?,?,?,?) ";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+partie.getJ1().getId());
            ps.setString(2,""+partie.getJ2().getId());
            ps.setString(3,""+partie.getJ1().getScore());
            ps.setString(4,""+partie.getJ2().getScore());
            rs = ps.executeQuery(requete);
            p=new Partie(rs.getInt("idPartie"),j.findById(new Joueur(rs.getInt("IdJoueur1"),null,0)),j.findById(new Joueur(rs.getInt("IdJoueur2"),null,0)));
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return p;
    }

    @Override
    public void delete(Partie partie) {
        ResultSet rs;
        try {
            String requete = "DELETE FROM `Partie` WHERE idPartie=? ";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+partie.getId());
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
    public void update(Partie partie) {
        ResultSet rs;
        try {
            String requete = "UPDATE `Partie` SET `idPartie`=?,`IdJoueur1`=?,`IdJoueur2`=?,`ScoreJoueur1`=?,`ScoreJoueur2`=? WHERE idPartie=?";
            PreparedStatement ps=conn.prepareStatement(requete);
            ps.setString(1,""+partie.getId());
            ps.setInt(2,partie.getJ1().getId());
            ps.setInt(3,partie.getJ2().getId());
            ps.setString(4,""+partie.getJ1().getScore());
            ps.setString(5,""+partie.getJ2().getScore());
            ps.setInt(6,partie.getId());
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
