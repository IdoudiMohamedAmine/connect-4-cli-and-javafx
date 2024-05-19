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

import java.security.PrivateKey;
import java.util.List;

public class Interface {
    private Label label;
    private Label startGame = new Label("Hello there press partie > lancer in the menu to start the game :)");
    private GridPane buttons = new GridPane(7, 6);
    private Button[][] button = new Button[6][7];
    private TextField idp = new TextField();
    private Button exportpb = new Button("export");
    private Button importpb = new Button("import");
    private GridPane exportG=new GridPane(1,2) ;
    private GridPane importG=new GridPane(1,2) ;

    private Menu partie = new Menu("Partie");
    private MenuItem lancer = new MenuItem("lancer");
    private Menu gestion = new Menu("gestion");
    private MenuItem ListeJ = new MenuItem("liste Joueur");
    private MenuItem maxJ = new MenuItem("max Joueur");
    private MenuItem exportP = new MenuItem("export partie");
    private MenuItem importP = new MenuItem("import partie");
    private Menu files = new Menu("Files");
    private MenuBar menu = new MenuBar(partie, gestion,files);

    private TableView<Joueur> maxPlayer = new TableView<>();
    private TableColumn<Joueur, Integer> idMaxPlayer = new TableColumn<>("id");
    private TableColumn<Joueur, String> nomMaxPlayer = new TableColumn<>("nom");
    private TableColumn<Joueur, Integer> scoreMaxPlayer = new TableColumn<>("Score");


    private TableView<Joueur> tableView = new TableView<>();
    private TableColumn<Joueur, Integer> id = new TableColumn<>("id");
    private TableColumn<Joueur, String> nom = new TableColumn<>("nom");
    private TableColumn<Joueur, Integer> score = new TableColumn<>("Score");


    public TableView<Joueur> getMaxPlayer(List<Joueur> joueurs) {
        this.idMaxPlayer.setMinWidth(100);
        this.nomMaxPlayer.setMinWidth(100);
        this.scoreMaxPlayer.setMinWidth(100);

        this.idMaxPlayer.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nomMaxPlayer.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.scoreMaxPlayer.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.maxPlayer.getColumns().addAll(idMaxPlayer, nomMaxPlayer, scoreMaxPlayer);
        this.maxPlayer.setItems(FXCollections.observableList(joueurs));
        return maxPlayer;
    }

    public MenuItem getLancer() {
        return lancer;
    }

    public Label getStartGame() {
        return startGame;
    }
    public Button getExportpb(){
        return exportpb;
    }
    public Button getImportpb(){
        return importpb;
    }
    public GridPane getexport() {
        this.exportG.add(this.idp,0,0);
        this.exportG.add(this.exportpb,0,1);
        return exportG;
    }
    public TextField getIdp() {
        return idp;
    }
    public GridPane getimport() {
        this.importG.add(this.idp,0,0);
        this.importG.add(this.importpb,0,1);
        return importG;
    }
    public MenuItem getListeJ() {
        return ListeJ;
    }

    public MenuItem getMaxJ() {
        return maxJ;
    }
    public MenuItem getExportP() {
        return exportP;
    }
    public MenuItem getImportP() {
        return importP;
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
        this.files.getItems().add(this.exportP);
        this.files.getItems().add(this.importP);
    }

    public Interface() {
        this.SetGrid();
        this.setTop();
    }

    public GridPane SetGrid() {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 7; i++) {
                Button b = new Button(" ");
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
        return this.buttons;
    }

    public Button getButton(int j, int i) {
        return this.button[j][i];
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

    public void setCoup(int l, int j) {
        this.button[l][j].setText(" ");
    }
}
