
import java.util.Arrays;



/**
 * Maintains pair of objects which are in collision
 */
public class Collision{
    
    private Integer[] boxes;
    
    /*
    Initialization of Collision class
    */
    public Collision(int id1, int id2){
        
        boxes = new Integer[]{id1,id2};
    }
    
    /*
    Returns pair of objects in collision
    */
    public Integer[] getBoxes(){
        return boxes;
    }
    
    /*
    Returns true if id in parameter is equal with one of objects in collision
    */
    public boolean contains(int id){
        if (boxes[0]==id || boxes[1]==id){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o){
        Collision obj = (Collision) o;
        
        if (boxes[0] == obj.getBoxes()[0] && boxes[1] == obj.getBoxes()[1]) return true;
        if (boxes[0] == obj.getBoxes()[1] && boxes[1] == obj.getBoxes()[0]) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Arrays.deepHashCode(this.boxes);
        return hash;
    }
    
    @Override
    public String toString(){
        return "["+boxes[0]+","+boxes[1]+"]";
    }

    
    
}
