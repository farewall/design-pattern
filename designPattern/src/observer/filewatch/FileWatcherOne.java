package observer.filewatch;

import java.util.Properties;

public class FileWatcherOne implements FileWatcher {

    private Properties prop;

    @Override
    public void update(Properties properties) {
        prop = properties;
        System.out.println("fileWatcherOne update props");
    }
}
