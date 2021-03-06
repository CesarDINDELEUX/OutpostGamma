package com.company;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.event.*;









 class Board extends Panel{

     class MyMenuItem extends JMenuItem
             implements ActionListener {
         public MyMenuItem(String text) {
             super(text);
             addActionListener(this);
         }
         public void actionPerformed(ActionEvent e) {
             System.out.println("Item clicked: "+e.getActionCommand());
             if (e.getActionCommand() == "Game Turn")
             {
                if(turn %2 == 0)
                {
                 
                    createImpMenu(frame);
                }
                else {
                    createIrdanMenu(frame);
                }
                turn += 1;

             }
         }
     }




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
    final static int BSIZE = 400;
    final static int HEXSIZE = 35;
    final static int BORDERS = 30;
    final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3;
    public int turn = 0;
    public JFrame frame;


    int[][] board = new int[BSIZE][BSIZE];

    void initGame(){

        Hex.setXYasVertex(false);

        Hex.setHeight(HEXSIZE);
        Hex.setBorders(BORDERS);

        for (int i=0;i<BSIZE;i++) {
            for (int j=0;j<BSIZE;j++)
            {
                board[i][j]=EMPTY;
            }
        }
    }



    public void createAndShowGUI()
    {
        DrawingPanel panel = new DrawingPanel();
        frame = new JFrame("Outpost GAMMA");
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
                createImpMenu(frame);
                turn = 1;
            }
            else
                {
                 createIrdanMenu(frame);
                 turn = 2;
                }

        frame.setSize(807, 813);
        frame.setResizable(false);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }


     public void createImpMenu (JFrame j){


         JMenu Menu = new JMenu("Menu");

         JMenu Unites = new JMenu("Unités");
         MyMenuItem Infantery = new MyMenuItem("Infantery");
         MyMenuItem Lourd = new MyMenuItem("Soldat Lourd");
         MyMenuItem Commandant = new MyMenuItem("Commandant");

         Unites.add(Infantery);
         Unites.add(Lourd);
         Unites.add(Commandant);

         JMenu Position = new JMenu("Positions");
         MyMenuItem Avance = new MyMenuItem("Position Avancée");
         MyMenuItem Fortifie = new MyMenuItem("Position Fortifié");

         Position.add(Avance);
         Position.add(Fortifie);

         JMenuItem end = new MyMenuItem("Game Turn");

         Menu.add(Unites);
         Menu.add(Position);
         Menu.add(end);

         JMenuBar menuBar = new JMenuBar();
         j.setJMenuBar(menuBar);
         menuBar.add(Menu);



     }

    public void createIrdanMenu(JFrame j){

        JMenu Menu = new JMenu("Menu");
        JMenuItem end = new JMenuItem("Game turn");

        JMenu Unites = new JMenu("Unités");
        MyMenuItem Irdan = new MyMenuItem("Irdan rebelle");
        MyMenuItem shooter = new MyMenuItem("Irdan Shooter");
        MyMenuItem leader = new MyMenuItem(" Irdan Leader");


        Unites.add(Irdan);
        Unites.add(shooter);
        Unites.add(leader);

        Menu.add(Unites);
        Menu.add(end);

        JMenuBar menuBar = new JMenuBar();
        j.setJMenuBar(menuBar);
        menuBar.add(Menu);



    }



    public void ChangeTurn()


    {
        if (turn %2 == 0)
        {
            createImpMenu(frame);
        }
        else {
            createIrdanMenu(frame);
        }
        turn +=1;
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
            for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    Hex.drawHex(i,j,g2);
                }
            }
            //Remplir les hex
            for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    Hex.fillHex(i,j,board[i][j],g2);
                }
            }
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