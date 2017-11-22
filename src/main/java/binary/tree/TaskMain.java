package binary.tree;

import binary.tree.telnet.TelnetServer;

import java.io.IOException;

public class TaskMain extends Thread {

    public static void main(String args[]) throws IOException, InterruptedException {
        new TelnetServer(args.length == 0 ? null : args[0], args.length == 1 ? null : args[1]).run();
    }
}

