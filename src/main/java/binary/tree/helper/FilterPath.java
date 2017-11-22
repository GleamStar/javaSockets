package binary.tree.helper;

import java.io.File;
import java.io.FilenameFilter;

public class FilterPath implements FilenameFilter {

    private String mask;

    public FilterPath(String mask) {
        this.mask = mask;
    }

    public boolean accept(File dir, String name) {
        return new File(name).getName().contains(mask);
    }
}
