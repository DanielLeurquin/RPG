package com.example.demo.Combattant;

import com.example.demo.Item.Consumable;
import com.example.demo.Item.Food;
import com.example.demo.Item.Potion;
import com.example.demo.Item.Weapon;

import java.util.ArrayList;


public class Hunter extends Hero {

    private int arrows;
    public Hunter(String name, Team team){
        this.level = 1;
        this.name = name;
        this.pv = 500;
        this.pm = 500;
        this.maxPm = this.pm;
        this.maxPv = this.pv;
        this.defense = 0.1f;
        this.strength = 8;
        this.team = team;
        this.team.addCombattant(this);
        this.heroType = HeroType.HUNTER;
        this.consumable = new ArrayList<Consumable>();
        this.addConsumable(new Potion());
        this.addConsumable(new Food());
        this.arrows = 5;
        Weapon w = new Weapon(10,"Bow",this);
        this.setSelectedTool(w);
        this.failMessage = "Oh no you are out of arrows !";

    }

    @Override
    public boolean action(Combattant target) {
        if(this.arrows<=0){
            return false;
        }
        int piercingDamage = (int)(Math.random()*5 + 2);
        int attack = this.selectedTool.getDamage() * this.getStrength() + piercingDamage;
        float damageDealed = (float)attack * (1-target.getDefense());
        target.setPv(target.getPv() - (int)damageDealed);
        if(target.getPv() > target.getMaxPv()){
            target.setPv(target.getMaxPv());
        } else if (target.getPv() <= 0) {
            target.setPv(0);
            target.die();
        }
        this.arrows -=1;
        this.lastDamage = (int) damageDealed;
        return true;
    }

    public void addArrows(int findArrows){
        this.arrows += findArrows;

    }


    @Override
    public void levelUp() {
        this.level += 1;
        this.setPv((int)(this.pv*(1.10)));
        this.strength += 1;
        this.addArrows(5);
    }
}
