import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

class ServerSockets {
    private ServerSocket serverSocket;

    ServerSockets(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    /**
     * Accepts new clients trying to connect.
     * @throws IOException if an I/O error occurs when waiting for a connection.
     * @return Returns the new connected socket.
     */
    Socket acceptConnection() throws IOException {
        return serverSocket.accept();
    }


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Reads all the new data from the given socket.
     * @param socket Socket the method will read data from.
     * @return Returns the new data from the socket.
     * @throws IOException if an I/O error occurs when waiting for a connection.
     */
    String readSocket(Socket socket) throws IOException {
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        StringBuilder string = new StringBuilder();

        if(socket.getInputStream().available() != 0) {
            while (socket.getInputStream().available() != 0) {
                string.append(bf.readLine());
            }
        } else {
            throw new SocketException("Socket contains no new data");
        }
        return string.toString();
    }

    /**
     * Writes data to a given socket.
     * @param socket Socket the method will write to.
     * @param string What the method will write.
     * @throws IOException if an I/O error occurs when waiting for a connection.
     */
    void writeSocket(Socket socket, String string) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(string);
        printWriter.flush();
    }
}
