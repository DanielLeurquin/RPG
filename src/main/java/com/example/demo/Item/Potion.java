package com.example.demo.Item;

import com.example.demo.Combattant.Hero;

public class Potion extends Consumable{

    public Potion(){
        this.heal = 100;
        this.name = "Potion";
    }

    @Override
    public void use(Hero target) {
        target.setPm(target.getPm() + this.heal);
        if(target.getPm() > target.getMaxPm()){
            target.setPm(target.getMaxPm());
        }
        target.removeConsumable(this);
    }
}
