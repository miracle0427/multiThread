package syn;

import java.util.concurrent.locks.ReentrantLock;

/*
从JDK 5.0开始， Java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当

java.util.concurrent.locks.Lock接口是控制多个线程对共享资源进行访问的工具。
锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁， 线程开始访问共享资源之前应先获得Lock对象

ReentrantLock类实现了Lock，它拥有与synchronized相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是
ReentrantLock， 可以显式加锁、释放锁。

synchronized与Lock的对比
    Lock是显式锁(手动开启和关闭锁， 别忘记关闭锁),synchronized是隐式锁， 出了作用域自动释放
    Lock只有代码块锁， synchronized有代码块锁和方法锁
    使用Lock锁， JVM将花费较少的时间来调度线程， 性能更好。并且具有更好的扩展性(提供更多的子类)

    优先使用顺序：
        Lock>同步代码块(已经进入了方法体， 分配了相应资源) >同步方法(在方法体之外)
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }

}

class TestLock2 implements Runnable{
    int ticketNums = 10;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if(ticketNums>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                }else{
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}