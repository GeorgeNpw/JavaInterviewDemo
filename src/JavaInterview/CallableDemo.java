package JavaInterview;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author npw
 * @date 2019/5/14
 * 多线程中，第三种获得多线程的方式
 *  1.继承Thread类
 *  2.实现Runnable接口
 *  3.实现Callable接口
 *  4.实现Java的线程池
 *
 * Callable与Runnable的区别：
 *  1.Callable有返回值
 *  2.Callable会抛异常
 *  3.接口实现的方法不同 call()和run()
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        //共用一个FutureTask只会进1次
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();

        int r1 = 100;


//        while (!futureTask.isDone()){
//
//        }

        //将耗时的计算操作使用Callable新起一个线程另行计算
        //要求获得Callable线程的计算结果，如果没有计算完成就要强求，会导致阻塞，知道计算完成，建议放在最后
        int r2 = futureTask.get();

        System.out.println(".....result"+r1+"\t"+r2);
    }
}


/*class MyThread implements Runnable{
    public void run(){
    }
}*/

class MyThread implements Callable<Integer> {
    public Integer call() throws Exception{
        System.out.println(Thread.currentThread().getName()+".........come in callable");
        //让线程阻塞2S
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 1024;
    }
}