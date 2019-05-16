package JavaInterview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author npw
 * @date 2019/4/23
 * volatile
 */

//资源类
class Mydata{
    //int num = 0;

    //volatile
    volatile int num = 0;

    public void addTo60(){
        this.num = 60;
    }

    //注意此时是加了volatile的
    public void add(){
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 *1.假如int num = 0，不加volatile关键字，没有可见性，添加volatile可解决
 *2.volatile不保证原子性（出现丢失写值的情况）
 *  2.1 原子性：不可分割，完整性，即某个线程正在做某个业务时中间不可以被分割或加塞，要么同时成功要么同时失败
 *  2.2 如何解决：加sync，或使用JUC下的AtomicInteger
 */
public class volatileDemo {

    public static void main(String[] args) {
        seeOkByVolatile();
        atoNotByVotalie();
    }

    //volatile不保证原子性
    private static void atoNotByVotalie() {
        Mydata mydata = new Mydata();

        for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    mydata.add();
                    mydata.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等上面20个线程都计算完成后，再用main线程获取结果
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type finally value: "+mydata.num);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type fianlly value: "+mydata.atomicInteger);
    }

    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        Mydata mydata = new Mydata();
        //第一个线程AAA
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated num value: "+mydata.num);
        },"AAA").start();

        while (mydata.num==0){

        }
        //第二个线程main线程
        System.out.println(Thread.currentThread().getName()+"\t over");
    }
}
