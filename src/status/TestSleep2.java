package status;

import java.text.SimpleDateFormat;
import java.util.Date;

//模拟倒计时
public class TestSleep2 {
    public void tenDown(){

        int num = 10;

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(num--);
                if(num <= 0) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //模拟倒计时
        TestSleep2 testSleep2 = new TestSleep2();
        testSleep2.tenDown();

        //打印当前时间
        Date startTime = new Date(System.currentTimeMillis());      //获取系统当前时间
        while(true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());      //更新系统当前时间

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
