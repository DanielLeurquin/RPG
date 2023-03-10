package com.isep.rpg.Combattant;

import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.Food;
import com.isep.rpg.Item.Potion;
import com.isep.rpg.Item.Weapon;

import java.util.ArrayList;

public class Healer extends SpellCaster{
    public Healer(String name, Team team){
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
        this.heroType = HeroType.HEALER;
        this.consumable = new ArrayList<Consumable>();
        this.addConsumable(new Potion());
        this.addConsumable(new Food());
        Weapon w = new Weapon(30,"Totem",this);
        this.setSelectedTool(w);
        this.failMessage = "Oh no ! You don't have enough mana to perform this action !";
        this.spell = new Spell("Heal",50);

    }

    //execute heal on target
    @Override
    public boolean action(Combattant target) {
        if(!this.canCastSpell()){
            this.lastDamage = 1;
            return false;
        }
        int heal = this.selectedTool.getDamage()*this.strength - target.getStrength();
        target.setPv(target.getPv() + heal);
        if(target.getPv() > target.getMaxPv()){
            target.setPv(target.getMaxPv());
        } else if (target.getPv() <= 0) {
            target.setPv(0);
            target.die();
        }
        this.setPm(this.getPm()-this.getSpell().getManaCost());
        this.lastDamage = heal;
        return true;
    }

    //upgrade stats
    @Override
    public void levelUp() {
        this.level += 1;
        this.setPv((int)(this.pv*(1.05)));
        this.setPm((int)(this.pm*(1.20)));
        this.setPm(maxPm);
        this.selectedTool.setDamage((int)(this.selectedTool.getDamage()*(1.15)) );
    }
}
