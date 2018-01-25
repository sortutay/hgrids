
import java.util.ArrayList;
import java.util.HashMap;



/**
 * Handles all Hierarchical Grids
 */
public class GridController {
    Grid[] grids;
    ArrayList<AABB> boxes;
    private HashMap<Integer, AABB> boxesMap;
    
    ArrayList<Collision> collisions;
    
    
    /*
    Initialization of GridController class
    */
    public GridController(ArrayList<AABB> b,HashMap<Integer, AABB> b2, double w, double h){
        boxes = b;        
        boxesMap = b2;
        grids = new Grid[4];
        for (int i = 0; i<4; i++){
            Grid g = new Grid(w,h, (int) (200/Math.pow(2, i)));
            grids[i] = g;
        }
        
        collisions = new ArrayList<>();  
    }
    
    
    /*
    Adds all AABB boxes in all hierarchical grids
    */
    public void addBoxes(){
        for (AABB box:boxes){
            addBox(box);
            
        }
    }
    
    /*
    Updates all AABB boxes 
    */
    public ArrayList<Collision> updateBoxes(){
        ArrayList<Collision> collided = new ArrayList<>();
        for(Collision c:PairManagement.getPairs()){
            AABB b1 = boxesMap.get(c.getBoxes()[0]);
            AABB b2 = boxesMap.get(c.getBoxes()[1]);
            
            if(intersects(b1, b2)){
                collided.add(c);
                
            }
            
        }
        
        
        
        
        removeBoxes();
        addBoxes();
        return collided;
    }
    
    /*
    Checks if two AABB boxes are overlapped
    */
    private boolean intersects(AABB b1, AABB b2){
        if ( b1.getPosition().getX() <= b2.getPosition().getX()+b2.getW() &&
                            b1.getPosition().getX()+b1.getW() >= b2.getPosition().getX() &&
                            b1.getPosition().getY() <= b2.getPosition().getY()+b2.getH() &&
                            b1.getPosition().getY()+b1.getH() >= b2.getPosition().getY()){
                        //b1.changeColor(Color.RED);
                        //b2.changeColor(Color.RED);
                        //System.out.println(b1+" "+b2);
                        return true;
                        
        }
        return false;
        
    }
    
    /*
    Removes all AABB boxes from all hierarchical grids
    */
    public void removeBoxes(){
        for (AABB box:boxes){
            removeBox(box);
        }
    }
    
    
    
    /*
    Add AABB box to all hierarchical grids
    */
    private void addBox(AABB box){
        
        boolean wasBold = false;
        for (int i = grids.length-1; i >=0 ; i--){
            
            
            if (grids[i].fit(box) && wasBold == false){
                grids[i].add(box, "bold");
                wasBold = true;
            }
            else if(grids[i].fit(box)){
                grids[i].add(box, "regular");
            } 
        }
        
        
    }
    
    /*
    Removes AABB box from all hierarchical grids
    */
    private void removeBox(AABB box){
        for (Grid g :box.getGridPositions().keySet()) {
            for (Integer i: box.getGridPositions().get(g)) {
                g.getBucket(i).remove(box.getID());
            }
        }
        for(Collision c:PairManagement.getPairs()){
            if(c.contains(box.getID())){
                PairManagement.removePair(c.getBoxes()[0],c.getBoxes()[1]);
            }
        }
        box.setGridPositions(new HashMap<>());
        
    }
    
    @Override
    public String toString(){
        String result = "GRIDS : \n";
        for (Grid g:grids){
            result+=g;
        }
        return result+"\n------------------------------------------------";
    }
    
    
    
    
    
    
    
    
    
}
