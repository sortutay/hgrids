
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



/**
 * Handles visualization and animation of all objects in Canvas
 */
public class Surface extends Pane{
    
    private double w;   //width
    private double h;   //height
    
    private ArrayList<AABB> boxes;
    private HashMap<Integer, AABB> boxesMap;
    private GridController gridController;
    
    private Text hierarchicalResult;
    private Text naiveResult;
    
    /*
    Initialization of Surface class
    */
    public Surface(double ww, double hh){
        this.w = ww;
        this.h = hh;
        
        boxes = new ArrayList<>();
        boxesMap = new HashMap<>();
        
        hierarchicalResult = new Text();
        hierarchicalResult.setX(10);
        hierarchicalResult.setY(720);
        hierarchicalResult.setFont(new Font(20));
        
        
        naiveResult = new Text();
        naiveResult.setX(10);
        naiveResult.setY(750);
        naiveResult.setFont(new Font(20));
        
        
        setBoundingBoxes();
        
        gridController = new GridController(boxes,boxesMap, w, h);
        
    }

    
    /*
    Sets n random positioned AABB boxes with some boundary and velocity
    */
    private void setBoundingBoxes(){
        
        Random rnd = new Random();
        
        int n = rnd.nextInt(20)+5;
        //int n = 10;
        for (int i = 0; i < n; i++){
            System.out.println(i);
            AABB box = new AABB(i, this.w,this.h);
            boxes.add(box);
            boxesMap.put(i, box);
        }
        PairManagement.getInstance(n);
        System.out.println(boxesMap);
    }
    
    /*
    Draws all objects into Canvas
    */
    public void draw(){
        getChildren().clear();
        
        naiveCollisionDetection();
        
        hierarchicalGridBroadPhase();
        
        
        
        for (AABB box:boxes){
            box.draw();
            box.update();
            getChildren().add(box);
        }
        
        Line line = new Line();
        line.setStartX(0);
        line.setEndX(1500);
        line.setStartY(700);
        line.setEndY(700);
        
        getChildren().addAll(hierarchicalResult,naiveResult,line);
    } 
    
    /*
    Implementation of Hierarchical grid broad phase algorithm
    */
    public void hierarchicalGridBroadPhase(){
        
        String result = gridController.updateBoxes().toString();
        
        hierarchicalResult.setText("HierarchicalGrids collision detection -> Collided boxes: "+result);
    }
    /*
    Implementation of Naive n^2 broad phase collision detection  algorithm
    */
    public void naiveCollisionDetection(){
        ArrayList<Collision> result = new ArrayList<>();
        
        for (int i = 0; i<boxes.size(); i++){
            AABB b1 = boxes.get(i);
            for (int j = 0; j<boxes.size(); j++){
                if (i < j){
                    
                    AABB b2 = boxes.get(j);
                    
                    if ( b1.getPosition().getX() <= b2.getPosition().getX()+b2.getW() &&
                            b1.getPosition().getX()+b1.getW() >= b2.getPosition().getX() &&
                            b1.getPosition().getY() <= b2.getPosition().getY()+b2.getH() &&
                            b1.getPosition().getY()+b1.getH() >= b2.getPosition().getY()){
                        result.add(new Collision(b1.getID(), b2.getID()));
                    }                   
                    
                }
                
            }
            
            
        }
       
        naiveResult.setText("Naive collision detection -> Collided boxes: "+result.toString());
    }
    
    
    /*
    Getters and Setters of private attributes weight and height of Surface 
    */
    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
    
}
