package functions;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public  class SceneChange {
   
    public void sceneTransition(StackPane stackPane, AnchorPane anchorPane, String url) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = anchorPane.getScene();
        //Set Y of second scene to Height of window
        root.translateYProperty().set(scene.getHeight());
        //Add second scene. Now both first and second scene is present
        stackPane.getChildren().add(root);
 
        //Create new TimeLine animation
        Timeline timeline = new Timeline();
        //Animate Y property
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
        timeline.getKeyFrames().add(kf);
        //After completing animation, remove first scene
        timeline.setOnFinished(t -> {
            stackPane.getChildren().remove(anchorPane);
        });
        timeline.play();
    }
}
