package com.example.controller;


import com.example.Interface.AfficheJoueur;
import com.example.Interface.Interface;
import com.example.Model.GestionJoueur;
import javafx.scene.layout.BorderPane;

public class ControllerListeJoueur {
    private AfficheJoueur inter=new AfficheJoueur();
    private GestionJoueur lj=new GestionJoueur();
    private BorderPane root=new BorderPane();

    public ControllerListeJoueur() {
        this.root.setCenter(inter.getCenter(lj.getList()));
    }

    public BorderPane getRoot() {
        return root;
    }
}
