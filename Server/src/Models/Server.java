package Models;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Implements.BookImplement;
import Implements.ManageBookImplement;
import Implements.UsersImplement;
import Interfaces.BookInterface;
import Interfaces.ManageBookInterface;
import Interfaces.UsersInterface;

public class Server {
    public Server() {
        try {
            // Tạo một đối tượng Registry trên cổng 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Tạo các đối tượng implement của các giao diện
            UsersInterface usersInterface = new UsersImplement();
            BookInterface bookInterface = new BookImplement();
            ManageBookInterface manageBookInterface = new ManageBookImplement();

            // Đăng ký các giao diện với Registry
            registry.rebind("UsersInterface", usersInterface);
            registry.rebind("BookInterface", bookInterface);
            registry.rebind("ManageBookInterface", manageBookInterface);

            System.out.println("Server is running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
