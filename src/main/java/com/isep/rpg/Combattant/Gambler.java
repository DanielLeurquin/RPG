package com.isep.rpg.Combattant;

import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.Food;
import com.isep.rpg.Item.Potion;
import com.isep.rpg.Item.Weapon;

import java.util.ArrayList;
//another type of hero he has very low chance to do 1000 damage
public class Gambler extends Hero{

    public Gambler(String name, Team team){
        this.level = 1;
        this.name = name;
        this.pv = 500;
        this.pm = 500;
        this.maxPm = this.pm;
        this.maxPv = this.pv;
        this.defense = 0.1f;
        this.strength = 5;
        this.team = team;
        this.team.addCombattant(this);
        this.heroType = HeroType.GAMBLER;
        this.consumable = new ArrayList<Consumable>();
        this.addConsumable(new Potion());
        this.addConsumable(new Food());
        this.failMessage = "Oh no the enemy just dodged your attack !";
        Weapon w = new Weapon(6,"Dice",this);
        this.setSelectedTool(w);
    }

    @Override
    public boolean action(Combattant target) {
        if(target.getName() == "Ghost") {
            int chance = (int) (Math.random() * 5);
            if (chance == 5) {
                chance = 4;
            }
            if (chance == 4) {
                return false;
            }
        }

        int damage;
        int random = (int)(Math.random()*14);
        if(random == 14) {
            random = 13;
        }
        if(random<4) {
            damage = 1 + this.level;
        }else if(random<7) {
            damage = 20 + 2*this.level;
        }else if(random<11) {
            damage = 60+ 4*this.level;
        }else if(random<13) {
            damage = 100 + 5*this.level;
        }else {
            damage = 1000;
        }

        float damageDealed = damage;
        target.setPv(target.getPv() - (int)damageDealed);
        if(target.getPv() > target.getMaxPv()){
            target.setPv(target.getMaxPv());
        } else if (target.getPv() <= 0) {
            target.setPv(0);
            target.die();
        }
        this.lastDamage = (int)damageDealed;
        return true;

    }

    @Override
    public void levelUp() {
        this.setPv(maxPv);
        this.level+=1;

    }
}
