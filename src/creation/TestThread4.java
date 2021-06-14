package creation;

//多个线程同时操作同一个对象
//买火车票的例子
//存在问题:多个线程操作同一个资源的情况下，线程不安全，数据紊乱
public class TestThread4 implements Runnable{
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
        TestThread4 testThread4 = new TestThread4();

        new Thread(testThread4, "小明").start();
        new Thread(testThread4, "老师").start();
        new Thread(testThread4, "黄牛党").start();
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
