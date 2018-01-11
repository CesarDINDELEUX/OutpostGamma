package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Board();
            }
        });
    }
}
