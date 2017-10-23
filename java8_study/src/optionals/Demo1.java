package optionals;

import java.util.Optional;

/**
 * Created by Administrator on 2017/10/22.
 * Optional不是一个函数式接口，而是一个精巧的工具接口，用来防止NullPointerException产生。
 Optional是一个简单的值容器，这个值可以是null，也可以是non-null。考虑到一个
 方法可能会返回一个non-null的值，也可能返回一个空值。为了不直接返回null，我
 们在Java 8中就返回一个Optional.
 */
public class Demo1 {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("bam");
        optional.isPresent(); // true  判断当前值是否为空
        optional.get(); // "bam"
        optional.orElse("fallback"); // "bam"  如果当前的value为空，则返回参数， 不为空，则返回value
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }
}
