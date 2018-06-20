/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testscapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

/**
 *
 * @author Christopher
 */
public class Main {

    public static void main(String args[]) throws IOException {
        String jsonURL = readURL.readURLString("http://statsapi.mlb.com/api/v1/schedule/games/?sportId=1&teamId=147&season=2018&startDate=2018-06-01&endDate=2018-06-30");
        Gson gson = new GsonBuilder().registerTypeAdapter(Dates[].class, new MyDeserializer()).setPrettyPrinting().create();

        Dates[] test;
        test = gson.fromJson(jsonURL, Dates[].class);

       for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
