package JavaInterview;

import java.lang.ref.WeakReference;

/**
 * @author npw
 * @date 2019/5/20
 *
 * 弱引用  不管内存是否足够，只要有gc就回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("...............");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
