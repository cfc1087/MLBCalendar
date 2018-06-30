/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mlbcalendar.business;

/**
 *
 * @author Christopher
 */
public class Dates {

    private String date; // YYYY-MM-DD
    private final Games games[];
    private int day;

    public Dates() {
        games = new Games[2];
    }

    public Games[] getGames() {
        return games;
    }

    @Override
    public String toString() {
        //Used StringBuilder to remove "[]"
        StringBuilder builder = new StringBuilder();
        for (Games game : games) {
            builder.append(game.toString());
        }

        String s = builder.toString();
        return "--------------\n" + this.date + s;
    }

    public String getDate() {
        return date;
    }

    public int getDay() {
        day = Integer.parseInt(date.substring(8, 10));
        return day;
    }

}
