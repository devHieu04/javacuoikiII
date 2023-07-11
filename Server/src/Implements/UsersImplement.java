package Implements;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Interfaces.UsersInterface;
import Models.Message;
import Models.Users;
import Services.UsersService;

public class UsersImplement extends UnicastRemoteObject implements UsersInterface {
    public UsersService userService = new UsersService();

    public UsersImplement() throws Exception {
        super();
    }

    @Override
    public Users getUserById(int id) throws Exception {
        return userService.getUserById(id);
    }

    @Override
    public Users getUser(Users user) {
        return null;
    }

    @Override
    public ArrayList<Users> getAllUser() throws Exception {
        return userService.getAllUser();
    }

    @Override
    public Message insertUser(Users user) throws Exception {
        return userService.insertUser(user);
    }

    @Override
    public Message updateUser(Users user) throws Exception {
        return userService.updateUser(user);
    }

    @Override
    public Message deleteUser(Users user) throws Exception {
        return userService.deleteUser(user);
    }

    @Override
    public ArrayList<Users> SearchUser(String search) throws Exception {
        return userService.Search(search);
    }

    @Override
    public Users checkLogin(Users user) throws Exception {
        return userService.checkLogin(user);
    }
}
