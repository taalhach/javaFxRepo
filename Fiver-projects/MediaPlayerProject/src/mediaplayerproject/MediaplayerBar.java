package mediaplayerproject;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaplayerBar extends HBox{
    MediaPlayer player;
    Slider progressbar = new Slider();
    Slider volumeRocker= new Slider();
    Button playbtn = new Button("||");
     Button fforward = new Button(">>");
     Button next = new Button(">");
     Button bplay = new Button("<<");
    Button back = new Button("<");
   
    Label volumeLabel =  new  Label("Volume: ");
    
    public MediaplayerBar(MediaPlayer player){
    this.player=player;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,10,5,10));
        volumeRocker.setPrefHeight(70);
        volumeRocker.setMinWidth(30);
        volumeRocker.setValue(100);
        HBox.setHgrow(progressbar, Priority.ALWAYS);
        playbtn.setPrefWidth(30);
        getChildren().add(bplay);
        getChildren().add(back);
        getChildren().add(playbtn);
        getChildren().add(next);
        getChildren().add(fforward);
        getChildren().add(progressbar);
        getChildren().add(volumeLabel);
        getChildren().add(volumeRocker);
        
        
        playbtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status status = player.getStatus();
                if(status==MediaPlayer.Status.PLAYING){
                pause(player);
                }else if(status==MediaPlayer.Status.PAUSED){
                resume(player);
                }
            }
        });
        fforward.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                fastForward();
            }
        });
        bplay.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                reWindBackward();
            }
        });
        player.currentTimeProperty().addListener(new InvalidationListener(){
        @Override
        public void invalidated(Observable observable) {
            updateProgressBar();   
        }
        });
        progressbar.valueProperty().addListener(new InvalidationListener(){
        @Override
        public void invalidated(Observable observable) {
           if(progressbar.isPressed()){
               
               skipSomepart();}    
        }
        });
        volumeRocker.valueProperty().addListener(new InvalidationListener(){
        @Override
        public void invalidated(Observable observable) {
           if(volumeRocker.isPressed()){
               
               adjustVolume();}    
        }
        });
        
                
    }
    public void pause(MediaPlayer player){
    player.pause();
    playbtn.setText(">");
    }
    public void resume(MediaPlayer player){
    player.play();
    playbtn.setText("||");
    }
    public void updateProgressBar(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
           progressbar.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
            }
        });
    }
   public void skipSomepart(){
   player.seek(player.getMedia().getDuration().multiply(progressbar.getValue()/100));
   
   }
   public void adjustVolume(){
   player.setVolume(volumeRocker.getValue()/100);
   
   }
   public void fastForward(){
       System.out.println(player.getCurrentTime().add(Duration.seconds(5)));
   player.seek(player.getCurrentTime().add(Duration.seconds(5)));
   
   
   }
   public void reWindBackward(){
       System.out.println(player.getCurrentTime().add(Duration.seconds(5)));
   player.seek(player.getCurrentTime().subtract(Duration.seconds(5)));
   
   
   }
}
