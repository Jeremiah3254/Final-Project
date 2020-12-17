
package finalproject;

public class Levels {
    private int rowWidth = 2;
    private int rowAmountNumber = 2;
    
    //constructor
    public Levels(int rowWidth,int rowAmountNumber) {
        this.rowWidth = rowWidth;
        this.rowAmountNumber = rowAmountNumber;
    }

    //methods
    public void CalculateLevelIncrease(Levels level) {
        int calculateIncrease = (int) (level.getRowWidth()/2)+1;
        
        if (calculateIncrease > 2) {
                    level.rowWidth = level.rowWidth + 2;
                    level.rowAmountNumber = level.rowAmountNumber + 2;
                } else {

                    level.rowWidth = level.rowWidth + calculateIncrease;
                    level.rowAmountNumber = level.rowAmountNumber + calculateIncrease;
                }
    }
    
    
    //getters & setters
    public int getRowWidth() {
        return rowWidth;
    }

    public int getRowAmountNumber() {
        return rowAmountNumber;
    }

    public void setRowWidth(int rowWidth) {
        this.rowWidth = rowWidth;
    }

    public void setRowAmountNumber(int rowAmountNumber) {
        this.rowAmountNumber = rowAmountNumber;
    }
    
    
}
