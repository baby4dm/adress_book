package com.example.adress_book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoPlayer implements Initializable {

    @FXML
    private Button pauseButton;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button playButton;
    @FXML
    private Button resetButton;
    private Stage newStage;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button btnAudio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file = new File("video.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

    }
    @FXML
    void playButton_method() {
        mediaPlayer.play();
    }
    @FXML
    void pauseButton_method() {
        mediaPlayer.pause();
    }
    @FXML
    void resetButton_method(ActionEvent event) {
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
            mediaPlayer.play();
        }
    }

    @FXML
    public void audioPlayer() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("audio.fxml"));
            Parent root = loader.load();
            AudioPlayer audioPlayer = loader.getController();

            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("Video");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(btnAudio.getScene().getWindow());
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);

            audioPlayer.setNewStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setNewStage(Stage newStage) {
        this.newStage = newStage;
    }


}
