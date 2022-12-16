package com.example.demo;

import com.example.demo.Combattant.Combattant;
import com.example.demo.Combattant.Team;
import com.example.demo.Combattant.HeroType;
import com.example.demo.Combattant.ActionType;
import com.example.demo.Item.Consumable;
import com.example.demo.Combattant.Hero;

public interface InputParser {

    void welcome();

    void askNumberHero();

    void askHeroType(int nbHero);

    void askHeroName(HeroType heroType);

    void displayEquipe(Team equipe);

    void annonceFight();

    void annonceAttack(Combattant combattant);

    void choseAction(Combattant combattant);

    void choseTarget(Team enemyTeam);

    void choseConsumable(Hero hero);

    void displayDamage(Combattant attacker, Combattant target, int damage);

    void displayFail(Combattant attacker);

    void consumableUse(Consumable c, Hero h);

    void levelWin();

    void win();

    void lose();


}
