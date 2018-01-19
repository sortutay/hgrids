
import com.sun.javafx.scene.layout.region.Margins;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;

/**
 *
 * @author Stefan
 */
public class Grid {
    
    private double cellSize;
    
    private double surfaceWidth;
    private double surfaceHeight; 
    
    private Bucket[] cells;
    
    
    public Grid(double w, double h, int c){
        this.cellSize = c;
        
        this.surfaceWidth = w;
        this.surfaceHeight = h;
        
        double i = surfaceHeight/cellSize;
        double j = surfaceWidth/cellSize;
        
        cells = new Bucket[(int) Math.ceil(j)*(int) Math.ceil(i)];
        
    }
    
    private int hash(double[] point) {
        return (((int)point[0]*1103) ^ ((int)point[1]*1567)) % cells.length; 
    }
    
    private void createBucket(int id) {
        if (cells[id] == null){
            cells[id] = new Bucket();
        }
    }
    
    public void add(AABB aabb) {
        if (aabb.getW() > cellSize || aabb.getH() > cellSize) {
            System.out.println("AABB with id "+aabb.getID()+ "in grid " +cellSize +" is too big");
            return;
        }
        Point2D position = aabb.getPosition();
        double[] leftUpperCell = {position.getX() / cellSize, position.getY() / cellSize};
        double[] leftLowerCell = {position.getX() / cellSize, (position.getY()+aabb.getH()) / cellSize};
        double[] rightUpperCell = {(position.getX()+aabb.getW()) / cellSize, position.getY() / cellSize};
        double[] rightLowerCell = {(position.getX()+aabb.getW()) / cellSize, (position.getY()+aabb.getH()) / cellSize};
        
        int leftUpperHash = hash(leftUpperCell);
        int leftLowerHash = hash(leftLowerCell);
        int rightUpperHash = hash(rightUpperCell);
        int rightLowerHash = hash(rightLowerCell); 
        
        createBucket(leftUpperHash);
        createBucket(leftLowerHash);
        createBucket(rightUpperHash);
        createBucket(rightLowerHash);
        
        cells[leftUpperHash].add(aabb.getID());
        cells[leftLowerHash].add(aabb.getID());
        cells[rightUpperHash].add(aabb.getID());
        cells[rightLowerHash].add(aabb.getID());  
        
    }
    
    public String toString() {
        String result = "";
        for (int i = 0; i < cells.length; i++) {
            result += cells[i].toString() + " ";
            
        }
        return result;
    }
    
}
