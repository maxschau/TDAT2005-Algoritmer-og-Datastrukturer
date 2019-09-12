import java.util.Date;

public class oppgave1 {
    public static void main(String[] args) {
        int x = 48;
        int n = 100000;


        System.out.println(xPowerOfN(x,n));
        int amountOfRounds = 4000;

        long startTime = System.nanoTime();
        for (int i = 0; i < amountOfRounds; i++) {
            xPowerOfN2(x,n);
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / amountOfRounds);


    }

    private static double xPowerOfN(double x, int n) {
       if (n == 0) {
            return 1;
       } else {
           return x*xPowerOfN(x, n-1);
       }
    }

    private static double xPowerOfN2(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n % 2 != 0) { // n is a odd number
            return x * xPowerOfN2((x*x), ((n-1)/2));
        } else {
            return xPowerOfN2((x*x),(n/2));
        }
    }
}
