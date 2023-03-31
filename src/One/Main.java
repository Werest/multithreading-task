package One;

public class Main {
    public static final String NAME_GROUP_THREAD = "main";

    public static void main(String[] args) {
        ThreadGroup mainGroup = new ThreadGroup(NAME_GROUP_THREAD);

        Thread thread = new MyThread();

        System.out.println("Создаю потоки...");
        Thread thread1 = new Thread(mainGroup, thread, "1");
        Thread thread2 = new Thread(mainGroup, thread, "2");
        Thread thread3 = new Thread(mainGroup, thread, "3");
        Thread thread4 = new Thread(mainGroup, thread, "4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Завершаю все потоки.");
        mainGroup.interrupt();
    }
}