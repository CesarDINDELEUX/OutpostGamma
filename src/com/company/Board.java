package com.company;

import sun.net.dns.ResolverConfiguration;

import java.beans.DesignMode;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.text.html.Option;
import java.io.File;

 class Board extends Panel{

    List<Hex> listeHex;
    List<Irdan> listeIrdan;
    List<Legionnaire> listeLegionnaire;
    List<Tempete> listeTempete;
    private Image map;

    public Board() {
        initGame();
        createAndShowGUI();
    }

    //constants and global variables
    final static Color COLOURBACK =  Color.WHITE;
    final static Color COLOURCELL =  Color.ORANGE;
    final static Color COLOURGRID =  Color.BLACK;
    final static Color COLOURONE = new Color(255,255,255,200);
    final static Color COLOURONETXT = Color.BLUE;
    final static Color COLOURTWO = new Color(0,0,0,200);
    final static Color COLOURTWOTXT = new Color(255,100,255);
    final static int EMPTY = 0;
    final static int BSIZE = 22;
    final static int HEXSIZE = 66;
    final static int BORDERS = 30;
    final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3;




    int[][] board = new int[BSIZE][BSIZE];

    void initGame(){

        Hex.setXYasVertex(false);

        Hex.setHeight(HEXSIZE);
        Hex.setBorders(BORDERS);

        for (int i=0;i<BSIZE;i++) {
            for (int j=0;j<BSIZE;j++) {
                board[i][j]=EMPTY;
            }
        }

        //set up board here
        //  board[3][3] = (int)'A';
        //  board[4][3] = (int)'Q';
        //  board[4][4] = -(int)'B';
    }



    private void createAndShowGUI()
    {
        DrawingPanel panel = new DrawingPanel();
        JFrame frame = new JFrame("Outpost GAMMA");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container content = frame.getContentPane();
        content.add(panel);


        DrawingPanel panelMenu = new DrawingPanel();
        JFrame frameMenu = new JFrame();
        Container con = frameMenu.getContentPane();
        JOptionPane optionPane = new JOptionPane();
        Object[] options = {"Irdans",
                "Impériaux",
                };
        int result =   optionPane.showOptionDialog(frame,
            "Le joueur 1 Choisit son équipe le joueur 2 aura l'autre",
                   "Débuter la partie",
                   optionPane.YES_NO_OPTION,
                   optionPane.QUESTION_MESSAGE,
                   null,
                   options,
                   options[0]);
        con.add(panelMenu);





            if (result == JOptionPane.NO_OPTION) {
                JMenuBar menuBar = new JMenuBar();
                frame.setJMenuBar(menuBar);
                JMenu Menu = new JMenu("Menu");

                JMenu Unites = new JMenu("Unités");
                JMenuItem Infantery = new JMenuItem("Infantery");
                JMenuItem Lourd = new JMenuItem("Soldat Lourd");
                JMenuItem Commandant = new JMenuItem("Commandant");

                Unites.add(Infantery);
                Unites.add(Lourd);
                Unites.add(Commandant);

                JMenu Position = new JMenu("Positions");
                JMenuItem Avance = new JMenuItem("Position Avancée");
                JMenuItem Fortifie = new JMenuItem("Position Fortifié");

                Position.add(Avance);
                Position.add(Fortifie);

                JMenuItem end = new JMenuItem("Game Turn");

                Menu.add(Unites);
                Menu.add(Position);
                Menu.add(end);
                menuBar.add(Menu);
            }
            else
                {
                    JMenuBar menuBar = new JMenuBar();
                    frame.setJMenuBar(menuBar);
                    JMenu Menu = new JMenu("Menu");
                    JMenuItem end = new JMenuItem("Game turn");

                   JMenu Unites = new JMenu("Unités");
                    JMenuItem Irdan = new JMenuItem("Irdan rebelle");
                    JMenuItem shooter = new JMenuItem("Irdan Shooter");
                    JMenuItem leader = new JMenuItem(" Irdan Leader");


                    Unites.add(Irdan);
                    Unites.add(shooter);
                    Unites.add(leader);

                    Menu.add(Unites);
                    Menu.add(end);
                    menuBar.add(Menu);
                }




        frame.setSize( (int)(SCRSIZE/1.23), SCRSIZE);
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }

    class menuaction extends  AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
        public menuaction(Icon icon){
        }
    }

    class DrawingPanel extends JPanel
    {
        Image image;

        public DrawingPanel()
        {
            setBackground(COLOURBACK);
            image=(new javax.swing.ImageIcon(getClass().getResource("board.png"))).getImage();

            MyMouseListener ml = new MyMouseListener();
            addMouseListener(ml);

        }

        public void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2.drawImage(image, 0, 0,null);
            super.paintComponent(g2);
            ImageIcon image_background = new ImageIcon(getClass().getResource("board.png"));

            // Image image = image_background.getImage(); // transform it
            // Image newImg = image.getScaledInstance(800, 600,  Image.SCALE_SMOOTH); // scale it the smooth way
            // ImageIcon lol = new ImageIcon(newImg);  // transform it back



            g.drawImage(image_background.getImage(), 0, 0, getWidth(), getHeight(), this);

            //Dessiner les hex
           /* for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    Hex.drawHex(i,j,g2);
                }
            }
            //Remplir les hex
            for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    Hex.fillHex(i,j,board[i][j],g2);
                }
            } */
        }

        class MyMouseListener extends MouseAdapter	{
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Point p = new Point( Hex.pxtoHex(e.getX(),e.getY()) );
                if (p.x < 0 || p.y < 0 || p.x >= BSIZE || p.y >= BSIZE) return;
                board[(p.x)][(p.y)] = (int)'X';
                String str = "X: " + p.x + " | " + "Y: " + p.y;
                JOptionPane.showMessageDialog(null,
                        str);
                // repaint();
            }
        }


    }
}