package edson.MyTemplate.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yangxi
 * @Date: 2021/12/13 14:59
 */
public class ThreadTest {

    //创建线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        //创建线程
        Thread thread1 = new Thread(()->{
            System.out.println("喝水，放下杯子");
        });

        Thread thread2 = new Thread(()->{
            System.out.println("拿起杯子，喝水");
        });

        executorService.submit(thread1);
        executorService.submit(thread2);

    }

}
