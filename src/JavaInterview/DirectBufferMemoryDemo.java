package JavaInterview;

import java.nio.ByteBuffer;

/**
 * @author npw
 * @date 2019/5/21
 *
 * OOM: Direct Buffer Memory
 *
 * 配置参数：
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 导致原因：
 *
 * 写NIO程序时经常使用ByteBuffer来读取或写入数据，这是一种基于通道和缓冲区的I/O方式，
 * 它可以使用Native函数直接分配堆外内存，然后通过DirectByteBuffer对象作为这块内存的引用进行操作。
 * 这样可以在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据
 *
 * ByteBuffer.allocate()  分配JVM内存，属于GC范围，速度较慢
 *
 * ByteBuffer.allocateDirect()  分配OS本地内存，不属于GC，速度较快
 *
 * 但如果不断分配本地内存，堆内存很少使用，JVM不需要GC，DirectByteBuffer对象们就不会被回收
 * 这时候，堆内存充足，但本地内存很可能使用光了，再次尝试分配本地内存就会出现OOM Error
 *
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的MaxDirectMemory："+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        //本地直接内存配置为5m，但实际使用时，我们故意将其设为6m
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);

    }
}
