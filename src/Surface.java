
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.Pane;

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
        
        int n = rnd.nextInt(10)+1;
        
        for (int i = 0; i < n; i++){
            AABB box = new AABB(this.w,this.h);
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
            for (int j = 0; j<boxes.size(); j++){
                if (i != j){
                    
                    AABB b1 = boxes.get(i);
                    AABB b2 = boxes.get(j);
                    
                    if (b1.getX()+b1.getW() >= b2.getX() && b1.getX()+b1.getW() <= b2.getX()+b2.getW()){
                        if (b1.getY() >= b2.getY() && b1.getY() <= b2.getY()+b2.getH() ||
                                b1.getY()+b1.getH() >= b2.getY() && b1.getY()+b1.getH() <= b2.getY()+b2.getH()){
                            b1.negateVelocityX();
                           // b2.negateVelocityX();
                            //b1.negateVelocityY();
                           
                            System.out.println("prva");
                            break;
                            
                        }
                        
                    }
                    
                    else if (b1.getY()+b1.getH() >= b2.getY() && b1.getY()+b1.getH() <= b2.getY()+b2.getH()){
                        if (b1.getX() >= b2.getX() && b1.getX() <= b2.getX()+b2.getW() ||
                                b1.getX()+b1.getW() >= b2.getX() && b1.getX()+b1.getW() <= b2.getX()+b2.getW()){
                            b1.negateVelocityY();
                           // b2.negateVelocityY();
                           break;
                           
                            
                        }
                    }
                    
                    else if (b1.getX() >= b2.getX() && b1.getX() <= b2.getX()+b2.getW()){
                        if (b1.getY() >= b2.getY() && b1.getY() <= b2.getY()+b2.getH() ||
                                b1.getY()+b1.getH() >= b2.getY() && b1.getY()+b1.getH() <= b2.getY()+b2.getH()){
                            b1.negateVelocityX();
                           // b2.negateVelocityX();
                            //b1.negateVelocityY();
                            break;
                            
                            
                        }
                        
                    }
                    
                    else if (b1.getY() >= b2.getY() && b1.getY()<= b2.getY()+b2.getH()){
                        if (b1.getX() >= b2.getX() && b1.getX() <= b2.getX()+b2.getW() ||
                                b1.getX()+b1.getW() >= b2.getX() && b1.getX()+b1.getW() <= b2.getX()+b2.getW()){
                            b1.negateVelocityY();
                           // b2.negateVelocityY();
                           
                           break;
                            
                        }
                    }
                    
                    
                }
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
