package com.example.rpg;

import com.example.rpg.Combattant.*;
import com.example.rpg.Item.Consumable;
import com.example.rpg.controllers.*;

import java.io.IOException;

public class GuiParser implements InputParser {
    private Game game;
    private HelloApplication app;

    private Controller controller;

    public GuiParser(Game game, HelloApplication app){
        this.game = game;
        this.app = app;
    }

    @Override
    public void welcome() {
        this.controller = this.app.getFxmlLoader().getController();
        this.controller.init(this);
    }

    @Override
    public void askNumberHero() {
        try {
            this.controller = this.app.changeScene("/com/example/rpg/nbHero.fxml", this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void askHeroType(int nbHero) {
        try {
            CreateHeroController.heroCompteur = nbHero;
            this.controller = this.app.changeScene("/com/example/rpg/createHeros.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void askHeroName(HeroType heroType) {

    }

    @Override
    public void annonceFight() {
        try{
            this.app.changeScene("/com/example/rpg/annonceFightView.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void displayDamage(Combattant attacker, Combattant target, int damage) {
        try {
            this.controller = this.app.changeScene("/com/example/rpg/damageInformation.fxml",this);
            DamageInformationController c = (DamageInformationController)this.controller;
            c.setLabelText(attacker,target,damage);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void displayFail(Combattant attacker) {
        Hero h = (Hero) attacker;
        try {
            this.controller = this.app.changeScene("/com/example/rpg/damageInformation.fxml",this);
            DamageInformationController c = (DamageInformationController)this.controller;

            c.setFailMessage(h.getFailMessage());

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void consumableUse(Consumable c, Hero h) {
        try {
            this.controller = this.app.changeScene("/com/example/rpg/damageInformation.fxml",this);
            DamageInformationController controller =(DamageInformationController) this.controller;
            controller.setLabelObject(c);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void levelWin() {
        try {
            this.controller = this.app.changeScene("/com/example/rpg/levelWinView.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void win() {

    }

    @Override
    public void lose() {

    }

    @Override
    public void choseConsumable(Hero hero) {
        try {
            this.controller = this.app.changeScene("/com/example/rpg/choseObjectView.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void choseTarget(Team enemyTeam) {
        try {
            this.controller = this.app.changeScene("/com/example/rpg/choseTargetView.fxml",this);
            ChoseTargetController c = (ChoseTargetController)this.controller;
            c.fillList(enemyTeam);

        }catch (IOException e){
            e.printStackTrace();
        }



    }

    @Override
    public void choseAction(Combattant combattant) {
        if(combattant instanceof Hero){
            try {
                this.controller = this.app.changeScene("/com/example/rpg/choseActionView.fxml",this);
                ChoseActionController c = (ChoseActionController) this.controller;
                c.setLabel(combattant);

            }catch (IOException e){
                e.printStackTrace();
            }


        }else {
            this.game.executeAction(ActionType.ATTACK);

        }


    }

    @Override
    public void annonceAttack(Combattant combattant) {

    }

    @Override
    public void displayEquipe(Team equipe) {
        CreateHeroController c = (CreateHeroController)this.controller;
        c.displayTeam();
    }

    public Game getGame() {

        return game;
    }

    public HelloApplication getApp() {

        return app;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
