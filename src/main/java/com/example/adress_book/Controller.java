package com.example.adress_book;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;


    @FXML
    private TableColumn<Person, String> columnPip;
    @FXML
    private TableView<Person> tableAddressBook;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label labelCount;

    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    Label label;
    @FXML
    private Stage newStage;
    @FXML
    private Parent root;
    @FXML
    private Stage editDialogStage;
    @FXML
    private FXMLLoader fxmlLoader = new FXMLLoader();
    @FXML
    private EditController editController;
    @FXML
    private AddController addController;
    @FXML
    private Button btnOtherLabs;


    @FXML
    public void initialize(){
        columnPip.setCellValueFactory(new PropertyValueFactory<Person, String>("pip"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                updateCountLabel();
            }
        });
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());

    }

    private void updateCountLabel() {
        labelCount.setText("Кількість записів: " + addressBookImpl.getPersonList().size());
    }

    @FXML
    void onAddButtonClick(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
            Parent root = fxmlLoader.load();
            AddController addController = fxmlLoader.getController();

            addController.setMainController(this);
            addController.setAddressBookImpl(addressBookImpl);

            Stage newStage = new Stage();
            newStage.setTitle("Adding");
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(btnAdd.getScene().getWindow());
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            addController.setDialogStage(newStage);
            newStage.showAndWait();

            if (addController.isOkClicked()){
                Person newPerson = addController.getPerson();

                if (newPerson != null){
                    addressBookImpl.add(newPerson);
                    updateTable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEditButtonClick() {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
                Parent root = loader.load();
                EditController editController = loader.getController();
                editController.setPerson(selectedPerson);

                Stage editDialogStage = new Stage();
                editDialogStage.setTitle("Editing");
                editDialogStage.initModality(Modality.WINDOW_MODAL);
                editDialogStage.initOwner(btnEdit.getScene().getWindow());
                Scene scene = new Scene(root);
                editDialogStage.setScene(scene);

                editController.setDialogStage(editDialogStage);

                editDialogStage.showAndWait();

                if (editController.isOkClicked()) {
                    addressBookImpl.update(selectedPerson);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onDeleteButtonClick() {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");

            alert.setContentText("Are you sure, that you want delete?");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (result == ButtonType.OK) {
                addressBookImpl.delete(selectedPerson);
            }
        }
    }

    @FXML
    public void onSearchButtonClick() {
        String searchTerm = txtSearch.getText().toLowerCase();
        ObservableList<Person> searchResults = addressBookImpl.search(searchTerm);
        tableAddressBook.setItems(searchResults);
    }

    public void updateTable() {
        Platform.runLater(() -> {
            tableAddressBook.setItems(addressBookImpl.getPersonList());
        });
    }

    @FXML
    public void exitApplication() {
        Platform.exit();
    }

    @FXML
    public void nextLab() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("testing.fxml"));
            Parent root = loader.load();
            TestingController testingController = loader.getController();

            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("PR 6");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(btnOtherLabs.getScene().getWindow());
            otherDialogStage.setMinHeight(480);
            otherDialogStage.setMinWidth(855);
            otherDialogStage.setMaxHeight(480);
            otherDialogStage.setMaxWidth(855);
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);

            testingController.setDialogStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}