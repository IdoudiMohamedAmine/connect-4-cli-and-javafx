package com.example.Interface;

import com.example.Model.Joueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AfficheJoueur {
    public TableView getCenter(List<Joueur> lj) {
        TableView<Joueur> tableView = new TableView<>();
        TableColumn<Joueur, Integer> id = new TableColumn<>("id");
        TableColumn<Joueur, String> nom = new TableColumn<>("nom");
        TableColumn<Joueur, Integer> score = new TableColumn<>("Score");
        id.setMinWidth(100);
        nom.setMinWidth(100);
        score.setMinWidth(100);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.getColumns().addAll(id, nom, score);
        tableView.setItems(FXCollections.observableList(lj));
        return tableView;
    }
}
