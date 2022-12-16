package com.isep.rpg;

import com.isep.rpg.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {

    FXMLLoader fxmlLoader;
    Stage stage;



    public static void main(String[] args) {
        InputParser inputParser;
        Game game;
        int inputType = chooseParser();
        if(inputType==1){
            ;
            game = new Game();
            inputParser = new ConsoleParser(game);
            game.setInputParser(inputParser);
            game.start();
        }
        else{

            launch();
        }

    }

    //ask to play in console or GUI mode
    public static int chooseParser(){
        System.out.println("Console mode (1)");
        System.out.println("GUI mode     (2)\n");
        Scanner sc = new Scanner(System.in);
        int cType = ConsoleParser.scanForNumber(sc);
        while(cType!=1 && cType!=2){
            System.out.println("Enter a valid number (1) or (2)");
            cType = ConsoleParser.scanForNumber(sc);
        }
        System.out.println("--------------------\n\n");
        return cType;
    }

    //first method called for gui mode,
    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        this.fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);

        this.stage.setTitle("RPG !");
        this.stage.setScene(scene);
        this.stage.show();
        Game game = new Game(this);
        game.setInputParser(new GuiParser(game, this));
        game.start();

    }


    //allows to change the scene with the fxmlFile name given in argument
    public Controller changeScene(String fxmlFile, GuiParser parser) throws IOException {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = this.fxmlLoader.load();
        Controller controller = this.fxmlLoader.getController();

        controller.init(parser);

        Scene scene = new Scene(root, 900, 700);
        this.stage.setScene(scene);
        this.stage.show();
        return controller;

    }

    public FXMLLoader getFxmlLoader() {

        return this.fxmlLoader;
    }
}