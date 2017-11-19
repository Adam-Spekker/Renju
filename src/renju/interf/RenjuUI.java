/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.interf;

import renju.board.*;
import renju.exception.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;
import renju.board.Piece.COLOR;

/**
 *
 * @author archghoul
 */
public final class RenjuUI extends JFrame implements RenjuInterface{
    JFrame frame;
    Board renjuBoard;
    
    JPanel main;
    JPanel bottom;
    JPanel board;
    
    JOptionPane message;
    
    FlowLayout fLayout = new FlowLayout(FlowLayout.LEADING);
    
    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuHelp;
    JMenuItem menuItemNew, menuItemSave, menuItemLoad, menuItemExit,
            menuItemAbout, menuItemRules;
    
    JLabel bottomLabel;    
    
    ActionListener menuAL; 
    
    int size;
    int fieldSize;
    String bottomLabelString;
    
    
   
    
    public RenjuUI() {
        
        ///setup frame to be visible and exit on X
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(false);
        this.setLocation(100, 20);        
        this.setTitle("Renju");
        frame = this;
        message = new JOptionPane();
        
        size = 900;
        fieldSize = (int) (size / 15);
        
        
        ///create menu action listener  
        menuAL = new MenuActionListener();
        
        ///create the menu bar
        menuBar = CreateMenuBar();
        this.setJMenuBar(menuBar);
               
        ///create panels
        main = new JPanel(new BorderLayout());
        board = new BoardPanel("wood2.jpg");
        bottom = new JPanel(new BorderLayout());
                
        ///set up bottom information field
        bottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Info"));        
        bottomLabel = new JLabel();
        bottom.add(bottomLabel);        
        bottomLabel.setText("Ready");
        
        ///set up board panel
        board.setPreferredSize(new Dimension(size,size));
        board.addMouseListener(new BoardMouseListener());
        
        ///set up main panel
        main.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Board"));
        main.add(board);       
        
        ///link panels to frame
        this.getContentPane().add(main, BorderLayout.NORTH);
        this.getContentPane().add(bottom, BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true);
        
        
        
       // bottomLabel.setText(infc.toString());
    }
    
    @Override
    public void setBoard(Board b) {
        renjuBoard = b;
        renjuBoard.setInterface(this);
        bottomLabel.setText(renjuBoard.getCurrentPlayer().toString());        
    }
        
    @Override
    public Board getBoard() {
        return renjuBoard;
    }
    
    @Override 
    public void update() {
        board.repaint();
    }
    
    /// panel with background image
    
        public class BoardPanel extends JPanel {
           
            private Image imgBG, imgBlack, imgWhite, currImg;
            
            public BoardPanel(String img) {                
                this.imgBG = new ImageIcon(img).getImage();
                this.imgBlack = new ImageIcon("Haematit.png").getImage();
                this.imgWhite = new ImageIcon("Amber.png").getImage();
                this.currImg = null;
            }
            
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(imgBG, 0,0, null);
                
                ///draw lines
                for ( int i = 0; i < 15; i++) {
                    ///vertical
                    g.setColor(Color.BLACK);
                    g.drawRect(fieldSize/2 + i*fieldSize, fieldSize/2, 1, 14*fieldSize);                    
                    //g.drawLine(distX + i*2*distX, distY, distX + i*2*distX , distY+14*2*distY);     
                    
                    ///horizontal
                    g.drawRect(fieldSize/2, fieldSize/2 + i*fieldSize, 14*fieldSize , 1);
                    //g.drawLine(distX, distY + i*2*distY, distX + 14*2*distX , distY + i*2*distY);
                }
                
