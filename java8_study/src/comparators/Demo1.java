package comparators;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by Administrator on 2017/10/22.
 * Comparator接口在早期的Java版本中非常著名。Java 8 为这个接口添加了不同的默认方法
 */
public class Demo1 {
    public static void main(String[] args) {

        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Comparator<Person> personComparator  = Comparator.comparing(Person::getFirstName);
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        System.out.println( personComparator.compare(p1, p2));
        System.out.println(  comparator.reversed().compare(p1, p2));
    }
}

class Person {
    protected String firstName;

    private String lastName;


    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
