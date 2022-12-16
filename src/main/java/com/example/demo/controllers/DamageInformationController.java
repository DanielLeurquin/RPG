package com.example.demo.controllers;

import com.example.demo.Combattant.Combattant;
import com.example.demo.Combattant.Healer;
import com.example.demo.Combattant.Hero;
import com.example.demo.Combattant.Team;
import com.example.demo.GuiParser;
import com.example.demo.Item.Consumable;
import com.example.demo.Item.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DamageInformationController extends Controller{

    @FXML
    private TableView<Combattant> heroTableView;

    @FXML
    private TableView<Combattant> enemyTableView;

    @FXML
    private TableColumn<Combattant,String> heroNameColumn;

    @FXML
    private TableColumn<Combattant,String> enemyNameColumn;

    @FXML
    private TableColumn<Combattant,String> heroPvColumn;

    @FXML
    private TableColumn<Combattant,String> enemyPvColumn;
    @FXML
    private Label label;
    @FXML
    private Button button;

    private GuiParser parser;


    @Override
    public void init(GuiParser parser) {
        this.parser = parser;
        heroNameColumn.setCellValueFactory(new PropertyValueFactory<Combattant, String>("name"));
        heroPvColumn.setCellValueFactory(new PropertyValueFactory<Combattant, String>("pv"));

        enemyNameColumn.setCellValueFactory(new PropertyValueFactory<Combattant, String>("name"));
        enemyPvColumn.setCellValueFactory(new PropertyValueFactory<Combattant, String>("pv"));

        heroTableView.setItems(this.getList(parser.getGame().getHeroTeam()));
        enemyTableView.setItems(this.getList(parser.getGame().getEnemyTeam()));

        button.setOnAction(event -> {
            parser.getGame().endTurn();
        });




    }

    public ObservableList<Combattant> getList(Team team){
        ObservableList<Combattant> list = FXCollections.observableArrayList();
        for (Combattant c : team.getTeamList()){
            list.add(c);
        }
        return list;
    }

    public void setLabelText(Combattant attacker, Combattant target, int damage){
        if(attacker instanceof Healer){
            label.setText(attacker.getName()+" healed "+target.getName()+" of "+damage+
                    " life points");
        }else{
            label.setText(attacker.getName()+" dealed "+damage+" damage to "+
                    target.getName());
        }
    }

    public void setLabelObject(Consumable consumable){
        if(consumable instanceof Food){
            label.setText(this.parser.getGame().getActiveCombattant().getName()+
                    " healed his PV of "+consumable.getHeal()+" points ");
        }else {
            label.setText(this.parser.getGame().getActiveCombattant().getName()+
                    " healed his PM of "+consumable.getHeal()+" points ");
        }

    }

    public void setFailMessage(String text){
        label.setText(text);
    }

}
