package com.mycompany.mlbcalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.mlbcalendar.business.Dates;
import com.mycompany.mlbcalendar.business.MyDeserializer;
import com.mycompany.mlbcalendar.business.readURL;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FXMLController implements Initializable {

    private Dates dates[];
    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane bp;
    int teamId = 147;
    private LocalDateTime time;
    private LocalDate date;
    private LocalDate gameDates[]; // To-Do: set size to selected month
    private Label labels[];
   

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
        createCalendar();//creates calendar with dates(Labels))
    }

    @FXML
    public void createCalendar() {

        int year = Integer.parseInt(dates[0].getDate().substring(0, 4));
        int month = Integer.parseInt(dates[0].getDate().substring(4, 6));
        gameDates = new LocalDate[date.lengthOfMonth()];

        int firstDay = date.withDayOfMonth(1).getDayOfWeek().getValue();

        for (Label label : labels) {
            gridPane.add(label, firstDay % 7, firstDay / 7);
            GridPane.setHgrow(label, Priority.ALWAYS);
            GridPane.setVgrow(label, Priority.ALWAYS);
            GridPane.setHalignment(label, HPos.LEFT);
            GridPane.setMargin(label, new Insets(5));
            GridPane.setValignment(label, VPos.TOP);
            GridPane.setFillHeight(label, Boolean.TRUE);
            GridPane.setFillWidth(label, Boolean.TRUE);
            firstDay++;
        }

    }

    @FXML
    public void createDates() {
      
        labels = new Label[date.lengthOfMonth()];

        int j = 1;
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label("" + j);

            labels[i].setFont(Font.font("Versana", FontWeight.BOLD, 14));

            ++j;
        }

    }

    public void getMLBJSON() throws IOException {//int teamID, int month, int lastDay) throws IOException {///change later to pass in selected home team(team ID)
        // String url = readURL.createURL(147, date.getMonthValue(), date.lengthOfMonth());//teamID, month, last day of the month
        String jsonURL = readURL.readURLString("http://statsapi.mlb.com/api/v1/schedule/games/?sportId=1&teamId=147&season=2018&startDate=2018-06-01&endDate=2018-06-30");
        Gson gson = new GsonBuilder().registerTypeAdapter(Dates[].class, new MyDeserializer()).setPrettyPrinting().create();
        dates = gson.fromJson(jsonURL, Dates[].class);
    }

}
