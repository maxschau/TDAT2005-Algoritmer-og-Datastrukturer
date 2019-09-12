import java.util.Random;

public class main {
    public static void main(String[] args) {
        int n = 1000000;


        int[] t = generateTable(n);



        //Methods to check the sorting algorithm:
        quickSortWithChange(t, 0, t.length-1);
        System.out.println((testSort(t)));
        checksum(t);








        t = generateTable(n);
        long startTime = System.nanoTime();
        quickSortWithChange(t, 0, t.length-1);
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("The time of the algorithm is: " + (time*Math.pow(10,-6)) + " in ms");


    }

    public static void quickSort(int[] t, int v, int h) {
        if (h - v > 2) {
            int pivot = partition(t, v, h);
            quickSort(t, v, pivot - 1);
            quickSort(t, pivot + 1, h);
        }else {
            median3sort(t,v,h);
        }
    }



    /**
     *
     * @param t Array we want to sort
     * @param v The first index of the table
     * @param h The last index of the table
     */
    public static void quickSortWithChange(int[] t, int v, int h) {
        if (h - v > 20) {
            int pivot = partition(t, v, h);
            quickSortWithChange(t, v, pivot - 1);
            quickSortWithChange(t, pivot + 1, h);
        }else {
            bubblesort(t,v,h);
        }
    }

    private static int partition(int[] t, int v, int h) {
        int iv, ih;
        int m = median3sort(t, v, h);
        int dv = t[m];
        swap(t, m, h - 1);
        for (iv = v, ih = h - 1; ; ) {
            while (t[++iv] < dv) ;
            while (t[--ih] > dv) ;
            if (iv >= ih) break;
            swap(t, iv, ih);
        }
        swap(t, iv, h - 1);
        return iv;
    }

    private static int median3sort(int[] t, int v, int h) {
        int m = (v + h) / 2;
        if (t[v] > t[m]) swap(t,v,m);
        if (t[m] > t[h]) {
            swap(t,m,h);
            if(t[v] > t[m]) swap(t,v,m);
        }
        return m;
    }

    /**
     * Bubblesort method which sorts a specific part of an array
     * @param t Which table we are sorting
     * @param v The smallest index
     * @param h The highest index
     */
    private static void bubblesort(int[] t, int v, int h) {
        for (int i = h; i >= v; --i) {
            for (int j = v; j < i; ++j) {
                if (t[j] > t[j+1]) {
                    swap(t,j,j+1);
                }
            }
        }
    }

    /*
    Method to swap two indexes in a specific array
     */
    private static void swap(int[] t, int a, int b) {
        int temp = t[a];
        t[a] = t[b];
        t[b] = temp;
    }

    private static int[] generateTable(int n) {
        int[] t = new int[n];
        Random random = new Random();
        for (int i = 0; i < t.length; i++) {
            t[i] = random.nextInt(100);
        }
        return t;
    }

    /*
    Private test methods to check if the sorting is working
     */

    private static boolean testSort(int[] t) {
        for (int i = 0; i < t.length - 2; i++) {
            if (t[i] > t[i+1]) {
                return false;
            }
        }
        return true;
    }

    private static void checksum(int[] t) {
        int sum = 0;
        for (int i = 0; i < t.length; i++) {
            sum += t[i];
        }
        //Sorting the table
        int sum1 = 0;
        quickSort(t, 0, t.length - 1);
        for (int i = 0; i < t.length; i++) {
            sum1 += t[i];
        }

        System.out.println("Sum before sorting: " + sum);
        System.out.println("Sum after sorting: " + sum1);

    }

    /**
     * Method which calculates what we should choose as the limit of the smaller tables
     * @return the size of the table
     */
    private static int findN() {
        long lowestTime = 999999999;
        int index = 0;
        int[] t = generateTable(1000000);

        for (int i = 2; i < 200; i++) {
            long startTime = System.nanoTime();
            quickSortWithChange(t, 0, t.length - 1);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            if (time < lowestTime) {
                lowestTime = time;
                index = i;
            }
        }
        return index;

    }


}
