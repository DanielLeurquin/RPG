package com.isep.rpg.Combattant;

import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.Food;
import com.isep.rpg.Item.Potion;
import com.isep.rpg.Item.Weapon;

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


    //execute attack on target
    @Override
    public boolean action(Combattant target) {

        if(!this.canCastSpell()){
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

        int attack = this.selectedTool.getDamage()*this.getStrength() + 11*this.level;
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

    //upgrade stats
    @Override
    public void levelUp() {
        this.level += 1;
        this.setPv((int)(this.pv*(1.05)));
        this.setPm((int)(this.pm*(1.20)));
        this.setPm(this.maxPm);
        this.setPv((int)(this.maxPv/2)+this.getPv());
        if(this.getPv()>maxPv){
            this.setPv(maxPv);
        }
        this.strength += 1;

    }
}
