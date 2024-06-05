//Fix this class, so that the ArrayList result in the below code will be assigned the indicated list.

import java.util.*;

class Numbers
{
  private int max;
  private int min;
  private int step;

  public Numbers(int max, int min, int step)
  {
    this.max = max;
    this.min = min;
    this.step = step;
  }
  public ArrayList<Integer> countDown()
  {
    ArrayList<Integer> result = new ArrayList<Integer>();

    for(int i = this.max; i >= this.min && this.step > 0; i = i - this.step) 
    {
      result.add(i);
    }

    return result;
  }
}


    
public class Exercise  {
  
  public static void main(String [] arg){

    Numbers numbers = new Numbers(10,-2,2);
    ArrayList<Integer> result = numbers.countDown(); //result <- [10, 8, 6, 4, 2, 0, -2]