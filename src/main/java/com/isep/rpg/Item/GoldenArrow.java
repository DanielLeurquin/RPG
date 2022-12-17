package com.isep.rpg.Item;

import com.isep.rpg.Combattant.Hero;
import com.isep.rpg.Combattant.Hunter;
import com.isep.rpg.Game;

public class GoldenArrow extends Consumable{

    public GoldenArrow(){
        this.name = "Golden Arrow";
    }

    @Override
    public void use(Hero target) {
        if(target instanceof Hunter){
            Hunter hunter = (Hunter) target;
            if(hunter.getArrows()<=0){
                hunter.setGoldenArrow(true);
                hunter.setArrows(1);
            }
            target.getConsumable().remove(this);
        }
    }
}
