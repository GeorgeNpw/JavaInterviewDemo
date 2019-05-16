package JavaInterview;

/**
 * @author npw
 * @date 2019/4/23
 * 单例模式下的volatile
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }

    /**
     * 懒汉式加载在多线程下会出现线程安全问题，通过DCL(double check lock)也无法完全解决，可能出现指令重拍问题，
     * 因此需要使用volatile关键字
     */
    public static SingletonDemo getInstance(){
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }

}
