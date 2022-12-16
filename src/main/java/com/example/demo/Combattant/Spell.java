package com.example.demo.Combattant;

public class Spell {
    private int manaCost;
    private String name;

    public Spell(String name, int manaCost){
        this.manaCost = manaCost;
        this.name = name;

    }


    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }
}
