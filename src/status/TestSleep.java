package status;
//模拟网络延时，放大问题发生的可能性
public class TestSleep implements Runnable{
    //票数
    private int ticketNum = 10;
    @Override
    public void run() {
        while (true){
            if(ticketNum <= 0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum-- + "票");
        }
    }

    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();

        new Thread(testSleep, "小明").start();
        new Thread(testSleep, "老师").start();
        new Thread(testSleep, "黄牛党").start();
        /*
            小明拿到了第10票
            黄牛党拿到了第8票
            老师拿到了第9票
            黄牛党拿到了第7票
            老师拿到了第6票
            小明拿到了第5票
            黄牛党拿到了第4票
            小明拿到了第3票
            老师拿到了第2票
            黄牛党拿到了第1票
            小明拿到了第0票
            老师拿到了第-1票
         */
    }
}
