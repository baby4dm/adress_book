package com.example.adress_book;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtPhoneNum;

    @FXML
    private TextField txtPip;

    private Person person;

    private boolean okClicked = false;

    private Stage dialogStage;

    private CollectionAddressBook addressBookImpl;
    private Controller mainController;


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public void initialize() {
        person = new Person("", "");
        txtPip.textProperty().bindBidirectional(person.pipProperty());
        txtPhoneNum.textProperty().bindBidirectional(person.phoneProperty());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setAddressBookImpl(CollectionAddressBook addressBookImpl) {
        this.addressBookImpl = addressBookImpl;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPip.textProperty().bindBidirectional(person.pipProperty());
        txtPhoneNum.textProperty().bindBidirectional(person.phoneProperty());
    }

}
