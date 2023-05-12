import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
public class Minesweeper {
    JFrame frame; //creates frame
    JPanel panel;
    public static Tile[][] grid; //names the grid of buttons
    public static int bombs; // num bombs
    public static int[][]  table; //table
    static Scanner scan = new Scanner(System.in);
    ArrayList<ImageIcon> images;

    public Minesweeper(){ //constructor
        frame =new JFrame();
        Color a = new Color(0,0,0);
        frame.setLayout(new GridLayout(10,10)); //set layout
        grid=new Tile[10][10]; //allocate the size of grid
        Minesweepa();
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                JButton tempButton = new JButton();
                tempButton.setBorder(BorderFactory.createMatteBorder(2,2,2,2,a));
                Color c = new Color(26, 173, 66);
                Color q = new Color(113, 232, 58);
                tempButton.setSize(40,40);
                frame.add(tempButton);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setSize(800,800); //sets appropriate size for frame

                frame.setResizable(false); //cannot change size


                if (x%2==0 && y%2==0){
                    tempButton.setBackground(c);
                }
                else if (x%2!=0 && y%2!=0){
                    tempButton.setBackground(c);
                }
                else{
                    tempButton.setBackground(q);
                }

                int temp = table[x][y];
                grid[x][y]=new Tile(tempButton, temp, false, false); //creates new button

                //adds button to grid
            }
        }
        Color g = new Color(230, 229, 218);
        for (int row=0; row<grid.length ;row++){
            for (int col=0; col<grid[row].length; col++){
                grid[row][col].getButton().addMouseListener(new CustomMouseListener(grid[row][col],frame,row,col));
            }
        }
        frame.setVisible(true); //makes frame visible
    }

    public static void main(String[] args) {

        new Minesweeper();//makes new ButtonGrid with 2 parameters

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

    public static int Minesweepa(){
        table=new int[10][10];
        //bombs = Integer.parseInt( JOptionPane.showInputDialog(null,"How many bombs would you like to find?", "# of Bombs",JOptionPane.INFORMATION_MESSAGE));
        bombs=1;
        int bombsNotPlaced = bombs;
        int row=0;
        int col=0;
        while (bombsNotPlaced>0){
            row=(int)(Math.random()*10);
            col=(int)(Math.random()*10);
            if (table[row][col]==0){
                table[row][col] = 9;
                bombsNotPlaced--;    
            }
        }
        numbers();
        /*for (int i=0;i<table.length;i++){
            System.out.println();
            for (int j=0; j<table[0].length;j++)
                System.out.print(table[i][j]);

        }
        */
        return bombs;
    }

    public static void numbers(){
        for (int i = 0; i<10; i++){
            for (int j=0;j<10; j++){

                if (table[i][j]==9){
                    if (i!=9 && i!=0 && j!=0&&j!=9){
                        if(table[i][j+1]!=9)
                            table[i][j+1] +=1;//right
                        if(table[i][j-1]!=9)
                            table[i][j-1] +=1;//left
                        if(table[i+1][j]!=9)
                            table[i+1][j]+=1;//below

                        if(table[i-1][j]!=9)
                            table[i-1][j]+=1;//above
                        if(table[i-1][j-1]!=9)
                            table[i-1][j-1]+=1;//top left
                        if(table[i-1][j+1]!=9)
                            table[i-1][j+1]+=1;//top right
                        if(table[i+1][j-1]!=9)
                            table[i+1][j-1]+=1;//bottom left
                        if(table[i+1][j+1]!=9)
                            table[i+1][j+1]+=1;//bottom right

                    }
                }
            }
        }
        if (table[0][0]==9){ //top left corner
            if (table[0][1]!=9)
                table[0][1]+=1;
            if (table[1][1]!=9)
                table[1][1]+=1;
            if (table[1][0]!=9)
                table[1][0]+=1;
        }
        if (table[9][0]==9){ //bottom left corner
            if (table[8][0]!=9)
                table[8][0]+=1;
            if (table[9][1]!=9)
                table[9][1]+=1;
            if (table[8][1]!=9)
                table[8][1]+=1;
        }
        if (table[0][9]==9){ //top right corner
            if (table[0][8]!=9)
                table[0][8]+=1;
            if (table[1][9]!=9)
                table[1][9]+=1;
            if (table[1][8]!=9)
                table[1][8]+=1;
        }
        if (table[9][9]==9){ //bottom right corner
            if (table[9][8]!=9)
                table[9][8]+=1;
            if (table[8][9]!=9)
                table[8][9]+=1;
            if (table[8][8]!=9)
                table[8][8]+=1;
        }

        for (int j = 1; j<9; j++){ //checking top row
            if(table[0][j]==9){
                if (table[1][j-1]!=9)
                    table[1][j-1]+=1;
                if (table[1][j]!=9)
                    table[1][j]+=1;
                if (table[1][j+1]!=9)
                    table[1][j+1]+=1;
                if (table[0][j-1]!=9)
                    table[0][j-1]+=1;
                if (table[0][j+1]!=9)
                    table[0][j+1]+=1;
            }
        }
        for (int j = 1; j<9; j++){ //checking bottom row
            if(table[9][j]==9){
                if (table[8][j-1]!=9)
                    table[8][j-1]+=1;
                if (table[8][j]!=9)
                    table[8][j]+=1;
                if (table[8][j+1]!=9)
                    table[8][j+1]+=1;
                if (table[9][j-1]!=9)
                    table[9][j-1]+=1;
                if (table[9][j+1]!=9)
                    table[9][j+1]+=1;
            }
        }
        for (int i = 1; i<9; i++){ //checking leftmost column
            if(table[i][0]==9){
                if (table[i-1][1]!=9)
                    table[i-1][1]+=1;
                if (table[i][1]!=9)
                    table[i][1]+=1;
                if (table[i+1][1]!=9)
                    table[i+1][1]+=1;
                if(table[i-1][0]!=9)
                    table[i-1][0]+=1;
                if(table[i+1][0]!=9)
                    table[i+1][0]+=1;
            }
        }
        for (int i = 1; i<9; i++){ //checking rightmost column
            if(table[i][9]==9){
                if (table[i-1][8]!=9)
                    table[i-1][8]+=1;
                if (table[i][8]!=9)
                    table[i][8]+=1;
                if (table[i+1][8]!=9)
                    table[i+1][8]+=1;
                if(table[i-1][9]!=9)
                    table[i-1][9]+=1;
                if(table[i+1][9]!=9)
                    table[i+1][9]+=1;
            }
        }
    }
    public static int isBomb(){
        return bombs;
    }
}
















