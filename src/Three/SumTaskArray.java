package Three;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class SumTaskArray extends RecursiveTask<Integer> {
    private final int[] array;

    public SumTaskArray(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if(array.length <= 2) {
            return Arrays.stream(array).sum();
        }
        //Первая половина массива
        SumTaskArray firstHalfArray =
                new SumTaskArray(Arrays.copyOfRange(array, 0, array.length / 2));
        //Вторая половина массива
        SumTaskArray twoHalfArray =
                new SumTaskArray(Arrays.copyOfRange(array, array.length / 2, array.length));

        invokeAll(firstHalfArray, twoHalfArray);
        return firstHalfArray.join() + twoHalfArray.join();
    }
}
