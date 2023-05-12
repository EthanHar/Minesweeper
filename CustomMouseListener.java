import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
public class CustomMouseListener implements MouseListener {
    Tile button;
    JFrame frame;
    int i;
    int j;
    private static int totalCovered;
    private static int totalClicked;
    int[][] table;
    Color g = new Color(230, 229, 218);
    public CustomMouseListener(Tile button, JFrame frame, int i, int j){
        this.button=button;
        this.frame=frame;
        this.i=i;
        this.j=j;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) { // left click
            reveal(i,j);
        }
        if (e.getButton() == MouseEvent.BUTTON3) { //right click
            if (button.clicked==false){
                button.getButton().setText("#");
                button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
                button.getButton().setForeground(Color.BLACK);
                button.getButton().setFocusable(false);

                button.covered=true;
                totalCovered=0;
                for(Tile[] t : Minesweeper.grid){
                    for (Tile b : t){
                        if (b.isCovered())
                            totalCovered++;
                    }
                }
                
            }
        }
        if ((totalCovered==Minesweeper.bombs) && (totalClicked+Minesweeper.bombs==100)){
            int n = JOptionPane.showConfirmDialog(null, "Wow, You Won! Want to Play again?", "Nice Job!", JOptionPane.YES_NO_OPTION);
            //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            if (n == JOptionPane.YES_OPTION){
                frame.dispose();
                new Minesweeper();//makes new ButtonGrid with 2 parameters
            }
            else{
                System.exit(0);
            }

        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void bomb(){
        int n = JOptionPane.showConfirmDialog(null, "Oops! That was a bomb! Want to play again?", "Twas a Bomb :(", JOptionPane.YES_NO_OPTION);
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        if (n == JOptionPane.YES_OPTION){
            frame.dispose();
            new Minesweeper();//makes new ButtonGrid with 2 parameters
        }
        else{
            System.exit(0);
        }
        /* else{
        System.out.print("Please enter a valid response");
        }
         */
    }

    public void nothing(int i, int j){
        Minesweeper.grid[i][j].getButton().setBackground(g);
        Minesweeper.grid[i][j].getButton().setText("");
        Minesweeper.grid[i][j].getButton().setFont(new Font("Arial", Font.BOLD ,60));
        Minesweeper.grid[i][j].getButton().setForeground(Color.BLACK);
        Minesweeper.grid[i][j].getButton().setFocusable(false);

    }

