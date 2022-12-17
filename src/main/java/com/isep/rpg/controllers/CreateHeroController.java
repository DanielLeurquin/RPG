package com.isep.rpg.controllers;

import com.isep.rpg.Combattant.*;
import com.isep.rpg.Combattant.Combattant;
import com.isep.rpg.Combattant.Hero;
import com.isep.rpg.Combattant.HeroType;
import com.isep.rpg.Combattant.Team;
import com.isep.rpg.Game;
import com.isep.rpg.GuiParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateHeroController extends Controller{
    ObservableList<HeroType> heroType = FXCollections.observableArrayList(HeroType.WARRIOR,
            HeroType.HUNTER,HeroType.MAGE,HeroType.HEALER,HeroType.GAMBLER);

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private Label label;

    @FXML
    private Button button;

    @FXML
    private TextField textField;

    public static int heroCompteur;

    private GuiParser parser;

    @FXML
    private TableView<Hero> tableView;

    @FXML
    private Label classLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TableColumn<Hero, String > heroNameColumn;
    @FXML
    private TableColumn<Hero, String> classColumn;
    @FXML
    private TableColumn<Hero, String> pvColumn;
    @FXML
    private TableColumn<Hero, String> pmColumn;
    @FXML
    private TableColumn<Hero, String> attackColumn;
    @FXML
    private TableColumn<Hero, String> defenseColumn;
    @FXML
    private TableColumn<Hero, String> weaponColumn;
    @FXML
    private TableColumn<Hero, String> foodColumn;
    @FXML
    private TableColumn<Hero, String> potionColumn;

    @Override
    public void init(GuiParser parser) {
        this.parser = parser;
        this.fillTable();


        choiceBox.setItems(heroType);
        this.changeLabelNumber(heroCompteur);
        button.setOnAction(event -> {

            if(choiceBox.getValue()!=null && !textField.getText().equals("")){
                HeroType heroType = (HeroType) choiceBox.getValue();
                this.parser.getGame().generateHero(textField.getText(),heroType);
            }

        });



    }

    public ObservableList<Hero> getList(){
        Team team = this.parser.getGame().getHeroTeam();
        ObservableList<Hero> list = FXCollections.observableArrayList();
        for(Combattant combattant: team.getTeamList()){
            list.add((Hero)combattant);
        }
        return list;
    }

    public void changeLabelNumber(int num){
        label.setText("Creation of hero number "+num);
    }

    public void fillTable(){
        heroNameColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        classColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("heroType"));
        pvColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("pv"));
        pmColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("pm"));
        attackColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("strength"));
        defenseColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("defense"));
        weaponColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("weaponName"));
        foodColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("nbFood"));
        potionColumn.setCellValueFactory(new PropertyValueFactory<Hero, String>("nbPotion"));

        tableView.setItems(this.getList());

    }

    public void displayTeam(){
        this.fillTable();
        nameLabel.setVisible(false);
        textField.setVisible(false);
        textField.setText("");
        classLabel.setVisible(false);
        choiceBox.setVisible(false);
        button.setText("Next");
        label.setText("Heros of your team");
        button.setOnAction(event -> {
            Game game = this.parser.getGame();
            game.generateEnemyTeam(game.getHeroTeam());
        });
    }


}
