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
public class Team {

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return name + "\nID:" + id;
    }
}
