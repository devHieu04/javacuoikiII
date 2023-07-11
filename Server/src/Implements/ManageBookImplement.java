package Implements;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Interfaces.ManageBookInterface;
import Models.ManageBook;
import Models.Message;
import Services.ManageBookService;

public class ManageBookImplement extends  UnicastRemoteObject implements ManageBookInterface {
     
    public ManageBookImplement() throws RemoteException {
        super();
    }
    ManageBookService manageBookService = new ManageBookService();
    @Override
    public ArrayList<ManageBook> getAllManageBook() throws Exception
     {
        return manageBookService.getAllManageBook();
     }
     @Override
    public ManageBook getManageBook(ManageBook manageBook ) throws Exception
    {
        return manageBookService.getManageBook(manageBook);
    }
    @Override
    public Message insertManageBook(ManageBook manageBook) throws Exception
    {
        return manageBookService.insertManageBook(manageBook);
    }
    @Override
    public Message updateManageBook(ManageBook manageVehicle) throws Exception
    {
        return manageBookService.updateManageBook(manageVehicle);
    }
    @Override
    public Message deleteManageBook(ManageBook manageVehicle) throws Exception
    {
        return manageBookService.deleteManageBook(manageVehicle);
    }
    @Override
    public ArrayList<ManageBook> searchManageBook(String search) throws Exception
    {
        return manageBookService.searchManageBook(search);
    }
    @Override
    public ManageBook getManageBookbyId(int id) throws Exception
    {
        return manageBookService.getManageBookbyId(id);
    }
}
