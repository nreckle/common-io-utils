# Utility

- `FilenameUtils`

  ```java
  FilenameUtils.getFullPath(EXAMPLE_TXT_PATH);
  FilenameUtils.getName(EXAMPLE_TXT_PATH);
  FilenameUtils.getExtension(EXAMPLE_TXT_PATH);
  FilenameUtils.getBaseName(EXAMPLE_TXT_PATH);
  ```
  
- `FileUtils`

  ```java
  // We can create a new File object using FileUtils.getFile(String)
  // and then use this object to get information from the file
  File exampleFile = FileUtils.getFile(EXAMPLE_TXT_PATH);
  LineIterator iter = FileUtils.lineIterator(exampleFile);
  
  System.out.println("Contents of exampleTxt");
  while (iter.hasNext()) {
      System.out.println("\t" + iter.next());
  }
  iter.close();
  
  // We can check if a file exists somewhere inside a certain directory.
  File parent = FileUtils.getFile(PARENT_DIR);
  System.out.println("Parent directory contains exampleTxt file: " +
          FileUtils.directoryContains(parent, exampleFile));
  ```

- `IOCase`

  ```java
  IOCase.SENSITIVE.checkEndsWith(str1, "string.");
  IOCase.INSENSITIVE.checkEndsWith(str1, "string.");
  IOCase.SENSITIVE.checkEquals(str1, str2);
  ```

- `FileSystemUtils`

  ```java
  @Deprecated
  FileSystemUtils.freeSpaceKb("E:");
  ```
  
**Replaced with `FileStore`**
  
- `FileStore`

  ```java
  FileStore fileStore = getFileStore("E:\\");
  
  fileStore.getUsableSpace() / FileUtils.ONE_KB;
  fileStore.getUsableSpace() / FileUtils.ONE_MB;
  fileStore.getUsableSpace() / FileUtils.ONE_GB;
  ```

  ```java
  public static FileStore getFileStore(final String path) {
      File diskFile = new File(path);
      if (!diskFile.exists()) {
          diskFile = diskFile.getParentFile();
      }
      Path currentPath = diskFile.toPath();
      if (currentPath.isAbsolute() && Files.exists(currentPath)) {
          try {
              return Files.getFileStore(currentPath);
          } catch (IOException e) {
              e.printStackTrace(System.err);
              return null;
          }
      }
      return null;
  }
  ```

  