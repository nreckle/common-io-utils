import org.apache.commons.io.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

public class UtilityExample {

    // We are using the file exampleTxt.txt in the folder ExampleFolder,
    // and we need to provide the full path to the Utility classes.
    private static final String EXAMPLE_TXT_PATH =
            "E:\\workspace\\ApacheCommonsExample\\ExampleFolder\\exampleTxt.txt";

    private static final String PARENT_DIR =
            "E:\\workspace\\ApacheCommonsExample\\ExampleFolder";

    public static void runExample() throws IOException {
        System.out.println("Utility Classes example...");

        // FilenameUtils
        System.out.println("Full path of exampleTxt: " +
                FilenameUtils.getFullPath(EXAMPLE_TXT_PATH));

        System.out.println("Full name of exampleTxt: " +
                FilenameUtils.getName(EXAMPLE_TXT_PATH));

        System.out.println("Extension of exampleTxt: " +
                FilenameUtils.getExtension(EXAMPLE_TXT_PATH));

        System.out.println("Base name of exampleTxt: " +
                FilenameUtils.getBaseName(EXAMPLE_TXT_PATH));

        // FileUtils

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


        // IOCase
        String str1 = "This is a new String.";
        String str2 = "This is another new String, yes!";
        System.out.println("Ends with string (case sensitive): " +
                IOCase.SENSITIVE.checkEndsWith(str1, "string."));
        System.out.println("Ends with string (case insensitive): " +
                IOCase.INSENSITIVE.checkEndsWith(str1, "string."));

        System.out.println("String equality: " +
                IOCase.SENSITIVE.checkEquals(str1, str2));

        // FileSystemUtils
        System.out.println("Free disk space (in KB): " + FileSystemUtils.freeSpaceKb("E:\\"));
        System.out.println("Free disk space (in MB): " + FileSystemUtils.freeSpaceKb("E:\\") / FileUtils.ONE_KB);
        System.out.println("Free disk space (in GB): " + FileSystemUtils.freeSpaceKb("E:\\") / FileUtils.ONE_MB);

        FileStore fileStore = getFileStore("E:\\");
        if (fileStore != null) {
            System.out.println("2nd Free disk space (in KB): " + fileStore.getUsableSpace() / FileUtils.ONE_KB);
            System.out.println("2nd Free disk space (in MB): " + fileStore.getUsableSpace() / FileUtils.ONE_MB);
            System.out.println("2nd Free disk space (in GB): " + fileStore.getUsableSpace() / FileUtils.ONE_GB);
        }
    }

    public static FileStore getFileStore(final String path) {
        File diskFile = new File(path);
        if (!diskFile.exists()) {
            diskFile = diskFile.getParentFile();
        }
        Path currentPath = diskFile.toPath();
        System.out.println(currentPath.isAbsolute());
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
}
