package lk.ijse.dep11;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class MainViewController {
    public AnchorPane root;
    public TextField txtBrowse;
    public Button btnBrowse;
    public MediaView mvMain;
    public Button btnPlay;
    public Button btnStop;
    public Button btnFast;
    public Button btnNormal;
    public Button btnSound;
    public Slider sdSound;
    public Slider sldDuration;

    MediaPlayer mediaPlayer;
    boolean soundVisibility;

    public void initialize() {
        soundVisibility=false;
        sdSound.setVisible(soundVisibility);
        sdSound.setValue(100);
        Platform.runLater(() -> {
            mvMain.fitWidthProperty().bind(root.getScene().getWindow().widthProperty());
            mvMain.fitHeightProperty().bind(root.getScene().getWindow().heightProperty());

        });
        sdSound.valueProperty().addListener(e -> {
            mediaPlayer.setVolume(sdSound.getValue());
        });
        sldDuration.valueProperty().addListener(observable -> {
            if(sldDuration.isValueChanging()){
                mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(sldDuration.getValue()/100));
            }
        });

    }

    public void btnBrowseOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.mp4", "*.avi", "*.mkv", "*.mp3", "*.wav"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv"),
                new FileChooser.ExtensionFilter("Mp3 Files", "*.mp3"),
                new FileChooser.ExtensionFilter("Wave Files", "*.wav")
        );
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (file != null) {
            txtBrowse.setText(file.getAbsolutePath());
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        } else {
            txtBrowse.clear();
        }
    }

    public void btnPlayOnAction(ActionEvent actionEvent) {
        //btnPlay.setText(btnPlay.getText().equals("▶")?"॥":"▶");
        if (mediaPlayer != null && btnPlay.getText().equals("▶")) {
            mvMain.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            btnPlay.setText("॥");
        } else if (mediaPlayer != null && btnPlay.getText().equals("॥")) {
            mediaPlayer.pause();
            btnPlay.setText("▶");
        } else {
            return;
        }
    }

    public void btnStopOnAction(ActionEvent actionEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            txtBrowse.clear();
            btnPlay.setText("▶");
        }
    }

    public void btnFastOnAction(ActionEvent actionEvent) {
        if (mediaPlayer != null) mediaPlayer.setRate(1.5);
    }

    public void btnNormalOnAction(ActionEvent actionEvent) {
        if (mediaPlayer != null) mediaPlayer.setRate(1.0);
    }

    public void btnSoundOnAction(ActionEvent actionEvent) {


                soundVisibility = !soundVisibility;
                sdSound.setVisible(soundVisibility);


    }
}
