package greedy;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class appleStock_old {

    public static int getMaxProfit(int[] stockPrices) {
        
        if(stockPrices.length <= 1) return 0;
        
        //find the smallest # starting from the begining  and buy.
        int buy = stockPrices[0];
        int buyIndex = 0;
        for(int i=1; i < stockPrices.length; i++){
            if(stockPrices[i] < buy){
                buy = stockPrices[i];
                buyIndex=i;
            }
        }
        //find the largest # starting after the buy and sell
        if(buyIndex == stockPrices.length-1) return buy * -1;
        
        int sell = stockPrices[buyIndex + 1];
        for(int j=buyIndex+2; j < stockPrices.length; j++){
                if(stockPrices[j] > sell){
                    sell = stockPrices[j]; 
                }
        }
        
        
        
        return sell - buy; 
    }


















    // tests

    @Test
    public void priceGoesUpThenDownTest() {
        final int actual = getMaxProfit(new int[] {1, 5, 3, 2});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void priceGoesDownThenUpTest() {
        final int actual = getMaxProfit(new int[] {7, 2, 8, 9});
        final int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    public void priceGoesUpAllDayTest() {
        final int actual = getMaxProfit(new int[] {1, 6, 7, 9});
        final int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    public void priceGoesDownAllDayTest() {
        final int actual = getMaxProfit(new int[] {9, 7, 4, 1});
        final int expected = -2;
        assertEquals(expected, actual);
    }

    @Test
    public void priceStaysTheSameAllDayTest() {
        final int actual = getMaxProfit(new int[] {1, 1, 1, 1});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithOnePriceTest() {
        getMaxProfit(new int[] {5});
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyPricesTest() {
        getMaxProfit(new int[] {});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(appleStock_old.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
