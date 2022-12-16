package com.example.demo.Combattant;

public abstract class SpellCaster extends Hero {

    protected Spell spell;

    public Spell getSpell() {
        return spell;
    }

    public boolean canCastSpell(){
        return this.getPm()>=this.getSpell().getManaCost();
    }
}
