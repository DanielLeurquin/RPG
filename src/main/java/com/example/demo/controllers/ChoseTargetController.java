package com.example.demo.controllers;

import com.example.demo.Combattant.Combattant;
import com.example.demo.Combattant.Enemy;
import com.example.demo.Combattant.Team;
import com.example.demo.GuiParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChoseTargetController extends Controller{

    @FXML
    private Button button;

    @FXML
    private TableView<Combattant> tableView;

    @FXML
    private TableColumn<Combattant,String> nameColumn;

    @FXML
    private TableColumn<Combattant,String> pvColumn;

    @FXML
    private Label label;

    private ObservableList<Combattant> list = FXCollections.observableArrayList();

    private Team team;

    @Override
    public void init(GuiParser parser) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Combattant, String>("name"));
        pvColumn.setCellValueFactory(new PropertyValueFactory<Combattant, String>("pv"));

        button.setOnAction(event -> {
            if(team.getTeamList().size()>0){
                if(tableView.getSelectionModel().getSelectedItem()!=null){
                    parser.getGame().inflictDamage(parser.getGame().getActiveCombattant(),
                            tableView.getSelectionModel().getSelectedItem());
                }else {
                    label.setText("Please select a target in the list !");
                }


            }

        });

    }

    public void fillList(Team team){
        this.team = team;
        for(Combattant c : team.getAliveList()){
            this.list.add(c);
        }
        tableView.setItems(this.list);
    }


}
