package com.isep.rpg.controllers;

import com.isep.rpg.Combattant.ActionType;
import com.isep.rpg.Combattant.Combattant;
import com.isep.rpg.Combattant.Hero;
import com.isep.rpg.GuiParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ChoseActionController extends Controller{

    @FXML
    private Button attackButton;

    @FXML
    private Button objectButton;

    @FXML
    private Label label;

    @FXML
    private Label label2;

    @Override
    public void init(GuiParser parser) {
        this.attackButton.setOnAction(event -> {
            parser.getGame().executeAction(ActionType.ATTACK);
        });

        this.objectButton.setOnAction(event -> {
            Hero h = (Hero) parser.getGame().getActiveCombattant();
            if(h.getConsumable().size()>0){

                parser.getGame().executeAction(ActionType.OBJECT);

            }else {
                label2.setText("You don't have any object !");
            }

        });

    }

    public void setLabel(Combattant combattant) {
        label.setText("Please chose the action you want "+ combattant.getName()+" to perform");

    }
}
