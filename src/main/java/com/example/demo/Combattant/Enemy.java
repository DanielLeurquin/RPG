package com.example.demo.Combattant;

import com.example.demo.Combattant.Combattant;
import com.example.demo.Item.Weapon;

import java.util.ArrayList;

public class Enemy extends Combattant {
    public Enemy(String name, Team team, int pv, int pm, float defense, int strength){
        this.name = name;
        this.team = team;
        this.team.addCombattant(this);
        this.pv = pv;
        this.pm = pm;
        this.defense = defense;
        this.strength = strength;
        this.maxPm = this.pm;
        this.maxPv = this.pv;

        Weapon w = new Weapon(10,"Sword",this);
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
        this.lastDamage = (int) damageDealed;
        return true;
    }
}