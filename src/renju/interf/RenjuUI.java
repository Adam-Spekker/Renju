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
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



import javax.swing.*;
import renju.board.Piece.COLOR;

/**
 *
 * @author archghoul
 */
public final class RenjuUI extends JFrame implements RenjuInterface{    
    Board gameBoard;
    COLOR color;
    static int frameCnt = 0;
    
    JFrame frame;
    JPanel main;
    JPanel bottom;
    JPanel board;
    
    JOptionPane message;
    
    FlowLayout fLayout = new FlowLayout(FlowLayout.LEADING);
    
    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuHelp;
    JMenuItem menuItemNew, menuItemSave, menuItemLoad, menuItemSwitch,
            menuItemAbout, menuItemRules;
    
    JLabel bottomLabel;    
    
    ActionListener menuAL; 
    
    int size;
    int fieldSize;
    String bottomLabelString;
    
    boolean winnerNotified;
    
    
   
    
    public RenjuUI() {
        
        ///setup frame to be visible and exit on X
        size = 800;
        fieldSize = (int) (size / 15);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(false);
        this.setLocation(100+(size+10)*frameCnt, 20);        
        this.setTitle("Renju");
        
        message = new JOptionPane();
        
        ///create menu action listener  
        menuAL = new MenuActionListener();
        
        ///create the menu bar
        menuBar = CreateMenuBar();
        this.setJMenuBar(menuBar);
               
        ///create panels
        main = new JPanel(new BorderLayout());
        board = new BoardPanel("textures/wood2.jpg");
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
        
        frameCnt++;
        
       // bottomLabel.setText(infc.toString());
    }
    
    @Override
    public void setBoard(Board b) {
        gameBoard = b;
        //gameBoard.setInterface(this);
       // bottomLabel.setText(gameBoard.getCurrentPlayer().toString()); 
        
    }
    
    @Override
    public void setColor(COLOR c) {
        color = c;
        this.setTitle("Renju " + c.toString());
    }
    
    @Override
    public COLOR getColor() {
        return color;
    }
        
    @Override
    public Board getBoard() {
        return gameBoard;
    }
    
    public boolean getIsNotified() {
        return winnerNotified;
    }
    
    
    @Override 
    public void update() {
        String str = gameBoard.getCurrentPlayer() == color ? "Your turn." : "AI turn"; 
        bottomLabel.setText(str); 
        board.repaint();
                       
        if (COLOR.EMPTY != gameBoard.getWinner())  {
            if (!winnerNotified) {
                    JOptionPane.showMessageDialog(frame, (gameBoard.getWinner().toString() + " Wins"), "WINNER" , JOptionPane.PLAIN_MESSAGE);
                    this.winnerNotified = true;
            }
        }else {
            this.winnerNotified = false;
        }
    }

