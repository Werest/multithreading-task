package Two;

import java.util.concurrent.Callable;

public class MyCallable extends Thread implements Callable<String> {

    private final String nameTask;

    public MyCallable(String nameTask) {
        this.nameTask = nameTask;
    }


    @Override
    public String call() throws Exception {
        int countMsg = 0;
        try {
            while (countMsg != 7) {
                Thread.sleep(2500);
                System.out.println("Я поток " + Thread.currentThread().getName() + ". Всем привет! Выполняю - " + nameTask);
                countMsg++;
            }
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
        finally {
            Thread.sleep(1000);
            System.out.printf("%s завершен\n", Thread.currentThread().getName());
        }
        return "Кол-во сообщений (задача " + nameTask + ") - " + countMsg;
    }
}
