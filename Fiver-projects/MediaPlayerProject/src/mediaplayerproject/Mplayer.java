package mediaplayerproject;

import java.awt.Color;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Mplayer extends BorderPane {
    
    String path ;
    //String path = new File("src/media/song.mp4").getAbsolutePath();
        
        Media mediaFile;
        MediaPlayer player;
        MediaView view ;
        MediaplayerBar bar;
        MediaplayerList list;
        Image img;
    
    public Mplayer() {
        String path = new File("src\\media\\song.mp3").getAbsolutePath();
        mediaFile= new Media(new File(path).toURI().toString());
        player= new MediaPlayer(mediaFile);
        view = new MediaView(player);
        
        bar= new MediaplayerBar(player);
        list= new MediaplayerList(player);
        setCenter(view);
        setBorder(new Border((new BorderStroke(javafx.scene.paint.Color.BLUE,  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        setBottom(bar);
        setRight(list);

        player.play();
        
        setPadding(new Insets(5,10,5,10));
 //         setBottom(bar);
//        setRight(list);
           


    }
        
}
