package suppliers;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2017/10/22.
 * Supplier接口产生一个给定类型的结果
 */
public class Demo1 {
    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;
        // 产生对象
        System.out.println(personSupplier.get());
        System.out.println(personSupplier.get());
        Random random = new Random(1000);
        Supplier<Integer> intergerSupplier = random::nextInt;
        System.out.println(intergerSupplier.get());
        System.out.println(intergerSupplier.get());
        System.out.println(intergerSupplier.get());

    }
}


class Person{

}