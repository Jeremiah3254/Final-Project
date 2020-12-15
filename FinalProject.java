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
        
        int level = 0;
        
        int CurrentLives = 1;
        int livesLostOnLevel = 0;
        int livesLostOnRound = 0;
        
        int x = 1;
        int y = 2; 
        boolean answer = false;
        
        int rowWidth = 2;
        int rowAmountNumber = 2;
        
        int currentRowAmount = 0;
        int currentRowAmountNumber = 0;
    
        int[] boxSize = new int[250000];
        int[] answeredRows = new int[250000];
        int[] livesLostOnPoints = new int[250000];
        
        for (int r = 0; r < livesLostOnPoints.length; r++) {
            livesLostOnPoints[r] = 0;
        }
        
        for (int q = 0; q < answeredRows.length; q++) {
            answeredRows[q] = 0;
        }
        
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

            answer = true;
            for (int e = 0; e < (rowWidth*rowAmountNumber); e++) {
                if (answeredRows[e] == 1 && boxSize[e] > 8 && livesLostOnPoints[e] == 0) {
                    livesLostOnPoints[e] = 1;
                    livesLostOnLevel = livesLostOnLevel + 1;
                }
            }
            CurrentLives = CurrentLives - (livesLostOnLevel - livesLostOnRound);
            livesLostOnRound = livesLostOnLevel;

            if (CurrentLives <= 0) {
               for (int i = 0; i < (rowWidth*rowAmountNumber); i++) {
                   answeredRows[i] = 1;
               }
               questionAnswered(boxSize,answeredRows,rowWidth,rowAmountNumber,CurrentLives);
               System.out.println("G a m e  O v e r");
                }else if (CurrentLives >= 1) {
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
        
    }
    
    
    public static void findCordinates(int cordinateOne, int cordinateTwo,int rowLength,int rowHeight,int[] otherStuff) {
        int calculateCordinates = (rowHeight*rowLength) - (cordinateTwo*rowLength);
        int finalCalculate = calculateCordinates + (cordinateOne-1);
        otherStuff[finalCalculate] = 1;
    }
    
    
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
}
