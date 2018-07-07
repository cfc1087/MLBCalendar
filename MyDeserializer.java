/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mlbcalendar.business;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author Christopher
 */
public class MyDeserializer implements JsonDeserializer<Dates[]> {

    @Override
    public Dates[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
       
        JsonElement test = json.getAsJsonObject().get("dates");

        JsonArray dates = test.getAsJsonArray();

        return new Gson().fromJson(dates, Dates[].class);

    }

}
