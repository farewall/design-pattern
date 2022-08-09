package observer.filewatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class FileChange {
    List<FileWatcher> fileWatcherList = new ArrayList<>();

    public void addFileWatcher(FileWatcher watcher) {
        fileWatcherList.add(watcher);
    }

    public void delFileWatcher(FileWatcher watcher) {
        fileWatcherList.remove(watcher);
    }

    public void notifyChange(Properties props) {
        for (FileWatcher fileWatcher : fileWatcherList) {
            fileWatcher.update(props);
        }
    }

}
