package Interfaces;

import java.rmi.Remote;
import java.util.ArrayList;

import Models.ManageBook;
import Models.Message;

public interface ManageBookInterface extends Remote{
    public ArrayList<ManageBook> getAllManageBook() throws Exception;
    public ManageBook getManageBook(ManageBook manageBook ) throws Exception;
    public Message insertManageBook(ManageBook manageBook) throws Exception;
    public Message updateManageBook(ManageBook manageBook) throws Exception;
    public Message deleteManageBook(ManageBook manageBook) throws Exception;
    public ArrayList<ManageBook> searchManageBook(String search) throws Exception;
    public ManageBook getManageBookbyId(int id) throws Exception;
    
}
