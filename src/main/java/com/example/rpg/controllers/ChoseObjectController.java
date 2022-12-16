package com.example.rpg.controllers;

import com.example.rpg.Combattant.Hero;
import com.example.rpg.GuiParser;
import com.example.rpg.Item.Consumable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ChoseObjectController extends Controller{

    @FXML
    private TableView<Consumable> tableView;

    @FXML
    private TableColumn<Consumable, String> objectColumn;

    @FXML
    private TableColumn<Consumable , String> valueColumn;

    @FXML
    private Button button;

    private GuiParser parser;

    @Override
    public void init(GuiParser parser) {
        this.parser = parser;
        valueColumn.setCellValueFactory(new PropertyValueFactory<Consumable, String>("heal"));
        objectColumn.setCellValueFactory(new PropertyValueFactory<Consumable, String>("name"));



        tableView.setItems(this.getList());

        button.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                parser.getGame().useConsumable(tableView.getSelectionModel().getSelectedItem());
            }
        });

    }

    public ObservableList<Consumable> getList(){
        ObservableList<Consumable> list = FXCollections.observableArrayList();
        Hero h = (Hero) this.parser.getGame().getActiveCombattant();
        for(Consumable c : h.getConsumable()){
            list.add(c);
        }
        return list;
    }


}
