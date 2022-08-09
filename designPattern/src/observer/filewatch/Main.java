package observer.filewatch;

import java.io.IOException;

/**
 * 监控文件的变化，一有变化则通知更新properties
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileListener fileListener = new FileListener();
        fileListener.addFileWatcher(new FileWatcherOne());
        fileListener.addFileWatcher(new FileWatcherTwo());
        fileListener.fileChangeWatcher.start();
        System.out.println(1);
        System.in.read();
        System.out.println(2);
    }
}
