package com.isep.rpg;

import com.isep.rpg.Combattant.*;
import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.Food;
import com.isep.rpg.Item.GoldenArrow;
import com.isep.rpg.Item.Potion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleParser implements InputParser {
    private Scanner sc = new Scanner(System.in);
    Game game;
    public ConsoleParser(Game game){
        this.sc = sc;
        this.game = game;
    }

    @Override
    public void welcome() {
        System.out.println("Welcome in my game ! Let's begin to have fun !\n");
        this.askNumberHero();
    }

    //ask the player how many heroes does he want
    @Override
    public void askNumberHero() {

        System.out.println("With how many heros do you want to play ?");

        int i = scanForNumber(this.sc);

        while(i<1 || i > 10){
            System.out.println("You cannot play with this number of heros, please chose a number between 1 and 10");
            i = scanForNumber(this.sc);
        }
        this.game.setNbHero(i);
        this.askHeroType(1);
    }


    //ask player for the type of hero he wants
    @Override
    public void askHeroType(int nbHero) {
        System.out.println("Let's choose the type of the hero number "+nbHero);
        System.out.println("Warrior (1)");
        System.out.println("Hunter  (2)");
        System.out.println("Healer  (3)");
        System.out.println("Mage    (4)");
        System.out.println("Gambler (5)");

        int num = scanForNumber(this.sc);
        while (num!=1 && num!=2 && num!=3 && num!=4 && num!=5){
            System.out.println("Please enter a number between 1 and 5");
            System.out.println("Warrior (1)");
            System.out.println("Hunter  (2)");
            System.out.println("Healer  (3)");
            System.out.println("Mage    (4)");
            System.out.println("Gambler (5)");
            num = scanForNumber(this.sc);

        }

        this.askHeroName(HeroType.HeroTypeFromNum(num));
    }


    //ask player for hero name
    public void askHeroName(HeroType heroType) {
        System.out.println("\nTime to choose the name of your new Hero");
        String name = this.sc.nextLine();
        this.game.generateHero(name,heroType);
    }

    //self-explanatory
    @Override
    public void displayTeam(Team equipe) {
        System.out.println("\n\nHeros of your team :");
        for(Combattant c : equipe.getTeamList()){
            if(c instanceof Hero){
                Hero h = (Hero)c;
                System.out.println("Type : "+h.getHeroType().toSring()+"    Name : "+h.getName()+
                        "   Status : "+h.getAliveString());
                System.out.println("Stats |  Defense : "+h.getDefense()+ "      Strength "+h.getStrength()+
                        "   Life points : "+h.getPv()+"     Mana points : "+h.getPm());
                System.out.println("-".repeat(30));

            }
        }
        game.generateEnemyTeam(game.getHeroTeam());
    }

    //self-explanatory
    @Override
    public void annonceFight() {

        System.out.println("Prepare yourself a fight is about to begin !\n");
        this.game.startTurn();

    }

    //self-explanatory
    public void annonceAttack(Combattant combattant) {
        System.out.println("");
        if(combattant instanceof Hero){
            Hero hero  = (Hero)combattant;
            System.out.println("It's your turn !");
            System.out.println("Your hero : "+hero.getName()+" of the class "+ hero.getHeroType().toSring()+
                    " has to chose his action for this turn");
        }else {
            Enemy enemy = (Enemy)combattant;
            System.out.println("It's opponent "+enemy.getName()+"'s turn !");
        }
        System.out.println("");

    }

    //ask player for the action he wants to perform
    @Override
    public void choseAction(Combattant combattant) {
        annonceAttack(combattant);
        ActionType action;
        if(combattant instanceof Hero){
            Hero hero = (Hero)combattant;
            System.out.println("What action do you want "+hero.getName()+" to perform ?");
            System.out.println("Attack  (1)");
            System.out.println("Object  (2)");
            int nombre = scanForNumber(this.sc);
            while (nombre<=0 || nombre>=3){
                System.out.println("Please enter a valid number");
                nombre = scanForNumber(this.sc);
            }
            action =ActionType.ActionTypeFromNum(nombre);

        }else {
            System.out.println("The enemy "+combattant.getName()+" is attacking you");
            action = ActionType.ATTACK;
        }
        this.game.executeAction(action);


    }

    //chose the target of the attack, if the hero is a healer he is choosing in his team
    @Override
    public void choseTarget(Team team) {
        System.out.println("Please chose a target");
        int compteur = 1;

        for(Combattant c : team.getAliveList()){
            System.out.println("("+compteur+") target "+c.getName()+" | remaining life : "+c.getPv());
            compteur++;
        }
        int num = scanForNumber(this.sc);
        while(num<1 || num>team.getAliveList().size()){
            System.out.println("Please enter a valid number");
            num = scanForNumber(this.sc);
        }
        this.game.inflictDamage(this.game.getActiveCombattant(),team.getAliveList().get(num-1));

    }

    //ask player to choose the consumable to use
    @Override
    public void choseConsumable(Hero hero) {
        System.out.println("Please choose which consumable you want to take ?");
        ArrayList<Consumable> list_c = (ArrayList<Consumable>) hero.getConsumable().clone();
        if(hero instanceof Hunter){
            Hunter hunter = (Hunter) hero;
            if(hunter.getArrows()>0){
                for(Consumable consumable : list_c){
                    if(consumable instanceof GoldenArrow){
                        list_c.remove(consumable);
                    }
                }
            }
        }

        int compteur = 1;
        for(Consumable consumable : list_c){
            if(consumable instanceof Potion){
                System.out.println("Potion ("+compteur+")");
            }else if(consumable instanceof Food){
                System.out.println("Food   ("+compteur+")");
            }else if(consumable instanceof GoldenArrow){
                System.out.println("Golden Arrow   ("+compteur+")");

            }
            compteur++;
        }
        int num = scanForNumber(this.sc);
        if(num<1 || num >compteur){
            System.out.println("Please enter a valid number");
            num = scanForNumber(this.sc);
        }
        Consumable c = hero.getConsumable().get(num-1);
        this.game.useConsumable(c);

    }

    //self-explanatory
    public void displayCombattantPv(Combattant combattant) {
        if(combattant instanceof Hero){
            System.out.println("Class : "+((Hero)(combattant)).getHeroType().toSring()+" | Name : "+ combattant.getName()+
                    " now has : "+ combattant.getPv()+" Pv.");
        }else {
            System.out.println("Enemy named : "+ combattant.getName()+" now has : "+combattant.getPv()+ " Pv.");
        }

    }
    //self-explanatory
    public void displayCombattantPm(Combattant combattant) {
        if(combattant instanceof Hero){
            System.out.println("Class : "+((Hero)(combattant)).getHeroType().toSring()+" | Name : "+ combattant.getName()+
                    " now has : "+ combattant.getPm()+" Pm.");
        }else {
            System.out.println("Enemy named : "+ combattant.getName()+" now has : "+combattant.getPm()+ " Pm.");
        }

    }

    //self-explanatory
    @Override
    public void displayDamage(Combattant attacker, Combattant target, int damage) {
        if(!(attacker instanceof Healer)){
            System.out.println(attacker.getName()+" is attacking "+target.getName()+" inflicting "+damage+
                    " damage points!");
            this.displayCombattantPv(target);
        }else {
            System.out.println(attacker.getName()+" is healing "+target.getName()+" of "+damage+" points !");
            this.displayCombattantPv(target);
        }
        System.out.println("\n"+"-".repeat(30));
        this.game.endTurn();

    }

    //display a message of a failed attack
    @Override
    public void displayFail(Combattant attacker) {
        Hero h = (Hero)attacker;
        if(attacker.getLastDamage()==0){
            System.out.println("Oh no the enemy just dodged your attack !");
        }
        System.out.println(h.getFailMessage());
        this.game.endTurn();
    }

    //self-explanatory
    @Override
    public void consumableUse(Consumable c, Hero h) {
        if (c instanceof Food){
            System.out.println(h.getName()+" is using the object food !");
            this.displayCombattantPv(h);
        }else if(c instanceof Potion){
            System.out.println(h.getName()+" is using the object potion !");
            this.displayCombattantPm(h);
        }else if(c instanceof GoldenArrow){
            System.out.println("The enemy is trembling as you draw your golden arrow");
        }
        this.game.endTurn();
    }

    //self-explanatory
    @Override
    public void levelWin() {
        System.out.println("Congratulations you defeated all your enemies !");
        System.out.println("Your heros just became better !");
        this.displayTeam(this.game.getHeroTeam());
    }

    //self-explanatory
    @Override
    public void win() {
        System.out.println("\n"+"-".repeat(30)+"\n");
        System.out.println("Congratulations juste won the game !!");
    }

    //self-explanatory
    @Override
    public void lose() {
        System.out.println("\n"+"-".repeat(30)+"\n");
        System.out.println("All your team is dead...");
        System.out.println("This is Game Over !");
    }

    //Check if a String contains only numbers and returns a boolean
    public static boolean onlyNumber(String str){
        if(str.equals("")){
            return false;
        }

        List<Character> list = Arrays.asList('1','2','3','4','5','6','7','8','9','0');
        for(char c : str.toCharArray()){
            if(!list.contains(c)){
                return false;
            }
        }
        return true;

    }

    //ask for a number to the player and
    public static int scanForNumber(Scanner sc){
        String str = sc.nextLine();
        while(!onlyNumber(str)){
            System.out.println("Please enter a positive number");
            str = sc.nextLine();
        }
        return Integer.parseInt(str);
    }

}
