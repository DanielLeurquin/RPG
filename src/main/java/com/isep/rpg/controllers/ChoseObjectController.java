package com.isep.rpg.controllers;

import com.isep.rpg.Combattant.Hero;
import com.isep.rpg.Combattant.Hunter;
import com.isep.rpg.GuiParser;
import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.GoldenArrow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Arrays;


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

        ArrayList<Consumable> list_c = (ArrayList<Consumable>) h.getConsumable().clone();
        if(h instanceof Hunter){
            Hunter hunter = (Hunter) h;
            if(hunter.getArrows()>0){
                for(Consumable consumable : list_c){
                    if(consumable instanceof GoldenArrow){
                        list_c.remove(consumable);
                    }
                }
            }
        }


        for(Consumable c : list_c){
            list.add(c);
        }
        return list;
    }


}
