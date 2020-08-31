package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    MediaPlayer player;

    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView backimg;

    @FXML
    private ImageView muavi;

    @FXML
    private Button infobtn;

    @FXML
    private Button prebtnn;

    @FXML
    private Text textview;

    @FXML
    private Button playbutton1;

    @FXML
    private Button nextBtnn;

    @FXML
    private Button ytbtn;

    @FXML
    private Text nametext;

    @FXML
    private Button videobtn;

    @FXML
    private ImageView youtubeico;

    @FXML
    private Slider volumeslider;

    @FXML
    private Button closeyt;

    @FXML
    private Button musicbtn;

    @FXML
    private Button choose;

    @FXML
    private ImageView user;

    @FXML
    private Slider timeslider;

    @FXML
    private WebView youtube;
    private WebEngine e;

    private Boolean info = false;

    @FXML
    void btn(ActionEvent event) {
        System.out.println("hi");

    }

    @FXML
    void openSongMenu(ActionEvent event) {
        try {
            System.out.println("opened");

        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        nametext.setText(file.getName());

        Media m = new Media(file.toURI().toURL().toString());
        if(player!=null){
            player.dispose();
        }
        player = new MediaPlayer(m);
        mediaView.setMediaPlayer(player);

        volumeslider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                player.setVolume(volumeslider.getValue()/10);
            }
        });


        player.setOnReady(()->{
            timeslider.setMin(0);
            timeslider.setMax(player.getMedia().getDuration().toSeconds());
            timeslider.setValue(0);
            try {
                playbutton1.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/icon.png"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                Duration d= player.getCurrentTime();
                timeslider.setValue(d.toSeconds());
            }
        });

        timeslider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(timeslider.isPressed()){
                    double val = timeslider.getValue();
                    player.seek(new Duration(val*1000));
                }
            }
        });

    }catch(Exception e){
         e.printStackTrace();
        }

    }

    @FXML
    void play(ActionEvent event) {
       try{
           MediaPlayer.Status status =player.getStatus();
           if(status==MediaPlayer.Status.PLAYING){
               //pause
               player.pause();
               // playbutton1.setText("Play");
               playbutton1.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/icon.png"))));
           }else{
               player.play();
               //  playbutton1.setText("Pause");
               playbutton1.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/pause2.png"))));
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @FXML
    void ytbutton(ActionEvent event) {
        System.out.println("yt opened");
        nametext.setText("Playing On Youtube");
        youtube.setOpacity(1);
        e.load("https://www.youtube.com/");
    }

    @FXML
    void closeweb(ActionEvent event) {
        youtube.getEngine().load(null);
        youtube.setOpacity(0);
        nametext.setText("Choose a file to play");
    }

    @FXML
    void music(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File("C:\\Users\\KRITHIN\\Music"));
            File file = chooser.showOpenDialog(null);
            nametext.setText(file.getName());

            Media m = new Media(file.toURI().toURL().toString());
            if(player!=null){
                player.dispose();
            }
            player = new MediaPlayer(m);
            mediaView.setMediaPlayer(player);

            volumeslider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    player.setVolume(volumeslider.getValue()/10);
                }
            });


            player.setOnReady(()->{
                timeslider.setMin(0);
                timeslider.setMax(player.getMedia().getDuration().toSeconds());
                timeslider.setValue(0);
                try {
                    playbutton1.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/icon.png"))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });

            player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    Duration d= player.getCurrentTime();
                    timeslider.setValue(d.toSeconds());
                }
            });

            timeslider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(timeslider.isPressed()){
                        double val = timeslider.getValue();
                        player.seek(new Duration(val*1000));
                    }
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void video(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File("C:\\Users\\KRITHIN\\Videos"));
            File file = chooser.showOpenDialog(null);
            nametext.setText(file.getName());

            Media m = new Media(file.toURI().toURL().toString());
            if(player!=null){
                player.dispose();
            }
            player = new MediaPlayer(m);
            mediaView.setMediaPlayer(player);


            volumeslider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    player.setVolume(volumeslider.getValue()/10);
                }
            });


            player.setOnReady(()->{
                timeslider.setMin(0);
                timeslider.setMax(player.getMedia().getDuration().toSeconds());
                timeslider.setValue(0);
                try {
                    playbutton1.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/icon.png"))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });

            player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    Duration d= player.getCurrentTime();
                    timeslider.setValue(d.toSeconds());
                }
            });

            timeslider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(timeslider.isPressed()){
                        double val = timeslider.getValue();
                        player.seek(new Duration(val*1000));
                    }
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try{
           e = youtube.getEngine();
           volumeslider.setValue((1.0*10));

            playbutton1.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/icon.png"))));
            muavi.setImage(new Image(new FileInputStream("src/icons/sideimg.png")));
            nextBtnn.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/next2.png"))));
            prebtnn.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/back13.png"))));
            choose.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/filicon.png"))));
            musicbtn.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/musicicon.png"))));
            videobtn.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/vidicon.png"))));
            ytbtn.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/youtube.png"))));
            backimg.setImage(new Image(new FileInputStream("src/icons/mainimg1.png")));
            infobtn.setGraphic( new ImageView(new Image(new FileInputStream("src/icons/music.png"))));
            user.setImage(new Image(new FileInputStream("src/icons/users.png")));
            choose.setCursor(Cursor.HAND);
            ytbtn.setCursor(Cursor.HAND);
            musicbtn.setCursor(Cursor.HAND);
            playbutton1.setCursor(Cursor.HAND);
            videobtn.setCursor(Cursor.HAND);

            Boolean info = false;
            textview.setOpacity(0);


       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @FXML
    void prebuttonclick(ActionEvent event) {
        Double d = player.getCurrentTime().toSeconds();
        d=d-10;
        player.seek(new Duration(d*1000));

    }
    @FXML
    void nextbuttonclick(ActionEvent event) {
        Double d = player.getCurrentTime().toSeconds();
        d=d+10;
        player.seek(new Duration(d*1000));
    }

    @FXML
    void info(ActionEvent event) {
        if(info==false) {
            textview.setOpacity(1);
            info = true;
        }else{
            textview.setOpacity(0);
            info=false;
        }
    }

}
