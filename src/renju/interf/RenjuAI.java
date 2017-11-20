/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.interf;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import renju.board.Board;
import renju.board.Piece.COLOR;
import renju.exception.BoardException;

/**
 *
 * @author archghoul
 */
public class RenjuAI implements RenjuInterface{
    Board renjuBoard;
    COLOR color;
    
    @Override
    public void setBoard(Board b) {
        renjuBoard = b;
        renjuBoard.setInterface(this);
    }
    
    @Override
    public void setColor(COLOR c) {
        color = c;
    }
    
    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public void update() {
        if (renjuBoard.getWinner() == COLOR.EMPTY)
            if (renjuBoard.getCurrentPlayer() == color ){
               
            
                //this.moveSystematic(renjuBoard.getLastField().getX(),renjuBoard.getLastField().getY());
                this.moveRandom(renjuBoard.getLastField().getX(),renjuBoard.getLastField().getY());
          
            }
        
    }
    
    void moveSystematic(int x, int y) {      
        for (int i = 1; i < 6; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    int xc = x+i*j;
                    int yc = y+i*k;
                    if ((xc<= 15) && (xc>=0) && (yc>=0) && (yc<=15)) {
                        try {
                            renjuBoard.putPiece(xc, yc);                              
                        } catch (BoardException e){
                            continue;
                        }
                        return;
                    }
                }               
            }
        }
    }
    
    void moveRandom(int x, int y) {
        Random rand = new Random();
        int radius = 1;
        
        
        while (true) {       
            
            int xc = x+(rand.nextInt(1+2*radius)-radius);
            int yc = y+(rand.nextInt(1+2*radius)-radius);
            
            if (rand.nextInt(10) < 1)
                radius++;
            
            if ((xc<= 15) && (xc>=0) && (yc>=0) && (yc<=15)) {
                try {
                    renjuBoard.putPiece(xc, yc);                              
                } catch (BoardException e){
                    continue;
                }
                return;
            }
        }
        
    }
    
}
