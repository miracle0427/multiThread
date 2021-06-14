package syn;

public class DeadLock {
    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0, "灰姑凉");
        Makeup girl2 = new Makeup(1, "白雪公主");

        girl1.start();
        girl2.start();
    }
}
//口红
class Lipstick{}

//镜子
class Mirror{}

class Makeup extends Thread{
    //需要的资源只有一份，用static来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;
    public Makeup(int choice, String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }
    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //化妆，互相持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if(choice == 0){
            synchronized (lipstick){
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror){
                    System.out.println(this.girlName + "获得镜子的锁");
                }
            }
            /*
            synchronized (lipstick){
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror){
                System.out.println(this.girlName + "获得镜子的锁");
            }*/
        }else {
            synchronized (mirror){
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
                synchronized (lipstick){
                    System.out.println(this.girlName + "获得口红的锁");
                }
            }
            /*
            synchronized (mirror){
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick){
                System.out.println(this.girlName + "获得口红的锁");
            }
            */
        }
    }
}