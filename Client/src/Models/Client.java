package Models;

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import Interfaces.BookInterface;
import Interfaces.ManageBookInterface;
import Interfaces.UsersInterface;

public class Client {
    public UsersInterface usersInterface;
    public ManageBookInterface manageBookInterface;
    public BookInterface bookInterface;

    public Client() {
        try {
            // tạo một đối tượng UserInterface từ Server ở port 1099 và tên UserInterface
            usersInterface = (UsersInterface) LocateRegistry.getRegistry("localhost", 1099).lookup("UsersInterface");
            bookInterface = (BookInterface) LocateRegistry.getRegistry("localhost", 1099).lookup("BookInterface");
            manageBookInterface = (ManageBookInterface) LocateRegistry.getRegistry("localhost", 1099)
                    .lookup("ManageBookInterface");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public ArrayList<Users> getAllUser() throws Exception {
        return usersInterface.getAllUser();
    }

    public Users getUser(Users user) throws Exception {
        return usersInterface.getUser(user);
    }

    public Message insertUser(Users user) throws Exception {
        return usersInterface.insertUser(user);
    }

    public Message updateUser(Users user) throws Exception {
        return usersInterface.updateUser(user);
    }

    public Message deleteUser(Users user) throws Exception {
        return usersInterface.deleteUser(user);
    }

     public ArrayList<Users> searchUser(String search) throws Exception {
        return usersInterface.SearchUser(search);
    }

    public Users checkLogin(Users user) throws Exception {
        return usersInterface.checkLogin(user);
    }

    public Users getUserById(int id) throws Exception {
        return usersInterface.getUserById(id);
    }
    public ArrayList<Book> getAllBook() throws Exception
    {
        return bookInterface.getAllBook();
    }
    public Book getBookbyId(int id) throws Exception
    {
        return bookInterface.getBookbyId(id);
    }
    public Book getBook(Book book) throws Exception
    {
        return bookInterface.getBook(book);
    }
    public Message insertBook(Book book) throws Exception
    {
        return bookInterface.insertBook(book);
    }
    public Message updateBook(Book book) throws Exception
    {
        return bookInterface.updateBook(book);
    }
    public Message deleteBook(Book book) throws Exception
    {
        return bookInterface.deleteBook(book);
    }
     public ArrayList<Book> searchBook(String search) throws Exception
     {
        return bookInterface.searchBook(search);
     }
    public ArrayList<ManageBook> getAllManageBook() throws Exception
    {
        return manageBookInterface.getAllManageBook();
    }
      public ManageBook getManageBook(ManageBook manageBook ) throws Exception
      {
        return manageBookInterface.getManageBook(manageBook);
      }
       public Message insertManageBook(ManageBook manageBook) throws Exception
       {
        return manageBookInterface.insertManageBook(manageBook);
       }
    public Message updateManageBook(ManageBook manageBook) throws Exception
    {
        return manageBookInterface.updateManageBook(manageBook);
    }
    public Message deleteManageBook(ManageBook manageBook) throws Exception
    {
        return manageBookInterface.deleteManageBook(manageBook);
    }
    public ArrayList<ManageBook> searchManageBook(String search) throws Exception
    {
        return manageBookInterface.searchManageBook(search);
    }
    public ManageBook getManageBookbyId(int id) throws Exception
    {
        return manageBookInterface.getManageBookbyId(id);
    }
}
