package lambda;

import java.util.*;

/**
 * Created by Administrator on 2017/10/19.
 */
public class Demo1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Hello", "World", "world", "xxx", "xxx");
        Collections.sort(names, Comparator.naturalOrder());

        System.out.println(names);
        Collections.sort(names, (String a, String b) -> {
            return -a.compareTo(b);
        });


        System.out.println(names);

        Collections.sort(names, (String a, String b) -> -a.compareTo(b));
        System.out.println(names);

        Collections.sort(names, (a, b) -> -a.compareTo(b));
        System.out.println(names);
    }
}

/**
 * 允许接口中方法有默认实现
 * 使用default关键字，为接口声明添加非抽象的方法实现
 */
interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}