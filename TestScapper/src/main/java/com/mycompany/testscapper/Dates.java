/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testscapper;



/**
 *
 * @author Christopher
 */
public class Dates {

    private String date; // YYYY-MM-DD
    private final Games games[];

    public Dates() {
        games = new Games[2];
    }

    public int getGameSize() {
        return this.games.length;
    }

    public Games[] getGames() {
        return this.games;
    }

    @Override
    public String toString() {
        //Used StringBuilder to remove "[]"
        StringBuilder builder = new StringBuilder();
        for(Games game:games){
            builder.append(game.toString());
        }
        
        String s =builder.toString();
        return "--------------\n" + this.date + s;
    }

}
