package lk.ijse.dep11;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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

    MediaPlayer mediaPlayer;

    public void initialize(){

        sdSound.setVisible(false);
        Platform.runLater(()->{
            mvMain.fitWidthProperty().bind(root.getScene().getWindow().widthProperty());
            mvMain.fitHeightProperty().bind(root.getScene().getWindow().heightProperty());
        });
        sdSound.valueProperty().addListener(e->{
            mediaPlayer.setVolume(sdSound.getValue());
        });

    }

    public void btnBrowseOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.mp4", "*.avi", "*.mkv","*.mp3","*.wav"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv"),
                new FileChooser.ExtensionFilter("Mp3 Files", "*.mp3"),
                new FileChooser.ExtensionFilter("Wave Files", "*.wav")
        );
        File file=fileChooser.showOpenDialog(root.getScene().getWindow());

        if(file!=null){
            txtBrowse.setText(file.getAbsolutePath());
            Media media=new Media(file.toURI().toString());
            mediaPlayer=new MediaPlayer(media);
        }else{
            txtBrowse.clear();
        }
    }

    public void btnPlayOnAction(ActionEvent actionEvent) {
        //btnPlay.setText(btnPlay.getText().equals("▶")?"॥":"▶");
        if(mediaPlayer!=null&&btnPlay.getText().equals("▶")) {
            mvMain.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            btnPlay.setText("॥");
        } else if (mediaPlayer!=null&&btnPlay.getText().equals("॥")) {
            mediaPlayer.pause();
            btnPlay.setText("▶");
        } else {return;}

    }

    public void btnStopOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            txtBrowse.clear();
            btnPlay.setText("▶");
        }
    }

    public void btnFastOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null)mediaPlayer.setRate(1.5);
    }

    public void btnNormalOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null)mediaPlayer.setRate(1.0);
    }



    public void btnSoundOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null)sdSound.setVisible(true);
    }

    public void sdSoundDragDone(DragEvent dragEvent) {

    }
}
