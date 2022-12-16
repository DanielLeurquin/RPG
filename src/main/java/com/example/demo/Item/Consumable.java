package com.example.demo.Item;

import com.example.demo.Combattant.Combattant;
import com.example.demo.Combattant.Hero;

public abstract class Consumable extends Item{

    protected int heal;
    public abstract void use(Hero target);

    public int getHeal() {
        return heal;
    }
}
