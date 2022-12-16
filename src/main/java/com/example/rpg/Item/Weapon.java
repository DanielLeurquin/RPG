package com.example.rpg.Item;

import com.example.rpg.Combattant.Combattant;

public class Weapon extends Item{
    private int damage;
    public Weapon(int damage,String name,Combattant owner){
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
