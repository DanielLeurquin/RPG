package com.example.rpg;

import com.example.rpg.Combattant.*;
import com.example.rpg.Item.Consumable;
import com.example.rpg.Item.Food;
import com.example.rpg.Item.Potion;

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

    @Override
    public void askHeroType(int nbHero) {
        System.out.println("Let's choose the type of the hero number "+nbHero);
        System.out.println("Warrior (1)");
        System.out.println("Hunter  (2)");
        System.out.println("Healer  (3)");
        System.out.println("Mage    (4)");

        int num = scanForNumber(this.sc);
        while (num!=1 && num!=2 && num!=3 && num!=4){
            System.out.println("Please enter a number between 1 and 4");
            System.out.println("Warrior (1)");
            System.out.println("Hunter  (2)");
            System.out.println("Healer  (3)");
            System.out.println("Mage    (4)");
            num = scanForNumber(this.sc);

        }

        this.askHeroName(HeroType.HeroTypeFromNum(num));
    }

    @Override
    public void askHeroName(HeroType heroType) {
        System.out.println("\nTime to choose the name of your new Hero");
        String name = this.sc.nextLine();
        this.game.generateHero(name,heroType);
    }

    @Override
    public void displayEquipe(Team equipe) {
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

    @Override
    public void annonceFight() {

        System.out.println("Prepare yourself a fight is about to begin !\n");
        this.game.startTurn();

    }

    @Override
    public void annonceAttack(Combattant combattant) {
        if(combattant instanceof Hero){
            Hero hero  = (Hero)combattant;
            System.out.println("It's your turn !");
            System.out.println("Your hero : "+hero.getName()+" of the class "+ hero.getHeroType().toSring()+
                    " has to chose his action for this turn");
        }else {
            Enemy enemy = (Enemy)combattant;
            System.out.println("It's opponent "+enemy.getName()+"'s turn !");
        }

    }

    @Override
    public void choseAction(Combattant combattant) {
        ActionType action;
        if(combattant instanceof Hero){
            Hero hero = (Hero)combattant;
            System.out.println("What action do you want "+hero.getName()+" to perform ?");
            System.out.println("Attack  (1)");
            System.out.println("Defense (2)");
            System.out.println("Object  (3)");
            int nombre = scanForNumber(this.sc);
            while (nombre<=0 || nombre>=4){
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

    @Override
    public void choseConsumable(Hero hero) {
        System.out.println("Please choose which consumable you want to take ?");
        int compteur = 1;
        for(Consumable consumable : hero.getConsumable()){
            if(consumable instanceof Potion){
                System.out.println("Potion ("+compteur+")");
            }else {
                System.out.println("Food   ("+compteur+")");
            }
            compteur++;
        }
        int num = scanForNumber(this.sc);
        if(num<1 || num >hero.getConsumable().size()){
            System.out.println("Please enter a valid number");
            num = scanForNumber(this.sc);
        }
        Consumable c = hero.getConsumable().get(num-1);
        this.game.useConsumable(c);

    }


    public void displayCombattantPv(Combattant combattant) {
        if(combattant instanceof Hero){
            System.out.println("Class : "+((Hero)(combattant)).getHeroType().toSring()+" | Name : "+ combattant.getName()+
                    " now has : "+ combattant.getPv()+" Pv.");
        }else {
            System.out.println("Enemy named : "+ combattant.getName()+" now has : "+combattant.getPv()+ " Pv.");
        }

    }

    public void displayCombattantPm(Combattant combattant) {
        if(combattant instanceof Hero){
            System.out.println("Class : "+((Hero)(combattant)).getHeroType().toSring()+" | Name : "+ combattant.getName()+
                    " now has : "+ combattant.getPm()+" Pm.");
        }else {
            System.out.println("Enemy named : "+ combattant.getName()+" now has : "+combattant.getPm()+ " Pm.");
        }

    }

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

    @Override
    public void displayFail(Combattant attacker) {
        Hero h = (Hero)attacker;
        System.out.println(h.getFailMessage());
        this.game.endTurn();
    }

    @Override
    public void consumableUse(Consumable c, Hero h) {
        if (c instanceof Food){
            System.out.println(h.getName()+" is using the object food !");
            this.displayCombattantPv(h);
        }else {
            System.out.println(h.getName()+" is using the object potion !");
            this.displayCombattantPm(h);
        }
        this.game.endTurn();
    }

    @Override
    public void levelWin() {
        System.out.println("Congratulation you defeated all your enemies !");
        System.out.println("Your heros just became better !");
        this.displayEquipe(this.game.getHeroTeam());
    }

    @Override
    public void win() {
        System.out.println("\n"+"-".repeat(30)+"\n");
        System.out.println("Congratulation juste won the game !!");
    }

    @Override
    public void lose() {
        System.out.println("\n"+"-".repeat(30)+"\n");
        System.out.println("All your team is dead...");
        System.out.println("This is Game Over !");
    }


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

    public static int scanForNumber(Scanner sc){
        String str = sc.nextLine();
        while(!onlyNumber(str)){
            System.out.println("Please enter a positive number");
            str = sc.nextLine();
        }
        return Integer.parseInt(str);
    }

}