    public void clear(int i, int j){
        table=Minesweeper.table;

        if (i<9 && i>0 && j>0&&j<9){
            if(table[i][j+1]==0&& Minesweeper.grid[i][j+1].isClicked()==false && Minesweeper.grid[i][j+1].isCovered()==false){
                nothing(i,j+1);
                Minesweeper.grid[i][j+1].clicked=true;
                clear(i,j+1);
                //right
            }
            if(table[i][j-1]==0&& Minesweeper.grid[i][j-1].isClicked()==false && Minesweeper.grid[i][j-1].isCovered()==false){
                nothing(i,j-1);
                Minesweeper.grid[i][j-1].clicked=true;
                clear(i,j-1);//left
            }
            if(table[i+1][j]==0&& Minesweeper.grid[i+1][j].isClicked()==false && Minesweeper.grid[i+1][j].isCovered()==false){
                nothing(i+1,j);
                Minesweeper.grid[i+1][j].clicked=true;
                clear(i+1,j);//below
            }
            if(table[i-1][j]==0&& Minesweeper.grid[i-1][j].isClicked()==false && Minesweeper.grid[i-1][j].isCovered()==false){
                nothing(i-1,j);
                Minesweeper.grid[i-1][j].clicked=true;
                clear(i-1,j);//above
            }

        }
        if (i==0&&j==0){ //top left corner
            if (table[i][j+1]==0&& Minesweeper.grid[i][j+1].isClicked()==false && Minesweeper.grid[i][j+1].isCovered()==false){
                nothing(i,j+1);
                Minesweeper.grid[i][j+1].clicked=true;
                clear(i,j+1);
            }
            if (table[i+1][j+1]==0&& Minesweeper.grid[i+1][j+1].isClicked()==false && Minesweeper.grid[i+1][j+1].isCovered()==false){
                nothing(i+1,j+1);
                Minesweeper.grid[i+1][j+1].clicked=true;
                clear(i+1,j+1);
            }
            if (table[i+1][j]==0&& Minesweeper.grid[i+1][j].isClicked()==false && Minesweeper.grid[i+1][j].isCovered()==false){
                nothing(i+1,j);
                Minesweeper.grid[i+1][j+1].clicked=true;
                clear(i+1,j);
            }
        }
        if (i==9&&j==0){ //bottom left corner
            if (table[i-1][j]==0&& Minesweeper.grid[i-1][j].isClicked()==false && Minesweeper.grid[i-1][j].isCovered()==false){
                nothing(i-1,j);
                Minesweeper.grid[i-1][j].clicked=true;
                clear(i-1,j);
            }
            if (table[i][j+1]==0&& Minesweeper.grid[i][j+1].isClicked()==false && Minesweeper.grid[i][j+1].isCovered()==false){
                nothing(i,j+1);
                Minesweeper.grid[i][j+1].clicked=true;
                clear(i,j+1);
            }
            if (table[i-1][j+1]==0&& Minesweeper.grid[i-1][j+1].isClicked()==false && Minesweeper.grid[i-1][j+1].isCovered()==false){
                nothing(i-1,j+1);
                Minesweeper.grid[i-1][j+1].clicked=true;
                clear(i-1,j+1);
            }
        }
        if (i==0&&j==9){ //top right corner
            if (table[i][j-1]==0&& Minesweeper.grid[i][j-1].isClicked()==false && Minesweeper.grid[i][j-1].isCovered()==false){
                nothing(i,j-1);
                Minesweeper.grid[i][j-1].clicked=true;
                clear(i,j-1);
            }
            if (table[i+1][j]==0&& Minesweeper.grid[i+1][j].isClicked()==false && Minesweeper.grid[i+1][j].isCovered()==false){
                nothing(i+1,j);
                Minesweeper.grid[i+1][j].clicked=true;
                clear(i+1,j);
            }
            if (table[i+1][j-1]==0&& Minesweeper.grid[i+1][j-1].isClicked()==false && Minesweeper.grid[i+1][j-1].isCovered()==false){
                nothing(i+1,j-1);
                Minesweeper.grid[i+1][j-1].clicked=true;
                clear(i+1,j-1);
            }
        }
        if (i==9&&j==9){ //bottom right corner
            if (table[i][j-1]==0&& Minesweeper.grid[i][j-1].isClicked()==false && Minesweeper.grid[i][j-1].isCovered()==false){
                nothing(i,j-1);
                Minesweeper.grid[i][j-1].clicked=true;
                clear(i,j-1);
            }
            if (table[i-1][j]==0&& Minesweeper.grid[i-1][j].isClicked()==false && Minesweeper.grid[i-1][j].isCovered()==false){
                nothing(i-1,j);
                Minesweeper.grid[i-1][j].clicked=true;
                clear(i-1,j);
            }
            if (table[i-1][j-1]==0&& Minesweeper.grid[i-1][j-1].isClicked()==false && Minesweeper.grid[i-1][j-1].isCovered()==false){
                nothing(i-1,j-1);
                Minesweeper.grid[i-1][j-1].clicked=true;
                clear(i-1,j-1);
            }
        }
        if(i==0&&j!=0&&j<9){
            if (table[1][j-1]==0&& Minesweeper.grid[1][j-1].isClicked()==false && Minesweeper.grid[1][j-1].isCovered()==false){
                nothing(1,j-1);
                Minesweeper.grid[1][j-1].clicked=true;
                clear(1,j-1);
            }
            if (table[1][j]==0&& Minesweeper.grid[1][j].isClicked()==false && Minesweeper.grid[1][j].isCovered()==false){
                nothing(1,j);
                Minesweeper.grid[1][j].clicked=true;
                clear(1,j);
            }
            if (table[1][j+1]==0&& Minesweeper.grid[1][j+1].isClicked()==false && Minesweeper.grid[1][j+1].isCovered()==false){
                nothing(i,j+1);
                Minesweeper.grid[1][j+1].clicked=true;
                clear(1,j+1);
            }
            if (table[0][j-1]==0&& Minesweeper.grid[i][j-1].isClicked()==false && Minesweeper.grid[i][j-1].isCovered()==false){
                nothing(0,j-1);
                Minesweeper.grid[0][j-1].clicked=true;
                clear(i,j-1);
            }
            if (table[0][j+1]==0&& Minesweeper.grid[i][j+1].isClicked()==false && Minesweeper.grid[i][j+1].isCovered()==false){
                nothing(0,j+1);
                Minesweeper.grid[0][j+1].clicked=true;
                clear(i,j+1);
            }
        }
        if(i==9&&j!=0&&j<9){
            if (table[8][j-1]==0&& Minesweeper.grid[8][j-1].isClicked()==false && Minesweeper.grid[8][j-1].isCovered()==false){
                nothing(8,j-1);
                Minesweeper.grid[8][j-1].clicked=true;
                clear(8,j-1);
            }
            if (table[8][j]==0&& Minesweeper.grid[8][j].isClicked()==false && Minesweeper.grid[8][j].isCovered()==false){
                nothing(8,j);
                Minesweeper.grid[8][j].clicked=true;
                clear(8,j);
            }
            if (table[8][j+1]==0&& Minesweeper.grid[8][j+1].isClicked()==false && Minesweeper.grid[8][j+1].isCovered()==false){
                nothing(8,j+1);
                Minesweeper.grid[8][j+1].clicked=true;
                clear(8,j+1);
            }
            if (table[9][j-1]==0&& Minesweeper.grid[9][j-1].isClicked()==false && Minesweeper.grid[9][j-1].isCovered()==false){
                nothing(9,j-1);
                Minesweeper.grid[9][j-1].clicked=true;

            }
            if (table[9][j+1]==0&& Minesweeper.grid[9][j+1].isClicked()==false && Minesweeper.grid[9][j+1].isCovered()==false){
                nothing(9,j+1);
                Minesweeper.grid[9][j+1].clicked=true;
                clear(9,j+1);
            }
        }
        if(j==0&&i!=0&&i<9){ //dix thias
            if (table[i-1][1]==0&& Minesweeper.grid[i-1][1].isClicked()==false && Minesweeper.grid[i-1][1].isCovered()==false){
                nothing(i-1,1);
                Minesweeper.grid[i-1][1].clicked=true;
                clear(i-1,1);
            }
            if (table[i][1]==0&& Minesweeper.grid[i][1].isClicked()==false && Minesweeper.grid[i][1].isCovered()==false){
                nothing(i-1,1);
                Minesweeper.grid[i][1].clicked=true;
                clear(i,1);
            }
            if (table[i+1][1]==0&& Minesweeper.grid[i+1][1].isClicked()==false && Minesweeper.grid[i+1][1].isCovered()==false){
                nothing(i+1,1);
                Minesweeper.grid[i+1][1].clicked=true;
                clear(i+1,1);
            }
            if(table[i-1][0]==0&& Minesweeper.grid[i-1][0].isClicked()==false && Minesweeper.grid[i-1][0].isCovered()==false){
                nothing(i-1,0);
                Minesweeper.grid[i-1][0].clicked=true;
                clear(i-1,0);
            }
            if(table[i+1][0]==0&& Minesweeper.grid[i+1][0].isClicked()==false && Minesweeper.grid[i+1][0].isCovered()==false){
                nothing(i+1,0);
                Minesweeper.grid[i+1][0].clicked=true;
                clear(i+1,0);
            }
        }
        if(j==9&&i!=0&&i<9){
            if (table[i-1][8]==0&& Minesweeper.grid[i-1][8].isClicked()==false && Minesweeper.grid[i-1][8].isCovered()==false){
                nothing(i-1,8);
                Minesweeper.grid[i-1][8].clicked=true;
                clear(i-1,8);
            }
            if (table[i][8]==0&& Minesweeper.grid[i][8].isClicked()==false && Minesweeper.grid[i][8].isCovered()==false){
                nothing(i,8);
                Minesweeper.grid[i][8].clicked=true;
                clear(i,8);
            }
            if (table[i+1][8]==0&& Minesweeper.grid[i+1][8].isClicked()==false && Minesweeper.grid[i+1][8].isCovered()==false){
                nothing(i+1,8);
                Minesweeper.grid[i+1][8].clicked=true;
                clear(i+1,j-1);
            }
            if(table[i-1][9]==0&& Minesweeper.grid[i-1][9].isClicked()==false && Minesweeper.grid[i-1][9].isCovered()==false){
                nothing(1,9);
                Minesweeper.grid[i-1][9].clicked=true;
                clear(i-1,j);
            }
            if(table[i+1][9]==0&& Minesweeper.grid[i+1][9].isClicked()==false && Minesweeper.grid[i+1][9].isCovered()==false){
                nothing(i+1,9);
                Minesweeper.grid[i+1][9].clicked=true;
                clear(i+1,9);
            }
        }
    }

