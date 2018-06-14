/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Christopher
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane bp;

    private LocalDateTime time;
    private LocalDate date;

    private Label labels[];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        time = LocalDateTime.now();
        date = time.toLocalDate();
        createDates();
        test();
    }

    @FXML
    public void test() {

        
        int firstDay = date.withDayOfMonth(1).getDayOfWeek().getValue();
        

        for (int days = 0; days < labels.length; days++) {
            
            gridPane.add(labels[days], firstDay % 7, firstDay / 7);
            GridPane.setHgrow(labels[days], Priority.ALWAYS);
            GridPane.setVgrow(labels[days], Priority.ALWAYS);
            GridPane.setHalignment(labels[days], HPos.LEFT);
            GridPane.setMargin(labels[days], new Insets(5));
            GridPane.setValignment(labels[days], VPos.TOP);
            GridPane.setFillHeight(labels[days], Boolean.TRUE);
            GridPane.setFillWidth(labels[days], Boolean.TRUE);
            firstDay++;
        }
    }

    @FXML
    public void createDates() {
        labels = new Label[date.lengthOfMonth()];
        //  labels = new Label[7];
        int j = 1;
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label(""+j);
          
            labels[i].setFont(Font.font("Versana",FontWeight.BOLD,14));
            
            ++j;
        }
        
    }

}
