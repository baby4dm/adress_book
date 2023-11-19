package com.example.adress_book;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BookApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Книга");
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
        testData();
    }

    public static void main(String[] args) {
        launch();
    }

    private void testData(){
        CollectionAddressBook addressBook = new CollectionAddressBook();
        addressBook.fillTestData();
    }
}