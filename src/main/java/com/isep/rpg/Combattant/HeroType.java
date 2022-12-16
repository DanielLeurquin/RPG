package com.isep.rpg.Combattant;

//describe the for types of heroes
public enum HeroType {
    WARRIOR,
    HUNTER,
    HEALER,
    MAGE;

    public String toSring(){
        if(this.equals(HeroType.WARRIOR)){
            return "Warrior";
        }else if(this.equals(HeroType.HUNTER)){
            return "Hunter";
        }else if(this.equals(HeroType.HEALER)){
            return "Healer";
        }else {
            return "Mage";
        }
    }


    public static HeroType HeroTypeFromNum(int i){
        if(i == 1){
            return HeroType.WARRIOR;
        }else if(i == 2){
            return HeroType.HUNTER;
        }else if(i == 3) {
            return HeroType.HEALER;
        }else if(i == 4) {
            return HeroType.MAGE;
        }else {
            return null;
        }
    }



}
