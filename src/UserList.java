
import java.io.*;
import java.net.Socket;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Александр
 */
public class UserList {

    private ArrayList<User> clients = new ArrayList();

    public void addUser(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        clients.add(new User(socket, in, out));
    }

    public void deleteUser(User user) {
        clients.remove(user);
    }

    public ArrayList<User> getUsers() {
        return clients;
    }

}
