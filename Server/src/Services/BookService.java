package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Book;
import Models.Message;

public class BookService {
    ConnectDB connectDB = new ConnectDB();
    Connection conn = connectDB.getConnection();

    // insert vehicle input object vehicle and return true if success else return
    // false
    public Message insertBook(Book book) {
        Message message = new Message();

        try {
            String sql = "INSERT INTO book (Ten_sach, Mo_ta, ngay_xuataban, So_trang, IBSN, Loai_sach, Nha_xuat_ban) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.getTen_Sach());
            pst.setString(2, book.getMo_ta());
            pst.setString(3, book.getNgay_xuatban());
            pst.setInt(4, book.getSo_trang());
            pst.setString(5, book.getIbsn());
            pst.setString(6, book.getLoai_sach());
            pst.setString(7, book.getNha_xuatban());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                message.setMessage("Thêm sách thành công!");
                message.setCheck(true);
            } else {
                message.setMessage("Không thành công khi thêm sách!");
                message.setCheck(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message.setMessage("Có lỗi xảy ra khi thêm sách!");
            message.setCheck(false);
        }

        return message;
    }

    public Message updateBook(Book book) {
        Message message = new Message();

        try {
            String sql = "UPDATE book SET Ten_sach = ?, Mo_ta = ?, ngay_xuataban = ?, So_trang = ?, IBSN = ?, Loai_sach = ?, Nha_xuat_ban = ? WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.getTen_Sach());
            pst.setString(2, book.getMo_ta());
            pst.setString(3, book.getNgay_xuatban());
            pst.setInt(4, book.getSo_trang());
            pst.setString(5, book.getIbsn());
            pst.setString(6, book.getLoai_sach());
            pst.setString(7, book.getNha_xuatban());
            pst.setInt(8, book.getId());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                message.setMessage("Cập nhật sách thành công!");
                message.setCheck(true);
            } else {
                message.setMessage("Không thành công khi cập nhật sách!");
                message.setCheck(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message.setMessage("Có lỗi xảy ra khi cập nhật sách!");
            message.setCheck(false);
        }

        return message;
    }

  public Message deleteBook(Book book) {
    Message message = new Message();

    try {
        String sql = "DELETE FROM book WHERE ID = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, book.getId());
        
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            message.setMessage("Xóa sách thành công!");
            message.setCheck(true);
        } else {
            message.setMessage("Không thành công khi xóa sách!");
            message.setCheck(false);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        message.setMessage("Có lỗi xảy ra khi xóa sách!");
        message.setCheck(false);
    }
    
    return message;
}


    public ArrayList<Book> getAllBook() {
       ArrayList<Book> bookList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM book";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String tenSach = rs.getString("Ten_sach");
                String moTa = rs.getString("Mo_ta");
                String ngayXuatBan = rs.getString("ngay_xuataban");
                int soTrang = rs.getInt("So_trang");
                String IBSN = rs.getString("IBSN");
                String loaiSach = rs.getString("Loai_sach");
                String nhaXuatBan = rs.getString("Nha_xuat_ban");

                Book book = new Book(ID, tenSach, moTa, ngayXuatBan, soTrang, IBSN, loaiSach, nhaXuatBan);
                bookList.add(book);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public Book getBookbyId(int bookID) {
        Book book = null;

        try {
            String sql = "SELECT * FROM book WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bookID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt("ID");
                String tenSach = rs.getString("Ten_sach");
                String moTa = rs.getString("Mo_ta");
                String ngayXuatBan = rs.getString("ngay_xuataban");
                int soTrang = rs.getInt("So_trang");
                String IBSN = rs.getString("IBSN");
                String loaiSach = rs.getString("Loai_sach");
                String nhaXuatBan = rs.getString("Nha_xuat_ban");

                book = new Book(ID, tenSach, moTa, ngayXuatBan, soTrang, IBSN, loaiSach, nhaXuatBan);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public ArrayList<Book> searchBook(String search) {
        ArrayList<Book> bookList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM book WHERE Ten_sach LIKE ? OR Mo_ta LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + search + "%");
            pst.setString(2, "%" + search + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String tenSach = rs.getString("Ten_sach");
                String moTa = rs.getString("Mo_ta");
                String ngayXuatBan = rs.getString("ngay_xuataban");
                int soTrang = rs.getInt("So_trang");
                String IBSN = rs.getString("IBSN");
                String loaiSach = rs.getString("Loai_sach");
                String nhaXuatBan = rs.getString("Nha_xuat_ban");

                Book book = new Book(ID, tenSach, moTa, ngayXuatBan, soTrang, IBSN, loaiSach, nhaXuatBan);
                bookList.add(book);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

}
