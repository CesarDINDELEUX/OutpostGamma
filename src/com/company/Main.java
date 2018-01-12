package com.company;
import com.sun.deploy.uitoolkit.impl.awt.ui.SwingConsoleWindow;
import com.sun.java.swing.SwingUtilities3;
import javafx.embed.swing.SwingFXUtils;
import sun.swing.SwingAccessor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JButton;



public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Board();
            }
        });
    }
}
