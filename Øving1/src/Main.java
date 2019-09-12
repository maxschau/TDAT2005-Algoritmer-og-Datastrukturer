import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] changesInStock = getChangesInStock(10, 1000);
        int amtRounds = 10000;

        long startTime = System.nanoTime();
        for (int i = 0; i < amtRounds; i++) {
            stockFinder(changesInStock);
        }
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime);
        System.out.println("Time in ns: " + (totalTime/amtRounds));

    }

    public static void stockFinder(int[] stockValues) {
        int originalValue = 0;
        int dayBought = 0;
        int daySold = 1;
        int highestProfit = stockValues[0];
        for (int i = 0; i < stockValues.length; i++) {
            originalValue += stockValues[i]; //Adds up the starting value with the changes
            int checkingValue = originalValue;
            for (int j = i+1; j < stockValues.length; j++) {
                checkingValue += stockValues[j];
                if ((checkingValue - originalValue) > highestProfit) {
                    highestProfit = checkingValue - originalValue;
                    dayBought = i;
                    daySold = j;
                }
            }
        }
        System.out.println("If you buy on day " + (dayBought+1) + ", and sell on day " + (daySold+1) + " you will gain a profit of " + highestProfit);
    }

    /*
    Method which generates an array filled with random values, both negative and positive
     */

    public static int[] getChangesInStock(int stocksValue, int amountOfDays) {
        Random random = new Random();
        int[] changesInStock = new int[amountOfDays];
        for (int i = 0; i < amountOfDays; i++) {
            int randomNumber = random.nextInt(stocksValue) - stocksValue/2;
            changesInStock[i] = randomNumber;
        }
        return changesInStock;
    }
}
