package JavaInterview;

import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author npw
 * @date 2019/5/20
 *
 * 软引用  内存够用的时候就保留，不够用就回收
 *
 * 软引用的适用场景
 * 当一个应用要大量读取本地的图片，若每次都从硬盘读取会影响性能，而一次性全部加载进内存会造成内存溢出
 * 设计思路：使用一个HashMap来保存图片路径和相应图片对象关联的软引用间的映射关系，在内存不足时，JVM会自动回收这些空间
 * Map<String, SoftReference<BitMap>> imageCache = new Map<String, SoftReference<BitMap>>();
 *
 */
public class SoftReferenceDemo {

    /**
     * 内存够用的时候就保留，不够用就回收
     */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * JVM配置，故意产生大对象并配置小内存，引发OOM，查看软引用的回收情况
     * -Xms5m -Xmx5m -xx:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try{
            byte[] bytes = new byte[30*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args){
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
