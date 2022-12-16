package com.isep.rpg.Item;

import com.isep.rpg.Combattant.Combattant;

public class Weapon extends Item{
    private int damage;
    public Weapon(int damage, String name, Combattant owner){
        this.owner = owner;
        this.damage = damage;
        this.name = name;
        this.owner.addWeapon(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
