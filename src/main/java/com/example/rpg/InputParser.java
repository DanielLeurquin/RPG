package com.example.rpg;

import com.example.rpg.Combattant.Combattant;
import com.example.rpg.Combattant.Team;
import com.example.rpg.Combattant.HeroType;
import com.example.rpg.Item.Consumable;
import com.example.rpg.Combattant.Hero;

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
