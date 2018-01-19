
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefan
 */
public class Surface extends Pane{
    
    private double w;   //width
    private double h;   //height
    
    private ArrayList<AABB> boxes;
    
    
    
    public Surface(double ww, double hh){
        this.w = ww;
        this.h = hh;
        
        boxes = new ArrayList<>();
        
        setBoundingBoxes();
        
    }

    
    
    private void setBoundingBoxes(){
        
        Random rnd = new Random();
        
        int n = rnd.nextInt(20)+20;
        
        for (int i = 0; i < n; i++){
            AABB box = new AABB(i, this.w,this.h);
            boxes.add(box);
        }
    }
    
    public void draw(){
        update();
        getChildren().clear();
        
        for (AABB box:boxes){
            box.draw();
            box.update();
            getChildren().add(box);
        }
    } 
    
    public void update(){
        
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
