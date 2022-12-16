package com.example.rpg.Item;

import com.example.rpg.Combattant.Combattant;

public abstract class Item {
    protected String name;
    protected Combattant owner;

    public String getName() {
        return name;
    }

}
