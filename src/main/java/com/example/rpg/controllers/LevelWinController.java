package com.example.rpg.controllers;

import com.example.rpg.GuiParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class LevelWinController extends Controller {

     @FXML
     private Label labelCongrat,labelLevel;

     @FXML
     private Button button;

    @Override
    public void init(GuiParser parser) {
        labelCongrat.setText("Congratulation you just won level "+(parser.getGame().getLevel()-1));
        labelLevel.setText("Now let's begin level "+parser.getGame().getLevel());
        button.setOnAction(event -> {
            try {
                parser.setController(parser.getApp().
                        changeScene("/com/example/rpg/createHeros.fxml",parser));

            }catch (IOException e){
                e.printStackTrace();
            }

            parser.displayEquipe(parser.getGame().getHeroTeam());
        });

    }
}