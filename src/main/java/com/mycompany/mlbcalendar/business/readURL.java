/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mlbcalendar.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Christopher
 */
public class readURL {

    private BufferedReader reader = null;

    public static String readURLString(String urlString) throws MalformedURLException, IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static String createURL(int teamID, int month, int lastDay) {//lastDay - end of the month

        ///"http://statsapi.mlb.com/api/v1/schedule/games/?sportId=1&teamId=147&season=2018&startDate=2018-06-01&endDate=2018-06-30" keep here for reference
        String s = "http://statsapi.mlb.com/api/v1/schedule/games/?sportId=1&teamId=" + teamID + "&season=2018&startDate=2018-" + month + "-01&endDate=2018-" + month + "-" + lastDay;
        return s;
    }

}
