package com.example.rpg.Item;

import com.example.rpg.Combattant.Hero;

public abstract class Consumable extends Item{

    protected int heal;
    public abstract void use(Hero target);

    public int getHeal() {
        return heal;
    }
}
