module com.example.adress_book {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.adress_book to javafx.fxml;
    exports com.example.adress_book;
}