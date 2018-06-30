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
public class Games {

    private String gameDate;//YYYY-MM-DDTHH:MM:SSZ   24hr
    private final Teams teams;

    public Games() {
        teams = new Teams();
    }

    public String getHome() {
        return teams.getHome();
    }

    public String getAway() {
        return teams.getAway();
    }
    public String getGameDate(){
        
        return gameDate;
    }

    @Override
    public String toString() {
        return "\nGameDate: " + gameDate + teams;
    }
}
