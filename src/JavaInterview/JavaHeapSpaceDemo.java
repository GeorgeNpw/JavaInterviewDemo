package JavaInterview;

import java.util.Random;

/**
 * @author npw
 * @date 2019/5/21
 *
 * java.lang.OutOfMemoryError: Java heap space
 *
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args){
        String str = "seu";

        while(true){
            str += str + new Random().nextInt(11111111)+new Random().nextInt(22222222);
            str.intern();
        }

    }
}
