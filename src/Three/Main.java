package Three;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static int[] SIZE_ARRAY = {100, 1000, 10_000, 100_000, 10_000_000};

    public static void main(String[] args) {
        for (int size : SIZE_ARRAY) {
            int[] arr = fillingArray(size);

            System.out.println("Размер массива - " + size);

            long timeSingle = single(arr);
            long timeMulti = multi(arr);

            String theBest = "Single";
            if (timeMulti < timeSingle) {
                theBest = "Multi";
            } else if (timeMulti == timeSingle) {
                theBest = "Non";
            }

            System.out.println("Лучше " + theBest);
            System.out.println("Single time = " + timeSingle);
            System.out.println("Multi time = " + timeMulti);

            System.out.println(System.lineSeparator());
        }
    }

    //Заполнение массива
    private static int[] fillingArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }

    private static long single(int[] arr) {
        System.out.println("Однопоточное решение.");
        long startTime = System.currentTimeMillis();
        int sum = sumArray(arr);
        System.out.println("Сумма = " + sum);
        return (System.currentTimeMillis() - startTime);
    }

    private static long multi(int[] arr) {
        System.out.println("Мультипоточное решение");
        SumTaskArray sumTaskArray = new SumTaskArray(arr);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        long startTime = System.currentTimeMillis();
        System.out.println("Сумма = " + forkJoinPool.invoke(sumTaskArray));
        return (System.currentTimeMillis() - startTime);
    }

}
