package com.isep.rpg.Combattant;

import com.isep.rpg.Item.*;
import com.isep.rpg.Item.Consumable;
import com.isep.rpg.Item.Food;
import com.isep.rpg.Item.Potion;

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

    //is called by controller to get the number of food (implicitly called by PropertyCellValueFactory object)
    public int getNbFood() {
        int compteur = 0;
        for (Consumable c : this.consumable){
            if(c instanceof Food){
                compteur ++;
            }
        }
        return compteur;
    }

    //is called by controller to get the number of Potion (implicitly called by PropertyCellValueFactory object)
    public int getNbPotion() {
        int compteur = 0;
        for (Consumable c : this.consumable){
            if(c instanceof Potion){
                compteur ++;
            }
        }
        return compteur;
    }

    //is called by controller to get the weapon name (implicitly called by PropertyCellValueFactory object)
    public String getWeaponName(){
        return this.selectedTool.getName();
    }

}