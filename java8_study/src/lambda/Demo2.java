package lambda;

/**
 * Created by Administrator on 2017/10/19.
 * 任意只包含一个抽象方法的接口，都可以用来做成lambda表达式
 */
public class Demo2 {
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);


        //Java 8 允许你通过::关键字获取方法或者构造函数的的引用
        Converter<String, Integer> converters = Integer::valueOf;
        Integer converteds = converter.convert("123");
        System.out.println(converteds); // 123

        //:: 调用自定义方法
        Something something = new Something();
        Converter<String,String> conver = something::startsWith;
        String start = conver.convert("start");
        System.out.println(start);


        // :: 调用构造方法
        //通过Person::new来创建一个Person类构造函数的引用
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.firstName);
    }
}

/**
 * @FunctionalInterface 这个注解代表只有一个抽象方法 如果有两个及以上，则会报错
 * @param <F>
 * @param <T>
 *     测试 将字符串转换为整型
 */
@FunctionalInterface
interface Converter<F,T> {
    T convert(F form);
}

/**
 * 自定义方法，然后调用
 */
class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}


/**
 * 自定义Person类，调用其构造类
 */
class Person {
    String firstName;
    String lastName;
    Person() {}
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

/**
 * 创建工厂，创建Person
 * @param <P>
 */
interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}