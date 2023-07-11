package Services;

import Models.Message;
import Models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersService {
    public ConnectDB connectDB = new ConnectDB();
    public Connection conn = connectDB.getConnection();

    public Message insertUser(Users user) {
        Message message = new Message();
        if (checkEmail(user.getEmail())) {
            message.setMessage("Email đã tồn tại!");
            message.setCheck(false);
            return message;
        } else {
            try {
                String password = user.getPassword();
                SecurityRSA securityRSA = new SecurityRSA();
                String passwordEncrypt = securityRSA.encrypt(password);
                String sql = "INSERT INTO \"users\"(\"name\", \"email\", \"password\", \"address\", \"phone\", \"date\", \"code\", \"role\") VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, user.getName());
                pst.setString(2, user.getEmail());
                pst.setString(3, passwordEncrypt);
                pst.setString(4, user.getAddress());
                pst.setString(5, user.getPhone());
                pst.setString(6, user.getDate());
                pst.setString(7, user.getCode());
                pst.setString(8, user.getRole());
                pst.executeUpdate();
                message.setCheck(true);
                message.setMessage("Tài khoản được tạo thành công!");
                return message;
            } catch (Exception e) {
                e.printStackTrace();
                message.setCheck(false);
                message.setMessage("Có lỗi xảy ra");
            }

        }
        return message;

    }

    // update user to database and return true if success else return false
    public Message updateUser(Users user) {
        Users userCheck = getUserById(user.getId());

        if (checkEmail(user.getEmail()) && !userCheck.getEmail().equals(user.getEmail())) {
            Message message = new Message();
            message.setCheck(false);
            message.setMessage("Email đã tồn tại!");
            return message;
        }
        Message message = new Message();
        try {
            String password = user.getPassword();
            SecurityRSA securityRSA = new SecurityRSA();
            password = securityRSA.encrypt(password);
            String sql = "UPDATE \"users\" SET \"name\"=?, \"email\"=?, \"password\"=?, \"address\"=?, \"phone\"=?, \"date\"=?, \"code\"=?, \"role\"=? WHERE \"id\"=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, password);
            pst.setString(4, user.getAddress());
            pst.setString(5, user.getPhone());
            pst.setString(6, user.getDate());
            pst.setString(7, user.getCode());
            pst.setString(8, user.getRole());
            pst.setInt(9, user.getId());
            pst.executeUpdate();
            message.setCheck(true);
            message.setMessage("Tài khoản được cập nhật thành công!");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            message.setCheck(false);
            message.setMessage("Có lỗi xảy ra!");
            return message;
        }

    }

    // check Account email exist in database
    public boolean checkEmail(String email) {
        try {
            String sql = "SELECT * FROM \"users\" WHERE email = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /// delete user from database input object User and return true if success else
    /// return false
    public Message deleteUser(Users user) {
        Message message = new Message();
        // check in manage_vehicle table if user has vehicle
        try {
            String sql = "SELECT * FROM manage_book WHERE user_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                message.setCheck(false);
                message.setMessage("Tài khoản không thể xóa vì đang mượn sách !");
                return message;
            } else {
                sql = "DELETE FROM \"users\" WHERE id = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, user.getId());
                pst.executeUpdate();
                message.setCheck(true);
                message.setMessage("Tài khoản đã được xóa thành công!");
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setCheck(false);
            message.setMessage("Có lỗi xảy ra!");
            return message;
        }

    }

    /// kiểu muốn làm theo hướng đối tượng ấy cho nó chuẩn luôn chứ truyền vớ vẩn
    /// vào ko thích hahah
    /// checkLogin start check email after check password
    public Users checkLogin(Users user) {
        // check email exist
        try {
            String sql = "SELECT * FROM \"users\" WHERE \"email\"=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getEmail());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // check password
                String password = rs.getString("password");
                SecurityRSA securityRSA = new SecurityRSA();
                password = securityRSA.decrypt(password);
                if (password.equals(user.getPassword())) {
                    // get user
                    Users user1 = new Users();
                    user1.setId(rs.getInt("id"));
                    user1.setName(rs.getString("name"));
                    user1.setEmail(rs.getString("email"));
                    user1.setPassword(rs.getString("password"));
                    user1.setAddress(rs.getString("address"));
                    user1.setPhone(rs.getString("phone"));
                    user1.setDate(rs.getString("date"));
                    user1.setCode(rs.getString("code"));
                    user1.setRole(rs.getString("role"));
                    return user1;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // get ArrayList<User> from database and return ArrayList<User>
    /// hiểu cái này ko?
    //// ý là nó sẽ lấy ra tất cả các user trong database
    /// xong nó truyền tất cả vào ArrayList<User>
    public ArrayList<Users> getAllUser() {
        ArrayList<Users> list = new ArrayList<Users>();
        try {
            String sql = "SELECT * FROM \"users\"";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                String password = SecurityRSA.decrypt(rs.getString("password"));
                user.setPassword(password);
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setDate(rs.getString("date"));
                user.setCode(rs.getString("code"));
                user.setRole(rs.getString("role"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /// get user from database input Object user by id and return User
    public Users getUserById(Users user) {
        try {
            String sql = "SELECT * FROM \"users\" WHERE \"id\"=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setDate(rs.getString("date"));
                user.setCode(rs.getString("code"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    // getUser where Id
    public Users getUserById(int id) {
        try {
            String sql = "SELECT * FROM \"users\" WHERE \"id\"=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setDate(rs.getString("date"));
                user.setCode(rs.getString("code"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    // Search all row in database
    public ArrayList<Users> Search(String search) {
        ArrayList<Users> list = new ArrayList<Users>();
        try {
            String sql = "SELECT * FROM \"users\" WHERE \"name\" ILIKE '%" + search + "%' OR \"email\" ILIKE '%" + search
                    + "%' OR \"address\" ILIKE '%" + search + "%' OR \"phone\" ILIKE '%" + search
                    + "%' OR \"date\" ILIKE '%" + search + "%' OR \"code\" ILIKE '%" + search
                    + "%' OR \"role\" ILIKE '%" + search + "%' OR \"id\"::text ILIKE '%" + search + "%'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                String password = SecurityRSA.decrypt(rs.getString("password"));
                user.setPassword(password);
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setDate(rs.getString("date"));
                user.setCode(rs.getString("code"));
                user.setRole(rs.getString("role"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
