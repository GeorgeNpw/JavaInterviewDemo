package JavaInterview;

/**
 * @author npw
 * @date 2019/4/15
 * 赋值操作过程
 */
public class AssignmentDemo {
    public static void main(String[] args) {
        int i =1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i = "+ i);
        System.out.println("j = "+ j);
        System.out.println("k = "+ k);
    }
}
