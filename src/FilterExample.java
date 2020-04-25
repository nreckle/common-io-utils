import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.util.Objects;

public class FilterExample {
    private static final String PARENT_DIR =
            "E:\\workspace\\ApacheCommonsExample\\ExampleFolder";

    public static void runExample() {
        System.out.println("File Filter example...");

        // NameFileFilter
        // Right now, in the parent directory we have 3 files:
        //    directory example
        //    file exampleEntry.txt
        //    file exampleTxt.txt

        // Get all the files in the specified directory
        // that are named "example"
        File dir = FileUtils.getFile(PARENT_DIR);
        String[] acceptedNames = {"example", "exampleTxt.txt"};
        for (String file: Objects.requireNonNull(dir.list(new NameFileFilter(acceptedNames, IOCase.INSENSITIVE)))) {
            System.out.println("File found, named: " + file);
        }

        // WildcardFileFilter
        // We can use wildcards in order to get less specific results
        //     ? used for 1 missing char
        //     * used for multiple missing chars
        for (String file: Objects.requireNonNull(dir.list(new WildcardFileFilter("*ample*")))) {
            System.out.println("Wildcard file found, named: " + file);
        }

        // PixelFileFilter
        for (String file: Objects.requireNonNull(dir.list(new PrefixFileFilter("example")))) {
            System.out.println("Prefix file found, named: " + file);
        }

        // SuffixFileFilter
        for (String file: Objects.requireNonNull(dir.list(new SuffixFileFilter(".txt")))) {
            System.out.println("Suffix File found, named: " + file);
        }

        // OrFileFilter
        for (String file: Objects.requireNonNull(dir.list(new OrFileFilter(
                new WildcardFileFilter("*ample*"), new SuffixFileFilter(".txt"))))) {
            System.out.println("Or File found, named: " + file);
        }

        // And this can become very detailed.
        // Eg, get all the files that have "ample" int their name
        // but they are not text files (so they have no ".txt" extensions.)
        for (String file: Objects.requireNonNull(dir.list(new AndFileFilter( // we will match 2 filters..
                new WildcardFileFilter("*ample*"), // ...the 1st is a wildcard...
                new NotFileFilter(new SuffixFileFilter(".txt")))))) { // ...and the 2nd is NOT .txt.

            System.out.println("And/Not File found, named: " + file);
        }
    }
}
