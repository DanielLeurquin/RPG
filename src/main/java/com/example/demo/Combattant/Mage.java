package com.example.demo.Combattant;

import com.example.demo.Item.Consumable;
import com.example.demo.Item.Food;
import com.example.demo.Item.Potion;
import com.example.demo.Item.Weapon;

import java.util.ArrayList;

public class Mage extends SpellCaster{
    public Mage(String name, Team team){
        this.level = 1;
        this.name = name;
        this.pv = 500;
        this.pm = 500;
        this.maxPm = this.pm;
        this.maxPv = this.pv;
        this.defense = 0.1f;
        this.strength = 2;
        this.team = team;
        this.team.addCombattant(this);
        this.heroType = HeroType.MAGE;
        this.consumable = new ArrayList<Consumable>();
        this.addConsumable(new Potion());
        this.addConsumable(new Food());
        Weapon w = new Weapon(23,"Wand",this);
        this.setSelectedTool(w);
        this.failMessage = "Oh no ! You don't have enough mana to perform this action !";
        this.spell = new Spell("Fire Ball",50);
    }

    @Override
    public boolean action(Combattant target) {

        if(!this.canCastSpell()){
            return false;
        }
        int attack = this.selectedTool.getDamage()*this.getStrength() + 3*this.level;
        float damageDealed = (float)attack * (1-target.getDefense());
        target.setPv(target.getPv() - (int)damageDealed);
        if(target.getPv() > target.getMaxPv()){
            target.setPv(target.getMaxPv());
        } else if (target.getPv() <= 0) {
            target.setPv(0);
            target.die();
        }
        this.setPm(this.getPm()-this.getSpell().getManaCost());
        this.lastDamage = (int) damageDealed;
        return true;
    }


    @Override
    public void levelUp() {
        this.level += 1;
        this.setPv((int)(this.pv*(1.05)));
        this.setPm((int)(this.pm*(1.20)));

    }
}
