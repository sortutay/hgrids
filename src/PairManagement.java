
import java.util.ArrayList;


/*
 * Singleton pattern class
 * Implementation of simple full matrix pair management
 */
public class PairManagement {
    
    
    private static int[][] pairs;
    private static PairManagement pairManagement;
    
    /*
     * Private constructor, initialization of 2d pair matrix
     */
    private PairManagement(int n){
        pairs = new int[n][n];
    }
    
    /*
     * Returns instance of PairManagement class
     */
    public static PairManagement getInstance(int n){
        if(pairManagement==null){
            pairManagement = new PairManagement(n);
        }
        return pairManagement;
    }  
    
    /*
    Adds new pair to matrix
    */
    public static void addPair(int id1, int id2){
        if (id1 != id2){
            pairs[id1][id2]=1;
            pairs[id2][id1]=1;
        }
    }
    
    /*
    Removes pair from matrix
    */
    public static void removePair(int id1, int id2){
        if(id1 != id2){
            pairs[id1][id2]=0;
            pairs[id2][id1]=0;
        }
    }
    
    /*
    Returns all pairs from matrix
    */
    public static ArrayList<Collision> getPairs(){
        ArrayList<Collision> result = new ArrayList<>();
        for (int i = 0; i<pairs.length; i++){
            
            for (int j = 0; j<i; j++){
                if (pairs[i][j]==1){
                    result.add(new Collision(i, j));
                }
            }
            
        }     
        return result;
        
    }
  
    @Override
    public String toString(){
        String result = "[";
        for (int i = 0; i<pairs.length; i++){
            result+="[";
            for (int j = 0; j<pairs.length; j++){
                result+=pairs[i][j]+" ";
            }
            result+="]\n";
        }
        return result+"]";
    }
}
