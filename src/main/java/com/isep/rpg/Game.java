package com.isep.rpg;

import com.isep.rpg.Combattant.*;
import com.isep.rpg.Item.Consumable;

import java.util.ArrayList;

public class Game {
    public InputParser inputParser;
    private Team heroTeam;

    private Team enemyTeam;
    private HelloApplication app;

    private ArrayList<Combattant> initiative;

    private Combattant activeCombattant;

    private int nbHero;

    private int level = 1;

    //Gui mode contructor
    public Game(HelloApplication app){
        this.heroTeam = new Team();
        this.app = app;
    }

    //Console mode constructor
    public Game(){
        this.heroTeam = new Team();
    }

    public void setInputParser(InputParser inputParser){

        this.inputParser = inputParser;
    }


    public void start(){

        this.inputParser.welcome();

    }

    //create the right type of hero with given name and type
    //is sometimes called recursively
    public void generateHero(String name, HeroType heroType){
        if(heroType.equals(HeroType.WARRIOR)){
            new Warrior(name,this.heroTeam);
        }else if(heroType.equals(HeroType.HUNTER)){
            new Hunter(name,this.heroTeam);
        }else if(heroType.equals(HeroType.HEALER)){
            new Healer(name,this.heroTeam);
        }else if(heroType.equals(HeroType.MAGE)){
            new Mage(name,this.heroTeam);
        }else if(heroType.equals(HeroType.GAMBLER)){
            new Gambler(name,this.heroTeam);
        }
        if(this.heroTeam.getTeamList().size()<this.nbHero){
            this.inputParser.askHeroType(this.getHeroTeam().getTeamList().size()+1);
        }else{
            this.inputParser.displayTeam(this.heroTeam);
        }


    }

    //Select the next combattant of initiative as the active combattant
    public void nextCombattant(){
        int index = this.initiative.indexOf(this.activeCombattant);
        if(index<this.initiative.size()-1){
            this.activeCombattant = this.initiative.get(index+1);
        }else {
            this.activeCombattant = this.initiative.get(0);
        }
        if(!this.activeCombattant.getIsAlive()){
            this.nextCombattant();
        }

    }


    //create the enemy team with different enemies depending on the level
    public void generateEnemyTeam(Team heroTeam){
        String name = null;
        int pv;
        int pm;
        float defense;
        int strength;
        if (this.level == 1){
            name = "Bat";
            pv = 300;
            pm = 0;
            defense = 0.1f;
            strength = 5;
        }else if(this.level == 2){
            name = "Rat";
            pv = 350;
            pm = 0;
            defense = 0.1f;
            strength = 6;
        }else if(this.level == 3){
            name = "Ghost";
            pv = 400;
            pm = 0;
            defense = 0.1f;
            strength = 6;
        }else if(this.level == 4){
            name = "Zombie";
            pv = 500;
            pm = 0;
            defense = 0.1f;
            strength = 7;
        }else{
            name = "Boss";
            pv = 1000*this.nbHero;
            pm = 0;
            defense = 0.1f;
            strength = 8 + 5*(this.nbHero-1);
        }
        this.enemyTeam = new Team();
        if(this.level!=5){
            for(int i = 0; i<heroTeam.getTeamList().size(); i++){
                new Enemy(name,enemyTeam,pv,pm,defense,strength);
            }

        }else{
            //Only one boss
            new Enemy(name,enemyTeam,pv,pm,defense,strength);
        }
        this.initiative = Team.initiative(this.heroTeam,this.enemyTeam);
        this.activeCombattant = this.initiative.get(0);
        this.inputParser.annonceFight();

    }

    //first method of a fight
    public void startTurn(){
        this.inputParser.choseAction(this.activeCombattant);

    }

    //apply the action chose by the player if is an enemy turn then the action is attack
    public void executeAction(ActionType action){
        if(action.equals(ActionType.ATTACK)){
            if(this.activeCombattant instanceof Hero){
                if(this.activeCombattant instanceof Healer){
                    this.inputParser.choseTarget(this.heroTeam);
                }else {
                    this.inputParser.choseTarget(this.enemyTeam);
                }
            }else {
                int i = (int)(Math.random() * (this.heroTeam.getAliveList().size()));
                if(i == this.heroTeam.getAliveList().size()){
                    i = i-1;
                }
                Combattant target = this.heroTeam.getAliveList().get(i);
                this.inflictDamage(this.activeCombattant,target);
            }
        }else if(action.equals(ActionType.OBJECT)){
            this.inputParser.choseConsumable((Hero)this.activeCombattant);
        }

    }

    //inflict the damage in case of an attack
    public void inflictDamage(Combattant attacker, Combattant target){
        if(attacker.action(target)){
            this.inputParser.displayDamage(attacker,target,attacker.getLastDamage());
        }else {
            this.inputParser.displayFail(attacker);
        }

    }

    public void useConsumable(Consumable consumable){
        consumable.use((Hero) this.activeCombattant);
        this.inputParser.consumableUse(consumable,(Hero)this.activeCombattant);
    }

    //last method of a fight, decide if the level changed, if the player lost or if the fight goes on
    public void endTurn(){
        if(this.heroTeam.getAliveList().size()==0){
            this.inputParser.lose();

        }else if(this.enemyTeam.getAliveList().size() == 0){
            this.changeLevel();

        }else {
            this.nextCombattant();
            this.startTurn();
        }
    }

    public Team getHeroTeam() {
        return heroTeam;
    }

    public Combattant getActiveCombattant() {
        return activeCombattant;
    }

    //switch to next level, all the heros get their life back and now have increased stats
    public void changeLevel(){
        this.level += 1;
        if (this.level>5){
            this.inputParser.win();
        }else {
            for(Combattant c : this.heroTeam.getAliveList()){
                Hero h = (Hero)c;
                h.levelUp();
            }
            this.inputParser.levelWin();
        }
    }

    public void setNbHero(int nbHero) {
        this.nbHero = nbHero;
    }

    public int getLevel() {
        return level;
    }

    public Team getEnemyTeam() {
        return enemyTeam;
    }
}
