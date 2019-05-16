package JavaInterview;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author npw
 * @date 2019/4/28
 * 集合类不安全问题
 */
public class ContainerNoSafeDemo {
    public static void main(String[] args) {
        //listNotSafe();

        //setProblem();

        //mapNotSafe();


    }

    private static void mapNotSafe() {
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i<30; i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        // ConcurrentHashMap
        // Collections.synchronizedMap(new HashMap<>());
    }

    private static void setProblem() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i<30; i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
//        Set<String> set = new HashSet<>();
//        set.add("a");//这是key值
//        System.out.println(set);
        /**
         * 1 故障现象
         *      java.util.ConcurrentModificationException
         *
         * 2 导致原因
         *      并发争抢修改导致，参考花名册
         *      一个人正在写入，另一个过来抢夺，导致数据不一致异常，并发修改异常
         *
         * 3 解决方案
         *      3.1 Collections.synchronizedSet(new HashSet<>());
         *      3.2 new CopyOnWriteArraySet<>()
         *      3.3
         *
         *
         * 4 优化建议（同样的错误不犯第二次）
         *
         */
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i<30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /**
         * 1 故障现象
         *      java.util.ConcurrentModificationException
         *
         * 2 导致原因
         *      并发争抢修改导致，参考花名册
         *      一个人正在写入，另一个过来抢夺，导致数据不一致异常，并发修改异常
         *
         * 3 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList<>()
         *          写时复制，在容器中添加元素时不直接往里添加，而是先将当前容器进行copy，在copy的容器里添加，
         *          然后将原容器的指针指向新的容器。好处：读时不用对容器加锁，可以并发读，做到读写分离。
         *
         *
         * 4 优化建议（同样的错误不犯第二次）
         *
         */
    }

}
