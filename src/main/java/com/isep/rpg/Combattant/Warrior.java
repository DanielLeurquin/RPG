package com.isep.rpg.Combattant;

import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.Food;
import com.isep.rpg.Item.Potion;
import com.isep.rpg.Item.Weapon;

import java.util.ArrayList;

public class Warrior extends Hero {
    public Warrior(String name, Team team){
        this.level = 1;
        this.name = name;
        this.pv = 500;
        this.pm = 500;
        this.maxPm = this.pm;
        this.maxPv = this.pv;
        this.defense = 0.1f;
        this.strength = 10;
        this.team = team;
        this.team.addCombattant(this);
        this.heroType = HeroType.WARRIOR;
        this.consumable = new ArrayList<Consumable>();
        this.addConsumable(new Potion());
        this.addConsumable(new Food());
        Weapon w = new Weapon(7,"Axe",this);
        this.setSelectedTool(w);
        this.failMessage = "Oh no the enemy just dodged your attack !";
    }

    //execute attck on target
    @Override
    public boolean action(Combattant target) {
        if(target.getName() == "Ghost"){
            int chance = (int)(Math.random()*5);
            if(chance == 5){
                chance = 4;
            }
            if(chance==4){
                return false;
            }


        }
        int attack = this.selectedTool.getDamage() * this.getStrength();
        float damageDealed = (float)attack * (1-target.getDefense());
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

    //increase stats
    @Override
    public void levelUp() {
        this.level += 1;
        this.defense += 0.02f;
        this.strength += 1;
        this.setPv(this.maxPv);
        this.setPm(this.maxPm);
    }
}