    @Override
    public void run() {
        while (true){
            update();
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                System.out.println("userInterruptexception");
            }
            
            
        }
        
    }
    
    /// panel with background image
    
        public class BoardPanel extends JPanel {
           
            private Image imgBG, currImg;
            private ArrayList<Image> imgBlack, imgWhite; 
            
            public BoardPanel(String str) {                
                imgBG = new ImageIcon(str).getImage();
                currImg = null;
                
                imgBlack = new ArrayList<>();
                imgWhite = new ArrayList<>();
                
                int i =0;
                while(true){
                     Image img = new ImageIcon("textures/haematit"+i+".png").getImage();
                     if(img.getWidth(null) == -1){
                        break;
                     }
                     imgBlack.add(img);  
                     i++;
                }
                
                i =0;
                while(true){
                     Image img = new ImageIcon("textures/amber"+i+".png").getImage();
                     if(img.getWidth(null) == -1){
                        break;
                     }
                     imgWhite.add(img);
                     i++;
                }
                
//                imgBlack= new ImageIcon("textures/haematit0.png").getImage();
//                imgWhite = new ImageIcon("textures/amber0.png").getImage();
                
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
                
                //g.drawOval(fieldSize/2 + 3*fieldSize -3, fieldSize/2 + 3*fieldSize -3, 7, 7);
                g.fillOval(fieldSize/2 + 3*fieldSize -3, fieldSize/2 + 3*fieldSize -3, 7, 7);
                g.fillOval(fieldSize/2 + 11*fieldSize -3, fieldSize/2 + 3*fieldSize -3, 7, 7);
                g.fillOval(fieldSize/2 + 3*fieldSize -3, fieldSize/2 + 11*fieldSize -3, 7, 7);
                g.fillOval(fieldSize/2 + 11*fieldSize -3, fieldSize/2 + 11*fieldSize -3, 7, 7);
                g.fillOval(fieldSize/2 + 7*fieldSize -3, fieldSize/2 + 7*fieldSize -3, 7, 7);
                
                
               g.setColor(Color.WHITE);
                
                COLOR[][] currentState = gameBoard.getBoardColor();
                for(int i = 0; i < 15; i++) {
                    for(int j = 0; j < 15; j++) {
                        switch (currentState[j][i].toChar()) {  
                                case 'B' :                                     
                                    //g.setColor(Color.WHITE);
                                    currImg = imgBlack.get((gameBoard.getFieldSerial(j, i)/2) % imgBlack.size());
                                    break;
                                case 'W' : 
                                    //g.setColor(Color.WHITE);
                                    currImg = imgWhite.get((gameBoard.getFieldSerial(j, i)/2) % imgWhite.size());
                                    break;
                                    
                                default: 
                                    continue;                                 
                                }
                        //g.drawOval(i*fieldSize, j*fieldSize, fieldSize, fieldSize);
                        g.drawImage(currImg,i*fieldSize, j*fieldSize, fieldSize, fieldSize, null);
                        g.drawString(new Integer(gameBoard.getFieldSerial(j, i)).toString(), fieldSize*i + fieldSize/2-6, fieldSize*j+ fieldSize/2+2);
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
            
            menuItemSwitch = new JMenuItem("Switch color");
            menuItemSwitch.setMnemonic(KeyEvent.VK_W);        
            menuItemSwitch.addActionListener(menuAL);
            menuFile.add(menuItemSwitch);

          


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
                   gameBoard.reset();                                 
               } 
               
               if (command.equals("Save")) {
                   JOptionPane.showMessageDialog(frame, "Game Saved");
                   save("save.dat");                                 
               } 
               
               if (command.equals("Load")) {
                   load("save.dat");
                  // JOptionPane.showMessageDialog(frame, "New game initialized");
                  // gameBoard.reset();                                 
               } 
               
               if (command.equals("Switch color")) {
                   gameBoard.swapInterfaceColor();
                   gameBoard.reset();
                  // JOptionPane.showMessageDialog(frame, "New game initialized");
                  // gameBoard.reset();                                 
               } 
               
               if (command.equals("About...")) {
                    JOptionPane.showMessageDialog(frame, "Spekker Ádám prog3 nagyházi");
                  // gameBoard.reset();                                 
               } 
               
               if (command.equals("Rules of Renju")) {
                    if (Desktop.isDesktopSupported()) {
                        
                        try {   
                                Desktop.getDesktop().browse(new URL("http://www.renju.net/study/rifrules.php").toURI());
                                
                        } catch (URISyntaxException | IOException ex) {
                              System.out.println("URI failure");
                        }
                        
                    }   else {
                        System.out.println("Not Supported");
                    }               
               } 
              
               
//               else {
//                   JOptionPane.showMessageDialog(frame, "No functionality yet!", "Error", JOptionPane.ERROR_MESSAGE);
//               }

            }
        }
        
        public class BoardMouseListener implements MouseListener {              
            
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameBoard.getCurrentPlayer() != color) {
                    JOptionPane.showMessageDialog(frame, "Not your turn", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                int x = ((int) e.getPoint().getX()/fieldSize);
                int y = ((int) e.getPoint().getY()/fieldSize);                
                //bottomLabel.setText("Mouse coord: " + (x + 1)+ " " + (y + 1) );
                
                try { 
                    gameBoard.putPiece(y, x, color);
                } catch (GameFinishedException ex) {
                    JOptionPane.showMessageDialog(frame, "Game has ended, you can not move any more!", "Error", JOptionPane.ERROR_MESSAGE);
                    
                } catch (InvalidStepException ex) {
                    JOptionPane.showMessageDialog(frame, "Can not step there!", "Error", JOptionPane.ERROR_MESSAGE);                 
                }
                
                
                                  
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
        
        public void save(String str) {
            try {
                FileOutputStream fileOut = new FileOutputStream(str);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(gameBoard);
                out.close();
                fileOut.close();
               // System.out.println("Game Saved ("+str+")");
             } catch (IOException i) {
                JOptionPane.showMessageDialog(frame, "File error: "+str+i, "Error", JOptionPane.ERROR_MESSAGE); 
             }
            
        }
        
        public void load(String str) {
            try {
                FileInputStream fileIn = new FileInputStream(str);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                gameBoard = (Board) in.readObject();
                in.close();
                fileIn.close();
                gameBoard.updateInterface();
                
                
                
//                System.out.println("Game Loaded ("+str+")");
//                System.out.println("Current Player("+gameBoard.getCurrentPlayer()+")");
//                System.out.println("Game Loaded ("+gameBoard.getLastField()+")");
               // System.out.println("Game Loaded ("+str+")");
                
                
             } catch (IOException i) {
                 JOptionPane.showMessageDialog(frame, "File is non-existent...", "Error", JOptionPane.ERROR_MESSAGE);      
             } catch (ClassNotFoundException c) {
                 JOptionPane.showMessageDialog(frame, "Incompatible file...", "Error", JOptionPane.ERROR_MESSAGE);  
             }
            
        }
        
}
