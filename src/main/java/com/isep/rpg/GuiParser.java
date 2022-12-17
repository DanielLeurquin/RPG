package com.isep.rpg;

import com.isep.rpg.Combattant.*;
import com.isep.rpg.Item.Consumable;
import com.isep.rpg.controllers.*;
import com.isep.rpg.Combattant.*;
import com.isep.rpg.controllers.*;

import java.io.IOException;

public class GuiParser implements InputParser {
    private Game game;
    private HelloApplication app;

    private Controller controller;

    public GuiParser(Game game, HelloApplication app){
        this.game = game;
        this.app = app;
    }

    //self-explanatory
    @Override
    public void welcome() {
        this.controller = this.app.getFxmlLoader().getController();
        this.controller.init(this);
    }

    //load nbHero scene
    @Override
    public void askNumberHero() {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/nbHero.fxml", this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //load createHero scene
    @Override
    public void askHeroType(int nbHero) {
        try {
            CreateHeroController.heroCompteur = nbHero;
            this.controller = this.app.changeScene("/com/isep/rpg/createHeros.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //load annonce fight scene
    @Override
    public void annonceFight() {
        try{
            this.app.changeScene("/com/isep/rpg/annonceFightView.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //load damageInformation scene in attack mode
    @Override
    public void displayDamage(Combattant attacker, Combattant target, int damage) {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/damageInformation.fxml",this);
            DamageInformationController c = (DamageInformationController)this.controller;
            c.setLabelText(attacker,target,damage);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //load damageInformation scene in fail mode
    @Override
    public void displayFail(Combattant attacker) {
        Hero h = (Hero) attacker;
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/damageInformation.fxml",this);
            DamageInformationController c = (DamageInformationController)this.controller;

            if(attacker.getLastDamage()==0){
                c.setFailMessage("Oh no the enemy just dodged your attack !");
            }else {
                c.setFailMessage(h.getFailMessage());
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //load damageInformation scene in object mode
    @Override
    public void consumableUse(Consumable c, Hero h) {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/damageInformation.fxml",this);
            DamageInformationController controller =(DamageInformationController) this.controller;
            controller.setLabelObject(c);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //load level win scene
    @Override
    public void levelWin() {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/levelWinView.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //load end scene in win mode
    @Override
    public void win() {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/endView.fxml",this);
            EndController controller = (EndController) this.controller;
            controller.win();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //load end scene in win mode
    @Override
    public void lose() {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/endView.fxml",this);
            EndController controller = (EndController) this.controller;
            controller.lose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //load choose object scene
    @Override
    public void choseConsumable(Hero hero) {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/choseObjectView.fxml",this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //load choose target scene
    @Override
    public void choseTarget(Team enemyTeam) {
        try {
            this.controller = this.app.changeScene("/com/isep/rpg/choseTargetView.fxml",this);
            ChoseTargetController c = (ChoseTargetController)this.controller;
            c.fillList(enemyTeam);

        }catch (IOException e){
            e.printStackTrace();
        }



    }

    //load choose action scene with combattant name
    @Override
    public void choseAction(Combattant combattant) {
        if(combattant instanceof Hero){
            try {
                this.controller = this.app.changeScene("/com/isep/rpg/choseActionView.fxml",this);
                ChoseActionController c = (ChoseActionController) this.controller;
                c.setLabel(combattant);

            }catch (IOException e){
                e.printStackTrace();
            }


        }else {
            this.game.executeAction(ActionType.ATTACK);

        }


    }

    //change create controller to hide most widgets
    @Override
    public void displayTeam(Team equipe) {
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
