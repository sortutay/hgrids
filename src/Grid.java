
import java.util.HashSet;
import javafx.geometry.Point2D;

/**
 * Represents one grid 
 */
public class Grid {
    
    private double cellSize;
    
    private double surfaceWidth;
    private double surfaceHeight; 
    
    private Bucket[] cells;
    
    /*
    Initialization of Grid class
    */
    public Grid(double w, double h, int c){
        this.cellSize = c;
        
        this.surfaceWidth = w;
        this.surfaceHeight = h;
        
        double i = surfaceHeight/cellSize;
        double j = surfaceWidth/cellSize;
        
        cells = new Bucket[(int) Math.ceil(j)*(int) Math.ceil(i)];
        
    }
    
    /*
    Checks if AABB box fits into cell of grid
    */
    public boolean fit(AABB aabb){
        if (aabb.getW() > cellSize || aabb.getH() > cellSize) {
            
            return false;
        }
        return true;
    }
    
    /*
    Adds AABB box into grid
    */
    public void add(AABB aabb, String type) {
        
        Point2D position = aabb.getPosition();
        double[] leftUpperCell = {position.getX() / cellSize, position.getY() / cellSize};
        double[] leftLowerCell = {position.getX() / cellSize, (position.getY()+aabb.getH()) / cellSize};
        double[] rightUpperCell = {(position.getX()+aabb.getW()) / cellSize, position.getY() / cellSize};
        double[] rightLowerCell = {(position.getX()+aabb.getW()) / cellSize, (position.getY()+aabb.getH()) / cellSize};
        
        HashSet<Integer> pointsHash = new HashSet<Integer>();
        
        pointsHash.add(hash(leftUpperCell));
        pointsHash.add(hash(leftLowerCell));
        pointsHash.add(hash(rightUpperCell));
        pointsHash.add(hash(rightLowerCell));
        
        
        
        
        for(Integer hashId:pointsHash){
            createBucket(hashId);
            if(type.equals("regular")){
                cells[hashId].addRegular(aabb.getID());
                cells[hashId].reportRegular(aabb.getID());
            }
            else{
                cells[hashId].addBold(aabb.getID());
                cells[hashId].reportBold(aabb.getID());

            }
            aabb.addGridPosition(this, hashId);
        }
        
        
    }
    
    /*
    Returns bucket of one cell from grid
    */
    public Bucket getBucket(int id) {
        return cells[id];
    }    
    
    /*
    Function to hash position of cell to array
    */
    private int hash(double[] point) {
        return (((int)point[0]*1103) ^ ((int)point[1]*1567)) % cells.length; 
    }
    
    /*
    Creates bucket to handle hash collisions in one cell of grid
    */
    private void createBucket(int id) {
        if (cells[id] == null){
            cells[id] = new Bucket();
        }
    }
    
    
  
    
    @Override
    public String toString() {
        String result = "GRID CELL SIZE "+ cellSize+" [";
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] != null){
                result+=cells[i]+"\n";
            }
            
            
        }
        return result+"]";
    }
    
}
