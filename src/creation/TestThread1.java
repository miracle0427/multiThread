package creation;

//线程创建方式一：继承Thread类,重写run方法，调用start()开启线程
public class TestThread1 extends Thread{
    //重写run方法
    @Override
    public void run() {
        //线程体,通过20.for来快速写循环
        for(int i = 0; i < 20; i++){
            System.out.println("我在听课===" + i);
        }
    }
    //main线程，主线程
    public static void main(String[] args) {
        //创建线程对象,调用start()方法启动线程
        TestThread1 t = new TestThread1();
        t.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程===" + i);
        }
    }
}
