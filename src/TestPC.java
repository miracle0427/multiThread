//测试生产者和消费者:利用缓冲区解决，管程法

public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}
//生产者
class Productor extends Thread{
    SynContainer container;

    public Productor(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                container.push(new Chicken(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产了" + i + "只鸡");
        }
    }
}

//消费者
class Consumer extends Thread{
    SynContainer container;

    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("消费了-->" + container.pop().id + "只鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//产品
class Chicken{
    int id;
    public Chicken(int id){
        this.id = id;
    }
}

//缓冲区
class SynContainer{

    Chicken[] chickens = new Chicken[10];

    int count = 0;

    public synchronized void push(Chicken chicken) throws InterruptedException {
        if(count == chickens.length){
            this.wait();
        }
        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }
    public synchronized Chicken pop() throws InterruptedException {
        if(count == 0){
            this.wait();
        }
        count--;
        Chicken chicken = chickens[count];
        this.notifyAll();
        return chicken;
    }
}