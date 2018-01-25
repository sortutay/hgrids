
import java.util.ArrayList;

/**
 *
 * Bucket solves hash collisions in array
 */
public class Bucket {
    private ArrayList<Integer> bold;
    private ArrayList<Integer> regular;

    /*
    Initialization of Bucket class
    */
    public Bucket(){
        bold = new ArrayList<>();
        regular = new ArrayList<>();
    }
    
    /*
    Marks object in bucket as Bold
    */
    public void addBold(int id){
        if (bold.contains(id)){
            return;
        }
        bold.add(id);
    }
    
    /*
    Marks object in bucket as Regular
    */
    public void addRegular(int id){
        if(regular.contains(id)){
            return;
        }
        regular.add(id);
        
        
    }
    
    /*
    Removes objects from bucket
    */
    public void remove(int boxId) {
        for (int i = 0; i<regular.size(); i++){
            if (regular.get(i) == boxId){
                regular.remove(i);
                return;
            }
        } 
        for (int i = 0; i<bold.size(); i++){
            if (bold.get(i) == boxId){
                bold.remove(i);
                return;
            }
        }
    } 
    
    /*
    Reports objects which are in collision 
    */
    public void reportRegular(int id){
        
        if (bold.size()!=0){
            
                    
            for (Integer id2:bold){
                PairManagement.addPair(id, id2);
            }
        }
        
        
        
    }
    
    /*
    Reports objects which are in collision 
    */
    public void reportBold(int id){
        
        if (bold.size()!=0){
            
            for (Integer id2:bold){
                if (id!=id2){
                    PairManagement.addPair(id, id2);
                }
                
            }
        }
        if(regular.size()!=0){
            for (Integer id2:regular){
                PairManagement.addPair(id, id2);
            }
        }
        
       
    }
        
    @Override
    public String toString() {
        String result = "{BOLD: ";
        for (int i = 0; i < bold.size(); i++) {
            result += bold.get(i) + ",";
            
        }
        
        result+= " | REGULAR: ";
        for (int i = 0; i < regular.size(); i++) {
            result += regular.get(i) + ",";
            
        }
        return result + "}";
    }
    
}
