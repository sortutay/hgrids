
import java.util.Random;
import javafx.geometry.Point2D;
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
    private int id;
    final double dt = 1.5;
    
    private double surfaceWidth;
    private double surfaceHeight;
    
    
   
    private double width;
    private double height;
    
    private Color color;
    
    
    
    private Point2D position;   //position x y

    
    private Point2D velocity;   // velocityX velocityY
    
    
    public AABB(int id, double width, double height){
        Random rnd = new Random();
        
        this.surfaceWidth = width;
        this.surfaceHeight = height;
        
        
        this.width = 20+rnd.nextInt(70);
        this.height = 20+rnd.nextInt(70);
        
        color = Color.BLUE;
        
        
        
        
        double x = (rnd.nextDouble()*surfaceWidth)+1;
        
        if (x+this.width>=surfaceWidth){
            x = surfaceWidth-this.width - 1;
        }
        
        
        
         double y = (rnd.nextDouble()*surfaceHeight);
        
        if (y+this.height>=surfaceHeight){
            y = surfaceHeight-this.height - 1;
        }
        
        position = new Point2D(x, y);
        
        
        double velocityX = rnd.nextDouble();
        double velocityY = rnd.nextDouble();
        
        velocity = new Point2D(velocityX, velocityY);
        
    
    }
    
    public void changeColor(Color c){
        this.color = c;
    }
    
    
    
    public void draw(){
        getChildren().clear();
        
        Rectangle rectangle = new Rectangle(position.getX(),position.getY(),this.width,this.height);
        rectangle.setFill(this.color);
        
        getChildren().add(rectangle);
        
    }
    
    public void update(){
        
        
        position = position.add(velocity.multiply(dt));
        
        
        if(position.getX() <= 0 || (position.getX() + this.width) >= this.surfaceWidth ){
            negateVelocityX();
        }
        
        if(position.getY() <= 0 || (position.getY() + this.height) >= this.surfaceHeight ){
            negateVelocityY();
        }
        
        
    }
    
    public void negateVelocityX(){
        velocity = new Point2D(velocity.getX()*(-1), velocity.getY());
    }
    
    public void negateVelocityY(){
        velocity = new Point2D(velocity.getX(), velocity.getY()*(-1));
    }
    
    
    
    
    
    public int getID() {
        return id;
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
    
    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    
    
    
    
    
    
}
