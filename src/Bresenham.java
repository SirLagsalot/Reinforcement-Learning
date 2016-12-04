
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UNA
 */
public class Bresenham {
    public static Position checkCollision(int x, int y, int vx, int vy, char[][] board){
        int x1 = x;
        int y1 = y;
        int x2 = x + vx;
        int y2 = y + vy;
        int count = 1 + vx + vy;
        int xInc = (x2 > x) ? 1: -1;
        int yInc = (y2 > y) ? 1: -1;
        int error = vx - vy;
        vx *=2;
        vy *=2;
               
        for(; count > 0; --count){
            
            if(board[y][x] == '#'){
                return new Position(x, y);
            }            
            
            if(error > 0){
                x += xInc;
                error -= vy;
            }
            else{
                y += yInc;
                error += vx;
            }
        }
        return new Position(x2, y2);
    }
}
