package com.company.jdvboef2;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false&serverTimezone=Europe/Brussels", "root", "Vdab")){
            try(Statement stat = conn.createStatement()){
                try(ResultSet rs = stat.executeQuery("SELECT * from reis")){
                    while (rs.next()){
                        String bestemming = rs.getString("bestemming");
                        LocalDate beginDatum = rs.getDate("startdatum").toLocalDate();
                        LocalDate eindDatum = rs.getDate("einddatum").toLocalDate();
                        Period periode = Period.between (beginDatum, eindDatum);
                        int aantalDagen = periode.getDays();
                        System.out.printf("Je gaat naar %s van %s tot %s: %d dagen%n", bestemming, beginDatum.format(formatter), eindDatum.format(formatter), aantalDagen);


                    }

                }
            }
        }



    }
}
