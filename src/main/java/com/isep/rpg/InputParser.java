package com.isep.rpg;

import com.isep.rpg.Combattant.Combattant;
import com.isep.rpg.Combattant.Team;
import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Combattant.Hero;

public interface InputParser {

    void welcome();

    void askNumberHero();

    void askHeroType(int nbHero);

    void displayTeam(Team equipe);

    void annonceFight();


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
