package JavaInterview;

/**
 * @author npw
 * @date 2019/5/21
 *
 * 高并发请求服务器时经常出现的异常，准确的说，该native thread异常与对应的平台有关
 *
 * 导致原因：
 *      1.应用创建过多的线程，超过系统承载极限
 *      2.服务器不允许创建这么多线程，linux默认单个进程可以创建的线程数为1024
 *
 * 解决方法：
 *      1.降低应用程序线程数量
 *      2.对于那些确实需要的程序，修改Linux服务器的配置，扩大默认限制
 *
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i=1; ; i++){
            System.out.println("***************** i= "+i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();    
        }
    }
}
