package jepsen.demo.com.jack;

public class Hello {
    public static String sayHello(String name){
        System.out.println("print by java");
        return "hello " + name;
    }
}
