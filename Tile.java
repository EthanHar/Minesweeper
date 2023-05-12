import javax.swing.JFrame;
import javax.swing.JButton; 
import java.awt.GridLayout; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
public class Tile{
    JButton b;
    int num;
    public boolean clicked;
    public boolean covered;
    public Tile(JButton b, int num, boolean covered, boolean clicked){
        this.b = b;
        this.num = num;
        this.covered=covered;
        this.clicked=clicked;
    }

    public int number(){
        return num;
    }

    public JButton getButton(){
        return b;
    }

    public boolean isCovered(){
        return covered;
    }
    
    public boolean isClicked(){
        return clicked;
    }
}




