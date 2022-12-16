package com.example.rpg.controllers;

import com.example.rpg.Combattant.Combattant;
import com.example.rpg.Combattant.Enemy;
import com.example.rpg.GuiParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AnnonceFightController extends Controller{
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Enemy,String> nameColumn;

    @FXML
    private TableColumn<Enemy,String> pvColumn;

    @FXML
    private TableColumn<Enemy,String> pmColumn;

    @FXML
    private TableColumn<Enemy,String> attackColumn;

    @FXML
    private TableColumn<Enemy,String> defenseColumn;

    @FXML
    private Button button;

    @FXML
    private Label levelLabel;

    private GuiParser parser;

    @Override
    public void init(GuiParser parser) {
        this.parser = parser;
        levelLabel.setText("Level : "+parser.getGame().getLevel());

        nameColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("name"));
        pvColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("pv"));
        pmColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("pm"));
        attackColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("strength"));
        defenseColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("defense"));

        tableView.setItems(this.getList());

        button.setOnAction(event -> {
            this.parser.getGame().startTurn();
        });


    }

    public ObservableList<Enemy> getList(){
        ObservableList<Enemy> list = FXCollections.observableArrayList();
        for(Combattant combattant : this.parser.getGame().getEnemyTeam().getTeamList()){
            list.add((Enemy) combattant);
        }
        return list;
    }

}
