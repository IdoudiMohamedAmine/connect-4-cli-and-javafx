package com.example.DAO;

import com.example.Model.Coup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOCoup extends DAO<Coup> {
    @Override
    public List<Coup> findAll() {
        List<Coup> listCoup = new ArrayList<>();
        Statement ps;
        ResultSet rs;
        try {
            String requete = "select * from Coup ";
            ps = conn.createStatement();
            rs = ps.executeQuery(requete);
            while (rs.next()) {
                listCoup.add(new Coup(rs.getInt("id"), rs.getInt("numColonneJ1"), rs.getInt("numColonneJ2")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return listCoup;
    }

    @Override
    public Coup findById(Coup id) {
        Coup coup = null;
        ResultSet rs;
        try{
            String requete = "select * from Coup where id=? ";
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, "" + id.getId());
            rs = ps.executeQuery(requete);
            coup = new Coup(rs.getInt("id"), rs.getInt("numColonneJ1"), rs.getInt("numColonneJ2"));
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
        return coup;
    }

    @Override
    public Coup create(Coup coup) {
        Coup c = null;
        ResultSet rs;
        try{
            String requete = "insert into Coup (numColonneJ1, numColonneJ2) values (?, ?)";
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, coup.getCoupJ1());
            ps.setInt(2, coup.getCoupJ2());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c = new Coup(rs.getInt(1), coup.getCoupJ1(), coup.getCoupJ2());
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Execption");

        }
        return c;
    }

    @Override
    public void delete(Coup coup) {
        ResultSet rs;
        try {
            String requete = "delete from Coup where id=?";
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, coup.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
    }

    @Override
    public void update(Coup coup) {
        ResultSet rs;
        try{
            String requete = "update Coup set numColonneJ1=?, numColonneJ2=? where id=?";
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, coup.getCoupJ1());
            ps.setInt(2, coup.getCoupJ2());
            ps.setInt(3, coup.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Execption");
        }
    }
}
