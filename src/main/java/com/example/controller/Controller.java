package com.example.controller;

import com.example.Interface.Interface;
import com.example.Model.CoupException;
import com.example.Model.GestionJoueur;
import com.example.Model.Joueur;
import com.example.Model.Partie;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Controller {
    private Interface anInterface=new Interface();
    private GestionJoueur lj=new GestionJoueur();
    private BorderPane root;
    public Controller()
    {
        root=new BorderPane();
        this.initialstartstate();

    }
    public void initialstartstate(){
        this.root.setTop(anInterface.getTop());
        MenuItem listj=anInterface.getListeJ();
        MenuItem max=anInterface.getMaxJ();
        MenuItem lance=anInterface.getLancer();
        lance.setOnAction(event ->{
            gameControlleur();
        });
        TableView T=anInterface.getTableView(this.lj.getList());
        listj.setOnAction(event ->{listejoueur(T);});
    }
    public BorderPane getBorderpane(){
        return root;
    }
    public void listejoueur(TableView T){
        this.root.setCenter(T);
        this.root.setLeft(null);
        this.root.setRight(null);
    }
    public void gameControlleur(){
        Joueur j1 = lj.getList().get(0);
        Joueur j2 = lj.getList().get(1);
        Partie p=new Partie(j1,j2);
        this.root.setTop(anInterface.getTop());
        this.root.setCenter(anInterface.getCenter());
        this.root.setLeft(anInterface.getLeft(j1));
        this.root.setRight(anInterface.getRight(j2));
        gestionAction(p,j1,j2);
    }
    public void gestionAction(Partie p,Joueur j1,Joueur j2){
        for (int j = 0; j <6; j++) {
            for (int i = 0; i<7; i++) {
                Button b=anInterface.getButton(j,i);
                final int jj=i;
                b.setOnAction(even ->{
                    try{
                        gestionClick(jj,p,j1);
                    }catch (CoupException e){
                        System.out.println(e.getMessage());
                    }
                });
            }

        }
    }
    public void gestionClick(int j,Partie p,Joueur j1)throws CoupException {
        int l =p.getPuissance().getLigneVideByColonne(j);
        p.getPuissance().setCoup(l,j,p.getJoueurCourant().getId());
        boolean wincheck=p.getPuissance().estGagnant(l,j,p.getJoueurCourant().getId());
        anInterface.setCoup(l,j,p.getJoueurCourant());

        if(wincheck){
            System.out.println(p.getJoueurCourant().getNom()+" ganient ");
        }
        if(j1==p.getJoueurCourant()){
            p.modifieRole();
        }
        else{
            p.modifieRole();
        }
    }
}
