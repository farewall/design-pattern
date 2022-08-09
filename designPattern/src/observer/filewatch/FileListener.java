package observer.filewatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;


public class FileListener extends FileChange {

    private File file = new File("C:\\Users\\shterm\\shterm\\shterm.conf");

    public FileChangeWatcher fileChangeWatcher = new FileChangeWatcher();

    class FileChangeWatcher extends Thread {

        private boolean running;

        public FileChangeWatcher() {
            super();
            setName("CONFIG_CHANGE_WATCHER");
            setDaemon(true);
            running = true;
        }

        public void shutdown() {
            running = false;
            interrupt();
        }

        @Override
        public void run() {
            try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
                Kind<?>[] events = { ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE };
                if (file.isFile()) {
                    file.getParentFile().toPath().register(watcher, events);
                }
                while (running) {
                    WatchKey watchKey = watcher.take();
                    for (WatchEvent<?> pollEvent : watchKey.pollEvents()) {
                        String path = pollEvent.context().toString();
                        if (file.getName().equals(path) && file.lastModified() > 0) {
                            if (pollEvent.kind() == ENTRY_MODIFY) {
                                Properties properties = new Properties();
                                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                                    properties.load(br);
                                } catch (Exception e) {
                                    return;
                                }
                                notifyChange((Properties) properties.clone());
                            }
                        }
                    }
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
