package com.example.menuapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);

        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Veranda", FontWeight.BOLD,12));
        Label timeLabel = new Label("Time:");
        timeLabel.setFont(Font.font("Veranda", FontWeight.BOLD,12));
        Label backgroundColorRgb = new Label("Background rgb:");
        backgroundColorRgb.setFont(Font.font("Veranda", FontWeight.BOLD,12));
        Label backgroundColorHex = new Label("Background Hex:");
        backgroundColorHex.setFont(Font.font("Veranda", FontWeight.BOLD,12));

        TextField dateOutField = new TextField();
        dateOutField.setEditable(false);
        TextField timeOutField = new TextField();
        timeOutField.setEditable(false);
        TextField backgroundRgbOutField = new TextField();
        backgroundRgbOutField.setEditable(false);
        TextField backgroundHexOutField = new TextField();
        backgroundHexOutField.setEditable(false);

        primaryStage.setTitle("Menu Application");
        primaryStage.setWidth(350);
        primaryStage.setHeight(155);

        MenuItem menuItem1 = new MenuItem("Show Time");
        MenuItem menuItem2 = new MenuItem("To text");
        MenuItem menuItem3 = new MenuItem("Change background");
        MenuItem menuItem4 = new MenuItem("Exit");
        CustomMenuItem customMenuItem = new CustomMenuItem();

        MenuButton menuButton;
        menuButton = new MenuButton("Options", null, menuItem1, menuItem2, menuItem3, menuItem4, customMenuItem);

        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();
                String dateString = date.toString();
                String timeString = dtf.format(time);
                dateOutField.setText(dateString);
                timeOutField.setText(timeString);
            }
        });

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PrintWriter outPrint = null;
                try {
                    outPrint = new PrintWriter("log.txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String dateText = dateOutField.getText();
                String timeText = timeOutField.getText();
                String rgbText = backgroundRgbOutField.getText();
                String hexText = backgroundColorHex.getText();
                outPrint.println("Date:" + dateText);
                outPrint.println("Time:" + timeText);
                outPrint.println("Background RGB: " + rgbText);
                outPrint.println("Background Hex: " + hexText);
                outPrint.close();
            }
        });

        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Random randInt = new Random();
                int randGreen = randInt.nextInt(100, 255);
                Color green = Color.rgb(0, randGreen, 0);
                String hex = String.format("#%02x%02x%02x", 0, randGreen, 0);
                gridPane.setBackground(Background.fill(green));
                String rgbValue = String.format("(0, %d, 0)",randGreen);
                backgroundRgbOutField.setText(rgbValue);
                backgroundHexOutField.setText(hex);
            }
        });

        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        gridPane.add(menuButton, 2,2);
        gridPane.add(dateLabel,0,2);
        gridPane.add(timeLabel, 0, 3);
        gridPane.add(backgroundColorRgb,0, 4);
        gridPane.add(backgroundColorHex, 0, 5);
        gridPane.add(dateOutField, 1, 2);
        gridPane.add(timeOutField, 1, 3);
        gridPane.add(backgroundRgbOutField, 1, 4);
        gridPane.add(backgroundHexOutField, 1, 5);
        gridPane.setBackground(Background.fill(Color.ALICEBLUE));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}