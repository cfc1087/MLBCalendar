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
class Away {

    private final Team team;

    public Away() {
        team = new Team();
    }

    @Override
    public String toString() {
        return team.toString();
    }
}
