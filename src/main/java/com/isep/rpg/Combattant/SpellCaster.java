package com.isep.rpg.Combattant;

public abstract class SpellCaster extends Hero {

    protected Spell spell;

    public Spell getSpell() {
        return spell;
    }

    //check if enough mana to cast spell
    public boolean canCastSpell(){
        return this.getPm()>=this.getSpell().getManaCost();
    }
}
