/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mlbcalendar.business;

import javafx.scene.paint.Color;

/**
 *
 * @author Christopher
 */
public enum TeamSelection {

    ARIZONADIAMONDBACKS("Arizona Diamondbacks", "ARI", 109, Color.rgb(167,25,48)),
    ATLANTABRAVES("Atlanta Braves", "ATL", 144, Color.rgb(167, 25, 48)),
    BALTIMOREORIOLES("Baltimore Orioles", "BAL", 110, Color.rgb(252, 76, 2)),
    BOSTONREDSOX("Boston Red Sox", "BOS", 111, Color.rgb(12, 35, 64)),
    CHICAGOCUBS("Chicago Cubs", "CHC", 112, Color.rgb(0, 47, 108)),
    CHICAGOWHITESOX("Chicago White Sox", "CWS", 145, Color.rgb(39, 37, 31)),
    CINCINNATIREDS("Cincinnati Reds", "CIN", 113, Color.rgb(213, 0, 50)),
    CLEVELANDINDIANS("Cleveland Indians", "CLE", 114, Color.rgb(12, 35, 64)),
    COLORADOROCKIES("Colorado Rockies", "COL", 115, Color.rgb(51, 0, 114)),
    DETROITTIGERS("Detroit Tigers", "DET", 116, Color.rgb(12, 35, 64)),
    HOUSTONASTROS("Houston Astros", "HOU", 117, Color.rgb(0, 45, 98)),
    KANSASCITYROYALS("Kansas City Royals", "KAN", 118, Color.rgb(26, 71, 132)),
    LOSANGELESANGELSOFANAHEIM("Los Angeles Angels of Anaheim", "LAA", 108, Color.rgb(0, 50, 99)),
    LOSANGELESDODGERS("Los Angeles Dodgers", "LAD", 119, Color.rgb(0, 47, 119)),
    MIAMIMARLINS("Miami Marlins", "FLA", 146, Color.rgb(237, 111, 46)),
    MILWAUKEEBREWERS("Milwaukee Brewers", "MIL", 158, Color.rgb(19, 41, 75)),
    MINNESOTATWINS("Minnesota Twins", "MIN", 142, Color.rgb(12, 35, 65)),
    NEWYORKMETS("New York Mets", "NYM", 121, Color.rgb(0, 45, 114)),
    NEWYORKYANKEES("New York Yankees", "NYY", 147, Color.rgb(12, 35, 64)),
    OAKLANDATHLETICS("Oakland Athletics", "OAK", 133, Color.rgb(3, 70, 56)),
    PHILADELPHIAPHILLIES("Philadelphia Phillies", "PHI", 143, Color.rgb(0, 48, 135)),
    PITTSBURGHPIRATES("Pittsburgh Pirates", "PIT", 134, Color.rgb(39, 37, 31)),
    ST_LOUISCARDINALS("St. Louis Cardinals", "STL", 138, Color.rgb(186, 12, 47)),
    SANDIEGOPADRES("San Diego Padres", "SD", 135, Color.rgb(4, 30, 66)),
    SANFRANCISCOGIANTS("San Francisco Giants", "SF", 137, Color.rgb(39, 37, 31)),
    SEATTLEMARINERS("Seattle Mariners", "SEA", 136, Color.rgb(12, 35, 64)),
    TAMPABAYRAYS("Tampa Bay Rays", "TB", 139, Color.rgb(9, 44, 92)),
    TEXASRANGERS("Texas Rangers", "TEX", 140, Color.rgb(0, 45, 114)),
    TORONTOBLUEJAYS("Toronto Blue Jays", "TOR", 141, Color.rgb(29, 45, 92)),
    WASHINGTONNATIONALS("Washington Nationals", "WAS", 120, Color.rgb(186, 18, 43));

    private final String name;
    private final String abbr;
    private final int id;
    private final Color color;

    private TeamSelection(String name, String abbr, int id, Color color) {
        this.name = name;
        this.abbr = abbr;
        this.id = id;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}
