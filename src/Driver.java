
import java.util.Random;
import java.util.Scanner;

public class Driver {

    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    static int QScompar = 0;
    static int QSswaps = 0;
    static int step = 1;
    static int partitions = 0;

    public static void main(String[] args) {

        int[] Array;
        int[] sequence;
        int size = 0;
        char type;
        int quickSort4;
        String resp;

        do {

            System.out.println("Enter the size of the Array: ");
            size = sc.nextInt();

            System.out.println("Enter I for inverse Array, R for random Array "
                    + "or P for partially sorted Array");
            type = sc.next().charAt(0);

            System.out.println("With what sequence do you want to quickSort4 it:"
                    + "\n1.Quick Sort(Normal)\n2.Quick Sort(Median of 3)"
                    + "\n3.Quick Sort(Cut off to Insertion)\n4.Quick Sort(Dual-Pivot)");
            quickSort4 = sc.nextInt();

            switch (quickSort4) {
                case 1: {
                    /*1. Implement the partitionIt method in a Java program and 
                    run with a sample array populated with random numbers. Add 
                    counters to count the number of swaps and the number of
                    comparisons. Display these values.*/
                    QScompar = 0;
                    QSswaps = 0;
                    step = 1;
                    Array = generateRandom(size, type);
                    quickSort1(Array, 0, size - 1);
                    System.out.println(QSswaps + " Swaps were made.");
                    System.out.println(QScompar + " Comparisons were made.");
                    System.out.println("Final Array:");
                    print(Array);

                    /*2. What is the Big O notation for the partitionIt method? 
                    How do you know?*/
 /*It is o(n).
                    For each partition step it takes only one loop .*/
 /*3. Assuming the chosen pivot is always a[right] what 
                    problems would this algorithm run into with an inversely 
                    sorted array? Is this an efficient way to choose the pivot? 
                    Include a trace of this situation, similar to the provide 
                    trace above */
 /* With an inverse array every number will be greater than 
                    the pivot*/
 /*Run this with an inversely sorted array. What happens to 
                     the efficiency of the quick quickSort4? Why is this?*/
 /*With a random array of size 500:
                     1093 Swaps were made.
                     3049 Comparisons were made.
                     and with a inversely sorted array of the same size:
                     466 Swaps were made.
                     82375 Comparisons were made.
                     It makes more comparisons because all numbers are equal or greater than the pivot*/
                    break;
                }

                case 2: {
                    QScompar = 0;
                    QSswaps = 0;
                    step = 1;
                    Array = generateRandom(size, type);
                    quickSort2(Array, 0, size - 1);
                    System.out.println(QSswaps + " Swaps were made.");
                    System.out.println(QScompar + " Comparisons were made.");
                    System.out.println("Final Array:");
                    print(Array);

                    /*Run with an inversely sorted array and compare and contrast 
                    the efficiency between the original pivot choice and the median of three pivot choice. 
                     */

 /*With a inverse array of size 100 Normal Quick Sort:
                     96 Swaps were made.
                     4742 Comparisons were made.
                     and with the median of 3:
                     128 Swaps were made.
                     418 Comparisons were made.
                     An incredible improvement in the amount of comparisons, therefore
                    the time complexity will be more efficient*/
                    break;
                }

                case 3: {
                    QScompar = 0;
                    QSswaps = 0;
                    step = 1;
                    Array = generateRandom(size, type);
                    quickSort3(Array, 0, size - 1);
                    System.out.println(QSswaps + " Swaps were made.");
                    System.out.println(QScompar + " Comparisons were made.");
                    System.out.println("Final Array:");
                    print(Array);

                    /*Calculate the reduction of recursive calls and compare this to the quick sort 
                    implementation without the cutoff. Display the result. */
 /*Swaps and comparisons of recursive methods without cut off:
                    Size 500:
                    1097 Swaps were made.
                    2961 Comparisons were made.
                    Swaps and comparisons of recursive methods with cut off:
                    Size 500:
                    1374 Swaps were made.
                    3905 Comparisons were made.
                     */
                    break;
                }

                case 4: {

                    QScompar = 0;
                    QSswaps = 0;
                    step = 1;
                    Array = generateRandom(size, type);
                    quickSort2(Array, 0, size - 1);
                    System.out.println(QSswaps + " Swaps were made.");
                    System.out.println(QScompar + " Comparisons were made.");
                    System.out.println("Final Array:");
                    print(Array);
                    break;
                }

                case 5: {
                    size = 0;
                    for (int i = 0; i < 10; i++) {
                        size += 50000;
                        partitions = 0;
                        Array = generateRandom(size, type);
                        quickSort4(Array, 0, size - 1);
                        System.out.println("Size: " + size);
                        System.out.println("Partitions made: " + partitions);
                        System.out.println("---------------------------------");

                    }
                    break;
                }
            }

            System.out.println("Do you want to Sort another Array? y/n");
            resp = sc.next();

        } while (!resp.equals("n"));
    }

