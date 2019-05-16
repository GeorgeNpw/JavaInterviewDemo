package JavaInterview;

/**
 * @author npw
 * @date 2019/5/13
 * CountdownLatch 减少计数
 *
 * @Description:
 *    * 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 *
 * CountDownLatch 主要有两个方法，当一个或多个线程调用await 方法时，这些线程会阻塞。
 * 其它线程调用countDown 方法会将计数器减1(调用countDown 方法的线程不会阻塞)，
 * 当计数器的值变为0 时，因await 方法阻塞的线程会被唤醒，继续执行。
 *
 */
public class CountdownLatchDemo {
    public static void main(String[] args) throws Exception{
        //班长等同学走完后，最后关门
        //closeDoor();
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(6);

        for(int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CountryEnum.foreach_CountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *****秦帝国，一统华夏");
    }

    private static void closeDoor() throws InterruptedException {
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(6);

        for(int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *****班长最后关门走人");
    }
}


