
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefan
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        
        Surface surcafe = new Surface(1500,800);
        
        Scene scene = new Scene(surcafe, surcafe.getW(), surcafe.getH());
        
        primaryStage.setTitle("Hierarchical Grids");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), e -> {
			surcafe.draw();
		}));
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.play();
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
