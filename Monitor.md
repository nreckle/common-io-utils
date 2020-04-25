# Monitor

Monitor file lifecycle

-  `FileEntry`
```java
FileEntry entry = new FileEntry(FileUtils.getFile(EXAMPLE_PATH));

System.out.println("File monitored: " + entry.getFile());
System.out.println("File name: " + entry.getName());
System.out.println("Is the file a directory?: " + entry.isDirectory());
```

- `FileAlterationObserver`
```java
File parentDir = FileUtils.getFile(PARENT_DIR);

FileAlterationObserver observer = new FileAlterationObserver(parentDir);
observer.addListener(new FileAlterationListenerAdaptor() {
    @Override
    public void onFileCreate(File file) {
        System.out.println("File created: " + file.getName());
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("File deleted: " + file.getName());
    }

    @Override
    public void onDirectoryCreate(File dir) {
        System.out.println("Directory created: " + dir.getName());
    }

    @Override
    public void onDirectoryDelete(File dir) {
        System.out.println("Directory deleted: " + dir.getName());
    }
});
```
- `FileAlterationMonitor`
```java
FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
try {
    monitor.start();

    // After we attached the monitor, we can create some files and directories
    // and see what happens!
    File newDir = new File(NEW_DIR);
    File newFile = new File(NEW_FILE);

    newDir.mkdirs();
    newFile.createNewFile();

    FileDeleteStrategy.NORMAL.delete(newDir);
    FileDeleteStrategy.NORMAL.delete(newFile);

    Thread.sleep(1000);
    monitor.stop();
} catch (IOException | InterruptedException e) {
    System.out.println(e.getMessage());
    e.printStackTrace();
} catch (Exception e) {
    e.printStackTrace();
}
```