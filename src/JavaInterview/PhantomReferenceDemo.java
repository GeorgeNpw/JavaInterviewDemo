package JavaInterview;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author npw
 * @date 2019/5/20
 *
 * 虚引用：不决定对象的生命周期，在任何时候都可以被回收，不能单独使用，必须和引用队列ReferenceQueue联合使用
 * 主要作用：跟踪对象被垃圾回收的状态
 *
 * ReferenceQueue是用来配合引用工作的，创建引用时可以指定关联的队列，当GC释放引用对象的内存时，会将引用加入到ReferenceQueue
 * 如果程序发现某个虚引用被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动，这相当于一种通知机制
 *
 * 当队列中有数据时，意味着引用指向的堆内存中的对象被回收。通过这种方式，JVM允许我们在对象被销毁后做一些我们想做的事
 *
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=================");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
