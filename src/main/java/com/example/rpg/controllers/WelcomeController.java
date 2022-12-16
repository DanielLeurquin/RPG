package com.example.rpg.controllers;

import com.example.rpg.GuiParser;
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
