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
public class Teams {

    private final Home home;
    private final Away away;

    public Teams() {
        home = new Home();
        away = new Away();
    }

    public String getHome() {
        return home.getHomeName();
    }

    @Override
    public String toString() {

        return "\nHome: "+home.toString()+"\nAway: "+away.toString();
    }

}
