
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 *
 * Represents one AABB box
 */
public class AABB extends Pane{
    private int id;
    final double dt = 1.5;
    
    private double surfaceWidth;
    private double surfaceHeight;
    
    private HashMap<Grid, HashSet<Integer>> gridPositions;

    
    
   
    private double width;
    private double height;
    
    private Color color;
    
    
    
    private Point2D position;   //position x y

    
    private Point2D velocity;   // velocityX velocityY
    
    /*
    Initialization of AABB box
    */
    public AABB(int id, double width, double height){
        Random rnd = new Random();
        
        this.surfaceWidth = width;
        this.surfaceHeight = height;
        
        
        this.width = 20+rnd.nextInt(100);
        this.height = 20+rnd.nextInt(100);
        
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
        
        gridPositions = new HashMap<>();
        this.id = id;
    }
    
    /*
    Changes color of AABB box
    */
    public void changeColor(Color c){
        this.color = c;
    }
    
    
    /*
    Draws AABB as rectangle into Canvas
    */
    public void draw(){
        getChildren().clear();
        
        Rectangle rectangle = new Rectangle(position.getX(),position.getY(),this.width,this.height);
        rectangle.setFill(this.color);
        Text t = new Text(position.getX()+width/2-10,position.getY()+height/2+10, id+"");
        t.setFont(new Font(20));
        
        getChildren().addAll(rectangle,t);
        
    }
    
    /*
    Updates position of AABB box in Canvas
    */
    public void update(){
        
        
        position = position.add(velocity.multiply(dt));
        
        
        if(position.getX() <= 0 || (position.getX() + this.width) >= this.surfaceWidth ){
            negateVelocityX();
        }
        
        if(position.getY() <= 0 || (position.getY() + this.height) >= this.surfaceHeight ){
            negateVelocityY();
        }
        
        
    }
    
    /*
    Negates velocity of X axis
    */
    public void negateVelocityX(){
        velocity = new Point2D(velocity.getX()*(-1), velocity.getY());
    }
    /*
    Negates velocity of Y axis
    */
    public void negateVelocityY(){
        velocity = new Point2D(velocity.getX(), velocity.getY()*(-1));
    }
    
    /*
    Adds position of AABB box in particular grid
    */
    public void addGridPosition(Grid g, int hashPos){
        if (gridPositions.get(g) == null){
            gridPositions.put(g, new HashSet<Integer>());
        }
        gridPositions.get(g).add(hashPos);
    }
    
    
    /*
    Getters and setters of private attributes
    */
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
    public HashMap<Grid, HashSet<Integer>> getGridPositions() {
            return gridPositions;
    }
    public void setGridPositions(HashMap<Grid, HashSet<Integer>> gridPositions) {
        this.gridPositions = gridPositions;
    }
    
    public String toString(){
        return this.id+"";
    }
    
    
    
    
    
    
}
