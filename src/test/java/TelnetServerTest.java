import binary.tree.telnet.TelnetServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TelnetServerTest {

    private final SocketAddress socketAddress = new InetSocketAddress("localhost", 5555);
    private Socket socketes;
    private ServerLauncherHelper helper;


    private void startServerInBackground() throws InterruptedException {
        helper = new ServerLauncherHelper(new TelnetServer("5555", "C:\\home"));
        new Thread(helper).start();
        Thread.sleep(3000);
    }


    @Before
    public void setUp() throws Exception {
        startServerInBackground();
        socketes = new Socket();

        socketes.connect(socketAddress, 10000);
        assertNotNull(new BufferedReader(new InputStreamReader(socketes.getInputStream())).readLine());
    }

    @Test
    public void testInvokeStatus() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(socketes.getInputStream()));
        final PrintWriter writer = new PrintWriter(socketes.getOutputStream(), true);

        int deep = 1;
        // 1 парметр - глубина, 2 - ключевое слово для поиска
        writer.println(deep + " t");
        
        String line = "";
        String newLine;
        while ((newLine = in.readLine()).equals(line)) {
            System.out.println(line);
            line = newLine;
        }

    }

    @After
    public void teardown() throws Exception {

        // close the client.
        socketes.close();

        // shutdown the server
        helper.shutdown();

    }


}
