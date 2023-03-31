package Two;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final List<Callable<String>> callableList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("availableProcessors = " + Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Callable<String> callable1 = new MyCallable("1");
        Callable<String> callable2 = new MyCallable("2");
        Callable<String> callable3 = new MyCallable("3");
        Callable<String> callable4 = new MyCallable("4");

        prepare(callable1);
        prepare(callable2);
        prepare(callable3);
        prepare(callable4);

        //invokeAll для исполнения всех задач
        List<Future<String>> futureList = executorService.invokeAll(callableList);

        for (Future<String> stringFuture : futureList) {
            System.out.println(stringFuture.get());
        }

        System.out.println("--------------------------------------");

        //метод invokeAny для получения результата одной из них (самой быстрой)
        String callable = executorService.invokeAny(callableList);
        System.out.println(callable);

        executorService.shutdown();

    }

    private static void prepare(Callable<String> callable) {
        callableList.add(callable);
    }
}
