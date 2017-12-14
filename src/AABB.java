
import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefan
 */
public class AABB extends Pane{

    private double surfaceWidth;
    private double surfaceHeight;
    
    
    private double x;
    private double y;
    private double width;
    private double height;
    
    private double velocityX;
    private double velocityY;
    
    
    public AABB(double width, double height){
        
        this.surfaceWidth = width;
        this.surfaceHeight = height;
        
        this.width = 50;
        this.height = 70;
        
        Random rnd = new Random();
        
        this.x = (rnd.nextDouble()*surfaceWidth)+1;
        
        if (this.x+this.width>=surfaceWidth){
            this.x = surfaceWidth-this.width - 1;
        }
        
        
        
        this.y = (rnd.nextDouble()*surfaceHeight);
        
        if (this.y+this.height>=surfaceHeight){
            this.y = surfaceHeight-this.height - 1;
        }
        
        
        this.velocityX = rnd.nextDouble();
        this.velocityY = rnd.nextDouble();
        
    
    }
    
    
    
    public void draw(){
        getChildren().clear();
        
        Rectangle rectangle = new Rectangle(this.x,this.y,this.width,this.height);
        rectangle.setFill(Color.BLUE);
        
        getChildren().add(rectangle);
        
    }
    
    public void update(){
        
        
        
        this.x += velocityX;
        this.y += velocityY;
        
        if(this.x <= 0 || (this.x + this.width) >= this.surfaceWidth ){
            negateVelocityX();
        }
        
        if(this.y <= 0 || (this.y + this.height) >= this.surfaceHeight ){
            negateVelocityY();
        }
        
    }
    
    public void negateVelocityX(){
        this.velocityX *= (-1);
    }
    
    public void negateVelocityY(){
        this.velocityY *= (-1);
    }
    
    
    
    
    
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return width;
    }

    
    public void setW(double width) {
        this.width = width;
    }

    
    public double getH() {
        return height;
    }

    public void setH(double height) {
        this.height = height;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
    
    
    
    
    
}
