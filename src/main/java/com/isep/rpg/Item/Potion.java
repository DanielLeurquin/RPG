package com.isep.rpg.Item;

import com.isep.rpg.Combattant.Hero;

public class Potion extends Consumable{

    public Potion(){
        this.heal = 100;
        this.name = "Potion";
    }

    //heal hero pm
    @Override
    public void use(Hero target) {
        target.setPm(target.getPm() + this.heal);
        if(target.getPm() > target.getMaxPm()){
            target.setPm(target.getMaxPm());
        }
        target.removeConsumable(this);
    }
}
