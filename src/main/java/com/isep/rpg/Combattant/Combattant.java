package com.isep.rpg.Combattant;

import com.isep.rpg.Item.Weapon;


import java.util.ArrayList;

public abstract class Combattant {

    protected String name;
    protected int maxPv;
    protected int maxPm;
    protected int pv;
    protected int pm;
    protected float defense;
    protected int strength;
    protected boolean isAlive = true;

    protected int lastDamage;

    protected ArrayList<Weapon> tools = new ArrayList<Weapon>();
    protected Weapon selectedTool;

    protected Team team;

    public int getLastDamage() {
        return lastDamage;
    }

    public int getPv(){return this.pv;}

    public void setPv(int pv){this.pv = pv;}

    public int getPm(){return this.pm;}

    public void setPm(int pm){this.pm = pm;}

    public float getDefense(){return this.defense;}

    public void setDefense(int defense){this.defense = defense;}
    public int getStrength(){return this.strength;}

    public int getMaxPv(){return maxPv;}

    public void setMaxPv(int maxPv){this.maxPv = maxPv;}
    public int getMaxPm(){return maxPm;}

    public void setSelectedTool(Weapon weapon){
        this.selectedTool = weapon;
    }
    public void die(){
        this.isAlive = false;
    }

    public boolean getIsAlive(){return this.isAlive;}


    public String getAliveString(){
        if(this.getIsAlive()){
            return "Alive";
        }else {
            return "Dead";
        }
    }



    public void addWeapon(Weapon weapon){
        this.tools.add(weapon);
    }

    public abstract boolean action(Combattant target);

    public String getName() {
        return name;
    }

}
