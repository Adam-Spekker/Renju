/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;

import renju.exception.*;
import renju.board.Piece.COLOR;
/**
 *
 * @author archghoul
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;




public class BoardTest {
    
    Board board;
    
    @Before
    public void setUp() {
        board = new Board();
    }
    
    
    
///Method: isValid()  
    @Test
    public void testIsValid() throws InvalidStepException, GameFinishedException {
        Assert.assertEquals(false, board.isValid(-1, 0));
        Assert.assertEquals(false, board.isValid(0, 15));
        
        Assert.assertEquals(true, board.isValid(0, 0));
        board.putPiece(0, 0, COLOR.BLACK);
        Assert.assertEquals(false, board.isValid(0, 0));        
        
    }


///Method: putPiece()
    @Test
    public void testPutPiece() throws BoardException {
        board.putPiece(0, 0, Piece.COLOR.BLACK);
        Assert.assertEquals(COLOR.BLACK, board.getField(0, 0).getColor());
    }

    @Test (expected=InvalidStepException.class) 
    public void testPutPieceInvalidEx() throws InvalidStepException, GameFinishedException  {
        board.putPiece(0, 0, Piece.COLOR.BLACK);
        board.putPiece(0, 0, Piece.COLOR.BLACK);
        
    }
    
    @Test (expected=GameFinishedException.class) 
    public void testPutPiecegameFinishedEx() throws InvalidStepException, GameFinishedException  {
        for (int i = 0; i < 5; i++) {
            board.putPiece(0, i, Piece.COLOR.BLACK);
            
        }
        board.putPiece(0, 6, Piece.COLOR.BLACK);        
    }
    
///Method: getCurrentPlayer()
    @Test
    public void testGetCurrentPlayer() throws BoardException {
        Assert.assertEquals(COLOR.BLACK, board.getCurrentPlayer());
        board.putPiece(0, 0, Piece.COLOR.BLACK);
        Assert.assertEquals(COLOR.WHITE, board.getCurrentPlayer());
        board.putPiece(0, 1, Piece.COLOR.WHITE);
        Assert.assertEquals(COLOR.BLACK, board.getCurrentPlayer());
    }
    
///Method: getFieldSerial()
    @Test
    public void testGetFieldSerial() throws BoardException {
        Assert.assertEquals(0,board.getFieldSerial(0, 0));
        board.putPiece(0, 0, Piece.COLOR.BLACK);
        board.putPiece(0, 1, Piece.COLOR.BLACK);        
        Assert.assertEquals(1, board.getFieldSerial(0, 0));
        Assert.assertEquals(2, board.getFieldSerial(0, 1));
        
    }
    
///Method: checkBoard()
    @Test
    public void testBWinVert() throws BoardException {        
        
        
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(COLOR.EMPTY, board.checkBoard());
            board.putPiece(0, i, Piece.COLOR.BLACK);            
        }
        Assert.assertEquals(COLOR.BLACK, board.checkBoard());        
    }
    
    @Test
    public void testWWinDiag() throws BoardException {        
        
        
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(COLOR.EMPTY, board.checkBoard());
            board.putPiece(i, i, Piece.COLOR.WHITE);            
        }
        Assert.assertEquals(COLOR.WHITE, board.checkBoard());        
    }
    
///Method: reset()
    @Test
    public void testReset() throws BoardException {        
        
        
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(COLOR.EMPTY, board.checkBoard());
            board.putPiece(i, i, Piece.COLOR.WHITE);            
        }
        Assert.assertEquals(COLOR.WHITE, board.checkBoard());        
        Assert.assertEquals(true, board.isFinished); 
        Assert.assertEquals(COLOR.WHITE, board.winner);        
        Assert.assertEquals(6, board.currentTurn); 
        
        board.reset();
        Assert.assertEquals(COLOR.EMPTY, board.checkBoard());        
        Assert.assertEquals(false, board.isFinished); 
        Assert.assertEquals(COLOR.EMPTY, board.winner);        
        Assert.assertEquals(1, board.currentTurn); 
    }
 
}
