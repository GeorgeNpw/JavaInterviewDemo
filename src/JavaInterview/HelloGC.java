package JavaInterview;

/**
 * @author npw
 * @date 2019/5/16
 *
 * JVM xx参数
 *      boolean类型：+开启/ -关闭  公式：-xx:+/-某个属性
 *      KV设值类型：-xx:属性key=属性值value  case：-xx:MetaspaceSize=128m
 *
 * 如何查看当前运行程序的配置
 *  jps -l
 *  jinfo -flag 参数名 线程号
 *  jinfo -flags 线程号  -- 查看所有参数
 *      Non-default 后的是根据机器配置自动调整后出的参数
 *      Command line 后的是自己配置的参数
 *
 *  查看初始默认值
 *      java -xx:+PrintFlagsInitial -version
 *
 *  查看修改更新
     *      java -xx:+PrintFlagsFinal -version
     *      结果 = 没改过    := 修改过或JVM根据硬件不同自动调整过
 *
 *  在运行时修改变更值
 *      java -xx:+PrintFlagsFinal -xx:MetaspaceSize=128m 类名
 *
 *  java -xx:+PrintCommandLineFlags -version 可以用来查看垃圾回收器
 *
 *
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        //返回Java虚拟机中的内存总量（默认为物理内存的1/64）
        long totalMemory = Runtime.getRuntime().totalMemory();
        //返回Java虚拟机试图使用的最大内存量（默认为物理内存的1/4）
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("total_memory(-Xms) = "+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("max_memory(-Xmx) = "+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB");
        //线程等待，便于查看并调整JVM参数
        Thread.sleep(Integer.MAX_VALUE);
    }
}
