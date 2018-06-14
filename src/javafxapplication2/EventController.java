/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Christopher
 */
public class EventController {

    /*private LocalDate date;
    private LocalTime time;
    private String homeTeam;
    private String awayTeam;*/
    private String date;
    private String time;

    // private ArrayList<String> words;
    public EventController() {
        // words = new ArrayList<String>();
    }

    public void testCSV() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("EventTicketPromotionPrice.csv"));
        scanner.nextLine();
        int i = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String words[] = line.split(",");
            date = words[0];
            time = words[1];
            System.out.println(date+" @ "+time);
        }
    }
}