                COLOR[][] currentState = renjuBoard.getBoardColor();
                for(int i = 0; i < 15; i++) {
                    for(int j = 0; j < 15; j++) {
                        switch (currentState[j][i].toChar()) {  
                                case 'B' :                                     
                                    g.setColor(Color.BLACK);
                                    currImg = imgBlack;
                                    break;
                                case 'W' : 
                                    g.setColor(Color.WHITE);
                                    currImg = imgWhite;
                                    break;
                                    
                                default: 
                                    continue;                                 
                                }
                        //g.drawOval(i*fieldSize, j*fieldSize, fieldSize, fieldSize);
                        g.drawImage(currImg,i*fieldSize, j*fieldSize, fieldSize, fieldSize, null);
                    }
                }
                
                
            }
            
            
            
        }
        
    
        
        
        
    ///Create Menu
        public JMenuBar CreateMenuBar() {
            menuBar = new JMenuBar();
            ///create file menu and add it to the menu bar
            menuFile = new JMenu("File");
            menuFile.setMnemonic(KeyEvent.VK_F);
            menuBar.add(menuFile);

            ///create file menu items (nameing follows functionality)
            menuItemNew = new JMenuItem("New Game");
            ///mnemonic -> possibility
            menuItemNew.setMnemonic(KeyEvent.VK_N);    
            menuItemNew.addActionListener(menuAL);
            menuFile.add(menuItemNew);
            menuFile.addSeparator();

            menuItemSave = new JMenuItem("Save");
            menuItemSave.setMnemonic(KeyEvent.VK_S);        
            menuItemSave.addActionListener(menuAL);
            menuFile.add(menuItemSave);

            menuItemLoad = new JMenuItem("Load");
            menuItemLoad.setMnemonic(KeyEvent.VK_L);        
            menuItemLoad.addActionListener(menuAL);
            menuFile.add(menuItemLoad);
            menuFile.addSeparator();

            menuItemExit = new JMenuItem("Quit");
            menuItemExit.setMnemonic(KeyEvent.VK_Q);        
            menuItemExit.addActionListener(menuAL);
            menuFile.add(menuItemExit);


            ///create help menu and add it to the menu bar
            menuHelp = new JMenu("Help");
            menuHelp.setMnemonic(KeyEvent.VK_H);        
            menuBar.add(menuHelp);

            ///create help menu items
            menuItemRules = new JMenuItem("Rules of Renju");
            menuItemRules.setMnemonic(KeyEvent.VK_R);       
            menuItemRules.addActionListener(menuAL);
            menuHelp.add(menuItemRules);
            menuHelp.addSeparator();

            menuItemAbout = new JMenuItem("About...");
            menuItemAbout.setMnemonic(KeyEvent.VK_A);        
            menuItemAbout.addActionListener(menuAL);
            menuHelp.add(menuItemAbout);
            
            return menuBar;
            
        }
       
        
        
    /// actionListeners
    
        public class MenuActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
               String command = e.getActionCommand();
               
               if (command.equals("New Game")) {
                   JOptionPane.showMessageDialog(frame, "New game initialized");
                   renjuBoard.reset();                                 
               } 
               
               if (command.equals("Quit")) {
                   
               }
               
//               else {
//                   JOptionPane.showMessageDialog(frame, "No functionality yet!", "Error", JOptionPane.ERROR_MESSAGE);
//               }

            }
        }
        
        public class BoardMouseListener implements MouseListener {              
            
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = ((int) e.getPoint().getX()/fieldSize);
                int y = ((int) e.getPoint().getY()/fieldSize);                
                bottomLabel.setText("Mouse coord: " + (x + 1)+ " " + (y + 1) );
                
                try { 
                    renjuBoard.putPiece(y, x);
                } catch (GameFinishedException ex) {
                    JOptionPane.showMessageDialog(frame, "Game has ended, you can not move any more!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (InvalidStepException ex) {
                    JOptionPane.showMessageDialog(frame, "Can not step there!", "Error", JOptionPane.ERROR_MESSAGE);                 
                }
                
                COLOR winner = renjuBoard.checkBoard();                
                if (COLOR.EMPTY != winner)                   
                    JOptionPane.showMessageDialog(frame, (winner.toString() + " Wins"), "WINNER" , JOptionPane.PLAIN_MESSAGE);
                                  
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }
        
        
}
