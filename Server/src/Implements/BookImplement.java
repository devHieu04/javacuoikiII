package Implements;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Interfaces.BookInterface;
import Models.Book;
import Models.Message;
import Services.BookService;

public class BookImplement extends UnicastRemoteObject implements BookInterface {
    // public Book vehicleService = new VehicleService();
    public BookService bookService = new BookService();

    public BookImplement() throws Exception {
        super();
    }

    @Override
    public Book getBookbyId(int id) throws Exception {
        return bookService.getBookbyId(id);

    }

     public Book getBook(Book book) throws Exception
     {
        return null;
     }
    @Override
    public ArrayList<Book> getAllBook() throws Exception {
        return bookService.getAllBook();
    }

    @Override
    public ArrayList<Book> searchBook(String search) throws Exception {
        return bookService.searchBook(search);
    }

    @Override
    public Message deleteBook(Book book) throws Exception {
        return bookService.deleteBook(book);
    }

    @Override
    public Message updateBook(Book book) throws Exception {
        return bookService.updateBook(book);
    }

    @Override
    public Message insertBook(Book book) throws Exception {
        return bookService.insertBook(book);
    }

}
