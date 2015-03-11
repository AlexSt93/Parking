package ServerPac;






import java.io.*;
import java.net.Socket;

public class User {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    

    User(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return the in
     */
    public ObjectInputStream getIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    /**
     * @return the out
     */
    public ObjectOutputStream getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

   
    
   
}