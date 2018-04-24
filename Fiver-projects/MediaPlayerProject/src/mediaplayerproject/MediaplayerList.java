package mediaplayerproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

public class MediaplayerList extends VBox{
ListView<String> listview ;
        String[] names= {"I see fire ","Daemons"};
        MediaPlayer player;
        Button addFile;
       
    public MediaplayerList(MediaPlayer player) {
        this.player=player;
        addFile=new Button("+");
        listview=  new ListView();
        listview.getItems().addAll(names);
        listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setAlignment(Pos.TOP_LEFT);
        setPadding(new Insets(5,10,5,10));
        getChildren().add(listview);
        getChildren().add(addFile);
        
        
    }

    
}
