package binary.tree.thread;

import binary.tree.helper.FindFile;

import java.util.concurrent.Exchanger;

public class ReadThread extends Thread {
    private String mask;
    private String rootPath;
    private int depth;
    Exchanger ex;

    public ReadThread(String mask, String rootPath, int depth, Exchanger ex) {
        this.mask = mask;
        this.rootPath = rootPath;
        this.depth = depth;
        this.ex = ex;
    }

    @Override
    public void run() {
        try {
            FindFile.getInstance().getFile(rootPath, mask, depth, ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
