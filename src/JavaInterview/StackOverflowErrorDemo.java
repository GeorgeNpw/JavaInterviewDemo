package JavaInterview;

/**
 * @author npw
 * @date 2019/5/21
 *
 * stackOverflowError
 *   方法的重复递归调用，可能会造成栈溢出
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args){
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
