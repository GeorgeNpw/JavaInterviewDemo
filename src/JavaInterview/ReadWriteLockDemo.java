package JavaInterview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author npw
 * @date 2019/5/9
 *
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是写共享资源只能有一个线程。
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断。
 *
 * 总结：
 *      读-读可以共存
 *      读-写不可以共存
 *      写-写不可以共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args){

        MyCache myCache = new MyCache();
        //5个线程同时进行操作
        for(int i=1;i<=5;i++){
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for(int i=1;i<=5;i++){
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}


//资源类
class MyCache{
    //volatile 可见，不保证原子性，禁止指令重拍
    private volatile Map<String,Object> map = new HashMap<>();
    //    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    //写操作
    public void put(String key,Object value){

        reentrantReadWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            try{
                //模拟网络延迟拥堵
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    //读操作
    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取："+key);
            try{
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {e.printStackTrace();}
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

}