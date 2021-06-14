package creation;

import java.util.concurrent.*;

//线程创建方式三:实现callable接口,可以获取返回值，可以抛出异常
public class TestThread6 implements Callable<Boolean> {
    private String url;     //网络图片地址
    private String name;    //保存的文件名

    public TestThread6(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.download(url, name);
        System.out.println("下载的文件名为:" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread6 t1 = new TestThread6("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10118%2F560%2Fw1080h1080%2F20210528%2F447b-kquziii0966349.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626182582&t=d4b0ba0932d21e3ebc7edd30b332e51f", "1.jpg");
        TestThread6 t2 = new TestThread6("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10118%2F560%2Fw1080h1080%2F20210528%2F447b-kquziii0966349.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626182582&t=d4b0ba0932d21e3ebc7edd30b332e51f", "2.jpg");
        TestThread6 t3 = new TestThread6("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10118%2F560%2Fw1080h1080%2F20210528%2F447b-kquziii0966349.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626182582&t=d4b0ba0932d21e3ebc7edd30b332e51f", "3.jpg");
        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> result1 = service.submit(t1);
        Future<Boolean> result2 = service.submit(t2);
        Future<Boolean> result3 = service.submit(t3);

        //获取结果
        Boolean r1 = result1.get();
        Boolean r2 = result2.get();
        Boolean r3 = result3.get();
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        //关闭服务
        service.shutdown();
    }
}