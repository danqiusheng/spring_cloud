package consumers;

import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/10/22.
 * Consumer代表了在一个输入参数上需要进行的操作
 */
public class Consumers {
    public static void main(String[] args) {
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
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