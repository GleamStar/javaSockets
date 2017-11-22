package binary.tree.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Exchanger;

public class FindFile {

    private static volatile  FindFile instance ;

    public static FindFile getInstance() {
        FindFile localInstance = instance;
        if (localInstance == null) {
            synchronized (FindFile.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FindFile();
                }
            }
        }
        return localInstance;
    }

    public void getFile(String rootPath, String mask, int depth, Exchanger ex) throws InterruptedException {
        ArrayList<File> listFiles = findPath(new File(rootPath));
        File[] all = listFiles.toArray(new File[listFiles.size()]);
        completeFile(all, mask, depth, ex);
    }

    private void completeFile(File[] all, String mask, int depth, Exchanger ex) throws InterruptedException {
        FilterPath filterPath = new FilterPath(mask);

        for (int i = 0; i < all.length; i++) {
            if (all[i].isDirectory() && i < depth) {
                File[] folderList = all[i].listFiles(filterPath);

                File[] in = (File[]) ex.exchange(folderList);

                for (int j = 0; j < folderList.length; j++) {
                    System.out.println("\n ReadThread FindFile completeFile path = " + folderList[j]);
                }
            }
        }
    }

    private ArrayList<File> findPath(File path) {
        ArrayList<File> listRoot = new ArrayList<File>();
        ArrayList<File> listFiles = new ArrayList<File>();
        listRoot.add(path);

        do {
            for (int i = 0; i < listRoot.size(); i++) {
                if (listRoot.get(i).isDirectory()) {
                    File local = listRoot.get(i);
                    listFiles.add(local.getAbsoluteFile());
                    System.out.println("\n ReadThread FindFile cdvczdsvsdleteFile path = " + local);
                    listRoot.addAll(Arrays.asList(local.listFiles()));
                }
            }
        } while (listRoot.isEmpty());

        return listFiles;
    }
}
