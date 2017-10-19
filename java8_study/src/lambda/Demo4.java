package lambda;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2017/10/19.
 *  内置函数接口的学习
 */
public class Demo4 {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo"); // true
        predicate.negate().test("foo"); // false
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
}