    public void reveal(int i, int j){
        if (i < 0 || i > 9 || j < 0 || j > 9) {
            return;
        }

        button=Minesweeper.grid[i][j];
        if (button.clicked) {
            return;
        }
        if (button.getButton().getText().equals("#"))
        button.covered=false;
        button.clicked=true;
        totalClicked=0;
        for(Tile[] t : Minesweeper.grid){
            for (Tile b : t){
                if (b.isClicked())
                    totalClicked++;
            }
        }
        
        
        if (button.number()==9){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("*");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.BLACK);
            button.getButton().setFocusable(false);
            bomb();
        }
        else if (button.number()==8){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("8");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.ORANGE);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==7){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("7");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.GREEN);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==6){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("6");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.YELLOW);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==5){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("5");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.CYAN);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==4){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("4");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.MAGENTA);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==3){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("3");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.RED);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==2){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("2");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.BLUE);
            button.getButton().setFocusable(false);
        }
        else if (button.number()==1){
            //button.getButton().setEnabled(false);
            button.getButton().setBackground(g);
            button.getButton().setText("1");
            button.getButton().setFont(new Font("Arial", Font.BOLD ,60));
            button.getButton().setForeground(Color.PINK);
            button.getButton().setFocusable(false);
        }
        else{
            //button.getButton().setEnabled(false);
            nothing(i,j);

            reveal(i+1, j);
            reveal(i-1, j);
            reveal(i, j+1);
            reveal(i, j-1);
        }

    }
}

