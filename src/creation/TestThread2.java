package creation;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//多线程同步下载图片
public class TestThread2 extends Thread{
    private String url;     //网络图片地址
    private String name;    //保存的文件名

    public TestThread2(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.download(url, name);
        System.out.println("下载的文件名为:" + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10118%2F560%2Fw1080h1080%2F20210528%2F447b-kquziii0966349.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626182582&t=d4b0ba0932d21e3ebc7edd30b332e51f", "1.jpg");
        TestThread2 t2 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10118%2F560%2Fw1080h1080%2F20210528%2F447b-kquziii0966349.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626182582&t=d4b0ba0932d21e3ebc7edd30b332e51f", "2.jpg");
        TestThread2 t3 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10118%2F560%2Fw1080h1080%2F20210528%2F447b-kquziii0966349.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626182582&t=d4b0ba0932d21e3ebc7edd30b332e51f", "3.jpg");
        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownloader{
    public void download(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，download方法出现问题");
        }
    }
}