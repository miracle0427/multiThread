package status;

//join合并线程，待此线程执行完后，再执行其它线程，其它线程阻塞

public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP来了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if(i == 200){
                thread.join();
            }
            System.out.println("main" + i);
        }
    }
}
