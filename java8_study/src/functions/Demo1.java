package functions;

import java.util.function.Function;

/**
 * Created by Administrator on 2017/10/22.
 * Function接口接收一个参数，并返回单一的结果。
 * 默认方法可以将多个函数串在一起（compose, andThen）
 */
public class Demo1 {
    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        // 这里还有compaose  andThen
        System.out.println(backToString.apply("123"));
    }
}
