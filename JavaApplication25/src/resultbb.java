/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class resultbb implements Comparable<resultbb>{
   int x,y;
   double sum;
   boolean l;
   boolean r;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isL() {
        return l;
    }

    public void setL(boolean l) {
        this.l = l;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }
   @Override
   public int compareTo(resultbb other) {
         return (int) (other.sum - sum);
      }

    @Override
    public String toString() {
        return x+"   "+y+"\n"+sum+"    l:  "+l+"   r:  "+r;
    }
 
   
    
}
