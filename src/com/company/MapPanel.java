package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel{

private Image map;

public MapPanel(){
    try {
        this.map = ImageIO.read(getClass().getResourceAsStream("Images/board.png"));
    }
    catch(Exception e){
        throw new RuntimeException(e);
    }
    }
}
