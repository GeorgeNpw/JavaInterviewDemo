package JavaInterview;

import java.util.concurrent.*;

/**
 * @author npw
 * @date 2019/5/15
 *
 * System.out.println(Runtime.getRuntime().availableProcessors());
 *              辅助工具类
 * Array        Arrays
 * Collection   Collections
 * Executor     Executors
 *
 * 第4种获得/使用java多线程的方式，通过线程池
 *
 * 线程池7大参数 以银行办理业务为例
 *  corePoolSize 当值窗口
 *  maximumPoolSize 全部窗口
 *  keepAliveTime 无业务窗口持续时间
 *  TimeUnit unit 持续时间单位
 *  BlockingQueue<Runnable> workQueue 候客区
 *  ThreadFactory threadFactory
 *  RejectedExecutionHandler handler 拒绝策略4种，人员爆满，全部窗口工作，候客区满但仍有业务进来
 *
 *
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try{
            for(int i=1;i<=11;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池固定数量个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程

        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try{
            for(int i=1;i<=10;i++){
                //使用
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭
            threadPool.shutdown();
        }
    }
}
