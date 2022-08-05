package singleton;

public class Singleton {
    private static final Singleton singleton = new Singleton();
    // 限制产生多个对象
    private Singleton() {

    }

    // 获取单一实例的方法
    public static Singleton getInstance() {
        return singleton;
    }

    //类中其他方法尽量是static的
    public static void doSomething() {

    }
}
