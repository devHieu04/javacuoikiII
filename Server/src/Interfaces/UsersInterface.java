package Interfaces;

import java.rmi.Remote;
import java.util.ArrayList;

import Models.Book;
import Models.Message;
import Models.Users;

public interface UsersInterface extends Remote {
  public Users getUserById(int id) throws Exception;
    public Users getUser(Users user) throws Exception;
    public ArrayList<Users> getAllUser() throws Exception;
    public Message insertUser(Users user) throws Exception;
    public Message updateUser(Users user) throws Exception;
    public Message deleteUser(Users user) throws Exception;
    public ArrayList<Users> SearchUser(String search) throws Exception;
    public Users checkLogin(Users user) throws Exception;

}
