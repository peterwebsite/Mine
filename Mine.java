import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.*;
import java.util.*;


public class Mine implements ActionListener{
	int grid[][]=new int[8][8];
	JButton bgrid[][]= new JButton[8][8];
	//133=Bomb
	//124=Blank
	final int BOMB=9;
	final int BLANK=0;
	JFrame win;
	Random rand = new Random();

	ImageIcon numbers[]={
		new ImageIcon("0.png"),
		new ImageIcon("1.png"),
		new ImageIcon("2.png"),
		new ImageIcon("3.png"),
		new ImageIcon("4.png"),
		new ImageIcon("5.png"),
		new ImageIcon("BOMB.png"),
		new ImageIcon("BOMB.png"),
		new ImageIcon("BOMB.png"),
		new ImageIcon("BOMB.png")		
		};

	public void buildGUI(){
		win=new JFrame("Mine");
		JPanel body=new JPanel();
		body.setLayout(new GridLayout(8,8));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				bgrid[i][j].setActionCommand(""+i+","+j);
				bgrid[i][j].addActionListener(this);
				body.add(bgrid[i][j]);
			}
		}
		win.add(body);
		win.pack();
		win.setVisible(true);
		win.setSize(270,270);
	}

	
	public void actionPerformed(ActionEvent e){
		String line=e.getActionCommand();//"4,5"
		System.out.println(line);
		int pos=line.indexOf(",");
		String part1=line.substring(0,pos);
		String part2=line.substring(pos+1);
		int r=Integer.parseInt(part1);
		int c=Integer.parseInt(part2);
		bgrid[r][c].setIcon(numbers[grid[r][c]]);
		if(grid[r][c]==BLANK){
			check(r,c);
		}
		if(grid[r][c]==BOMB){
			System.exit(0);
		}
	}

	public void check(int r,int c){
		if(r-1>-1)clear(r-1,c);
		if(r+1<8)clear(r+1,c);
		if(c-1>-1)clear(r,c-1);
		if(c+1<8)clear(r,c+1);
		if(r+1<8 && c+1<8)clear(r+1,c+1);
		if(r-1>-1 && c-1>-1)clear(r-1,c-1);
		if(r+1<8 && c+1>-1)clear(r+1,c-1);
		if(r-1>-1 && c+1<8)clear(r-1,c+1);
	}

	public void clear(int r, int c){
		if(grid[r][c]!=BLANK)return;
		if(bgrid[r][c].getIcon()==numbers[0])return;

		if(grid[r][c]==BLANK){
			bgrid[r][c].setIcon(numbers[0]);
			check(r,c);
		}
		clear(r,c);
	}


	public void init(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				grid[i][j]=BLANK;
				bgrid[i][j]=new JButton();
			}
		}
		//1st 10
		int counter=0;
		for(int i=0;i<8;i++){
 			for(int j=0;j<8;j++){
 				if(counter>9)break;
 				grid[i][j]=BOMB;
 				counter++;
 			}
 		}
 		shuffle();
 		check4Bombs();
 		printGrid();
 		buildGUI();

 	}
 
 	public void placeBombs(){
 		int pick=rand.nextInt(8*8);
 		int counter=0;
 		for(int i=0;i<8;i++){
 			for(int j=0;j<8;j++){
 				/*if(pick==counter){
 					grid[i][j];
 				}
 				counter++;
 			}*/
 		}
 	}
 	}
 	
 	public void shuffle(){
	for(int i=0; i<8; i++){
		for(int j=0; j<8; j++){
			int x= rand.nextInt(8);
			int y= rand.nextInt(8);
			swap(i,j,x,y);
		}
	}
}

	public void swap(int x1,int y1, int x2, int y2){
		int temp = grid[x1][x2];
		grid[x1][x2]=grid[x2][y2];
		grid[x2][y2]=temp;
	}

void check4Bombs(){
		int bombcounter=0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(grid[i][j]!= BOMB){
					if(i-1>=0&&i+1<=7&&j-1>=0&&j+1<=7){
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==0&&j==0){
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
					}
					else if(i==0&&j==7){
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==7&&j==7){
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
					}
					else if(i==7&&j==0){
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==0){
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
					}
					else if(j==0){
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
					}
					else if(j==7){
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==7){
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					grid[i][j]=bombcounter;
					
					//System.out.println(bombcounter);
					bombcounter=0;
				}
			}
		}
	}

 	public static void main(String args[]){new Mine();}

	public void printGrid(){

		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				System.out.print('|'+grid[i][j]+"|");
			
			}
			System.out.println();
		}
	}

	public Mine(){
		init();

	}

}
