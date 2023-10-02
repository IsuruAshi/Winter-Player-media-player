package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

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
    public Button btnPause;
    MediaPlayer mediaPlayer;

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
        if(mediaPlayer!=null) {
            mvMain.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

    public void btnStopOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            txtBrowse.clear();
        }
    }

    public void btnFastOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null)mediaPlayer.setRate(1.5);
    }

    public void btnNormalOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null)mediaPlayer.setRate(1.0);
    }

    public void btnPauseOnAction(ActionEvent actionEvent) {
        if(mediaPlayer!=null) mediaPlayer.pause();
    }
}
