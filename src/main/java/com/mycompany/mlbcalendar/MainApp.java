package com.mycompany.mlbcalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.mlbcalendar.business.Dates;
import com.mycompany.mlbcalendar.business.MyDeserializer;
import com.mycompany.mlbcalendar.business.readURL;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     launch(args);

     
     
     
     
     
     
     
     /*   String jsonURL = readURL.readURLString("http://statsapi.mlb.com/api/v1/schedule/games/?sportId=1&teamId=147&season=2018&startDate=2018-06-01&endDate=2018-06-30");
        Gson gson = new GsonBuilder().registerTypeAdapter(Dates[].class, new MyDeserializer()).setPrettyPrinting().create();

        Dates[] test;
        test = gson.fromJson(jsonURL, Dates[].class);
       */
     
     

    }

}
