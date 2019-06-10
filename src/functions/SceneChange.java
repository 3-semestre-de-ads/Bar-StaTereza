package functions;

import fxml.FXMLSystemInsertGameController;
import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Classe responsável por criar as transições de tela do sistema.
 * @author Isaías de França Leite
 */
public class SceneChange {
    
    /**
     * Realizar a transição entre telas.
     * @param stackPane - É componente da tela atual.  
     * @param anchorPane - É componente da tela atual.
     * @param url - É o endereço da próxima tela.
     * @throws IOException
     */
    public void sceneTransition(StackPane stackPane, AnchorPane anchorPane, String url) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = anchorPane.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.DISCRETE);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackPane.getChildren().remove(anchorPane);
        });
        timeline.play();
    }
    
    /**
     * Realizar a transição entre telas com parâmetro.
     * @param stackPane - É componente da tela atual.  
     * @param anchorPane - É componente da tela atual.
     * @param fxmlloader É componete da tela com construtor dela.
     * @throws IOException
     */
    public void sceneTransition(StackPane stackPane, AnchorPane anchorPane, FXMLLoader fxmlloader) throws IOException{
        Parent root = fxmlloader.load();
        Scene scene = anchorPane.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.DISCRETE);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackPane.getChildren().remove(anchorPane);
        });
        timeline.play();
    }
    
    
    /**
     * Realizar a transição entre telas.
     * @param borderpane - É componente principal da tela.
     * @param url - É o endereço da próxima tela.
     */
    public void sceneTransition(BorderPane borderpane, String url)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {
            //Logger.getLogger(FXMLSystemGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }
    
   public void sceneTransition(BorderPane borderpane, FXMLLoader fxmlloader)
    {
        Parent root = null;
        try {
            root = fxmlloader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXMLSystemGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }
}
