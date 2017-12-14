
import com.sun.javafx.scene.layout.region.Margins;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefan
 */
public class Grid {
    
    private double cellSize;
    
    private double surfaceWidth;
    private double surfaceHeight;
    
    
    
    private int[] cells;
    
    
    public Grid(double w, double h, int c){
        this.cellSize = c;
        
        this.surfaceWidth = w;
        this.surfaceHeight = h;
        
        double i = surfaceHeight/cellSize;
        double j = surfaceWidth/cellSize;
        
        cells = new int[(int) Math.ceil(i+j)];
        
       
    }
    
    public void add(int index, double x, double y, double w, double h){
        
    }
    
}
