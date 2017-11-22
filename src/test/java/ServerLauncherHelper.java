import binary.tree.telnet.TelnetServer;

public class ServerLauncherHelper implements Runnable {

    private final TelnetServer server;

    /**
     * Initialise the TelnetServer
     */
    public ServerLauncherHelper(final TelnetServer server) {
        this.server = server;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        server.run();
    }

    /**
     * Shutdown the server
     */
    public void shutdown() throws Exception {
        if (server.isRunning()) {
            server.shutDown();
        }

    }
}
