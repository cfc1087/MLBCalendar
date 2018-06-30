package com.mycompany.mlbcalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.mlbcalendar.business.Dates;
import com.mycompany.mlbcalendar.business.Games;
import com.mycompany.mlbcalendar.business.MyDeserializer;
import com.mycompany.mlbcalendar.business.readURL;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FXMLController implements Initializable {

    private Dates dates[];
    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane bp;
    int teamId = 147;
    private LocalDateTime time;
    private LocalDate date;
    private ZonedDateTime gameTime;
    private Label labels[];
    private StackPane days[];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        time = LocalDateTime.now();
        date = time.toLocalDate(); //today's date.
        try {
            getMLBJSON();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        createDates();//create lables for dates;
        getGameInfo();//create labels for home,away teams and time.
        createCalendar();//add stackpanes to gridpane
    }

    @FXML
    public void createCalendar() {

        int firstDay = date.withDayOfMonth(1).getDayOfWeek().getValue();

        for (StackPane stackpane : days) {
            gridPane.add(stackpane, firstDay % 7, firstDay / 7);
            GridPane.setHgrow(stackpane, Priority.ALWAYS);
            GridPane.setVgrow(stackpane, Priority.ALWAYS);
            //GridPane.setHalignment(label, HPos.LEFT);
            //GridPane.setMargin(label, new Insets(5));
            //GridPane.setValignment(label, VPos.TOP);
            GridPane.setFillHeight(stackpane, Boolean.TRUE);
            GridPane.setFillWidth(stackpane, Boolean.TRUE);
            firstDay++;
        }

    }

    
    
    public void getGameInfo() {
        String timeZone = "";
        int i = 1;
        for (Dates d : dates) {

            gameTime = ZonedDateTime.parse(d.getGames()[0].getGameDate(), DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault()));
            
            timeZone = gameTime.getZone().getDisplayName(TextStyle.SHORT, Locale.US);
            
            Label label = new Label(d.getGames()[0].getHome() + "\n" + "@" + "\n" + d.getGames()[0].getAway() + "\n" + gameTime.toLocalTime() + " " + timeZone);

            label.setTextAlignment(TextAlignment.CENTER);
            if (d.getDay() == i) {
                //if game exist on that date(i) 
                days[i - 1].getChildren().add(label);

            } else {
                days[i].getChildren().add(label);
                i++;
            }
            StackPane.setAlignment(label, Pos.CENTER);
            i++;
        }

    }

    @FXML
    public void createDates() {
        days = new StackPane[date.lengthOfMonth()];
        for (int i = 0; i < date.lengthOfMonth(); i++) {
            days[i] = new StackPane();
            Label label = new Label("" + (i + 1));
            label.setFont(Font.font("Versana", FontWeight.BOLD, 14));
            days[i].getChildren().add(label);
            StackPane.setAlignment(label, Pos.TOP_LEFT);
            StackPane.setMargin(label, new Insets(5, 0, 0, 5));//Top, Right, Bottom, Left insets
            ;
        }

    }

    public void getMLBJSON() throws IOException {//int teamID, int month, int lastDay) throws IOException {///change later to pass in selected home team(team ID)
        //  String url = readURL.createURL(147, date.getMonthValue(), date.lengthOfMonth());//teamID, month, last day of the month
        BufferedReader b = new BufferedReader(new FileReader("data.json"));
        Gson gson = new GsonBuilder().registerTypeAdapter(Dates[].class,
                new MyDeserializer()).setPrettyPrinting().create();
        dates
                = gson.fromJson(b, Dates[].class
                );
        //String jsonURL = readURL.readURLString("http://statsapi.mlb.com/api/v1/schedule/games/?sportId=1&teamId=147&season=2018&startDate=2018-06-01&endDate=2018-06-30");
        // Gson gson = new GsonBuilder().registerTypeAdapter(Dates[].class, new MyDeserializer()).setPrettyPrinting().create();
        // dates = gson.fromJson(jsonURL, Dates[].class);

    }

}
