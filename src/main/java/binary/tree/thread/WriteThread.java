package binary.tree.thread;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.Exchanger;

public class WriteThread extends Thread {
    int depth;
    Exchanger ex;
    PrintWriter out;

    public WriteThread(int depth, Exchanger ex, PrintWriter out) {
        this.depth = depth;
        this.ex = ex;
        this.out = out;
    }

    @Override
    public void run() {
        int depth2;
        if (depth == 0 )depth2 = 1;
        else depth2 = depth ;
        for (int k = 0; k < depth2 ; k++) {
            try {
                File[] key = (File[]) ex.exchange(null);
                System.out.println("\nWriteThread File key = " + k + " i " + key);
                for (int i = 0; i < key.length; i++) {
                    System.out.println("\nWriteThread File TaskMain = " + i + " i " + key[i]);
                    out.println(key[i]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
