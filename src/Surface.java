
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



/**
 * Handles visualization and animation of all objects in Canvas
 */
public class Surface extends Pane{
    
    private double w;   //width
    private double h;   //height
    
    private ArrayList<AABB> boxes;
    private HashMap<Integer, AABB> boxesMap;
    private GridController gridController;
    
    /*
    Initialization of Surface class
    */
    public Surface(double ww, double hh){
        this.w = ww;
        this.h = hh;
        
        boxes = new ArrayList<>();
        boxesMap = new HashMap<>();
        
        
        setBoundingBoxes();
        
        gridController = new GridController(boxes,boxesMap, w, h);
        
    }

    
    /*
    Sets n random positioned AABB boxes with some boundary and velocity
    */
    private void setBoundingBoxes(){
        
        Random rnd = new Random();
        
        int n = rnd.nextInt(20)+20;
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
        //naiveCollisionDetection();
        hierarchicalGridBroadPhase();
        getChildren().clear();
        
        for (AABB box:boxes){
            box.draw();
            box.update();
            getChildren().add(box);
        }
    } 
    
    /*
    Implementation of Hierarchical grid broad phase algorithm
    */
    public void hierarchicalGridBroadPhase(){
        gridController.updateBoxes();
    }
    /*
    Implementation of Naive n^2 broad phase collision detection  algorithm
    */
    public void naiveCollisionDetection(){
        
        for (int i = 0; i<boxes.size(); i++){
            AABB b1 = boxes.get(i);
            boolean collision = false;
            for (int j = 0; j<boxes.size(); j++){
                if (i != j){
                    
                    AABB b2 = boxes.get(j);
                    
                    if ( b1.getPosition().getX() <= b2.getPosition().getX()+b2.getW() &&
                            b1.getPosition().getX()+b1.getW() >= b2.getPosition().getX() &&
                            b1.getPosition().getY() <= b2.getPosition().getY()+b2.getH() &&
                            b1.getPosition().getY()+b1.getH() >= b2.getPosition().getY()){
                        b1.changeColor(Color.RED);
                        collision = true;
                    }                   
                    
                }
                
            }
            if (!collision){
                b1.changeColor(Color.BLUE);
            }
            
        }
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
