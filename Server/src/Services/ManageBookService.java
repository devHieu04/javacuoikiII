package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.ManageBook;
import Models.Message;

public class ManageBookService {
    public ConnectDB connectDB = new ConnectDB();
    public Connection conn = connectDB.getConnection();

    int id;
    int user_id;
    String registrain_date;
    String book_name;
    int book_id;
    int quantity;
    String borrow_code;

    public Message insertManageBook(ManageBook manageBook) throws Exception {
        Message message = new Message();

        try {
            String sql = "INSERT INTO manage_book (user_id, registration_date, book_name, book_id, quantity, borrow_code) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, manageBook.getUser_id());
            pst.setString(2, manageBook.getRegistration_date());
            pst.setString(3, manageBook.getBook_name());
            pst.setInt(4, manageBook.getBook_id());
            pst.setInt(5, manageBook.getQuantity());
            pst.setString(6, manageBook.getBorro_code());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // message.setStatus("success");
                message.setMessage("Dữ liệu manage_book đã được chèn thành công.");
            } else {
                // message.setStatus("error");
                message.setMessage("Không thể chèn dữ liệu vào manage_book.");
            }

            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi chèn dữ liệu vào bảng manage_book: " + e.getMessage());
        }

        return message;
    }

    public Message updateManageBook(ManageBook manageBook) throws Exception {
        Message message = new Message();

        try {
            String sql = "UPDATE manage_book SET registration_date = ?, book_name = ?, book_id = ?, quantity = ?, borrow_code = ? WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, manageBook.getRegistration_date());
            pst.setString(2, manageBook.getBook_name());
            pst.setInt(3, manageBook.getBook_id());
            pst.setInt(4, manageBook.getQuantity());
            pst.setString(5, manageBook.getBorro_code());
            pst.setInt(6, manageBook.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // message.setStatus("success");
                message.setMessage("Dữ liệu manage_book đã được cập nhật thành công.");
            } else {
                // message.setStatus("error");
                message.setMessage("Không thể cập nhật dữ liệu trong manage_book.");
            }

            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi cập nhật dữ liệu trong bảng manage_book: " + e.getMessage());
        }

        return message;
    }

    public Message deleteManageBook(ManageBook manageBook) throws Exception {
        Message message = new Message();

        try {
            String sql = "DELETE FROM manage_book WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, manageBook.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // message.setStatus("success");
                message.setMessage("Dữ liệu manage_book đã được xóa thành công.");
            } else {
                // message.setStatus("error");
                message.setMessage("Không thể xóa dữ liệu trong manage_book.");
            }

            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi xóa dữ liệu trong bảng manage_book: " + e.getMessage());
        }

        return message;
    }

    public ManageBook getManageBook(ManageBook manageBook) throws Exception {
        ManageBook result = null;

        try {
            String sql = "SELECT * FROM manage_book WHERE user_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, manageBook.getUser_id());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("user_id");
                String registrationDate = rs.getString("registration_date");
                String bookName = rs.getString("book_name");
                int bookID = rs.getInt("book_id");
                int quantity = rs.getInt("quantity");
                String borrowCode = rs.getString("borrow_code");

                result = new ManageBook(ID, userID, registrationDate, bookName, bookID, quantity, borrowCode);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi truy xuất dữ liệu từ bảng manage_book: " + e.getMessage());
        }

        return result;
    }

    public ArrayList<ManageBook> searchManageBook(String search) throws Exception {
        ArrayList<ManageBook> manageBookList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM manage_book WHERE book_name LIKE ? OR borrow_code LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + search + "%");
            pst.setString(2, "%" + search + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("user_id");
                String registrationDate = rs.getString("registration_date");
                String bookName = rs.getString("book_name");
                int bookID = rs.getInt("book_id");
                int quantity = rs.getInt("quantity");
                String borrowCode = rs.getString("borrow_code");

                ManageBook manageBook = new ManageBook(ID, userID, registrationDate, bookName, bookID, quantity,
                        borrowCode);
                manageBookList.add(manageBook);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi tìm kiếm dữ liệu trong bảng manage_book: " + e.getMessage());
        }

        return manageBookList;
    }

    public ManageBook getManageBookbyId(int id) throws Exception {
        ManageBook result = null;

        try {
            String sql = "SELECT * FROM manage_book WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("user_id");
                String registrationDate = rs.getString("registration_date");
                String bookName = rs.getString("book_name");
                int bookID = rs.getInt("book_id");
                int quantity = rs.getInt("quantity");
                String borrowCode = rs.getString("borrow_code");

                result = new ManageBook(ID, userID, registrationDate, bookName, bookID, quantity, borrowCode);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi truy xuất dữ liệu từ bảng manage_book: " + e.getMessage());
        }

        return result;
    }

    public ArrayList<ManageBook> getAllManageBook() throws Exception {
        ArrayList<ManageBook> manageBookList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM manage_book";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int userID = rs.getInt("user_id");
                String registrationDate = rs.getString("registration_date");
                String bookName = rs.getString("book_name");
                int bookID = rs.getInt("book_id");
                int quantity = rs.getInt("quantity");
                String borrowCode = rs.getString("borrow_code");

                ManageBook manageBook = new ManageBook(ID, userID, registrationDate, bookName, bookID, quantity,
                        borrowCode);
                manageBookList.add(manageBook);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi truy xuất dữ liệu từ bảng manage_book: " + e.getMessage());
        }

        return manageBookList;
    }

}
