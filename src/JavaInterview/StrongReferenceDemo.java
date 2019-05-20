package JavaInterview;

/**
 * @author npw
 * @date 2019/5/20
 *
 * 强引用 无论内存是否足够，都不回收
 */
public class StrongReferenceDemo {
    public static void main(String[] args){
        //这样定义默认的就是强引用
        Object obj1 = new Object();
        //obj2引用赋值
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
