package com.example.demo.Combattant;

import com.example.demo.Item.Consumable;
import com.example.demo.Item.Food;
import com.example.demo.Item.Potion;
import com.example.demo.Item.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

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
        Weapon w = new Weapon(12,"Axe",this);
        this.setSelectedTool(w);
    }


    @Override
    public boolean action(Combattant target) {
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

    @Override
    public void levelUp() {
        this.level += 1;
        this.setPv((int)(this.pv*(1.15)));
        this.defense += 0.02f;
        this.strength += 1;
        this.setPv(this.maxPv);
        this.setPm(this.maxPm);
    }
}
