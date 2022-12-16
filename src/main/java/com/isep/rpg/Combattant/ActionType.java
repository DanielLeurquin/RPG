package com.isep.rpg.Combattant;

//describe different types of possible action
public enum ActionType {
    ATTACK,
    OBJECT;

    public static ActionType ActionTypeFromNum(int i){
        if(i == 1){
            return ActionType.ATTACK;
        }else if(i == 2) {
            return ActionType.OBJECT;
        }else {
            return null;
        }

    }

}