    public static void quickSort1(int[] Array, int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            int pivot = Array[right];
            int partition = partitionIt(Array, left, right, pivot);
            System.out.println("Partition " + step);
            System.out.println("Range from " + left + " to " + right);
            step++;
            print(Array);
            quickSort1(Array, left, partition - 1);
            quickSort1(Array, partition + 1, right);
        }
    }

    public static void quickSort2(int[] Array, int left, int right) {
        int size = right - left + 1;
        if (size <= 3) // manual quickSort4 if small
        {
            manualSort(Array, left, right);
        } else // quicksort if large
        {
            int median = medianOf3(Array, left, right);
            int partition = partitionIt2(Array, left, right, median);
            partitions++;
            System.out.println("Partition " + step);
            System.out.println("Range from " + left + " to " + right);
            step++;
            print(Array);
            quickSort2(Array, left, partition - 1);
            quickSort2(Array, partition + 1, right);
        }
    }

    public static void quickSort3(int[] Array, int left, int right) {
        System.out.println("Size of Range: " + (right - left));
        if (right - left <= 0) {
            return;
        } else if (right - left <= 10) {
            System.out.println("Called Insertion Sort");
            insertionSort(Array, left, right);
        } else {
            int pivot = Array[right];
            int partition = partitionIt(Array, left, right, pivot);
            System.out.println("Partition " + step);
            System.out.println("Range from " + left + " to " + right);
            step++;
            print(Array);
            quickSort3(Array, left, partition - 1);
            quickSort3(Array, partition + 1, right);
        }
    }

    public static void quickSort4(int[] Array, int left, int right) {
        QScompar++;
        if (right > left) {

            if (Array[left] > Array[right]) {
                swap(Array, left, right);
                QSswaps++;
            }
            QScompar++;

            int p = Array[left], q = Array[right];

            // Partition
            int l = left + 1, g = right - 1, k = l;
            while (k <= g) {

                QScompar++;
                if (Array[k] < p) {
                    swap(Array, k, l);
                    QSswaps++;
                    ++l;
                } else if (Array[k] >= q) {
                    while (Array[g] > q && k < g) {
                        QScompar++;
                        --g;
                    }
                    swap(Array, k, g);
                    --g;
                    if (Array[k] < p) {
                        QScompar++;
                        swap(Array, k, l);
                        QSswaps++;
                        ++l;
                    }
                }
                QScompar++;
                ++k;
            }
            System.out.println("Partition " + step);
            System.out.println("Range from " + left + " to " + right);
            step++;
            print(Array);
            partitions++;
            --l;
            ++g;

            // Swap pivots to final place
            swap(Array, left, l);
            QSswaps++;
            swap(Array, right, g);
            QSswaps++;

            quickSort4(Array, left, l - 1);
            quickSort4(Array, l + 1, g - 1);
            quickSort4(Array, g + 1, right);
        }
    }

    public static void insertionSort(int[] Array, int lowerBound, int upperBound) {

        int temp, n;

        for (int i = lowerBound; i <= upperBound; i++) {

            n = i - 1;
            temp = Array[i];

            while (n >= 0 && Array[n] > temp) {
                QScompar++;
                Array[n + 1] = Array[n];
                n--;
                QSswaps++;
            }

            if (Array[n + 1] != temp) {
                Array[n + 1] = temp;
            }
        }

        print(Array);
    }

    public static int medianOf3(int[] Array, int left, int right) {
        int center = (left + right) / 2;
        // order left & center
        if (Array[left] > Array[center]) {
            swap(Array, left, center);
        }
        // order left & right
        if (Array[left] > Array[right]) {
            swap(Array, left, right);
        }
        // order center & right
        if (Array[center] > Array[right]) {
            swap(Array, center, right);
        }

        swap(Array, center, right - 1);             // put pivot on right
        return Array[right - 1];          // return median value
    }

    public static void manualSort(int[] Array, int left, int right) {
        int size = right - left + 1;
        if (size <= 1) {
            return;         // no quickSort4 necessary
        }
        if (size == 2) {               // 2-quickSort4 left and right
            if (Array[left] > Array[right]) {
                swap(Array, left, right);
            }
            return;
        } else // size is 3
        {               // 3-quickSort4 left, center, & right
            if (Array[left] > Array[right - 1]) {
                swap(Array, left, right - 1);                // left, center
            }
            if (Array[left] > Array[right]) {
                swap(Array, left, right);                  // left, right
            }
            if (Array[right - 1] > Array[right]) {
                swap(Array, right - 1, right);               // center, right
            }
        }
    }

    public static int partitionIt(int[] Array, int left, int right, int pivot) {

        int leftPtr = left - 1;
        int rightPtr = right;

        while (leftPtr < rightPtr) {
            QScompar++;
            while (Array[++leftPtr] < pivot) {
                QScompar++;
            }
            while (rightPtr > 0 && Array[--rightPtr] > pivot) {
                QScompar++;
            }

            if (leftPtr < rightPtr) {
                swap(Array, leftPtr, rightPtr);
                QSswaps++;
            }
            QScompar++;
        }
        swap(Array, leftPtr, right);
        QSswaps++;

        return leftPtr;
    }

    public static int partitionIt2(int[] Array, int left, int right, int pivot) {

        int leftPtr = left;
        int rightPtr = right - 1;

        while (true) {
            QScompar++;
            while (Array[++leftPtr] < pivot) {
                QScompar++;
            }  // find bigger
            //    (nop)
            while (Array[--rightPtr] > pivot) {
                QScompar++;
            } // find smaller
            //    (nop)
            if (leftPtr >= rightPtr) {      // if pointers cross,
                break;                    //    partition done
            } else {
                swap(Array, leftPtr, rightPtr);
                QSswaps++;
            }                         // not crossed, so
            // swap elements
        }  // end while(true)
        swap(Array, leftPtr, right - 1);
        QSswaps++;

        return leftPtr;
    }

    public static void swap(int[] Array, int dex1, int dex2) // swap two elements
    {
        int temp = Array[dex1];        // Array into temp
        Array[dex1] = Array[dex2];   // B into Array
        Array[dex2] = temp;             // temp into B
    }

    public static int[] generateRandom(int size, char t) {
        int[] Array = new int[size];

        switch (t) {
            case 'R': {
                for (int i = 0; i < Array.length; i++) {
                    Array[i] = rand.nextInt(1000) + 1;
                }
                break;
            }
            case 'I': {
                int temp = 0;
                for (int i = 0; i < Array.length; i++) {
                    Array[i] = rand.nextInt(1000) + 1;
                }

                for (int i = 0; i < Array.length; i++) {
                    for (int j = i + 1; j < Array.length; j++) {
                        if (Array[i] < Array[j]) {
                            temp = Array[i];
                            Array[i] = Array[j];
                            Array[j] = temp;
                        }
                    }
                }

                break;
            }

            case 'P': {
                int temp;
                for (int i = 0; i < Array.length; i++) {
                    Array[i] = rand.nextInt(1000) + 1;
                }

                for (int i = 0; i < Array.length; i++) {
                    for (int j = i + 1; j < Array.length; j++) {
                        if (Array[i] > Array[j]) {
                            temp = Array[i];
                            Array[i] = Array[j];
                            Array[j] = temp;
                        }
                    }
                }

                for (int i = 0; i < Array.length - 1; i += 2) {
                    temp = Array[i];
                    Array[i] = Array[i + 1];
                    Array[i + 1] = temp;
                }
            }
        }

        System.out.println();
        System.out.println(t + " Array:");
        print(Array);
        return Array;
    }

    public static void print(int[] a) {
        for (int l = 0; l < a.length; l++) {
            if (l == 0) {
                System.out.print(a[l]);
            } else {
                System.out.print(", " + a[l]);
            }
        }
        System.out.println("");
        System.out.println("************************************************");
    }
}
