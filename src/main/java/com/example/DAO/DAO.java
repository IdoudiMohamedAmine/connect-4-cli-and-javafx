package com.example.DAO;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO <T>{
    Connection conn;
    public abstract List<T> findAll();
    public abstract T  findById(T id);
    public abstract T  create(T t);
    public abstract void delete(T t);
    public abstract void update(T t);

    public DAO() {
        conn=connect("amine","amine3349E","jdbc:mysql://127.0.0.1:3306/pussance4?autoReconnect=true&useSSL=false");
    }

    public  Connection connect(String login, String password, String serveur){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(serveur, login, password);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Execption");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }
}
