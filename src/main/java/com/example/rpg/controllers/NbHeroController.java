package com.example.rpg.controllers;

import com.example.rpg.ConsoleParser;
import com.example.rpg.GuiParser;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NbHeroController extends Controller{
    @FXML
    Button button;

    @FXML
    TextField textField;

    @FXML
    Label label;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private GuiParser parser;

    public void init(GuiParser parser){
        this.parser = parser;
        button.setOnAction(event -> {

            String text = textField.getText();
            if(ConsoleParser.onlyNumber(text)){
                int num = Integer.parseInt(text);
                if(num>=1 && num <=10){
                    this.parser.getGame().setNbHero(num);
                    this.parser.askHeroType(1);
                }else {
                    label.setText("Please enter a number between 1 and 10");
                }
            }else {
                label.setText("Please enter a number");

            }
        });
    }

}
