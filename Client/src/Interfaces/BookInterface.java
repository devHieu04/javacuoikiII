package Interfaces;

import java.rmi.Remote;
import java.util.ArrayList;

import Models.Book;
import Models.Message;

public interface BookInterface extends Remote {
    public ArrayList<Book> getAllBook() throws Exception;
    public Book getBookbyId(int id) throws Exception;
    public Book getBook(Book book) throws Exception;
    public Message insertBook(Book book) throws Exception;
    public Message updateBook(Book book) throws Exception;
    public Message deleteBook(Book book) throws Exception;
    public ArrayList<Book> searchBook(String search) throws Exception;
}
