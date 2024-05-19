package com.example.controller;

import com.example.DAO.DAOJoueur;
import com.example.DAO.DAOPartie;
import com.example.Interface.Interface;
import com.example.Model.CoupException;
import com.example.Model.GestionJoueur;
import com.example.Model.Joueur;
import com.example.Model.Partie;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        this.root.setCenter(anInterface.getStartGame());
        MenuItem listj=anInterface.getListeJ();
        MenuItem max=anInterface.getMaxJ();
        MenuItem lance=anInterface.getLancer();
        MenuItem exportp=anInterface.getExportP();
        MenuItem importP=anInterface.getImportP();
        MenuItem listep=anInterface.getListep();
        TableView Tpartie=anInterface.getPartie(new DAOPartie().findAll());
        listep.setOnAction(event ->{this.root.setCenter(Tpartie);});
        lance.setOnAction(event ->{gameControlleur();});
        exportp.setOnAction(event->{
            export(anInterface.getexport(),anInterface.getExportpb(),anInterface.getIdp());
        });
        TableView T=anInterface.getTableView(new DAOJoueur().findAll());
        listj.setOnAction(event ->{listejoueur(T);});
        List<Joueur> l= new ArrayList<>();
        l.add(new DAOJoueur().findAll().stream().max((a1, a2)->{return a1.getScore()-a2.getScore();}).get());
        TableView m=anInterface.getMaxPlayer(l);
        max.setOnAction(event ->{maxJoueur(m);});
    }
    public void maxJoueur(TableView<Joueur> m){
        this.root.setCenter(m);
        this.root.setRight(null);
        this.root.setLeft(null);
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
        Partie p=new Partie();
        this.root.setTop(anInterface.getTop());
        this.root.setCenter(anInterface.SetGrid());
        this.root.setLeft(anInterface.getLeft(p.getJ1()));
        this.root.setRight(anInterface.getRight(p.getJ2()));
        gestionAction(p,p.getJ1(),p.getJ2());
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
        anInterface.setCoup(l,j);
        if(wincheck){
            //System.out.println(p.getJoueurCourant().getNom()+" ganient ");
            p.getJoueurCourant().incrementerScore();
            this.winScreen(p.getJoueurCourant());
        }
        if(j1==p.getJoueurCourant()){
            p.modifieRole();
            setCouleurButton(5 - l, j, "blue");
        }
        else{
            p.modifieRole();
            setCouleurButton(5 - l, j, "green");
        }
    }
    public void setCouleurButton(int lig,int col,String color){
        anInterface.getButton(5-lig,col).setStyle(
                "-fx-background-radius: 150em;"
                        + "-fx-min-width: 50px;"
                        + "-fx-min-height: 50px;"
                        + "-fx-max-width: 50px;"
                        + "-fx-max-height: 50px;"+
                        "-fx-background-color:"+color+";");
    }
    public void winScreen(Joueur j){
        this.root.setCenter(null);
        this.root.setLeft(null);
        this.root.setRight(null);
        this.root.setCenter(new Label(j.getNom()+"  is the winner"));
        j.incrementerScore();
    }
    public void export(GridPane g,Button b,TextField t)  {
        root.setCenter(g);
        this.root.setLeft(null);
        this.root.setRight(null);
        DAOPartie pp=new DAOPartie();
        Partie pf=new Partie();
        b.setOnAction(evnt->{
            try{
                String idp=t.getText();
                pf.setId(Integer.parseInt(idp));
                FileWriter f=new FileWriter(idp+".txt");
                Partie p= pp.findById(pf);
                f.write(p.getId()+"\n");
                f.write(p.getJ1().getId()+"\n");
                f.write(p.getJ2().getId()+"\n");
                f.write(p.getJ1().getScore()+"\n");
                f.write(p.getJ2().getScore()+"\n");
                f.write(p.getLisCoupJ().toString()+"\n");
                f.close();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        });
    }
}
