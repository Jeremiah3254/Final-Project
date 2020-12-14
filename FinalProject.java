/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;
import java.util.Scanner;
/**
 *
 * @author Jeremiah
 */
public class FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kboard = new Scanner(System.in);
        
        String spacers = "  ";
        
        //current level
        int level = 0;
        //current level
        
        //
        int CurrentLives = 1;
        int livesLostOnLevel = 0;
        int livesLostOnRound = 0;
        //
        
        // Player's Input's
        int x = 1;
        int y = 2; 
        boolean answer = false;
        // Player's Input's
        
        // current box size
        int rowWidth = 2;
        int rowAmountNumber = 2;
        // current box size
        
        // other Variables
        int currentRowAmount = 0;
        int currentRowAmountNumber = 0;
        // other Variables
    
        int[] boxSize = new int[250000];
        int[] answeredRows = new int[250000];
        int[] livesLostOnPoints = new int[250000];
        
        for (int r = 0; r < livesLostOnPoints.length; r++) {
            livesLostOnPoints[r] = 0;
        }
        
        for (int q = 0; q < answeredRows.length; q++) {
            answeredRows[q] = 0;
        }
        
        /*for (int i = 90; i < 100; i++) {
            answeredRows[i] = 1;
        }*/
    System.out.println("Current Lives: "+CurrentLives);
        for (int i = 0; i < (rowWidth*rowAmountNumber); i++) {
            
            boxSize[i] = (int) (Math.random()*11);
            
        if (currentRowAmountNumber < rowAmountNumber) {
            
            if (boxSize[i] <= 8 && answeredRows[i] == 1) {
                System.out.print("*"+spacers);
                currentRowAmount += 1;
            } else if (boxSize[i] > 8 && answeredRows[i] == 1) {
                System.out.print("+"+spacers);
                currentRowAmount += 1;
            } else {
                System.out.print(" "+spacers);
                currentRowAmount += 1;
            }
           
            if (currentRowAmount == rowWidth) {
                System.out.println(" "+spacers);
                currentRowAmount = 0;
                currentRowAmountNumber += 1;
                } 
            }
        }
        System.out.println("Grid Scale: (1-"+rowWidth+"),(1-"+rowAmountNumber+")");
        
        while (answer == false) {
            //check for level
            int maxNeeded = 0;
            int currentAnswered = 0;
            for (int q = 0; q<(rowWidth*rowAmountNumber); q++) {
                if (boxSize[q] <= 8) {
                    maxNeeded += 1;
                }
            }
            for (int i = 0; i<(rowWidth*rowAmountNumber); i++) {
                if (boxSize[i] <= 8 && answeredRows[i] == 1) {
                    currentAnswered += 1;
                }
            }

            if (maxNeeded == currentAnswered) {
                int calculateIncrease = (int) (rowWidth/2)+1;
                for (int i = 0; i < (rowWidth*rowAmountNumber); i++) {
                   answeredRows[i] = 1;
               }
               questionAnswered(boxSize,answeredRows,rowWidth,rowAmountNumber,CurrentLives);
               level = level + 1;
               System.out.println("\n"+"-------------------------------------");
                System.out.println("L e v e l "+"["+level+"]"+"  C o m p l e t e");
                if (livesLostOnLevel > 3) {
                    CurrentLives = CurrentLives+0;
                    System.out.println("Lives Gained: "+"0");
                } else {
                    CurrentLives = CurrentLives+(3-livesLostOnLevel);
                    System.out.println("Lives Gained: "+(3-livesLostOnLevel));
                }
                System.out.println("Bombs Exploded: "+livesLostOnLevel);
                System.out.println("-------------------------------------"+"\n");
                for (int w = 0; w < (rowWidth*rowAmountNumber); w++) {
                    answeredRows[w] = 0;
                }
                for (int v = 0; v < livesLostOnPoints.length; v++) {
                    livesLostOnPoints[v] = 0;
                }
                if (calculateIncrease > 2) {
                    rowWidth = rowWidth + 2;
                    rowAmountNumber = rowAmountNumber + 2;
                } else {

                    rowWidth = rowWidth + calculateIncrease;
                    rowAmountNumber = rowAmountNumber + calculateIncrease;
                }
                livesLostOnLevel = 0;
                livesLostOnRound = 0;
                for (int b = 0; b < (rowWidth*rowAmountNumber); b++) {
                    boxSize[b] = (int) (Math.random()*11);
                }
                questionAnswered(boxSize,answeredRows,rowWidth,rowAmountNumber,CurrentLives);
            }
            //check for level
            //changeLevel(rowWidth,rowAmountNumber,boxSize,answeredRows,livesLostOnPoints,CurrentLives,livesLostOnLevel);
            answer = true;
            for (int e = 0; e < (rowWidth*rowAmountNumber); e++) {
                if (answeredRows[e] == 1 && boxSize[e] > 8 && livesLostOnPoints[e] == 0) {
                    livesLostOnPoints[e] = 1;
                    livesLostOnLevel = livesLostOnLevel + 1;
                }
            }
            CurrentLives = CurrentLives - (livesLostOnLevel - livesLostOnRound);
            livesLostOnRound = livesLostOnLevel;
            //check to see if any of the picked points are bombs
            if (CurrentLives <= 0) {
               for (int i = 0; i < (rowWidth*rowAmountNumber); i++) {
                   answeredRows[i] = 1;
               }
               questionAnswered(boxSize,answeredRows,rowWidth,rowAmountNumber,CurrentLives);
               System.out.println("G a m e  O v e r");
                }else if (CurrentLives >= 1) {
                    // stuff
                    System.out.println("Enter the cordinates of where you dont think the bombs are");
                    System.out.print("X cordinate:");
                    x = kboard.nextInt();
                    System.out.print("Y cordinate:");
                    y = kboard.nextInt();
                    if (x == 0 || y == 0 || x > rowWidth || y > rowAmountNumber) {
                        System.out.println("~Error; Invalid Inputs~");
                        questionAnswered(boxSize,answeredRows,rowWidth,rowAmountNumber,CurrentLives);
                        answer = false;
                    }else {
                        findCordinates(x,y,rowWidth,rowAmountNumber,answeredRows);
                        questionAnswered(boxSize,answeredRows,rowWidth,rowAmountNumber,CurrentLives);
                        answer = false;
                }
            }
        }
        
        
       // check to see if answer is greater than 0 then repeat it all and show results
        
        
    }
    
    /*public static void changeLevel(int rowWidth,int rowAmountNumber,int[] startRows,int[] answeredRows,int[] livesLostOnPoints,int CurrentLives,int livesLostOnLevel){
        int maxNeeded = 0;
        int currentAnswered = 0;
        for (int q = 0; q<(rowWidth*rowAmountNumber); q++) {
            if (startRows[q] <= 8) {
                maxNeeded += 1;
            }
        }
        for (int i = 0; i<(rowWidth*rowAmountNumber); i++) {
            if (startRows[i] <= 8 && answeredRows[i] == 1) {
                currentAnswered += 1;
            }
        }
        
        if (maxNeeded == currentAnswered) {
            int calculateIncrease = (int) (rowWidth/2)+1;
            
            if (CurrentLives > 3) {
                CurrentLives = CurrentLives+0;
            } else {
                CurrentLives = CurrentLives+(3-livesLostOnLevel);
            }
            for (int v = 0; v < livesLostOnPoints.length; v++) {
                livesLostOnPoints[v] = 0;
            }
            if (calculateIncrease > 3) {
                rowWidth = rowWidth + 3;
                rowAmountNumber = rowAmountNumber + 3;
            } else {
                
                rowWidth = rowWidth + calculateIncrease;
                rowAmountNumber = rowAmountNumber + calculateIncrease;
                System.out.println("worked kinda"+calculateIncrease+" "+rowWidth+" "+rowAmountNumber);
            }
        }
    }*/
    
    public static void findCordinates(int cordinateOne, int cordinateTwo,int rowLength,int rowHeight,int[] otherStuff) {
        int calculateCordinates = (rowHeight*rowLength) - (cordinateTwo*rowLength);
        int finalCalculate = calculateCordinates + (cordinateOne-1);
        otherStuff[finalCalculate] = 1;
    }
    
    /* public static void prompt(int cordinateOne,int cordinateTwo,int rowWidth,int rowAmountNumber,Scanner kboard) {
        System.out.println("Grid Scale: (1-"+rowWidth+"),(1-"+rowAmountNumber+")");
        System.out.println("Enter the cordinates of where you dont think the bombs are");
       System.out.print("X cordinate:");
        cordinateOne = kboard.nextInt();
        System.out.print("Y cordinate:");
        cordinateTwo = kboard.nextInt();
    }*/
    
     public static void questionAnswered(int[] boxSize,int[] answeredRows,int rowWidth,int rowAmountNumber,int CurrentLives) {
        
        int currentRowAmount = 0;
        int currentRowAmountNumber = 0;
        String spacers = "  ";
        System.out.println(" ");
        System.out.println("Current Lives: "+CurrentLives);
        for (int i = 0; i < (rowWidth*rowAmountNumber); i++) {
            
        if (currentRowAmountNumber < rowAmountNumber) {
            
            if (boxSize[i] <= 8 && answeredRows[i] == 1) {
                System.out.print("*"+spacers);
                currentRowAmount += 1;
            } else if (boxSize[i] > 8 && answeredRows[i] == 1) {
                System.out.print("+"+spacers);
                currentRowAmount += 1;
            } else {
                System.out.print(" "+spacers);
                currentRowAmount += 1;
            }
           
            if (currentRowAmount == rowWidth) {
                System.out.println(" "+spacers);
                currentRowAmount = 0;
                currentRowAmountNumber += 1;
                } 
            }
        }
        System.out.println("Grid Scale: (1-"+rowWidth+"),(1-"+rowAmountNumber+")");
        
    }
    /*
    public static void checkAmountOfLives(int currentLives, boolean lost,int[] boxSize,int[] boxAnswers,int rowWidth,int rowAmountNumber) {
        if (currentLives <= 0) {
           lost = true; 
           for (int i = 0; i < (rowWidth*rowAmountNumber); i++) {
               boxAnswers[i] = 1;
           }
           questionAnswered(boxSize,boxAnswers,rowWidth,rowAmountNumber);
           System.out.println("G a m e  O v e r");
        } 
    }*/
}
