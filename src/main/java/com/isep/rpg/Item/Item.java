package com.isep.rpg.Item;

import com.isep.rpg.Combattant.Combattant;

public abstract class Item {
    protected String name;
    protected Combattant owner;

    public String getName() {
        return name;
    }

}
