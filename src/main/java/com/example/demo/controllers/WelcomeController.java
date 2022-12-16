package com.example.demo.controllers;

import com.example.demo.GuiParser;
import com.example.demo.controllers.NbHeroController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;


public class WelcomeController extends Controller{
    @FXML
    private Button button;

    public void init(GuiParser parser){

        button.setOnAction(event -> {

            parser.askNumberHero();

        });
    }



}
