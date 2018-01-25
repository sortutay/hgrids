
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Main class of project
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        Surface surface = new Surface(1500,700);
        
        Scene scene = new Scene(surface, 1500, 800);
        
        primaryStage.setTitle("Hierarchical Grids");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), e -> {
			surface.draw();
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
