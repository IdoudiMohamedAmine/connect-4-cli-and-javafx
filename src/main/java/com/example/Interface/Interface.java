package com.example.Interface;

import com.example.Model.Joueur;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class Interface {
    private Label label;
    private GridPane buttons = new GridPane(7, 6);
    private Button[][] button = new Button[6][7];
    private Menu partie = new Menu("Partie");
    private MenuItem lancer = new MenuItem("lancer");
    private Menu gestion = new Menu("gestion");
    private MenuItem ListeJ = new MenuItem("liste Joueur");
    private MenuItem maxJ = new MenuItem("max Joueur");
    private MenuBar menu = new MenuBar(partie, gestion);
    private TableView<Joueur> tableView = new TableView<>();
    private TableColumn<Joueur, Integer> id = new TableColumn<>("id");
    private TableColumn<Joueur, String> nom = new TableColumn<>("nom");
    private TableColumn<Joueur, Integer> score = new TableColumn<>("Score");
    public MenuItem getLancer() {
        return lancer;
    }
    public MenuItem getListeJ(){
        return ListeJ;
    }
    public MenuItem getMaxJ(){
        return maxJ;
    }
    public TableView<Joueur> getTableView(List<Joueur> lj) {
        this.id.setMinWidth(100);
        this.nom.setMinWidth(100);
        this.score.setMinWidth(100);

        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.score.setCellValueFactory(new PropertyValueFactory<>("score"));

        this.tableView.getColumns().addAll(id, nom, score);
        this.tableView.setItems(FXCollections.observableList(lj));
        return this.tableView;
    }
    public void setTop() {
        this.partie.getItems().add(this.lancer);
        this.gestion.getItems().add(this.ListeJ);
        this.gestion.getItems().add(this.maxJ);
    }

    public Interface() {
        this.SetGrid();
        this.setTop();
    }

    public void SetGrid() {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 7; i++) {
                Button b = new Button("-");
                b.setStyle("-fx-background-radius: 150em;"
                        + "-fx-min-width: 50px;"
                        + "-fx-min-height: 50px;"
                        + "-fx-max-width: 50px;"
                        + "-fx-max-height: 50px;"
                );
                this.buttons.add(b, i, 5 - j);
                this.button[j][i] = b;
            }
        }
        this.buttons.setAlignment(Pos.CENTER);
    }

    public Button getButton(int j, int i) {
        return this.button[j][i];
    }

    public GridPane getCenter() {
        return this.buttons;
    }

    public MenuBar getTop() {
        return this.menu;
    }

    public Label getLeft(Joueur j) {
        return new Label("Joueur 1: \n" + j.toString());
    }

    public Label getRight(Joueur j) {
        return new Label("Joueur 2: \n" + j.toString());
    }

    public void setLabel(String s) {
        this.label.setText(s);
    }

    public void setCoup(int l, int j, Joueur j1) {
        this.button[l][j].setText("" + j1.getId());
    }
}
