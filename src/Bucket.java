
import java.util.ArrayList;

/**
 *
 * @author MATEJ
 */
public class Bucket {
    private ArrayList<Integer> boxes;

    
    public Bucket(){
        boxes = new ArrayList<>();
    }
    
    public void add(int Id){
        if (boxes.contains(Id)){
            return;
        }
        boxes.add(Id);
    }
    
    @Override
    public String toString() {
        String result = "{";
        for (int i = 0; i < boxes.size(); i++) {
            result += boxes.get(i) + ",";
            
        }
        return result + "}";
    }
    
}
