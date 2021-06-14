package syn;

import java.util.concurrent.CopyOnWriteArrayList;

/*
由于同一进程的多个线程共享同一块存储空间，在带来方便的同时，也带来了访问
冲突问题，为了保证数据在方法中被访问时的正确性，在访问时加入锁机制synchronized，
当一个线程获得对象的排它锁， 独占资源， 其他线程必须等待，使用后释放锁即可
存在以下问题：
    一个线程持有锁会导致其他所有需要此锁的线程挂起；
    在多线程竞争下，加锁，释放锁会导致比较多的上下文切换和调度延时，引起性能问题；
    如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能问题

由于我们可以通过private关键字来保证数据对象只能被方法访问， 所以我们只需
要针对方法提出一套机制， 这套机制就是synchronized关键字， 它包括两种用法：
synchronized方法和synchronized块.
同步方法：public synchronized void method(int args) {}
synchronized方法控制对“对象”的访问， 每个对象对应一把锁， 每个synchronized方法都必须
获得调用该方法的对象的锁才能执行， 否则线程会阻塞，方法一旦执行，就独占该锁，直到该方法
返回才释放锁，后面被阻塞的线程才能获得这个锁，继续执行
缺陷：若将一个大的方法申明为synchronized将会影响效率

同步块：synchronized(Obj) {}
Obj称之为同步监视器
Obj可以是任何对象， 但是推荐使用共享资源作为同步监视器
同步方法中无需指定同步监视器， 因为同步方法的同步监视器就是this， 就是这个对象本身， 或者是class[反射中讲解]
同步监视器的执行过程
    1.第一个线程访问，锁定同步监视器，执行其中代码.
    2.第二个线程访问，发现同步监视器被锁定，无法访问
    3.第一个线程访问完毕，解锁同步监视器.
    4.第二个线程访问，发现同步监视器没有锁，然后锁定并访问
    */
//测试JUC安全类型的集合
class TestJUC {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                copyOnWriteArrayList.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(copyOnWriteArrayList.size());
    }
}