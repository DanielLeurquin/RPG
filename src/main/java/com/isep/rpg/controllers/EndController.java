package com.isep.rpg.controllers;

import com.isep.rpg.GuiParser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndController extends Controller{

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @Override
    public void init(GuiParser parser) {
        label2.setText("Thank you for playing Mini RPG Lite 3000 by Daniel");
    }

    public void win(){
        label1.setText("Congratulations ! You won !");

    }

    public void lose(){
        label1.setText("Oh no ! That is Game Over !");
    }

}
