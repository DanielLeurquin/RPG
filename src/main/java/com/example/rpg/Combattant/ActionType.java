package com.example.rpg.Combattant;

public enum ActionType {
    ATTACK,
    DEFENSE,
    OBJECT;

    public static ActionType ActionTypeFromNum(int i){
        if(i == 1){
            return ActionType.ATTACK;
        }else if(i == 2){
            return ActionType.DEFENSE;
        }else if(i == 3) {
            return ActionType.OBJECT;
        }else {
            return null;
        }

    }

}
