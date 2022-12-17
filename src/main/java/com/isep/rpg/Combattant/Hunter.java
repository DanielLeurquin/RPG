package com.isep.rpg.Combattant;

import com.isep.rpg.Item.*;

import java.util.ArrayList;


public class Hunter extends Hero {

    private int arrows;
    private boolean goldenArrow = false;
    public Hunter(String name, Team team){
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
        this.heroType = HeroType.HUNTER;
        this.consumable = new ArrayList<Consumable>();
        this.addConsumable(new Potion());
        this.addConsumable(new Food());
        this.addConsumable(new GoldenArrow());
        this.arrows = 5;
        Weapon w = new Weapon(12,"Bow",this);
        this.setSelectedTool(w);
        this.failMessage = "Oh no you are out of arrows !";

    }

    //execute attack on target
    @Override
    public boolean action(Combattant target) {
        int buff = 0;

        if(this.arrows<=0){
            this.lastDamage = 1;
            return false;
        }

        if(target.getName() == "Ghost") {
            int chance = (int) (Math.random() * 5);
            if (chance == 5) {
                chance = 4;
            }
            if (chance == 4) {
                return false;
            }
        }
        if(this.goldenArrow){
            this.goldenArrow = false;
            buff = 100;

        }

        int piercingDamage = (int)(Math.random()*5 + 2);
        int attack = this.selectedTool.getDamage() * this.getStrength() + piercingDamage + buff;

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

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public int getArrows() {
        return arrows;
    }

    public void setGoldenArrow(boolean goldenArrow) {
        this.goldenArrow = goldenArrow;
    }

    //upgrade stats
    @Override
    public void levelUp() {
        this.level += 1;
        this.setPv((int)(this.pv*(1.10)));
        this.strength += 1;
        this.setPv(maxPv);
        this.setArrows(5+this.level-1);
    }
}
