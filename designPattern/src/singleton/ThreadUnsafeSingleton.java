package singleton;

/**
 * 原因在于对象的创建实际上分为3步，第一步：开辟一个内存空间；第二步：对象的初始化；第三步：对象指向该空间
 */
public class ThreadUnsafeSingleton {
    private static ThreadUnsafeSingleton threadUnsafeSingleton = null;

    private ThreadUnsafeSingleton() {

    }
    public static ThreadUnsafeSingleton getInstance() {
        if (threadUnsafeSingleton == null) {
            threadUnsafeSingleton = new ThreadUnsafeSingleton();
        }
        return threadUnsafeSingleton;
    }
}