package streams;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/22.
 * java.util.Stream表示了某一种元素的序列，在这些元素上可以进行各种操作。
 * <p>
 * Stream操作可以是中间操作，也可以是完结操作。
 * 完结操作会返回一个某种类型的值，
 * 而中间操作会返回流对象本身，并且你可以通过多次调用同一个流操作方法来将操作结果串起来（就像StringBuffer的append方法一样————译者注）。
 * Stream是在一个源的基础上创建出来的，
 * 例如java.util.Collection中的list或者set（map不能作为Stream的源）。
 * Stream操作往往可以通过顺序或者并行两种方式来执行。
 * 我们先了解一下序列流。首先，我们通过string类型的list的形式创建示例数据：
 * Java 8中的Collections类的功能已经有所增强，你可以之直接通过调用
 * Collections.stream()或者Collection.parallelStream()方法来创建一个流对象。
 */
public class Demo1 {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        //  Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤。
        //  该操作是一个中间操作，因此它允许我们在返回结果的基础上再进行其他的流操作（forEach）
        stringCollection
                .stream() // 流
                .filter((s) -> s.startsWith("a")) //过滤
                .forEach(System.out::println);  // 打印
        System.out.println("sorted-----------------");
        //sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println("Map------------");
        //  map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上。
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> -b.compareTo(a))
                .forEach(System.out::println);


        System.out.println("Match:------------");
        // Match
        // 匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的。
        // 所有的匹配操作都是终结操作，只返回一个boolean类型的结果。
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA); // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA); // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ); // true

        System.out.println("Count:------------");

        // Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的元素数量。
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB); // 3

        System.out.println("Reduce:------------");
        // 该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的
        //  结果会放在一个Optional变量里返回。
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .map(String::toUpperCase)//AAA1#AAA2#BBB1#BBB2#BBB3#CCC#DDD1#DDD2
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        System.out.println(stringCollection);


        System.out.println("Parallel Streams:------------");

        // 像上面所说的，流操作可以是顺序的，也可以是并行的。顺序操作通过单线程执行，而并行操作则通过多线程执行。
        //下面的例子就演示了如何使用并行流进行操作来提高运行效率，代码非常简单。
        //首先我们创建一个大的list，里面的元素都是唯一的：
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        System.out.println("顺序排序：");
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms",
                millis));


        System.out.println("并行排序:");
        t0 = System.nanoTime();
        count = values.parallelStream().sorted().count();
        System.out.println(count);
        t1 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));


        System.out.println("Map--------------");
       // Map
        //正如前面已经提到的那样，map是不支持流操作的。而更新后的map现在则支持多
       // 种实用的新方法，来完成常规的任务。
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));
        // 计算编码
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3); // val33
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9); // false
        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23); // true
        map.computeIfAbsent(3, num -> "bam");
        map.get(3); // val33


        System.out.println("Map-----get");
        map.remove(3, "val3");
        System.out.println( map.get(3));
        map.remove(3, "val33");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(42, "not found") );// not found);
    }
}
