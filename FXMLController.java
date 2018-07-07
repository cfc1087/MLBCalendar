package com.mycompany.mlbcalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.mlbcalendar.business.Dates;
import com.mycompany.mlbcalendar.business.MyDeserializer;
import com.mycompany.mlbcalendar.business.TeamSelection;
import com.mycompany.mlbcalendar.business.readURL;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FXMLController implements Initializable {

    private Dates dates[];
    @FXML
    private GridPane gridPane;
    @FXML
    private ComboBox teamSelection;
    @FXML
    private HBox hbox;
    private int teamId;
    private LocalDateTime time;
    private LocalDate date;
    private ZonedDateTime gameTime;
    private StackPane days[];
    private String teamSelected;
    private Month monthSelected;
    private Spinner spinner;
    private String jsonURL;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        time = LocalDateTime.now();
        date = time.toLocalDate(); //today's date.
        monthSelected = date.getMonth();
        createTeamList();
        createMonthSpinner();
        createDates();//create lables for dates;
        createCalendar();
    }

    public void createCalendar() {

        gridPane.getChildren().clear();

        int firstDay = date.withDayOfMonth(1).getDayOfWeek().getValue();

        for (StackPane stackpane : days) {
            gridPane.add(stackpane, firstDay % 7, firstDay / 7);
            GridPane.setHgrow(stackpane, Priority.ALWAYS);
            GridPane.setVgrow(stackpane, Priority.ALWAYS);
            GridPane.setFillHeight(stackpane, Boolean.TRUE);
            GridPane.setFillWidth(stackpane, Boolean.TRUE);
            firstDay++;
        }
    }

    public void createDates() {

        days = new StackPane[date.lengthOfMonth()];
        for (int i = 0; i < days.length; i++) {
            days[i] = new StackPane();
            Label label = new Label("" + (i + 1));

            label.setFont(Font.font("Versana", FontWeight.BOLD, 14));
            days[i].getChildren().add(label);
            days[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            StackPane.setAlignment(label, Pos.TOP_LEFT);
            StackPane.setMargin(label, new Insets(5, 0, 0, 5));//Top, Right, Bottom, Left insets
        }
        createCalendar();
    }

    public void createTeamList() {

        ObservableList<String> list = FXCollections.observableArrayList();
        for (TeamSelection t : TeamSelection.values()) {
            list.add(t.getName());
        }

        teamSelection.getItems().addAll(list);
        teamSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                teamSelected = teamSelection.getValue().toString();
                teamSelected = teamSelected.replace('.', '_');
                teamId = TeamSelection.valueOf(teamSelected.replaceAll(" ", "").toUpperCase()).getId();
                teamSelected = teamSelected.replace('_','.');
                getMonthSchedule();
            }
        });

    }

    public void createMonthSpinner() {
        ObservableList<Month> list = FXCollections.observableArrayList();
        list.addAll(Month.values());
        spinner = new Spinner();
        SpinnerValueFactory<Month> value = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        spinner.setValueFactory(value);

        spinner.getValueFactory().setValue(date.getMonth());
        //  spinner.gets//add("Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL");
        hbox.getChildren().add(spinner);
        HBox.setMargin(spinner, new Insets(10, 0, 0, 0));
        spinner.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                monthSelected = (Month) spinner.getValue();
                date = LocalDate.of(2018, monthSelected, 1);
                createDates();
                if (teamSelected != null) {
                    getMonthSchedule();
                }
            }
        });

    }

    public void getMonthSchedule() {
        int lastDay = date.lengthOfMonth();
        int monthNum = date.getMonthValue();
        jsonURL = readURL.createURL(teamId, monthNum, lastDay);

        try {
            getMLBJSON(jsonURL);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getMLBJSON(String jsonURL) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Dates[].class, new MyDeserializer()).setPrettyPrinting().create();
        dates = gson.fromJson(readURL.readURLString(jsonURL), Dates[].class);
        getGameInfo();//create labels for home,away teams and time.
    }

    public void getGameInfo() {
        int i = 1;
        for (Dates d : dates) {
            if (prevGameExists(i)) {
                days[i - 1].setBackground(Background.EMPTY);
            }
            if (gameExists(d.getDay(), i)) {
                addGame(d, i);//if game exist on that date(i) add it to the StackPane days
            } else {
                i = findGame(d, i);
                addGame(d, i);
            }//else
            i++;
        }//for*/
        createCalendar();//add stackpanes to gridpane

    }

    public int findGame(Dates d, int i) {
        do {
            i++;
            if (prevGameExists(i)) {//if a previous game was found and removed, then change background.
                days[i - 1].setBackground(Background.EMPTY);
            }
        } while (d.getDay() != i);

        return i;
    }

    public boolean prevGameExists(int i) {//check if a label with game info already existed. prevents labels overlapping
        return days[i - 1].getChildren().remove((Label) days[i - 1].lookup("#gameInfo"));
    }

    public void addGame(Dates d, int i) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");

        gameTime = ZonedDateTime.parse(d.getGames()[0].getGameDate(), DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault()));

        String timeZone = gameTime.getZone().getDisplayName(TextStyle.SHORT, Locale.US);
        Label label = new Label();
        label.setTextAlignment(TextAlignment.CENTER);

        label.setText(d.getGames()[0].getAway() + "\n" + "@" + "\n" + d.getGames()[0].getHome() + "\n" + gameTime.toLocalTime().format(format) + " " + timeZone);

        label.setId("gameInfo");
        label.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 14));
        days[i - 1].getChildren().add(label);
        days[i - 1].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (isHome(d)) {
            homeColor(i);
            label.setTextFill(Color.WHITE);

        } else {
            days[i - 1].setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        StackPane.setAlignment(label, Pos.CENTER);
    }

    public boolean gameExists(int day, int i) {
        return day == i;
    }

    public boolean isHome(Dates d) {
        return d.getGames()[0].getHome().equals(teamSelected);
    }

    public void homeColor(int i) {
        Color color;
        teamSelected = teamSelected.replace('.','_');

        color = TeamSelection.valueOf(teamSelected.replaceAll(" ", "").toUpperCase()).getColor();
        BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        days[i - 1].setBackground(new Background(fill));

    }
}
