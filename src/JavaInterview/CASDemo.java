package JavaInterview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author npw
 * @date 2019/4/26
 * CAS: 比较并交换 compareAndSet
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //main do something
        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t current data: "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024)+"\t current data: "+atomicInteger.get());
    }
}

