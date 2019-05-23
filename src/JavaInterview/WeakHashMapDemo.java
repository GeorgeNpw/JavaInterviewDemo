package JavaInterview;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author npw
 * @date 2019/5/20
 *
 * 弱引用的适用场景
 * WeakHashMap
 *
 */
public class WeakHashMapDemo {
    public static void main(String[] args){
        myHashMap();
        System.out.println("========");
        myWeakHashMap();
    }

    private static void myHashMap(){
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+" size: "+map.size());
    }

    private static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+" size: "+map.size());
    }
}
