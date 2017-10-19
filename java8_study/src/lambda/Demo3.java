package lambda;

/**
 * Created by Administrator on 2017/10/19.
 * lambda的范围
 * 访问局部对应的外部区域的局部final变量，以及成员变量和静态变量测试
 */
public class Demo3 {
    static int outerStaticNum;
    int outerNum;

    public static void main(String[] args) {
        //访问局部变量
        //与匿名对象不同 变量num并不需要一定是final ,仍然可以使用
        //然而，num在编译的时候被隐式地当做final变量来处理，继续num = 2; 会报错
        int num = 1;
        //访问final变量
        //  final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        System.out.println(stringConverter.convert(2));// 3);


        // 访问成员变量
        // 在静态方法中，非静态成员变量不能访问
        Converter<Integer, String> stringConverter1 = (from) -> {
            //outerNum = 23; // 报错
            return String.valueOf(from);
        };
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

    // 在非静态方法内部，不论是成员变量还是静态变量，都可以访问
    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }
}

