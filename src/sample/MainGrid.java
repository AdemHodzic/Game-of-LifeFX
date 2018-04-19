package sample;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainGrid {
    {
        randomize();
    }
    private static final int WIDTH = 40;                  //Number of cells in one row
    private static final int HEIGHT = 40;                 //Number of Rows
    private static final int WINDOW_WIDTH = 375;          //Window width
    private static final int WINDOW_HEIGHT = 375;         //Window height

    private static byte[][] arr = new byte[HEIGHT][WIDTH];//Array of 1's and 0' that represents whether cell is alive or dead

    MainGrid(){

    }

    //Method that makes GridPane for Scene
    public GridPane getGrid(){
        int index = 0;
        GridPane grid = new GridPane();
        for(int i = 1;i<HEIGHT-1;i++){
            for(int j = 1;j<WIDTH-1;j++){
                Rectangle rect = new Rectangle();
                if(arr[i][j]==0) {
                    rect.setFill(Color.BLACK);
                    System.out.println("N");
                }else if(arr[i][j]==1) {
                    rect.setFill(Color.WHITE);
                    System.out.println("WP");
                }
                rect.setWidth(10);
                rect.setHeight(10);
                GridPane.setConstraints(rect,j,i);
                grid.getChildren().add(rect);
                index++;
            }
        }
        System.out.println("Finished");
        return grid;
    }

    //Returns Scene
    public Scene getScene(){
        return new Scene(getGrid(),WINDOW_WIDTH,WINDOW_HEIGHT);
    }

    //Populates arr with random values
    private void randomize(){
        for(int i = 1;i<HEIGHT-1;i++)
            for (int j = 1; j < WIDTH - 1; j++) {
                byte rand = (byte) ((Math.random() + 0.33) % 2);
                arr[i][j] = rand;
            }
    }

    //Updates arr based on Game of Life rules
    public void update(){
        byte tempArr[][] = new byte[HEIGHT][WIDTH];
        for(int i = 1;i<HEIGHT-1;i++){
            for(int j = 1;j<WIDTH-1;j++){
                byte current = arr[i][j];
                byte numOfLiveCells = 0;
                for(int k = -1;k<2;k++){
                    for(int g = -1;g < 2;g++){
                        numOfLiveCells+=arr[i+k][j+g];
                    }
                }
                numOfLiveCells-=current;
                if(current==1 && (numOfLiveCells > 3 || numOfLiveCells < 2)){
                    tempArr[i][j] = 0;
                }else if(current==0 && numOfLiveCells==3){
                    tempArr[i][j] = 1;
                }else{
                    tempArr[i][j] = current;
                }
            }
        }
        arr = tempArr;
        tempArr = null;
    }
}
