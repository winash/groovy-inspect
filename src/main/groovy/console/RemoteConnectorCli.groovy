package console

import org.codehaus.groovy.tools.shell.IO

/**
 * @author Winash
 *
 * Telnet <hostname> <9095> to manage instance
 */
public class RemoteConnectorCli {

    private static final int PORT = 9095
    ServerSocket server = new ServerSocket(PORT)

    public void destroy() {
        if (!server.closed)
            server.close()
    }

    public void init() {
        final thread = new Thread(new CLIRunner(server))
        thread.start()
    }


    private static class CLIRunner implements Runnable {

        ServerSocket server

        CLIRunner(ServerSocket server) {
            this.server = server
        }

        @Override
        void run() {
            try {
                while (true) {
                    server.accept { socket ->
                        println "accepting new connection..."
                        socket.withStreams { input, output ->
                            final def io = new IO(input, output, output)
                            final Console service = new Console()
                            service.startConsoleOnPort(io)
                        }
                        println "processing new connection...";
                    }
                }


            } catch (InterruptedException ie) {
                ie.printStackTrace()
                println "Thread was interrupted stopping cli service"
                server.close()
            }
        }
    }


}
