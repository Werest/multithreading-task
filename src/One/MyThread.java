package One;

public class MyThread extends Thread{
    @Override
    public void run() {
        try {
            while (!interrupted()) {
                Thread.sleep(2500);
                System.out.println("Я поток " + Thread.currentThread().getName() + ". Всем привет!");
            }
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
        finally {
            System.out.printf("%s завершен\n", Thread.currentThread().getName());
        }
    }
}
