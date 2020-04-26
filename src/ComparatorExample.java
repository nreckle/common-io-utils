import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;

import java.io.File;
import java.util.Date;

public class ComparatorExample {

	private static final String PARENT_DIR =
			"E:\\workspace\\ApacheCommonsExample\\ExampleFolder";

	private static final String FILE_1 =
			"E:\\workspace\\ApacheCommonsExample\\ExampleFolder\\example";

	private static final String FILE_2 =
			"E:\\workspace\\ApacheCommonsExample\\ExampleFolder\\exampleTxt.txt";


	public static void runExample() {
		System.out.println("Comparator example...");

		// NameFileComparator

		// Let's get a directory as a File object
		// and sort all its files.
		File parentDir = FileUtils.getFile(PARENT_DIR);
		NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
		File[] sortedFiles = comparator.sort(parentDir.listFiles());

		System.out.println("Sorted by name files in parent directory: ");
		for (File file : sortedFiles) {
			System.out.println("\t" + file.getAbsolutePath());
		}

		// SizeFileComparator
		// We can compare files based on their size.
		// The boolean in the constructor is about the directories.
		//      true: directory's contents count to the size.
		//      false: directory is considered zero size.
		SizeFileComparator sizeFileComparator = new SizeFileComparator(true);
		File[] sizeFiles = sizeFileComparator.sort(parentDir.listFiles());

		System.out.println("Sorted by size files in parent directory: ");
		for (File file: sizeFiles) {
			System.out.println("\t"+ file.getName() + " with size (kb): " + file.length());
		}

		// LastModifiedFileComparator
		LastModifiedFileComparator lastModifiedFileComparator = new LastModifiedFileComparator();
		File[] lastModifiedFiles = lastModifiedFileComparator.sort(parentDir.listFiles());

		System.out.println("Sorted by last modified files in parent directory: ");
		for (File file: lastModifiedFiles) {
			Date modified = new Date(file.lastModified());
			System.out.println("\t"+ file.getName() + " last modified on: " + modified);
		}
	}
}
