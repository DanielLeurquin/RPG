package com.example.rpg.Combattant;

import java.util.ArrayList;
import java.util.Collections;

public class Team {
    private ArrayList<Combattant> teamList;

    public Team(){
        this.teamList = new ArrayList<Combattant>();

    }

    public ArrayList<Combattant> getTeamList() {
        return teamList;
    }

    public void addCombattant(Combattant combattant){
        this.teamList.add(combattant);
    }

    public static ArrayList<Combattant> initiative(Team heroTeam, Team enemyTeam){
        ArrayList<Combattant> order = new ArrayList<>();
        for(Combattant c: heroTeam.getTeamList()){
            order.add(c);
        }
        for(Combattant c: enemyTeam.getTeamList()){
            order.add(c);
        }
        Collections.shuffle(order);
        return order;

    }
    public ArrayList<Combattant> getAliveList(){
        ArrayList<Combattant> alive = new ArrayList<Combattant>();
        for(Combattant c : this.teamList){
            if(c.isAlive){
                alive.add(c);
            }
        }
        return alive;
    }

}
