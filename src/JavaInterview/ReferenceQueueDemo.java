package JavaInterview;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author npw
 * @date 2019/5/20
 *
 * 引用队列
 * 在引用被回收前，需要放入引用队列中保存一下
 *
 * ReferenceQueue是用来配合引用工作的，创建引用时可以指定关联的队列，当GC释放引用对象的内存时，会将引用加入到ReferenceQueue
 *
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException{
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=============");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
