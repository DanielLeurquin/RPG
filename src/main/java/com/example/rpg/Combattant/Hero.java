package com.example.rpg.Combattant;

import com.example.rpg.Item.*;

import java.util.ArrayList;

public abstract  class Hero extends Combattant {

    protected HeroType heroType;

    protected ArrayList<Consumable> consumable;

    protected int level;
    protected String failMessage;

    public HeroType getHeroType() {
        return heroType;
    }
    public void addConsumable(Consumable consumable){
        this.consumable.add(consumable);
    }
    public void removeConsumable(Consumable consumable){
        this.consumable.remove(consumable);
    }

    public ArrayList<Consumable> getConsumable() {
        return consumable;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public abstract void levelUp();

    public int getNbFood() {
        int compteur = 0;
        for (Consumable c : this.consumable){
            if(c instanceof Food){
                compteur ++;
            }
        }
        return compteur;
    }

    public int getNbPotion() {
        int compteur = 0;
        for (Consumable c : this.consumable){
            if(c instanceof Potion){
                compteur ++;
            }
        }
        return compteur;
    }

    public String getWeaponName(){
        return this.selectedTool.getName();
    }

}