package com.example.rpg.Item;

import com.example.rpg.Combattant.Hero;

public class Food extends Consumable{

    public Food(){
        this.heal = 100;
        this.name = "Food";
    }

    @Override
    public void use(Hero target) {
        target.setPv(target.getPv() + this.heal);
        if(target.getPv() > target.getMaxPv()){
            target.setPv(target.getMaxPv());
        }
        target.removeConsumable(this);

    }
}
