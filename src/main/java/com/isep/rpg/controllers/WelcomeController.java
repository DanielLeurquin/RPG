package com.isep.rpg.controllers;

import com.isep.rpg.GuiParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class WelcomeController extends Controller{
    @FXML
    private Button button;

    public void init(GuiParser parser){

        button.setOnAction(event -> {

            parser.askNumberHero();

        });
    }



}
