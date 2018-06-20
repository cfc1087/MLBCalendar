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
public class Games {

    private String gameDate;
    private Teams teams;

    public Games() {
        teams = new Teams();
    }

    public String getTeams() {
        return teams.getHome();
    }

    @Override
    public String toString() {
        return "\nGameDate: " + gameDate  + teams;
    }
}
