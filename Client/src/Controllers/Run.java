package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Models.Book;
import Models.Client;
import Models.ManageBook;
import Models.Message;
import Models.Users;
import Views.Login;
import Views.Manage;
import Views.Register;

public class Run {
    public static Login login = new Login();
    public static Register register = new Register();
    public static Manage manage = new Manage();
    public static Client client = new Client();

    // public static Certificate certificate = new Certificate();
    public static void main(String[] args) {
        // login.submit_btnLogin.addActionListener(new ActionListener() )

        manage.LogoutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // show Dialog exit or login again
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    manage.setVisible(false);
                    login.setVisible(true);
                }
            }
        });
        register.login_btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register.setVisible(false);
                login.setVisible(true);
            }
        });
        register.submit_btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users userInput = new Users();
                String email = register.emailRegistration.getText();
                String password = register.passwordRegistration.getText();
                String name = register.usernameRegistration.getText();
                if (validateRegister(email, password, name)) {
                    userInput.setEmail(email);
                    userInput.setPassword(password);
                    userInput.setName(name);
                    userInput.setAddress("");
                    userInput.setCode("");
                    userInput.setPhone("");
                    userInput.setDate("2004-5-20");
                    userInput.setRole("User");
                    try {
                        Message message = client.insertUser(userInput);
                        if (message.getCheck()) {
                            register.notification(message.getMessage());
                            register.setVisible(false);
                            login.setVisible(true);
                        } else {
                            register.notification(message.getMessage());
                        }

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        login.reg_btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(false);
                register.setVisible(true);
            }
        });
        login.submit_btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users userInput = new Users();
                userInput.setEmail(login.emailLogin.getText());
                userInput.setPassword(login.passwordLogin.getText());
                try {
                    Users userOutput = client.checkLogin(userInput);
                    if (userOutput != null) {
                        if (userOutput.getRole().equals("Admin")) {
                            login.notification("Login Successful");
                            login.setVisible(false);
                            ArrayList<Users> listUser = client.getAllUser();
                            ArrayList<Book> listVehicle = client.getAllBook();
                            ArrayList<ManageBook> listManageVehicle = client.getAllManageBook();
                            addDataToTableManageVehicle(listManageVehicle);
                            addDataToComboBoxNameVehicleManage(listVehicle);
                            addDataToComboBoxNameUserManage(listUser);
                            addDataToTableUser(listUser);
                            addDataToTableBook(listVehicle);
                            manage.AuthUser.setText(userOutput.getName());
                            manage.setVisible(true);

                        } else {
                            login.notification("Không đủ quyền truy cập");
                        }

                    } else {
                        login.notification("Login Failed");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        /* Xử lí manage */

        manage.tableUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // get Model from to tableUser
                DefaultTableModel model = (DefaultTableModel) manage.tableUser.getModel();
                int row = manage.tableUser.getSelectedRow();
                manage.idUser.setText(model.getValueAt(row, 0) + "");
                manage.nameUser.setText(model.getValueAt(row, 1) + "");
                manage.emailUser.setText(model.getValueAt(row, 2) + "");
                manage.passwordUser.setText(model.getValueAt(row, 3) + "");
                manage.addressUser.setText(model.getValueAt(row, 4) + "");
                manage.phoneUser.setText(model.getValueAt(row, 5) + "");
                String dateManage = model.getValueAt(row, 6) + "";
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateManage);
                    manage.dateUser.setDate(date);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block

                }

                manage.codeUser.setText(model.getValueAt(row, 7) + "");
                manage.roleUser.setSelectedItem(model.getValueAt(row, 8) + "");

            }
        });
        /// thêm User vào database và load lại table
        manage.addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /// get dữ liệu User từ bàn views
                Users userInput = new Users();
                String id = manage.idUser.getText();
                String name = manage.nameUser.getText();
                String email = manage.emailUser.getText();
                String password = manage.passwordUser.getText();
                String address = manage.addressUser.getText();
                String phone = manage.phoneUser.getText();
                String date = ((JTextField) manage.dateUser.getDateEditor().getUiComponent()).getText();
                String code = manage.codeUser.getText();
                String role = manage.roleUser.getSelectedItem().toString();
                /// tạo dữ liệu object User từ bàn những biến ở trên
                userInput.setName(name);
                userInput.setEmail(email);
                userInput.setPassword(password);
                userInput.setAddress(address);
                userInput.setPhone(phone);
                userInput.setDate(date);
                userInput.setCode(code);
                userInput.setRole(role);
                try {
                    Message message = client.insertUser(userInput);
                    if (message.getCheck()) {
                        manage.notification(message.getMessage());
                        ArrayList<Users> listUser = client.getAllUser();
                        addDataToTableUser(listUser);
                        addDataToComboBoxNameUserManage(listUser);
                    } else {
                        manage.notification(message.getMessage());
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /// sửa User vào database và load lại table
        manage.updateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users userInput = new Users();
                String id = manage.idUser.getText();
                if (id.equals("")) {
                    manage.notification("Please select a user");
                } else {

                    String name = manage.nameUser.getText();
                    String email = manage.emailUser.getText();
                    String password = manage.passwordUser.getText();
                    String address = manage.addressUser.getText();
                    String phone = manage.phoneUser.getText();
                    String date = ((JTextField) manage.dateUser.getDateEditor().getUiComponent()).getText();
                    String code = manage.codeUser.getText();
                    String role = manage.roleUser.getSelectedItem().toString();
                    userInput.setId(Integer.parseInt(id));
                    userInput.setName(name);
                    userInput.setEmail(email);
                    userInput.setPassword(password);
                    userInput.setAddress(address);
                    userInput.setPhone(phone);
                    userInput.setDate(date);
                    userInput.setCode(code);
                    userInput.setRole(role);
                    try {
                        Message message = client.updateUser(userInput);
                        if (message.getCheck()) {
                            manage.notification(message.getMessage());
                            ArrayList<Users> listUser = client.getAllUser();
                            addDataToTableUser(listUser);
                            addDataToComboBoxNameUserManage(listUser);
                            ArrayList<ManageBook> manageVehicles = client.getAllManageBook();
                            addDataToTableManageVehicle(manageVehicles);
                        } else {
                            manage.notification(message.getMessage());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        /// xóa User vào database và load lại table
        manage.deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users userInput = new Users();
                if (manage.idUser.getText().equals("")) {
                    manage.notification("Please select a user");
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá tài khoản này không?",
                            "Xác nhận xoá tài khoản",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        userInput.setId(Integer.parseInt(manage.idUser.getText()));
                        try {
                            Message message = client.deleteUser(userInput);
                            if (message.getCheck()) {
                                manage.notification(message.getMessage());
                                ArrayList<Users> listUser = client.getAllUser();
                                addDataToTableUser(listUser);
                                addDataToComboBoxNameUserManage(listUser);
                            } else {
                                manage.notification(message.getMessage());
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        manage.deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users userInput = new Users();
                if (manage.idUser.getText().equals("")) {
                    manage.notification("Please select a user");
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá tài khoản này không?",
                            "Xác nhận xoá tài khoản",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        userInput.setId(Integer.parseInt(manage.idUser.getText()));
                        try {
                            Message message = client.deleteUser(userInput);
                            if (message.getCheck()) {
                                manage.notification(message.getMessage());
                                ArrayList<Users> listUser = client.getAllUser();
                                addDataToTableUser(listUser);
                                addDataToComboBoxNameUserManage(listUser);
                            } else {
                                manage.notification(message.getMessage());
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        manage.searchUser.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                String search = manage.searchUser.getText();
                try {
                    ArrayList<Users> listUser = client.searchUser(search);
                    addDataToTableUser(listUser);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        // ================================================================

        manage.addVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book bookInput = new Book();
                String name = manage.nameVehicle.getText();
                String mota = manage.colorVehicle.getText();
                String Ibsn = manage.typeVehicle.getText();
                String date = ((JTextField) manage.dateVehicle.getDateEditor().getUiComponent()).getText();
                System.out.println(date);
                String loai = manage.seat_capacityVehicle.getText();
                String nhaxb = manage.capacityVehicle.getText();
                System.out.println(nhaxb);
                String sotr = manage.brandVehicle.getText();
                bookInput.setTen_Sach(name);
                bookInput.setMo_ta(mota);
                bookInput.setIbsn(Ibsn);
                bookInput.setNgay_xuatban(date);
                bookInput.setLoai_sach(loai);
                bookInput.setNha_xuatban(nhaxb);
                bookInput.setSo_trang(Integer.parseInt(sotr));
                try {
                    Message message = client.insertBook(bookInput);
                    manage.notification(message.getMessage());
                    ArrayList<Book> listVehicle = client.getAllBook();
                    addDataToTableBook(listVehicle);
                    addDataToComboBoxNameVehicleManage(listVehicle);
                    // if (message.getCheck()) {

                    // } else {
                    // manage.notification(message.getMessage());
                    // }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        manage.updateVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book bookInput = new Book();
                String id = manage.idVehicle.getText();
                if (id.equals("")) {
                    manage.notification("Please select a book");
                } else {
                    String name = manage.nameVehicle.getText();
                    String mota = manage.colorVehicle.getText();
                    String Ibsn = manage.typeVehicle.getText();
                    String date = ((JTextField) manage.dateVehicle.getDateEditor().getUiComponent()).getText();
                    System.out.println(date);
                    String loai = manage.seat_capacityVehicle.getText();
                    String nhaxb = manage.capacityVehicle.getText();
                    System.out.println(nhaxb);
                    String sotr = manage.brandVehicle.getText();
                    bookInput.setId(Integer.parseInt(id));
                    bookInput.setTen_Sach(name);
                    bookInput.setMo_ta(mota);
                    bookInput.setIbsn(Ibsn);
                    bookInput.setNgay_xuatban(date);
                    bookInput.setLoai_sach(loai);
                    bookInput.setNha_xuatban(nhaxb);
                    bookInput.setSo_trang(Integer.parseInt(sotr));
                    try {
                        Message message = client.updateBook(bookInput);
                        if (message.getCheck()) {
                            manage.notification(message.getMessage());
                            ArrayList<Book> listVehicle = client.getAllBook();
                            addDataToTableBook(listVehicle);
                            addDataToComboBoxNameVehicleManage(listVehicle);
                            ArrayList<ManageBook> listManageVehicle = client.getAllManageBook();
                            addDataToTableManageVehicle(listManageVehicle);
                        } else {
                            manage.notification(message.getMessage());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        manage.deleteVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Book vehicleInput = new Book();
                String id = manage.idVehicle.getText();
                if (id.equals("")) {
                    manage.notification("Vui lòng chọn sách");
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xoá sách này không?", "Xác nhận xoá",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        vehicleInput.setId(Integer.parseInt(id));

                        try {
                            Message message = client.deleteBook(vehicleInput);
                            if (message.getCheck()) {
                                manage.notification(message.getMessage());
                                ArrayList<Book> listVehicle = client.getAllBook();
                                addDataToTableBook(listVehicle);
                                addDataToComboBoxNameVehicleManage(listVehicle);
                                ArrayList<ManageBook> manageVehicles = client.getAllManageBook();
                                addDataToTableManageVehicle(manageVehicles);
                            } else {
                                manage.notification(message.getMessage());
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
        manage.searchVehicle.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                String search = manage.searchVehicle.getText();
                ArrayList<Book> listVehicle = null;
                try {
                    listVehicle = client.searchBook(search);
                    addDataToTableBook(listVehicle);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        manage.tableVehicle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // get Model from to tableUser
                DefaultTableModel model = (DefaultTableModel) manage.tableVehicle.getModel();
                int row = manage.tableVehicle.getSelectedRow();
                manage.idVehicle.setText(model.getValueAt(row, 0) + "");
                manage.nameVehicle.setText(model.getValueAt(row, 1) + "");
                manage.seat_capacityVehicle.setText(model.getValueAt(row, 2) + "");
                manage.colorVehicle.setText(model.getValueAt(row, 3) + "");
                manage.brandVehicle.setText(model.getValueAt(row, 4) + "");
                manage.typeVehicle.setText(model.getValueAt(row, 5) + "");
                String date = model.getValueAt(row, 6) + "";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    // Chuyển đổi chuỗi ngày thành kiểu Date
                    Date day = dateFormat.parse(date);
                    manage.dateVehicle.setDate(day);

                    // In ngày từ JDateChooser
                    /// System.out.println(dateChooser.getDate());
                } catch (Exception ez) {
                    ez.printStackTrace();
                }
                // date to date format yyyy-mm-dd
                manage.capacityVehicle.setText(model.getValueAt(row, 7) + "");

            }
        });
        /// ================================================================\\
        manage.tableManage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // get Model from to tableUser
                DefaultTableModel model = (DefaultTableModel) manage.tableManage.getModel();
                int row = manage.tableManage.getSelectedRow();
                manage.idManage.setText(model.getValueAt(row, 0) + "");
                manage.nameUserManage.setSelectedItem(model.getValueAt(row, 1) + "");
                String dateUser = model.getValueAt(row, 2) + "";
                try {
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateUser);
                    manage.dateManage.setDate(date);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block

                }
                manage.nameVehicleManage.setSelectedItem(model.getValueAt(row, 3) + "");
                manage.nameManage.setText(model.getValueAt(row, 4) + "");
                manage.chassis_noManage.setText(model.getValueAt(row, 5) + "");
                manage.plateManage.setText(model.getValueAt(row, 6) + "");
                manage.engine_noMange.setText("");

            }
        });
        manage.addManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageBook manageVehicleInput = new ManageBook();
                String id = manage.idManage.getText();
                String nameUser = manage.nameUserManage.getSelectedItem().toString();
                Integer id_user = getIdFromString(nameUser);
                String nameVehicle = manage.nameVehicleManage.getSelectedItem().toString();
                Integer id_vehicle = getIdFromString(nameVehicle);
                String date = ((JTextField) manage.dateManage.getDateEditor().getUiComponent()).getText();
                String tendk = manage.nameManage.getText();
                int soluong = Integer.parseInt(manage.chassis_noManage.getText());
                String borrow_code = manage.plateManage.getText();
                String mamuon = manage.chassis_noManage.getText();

                // manageVehicleInput.setId(Integer.parseInt(id));
                manageVehicleInput.setUser_id(id_user);
                manageVehicleInput.setBook_id(id_vehicle);
                manageVehicleInput.setRegistration_date(date);
                manageVehicleInput.setQuantity(soluong);
                manageVehicleInput.setBook_name(tendk);
                manageVehicleInput.setBorro_code(borrow_code);
                // manageVehicleInput.setBorro_code();

                try {
                    Message message = client.insertManageBook(manageVehicleInput);
                    manage.notification(message.getMessage());
                    ArrayList<ManageBook> listManageVehicle = client.getAllManageBook();
                    addDataToTableManageVehicle(listManageVehicle);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        manage.updateManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageBook manageVehicleInput = new ManageBook();
                String id = manage.idManage.getText();
                String nameUser = manage.nameUserManage.getSelectedItem().toString();
                Integer id_user = getIdFromString(nameUser);
                String nameVehicle = manage.nameVehicleManage.getSelectedItem().toString();
                Integer id_vehicle = getIdFromString(nameVehicle);
                String date = ((JTextField) manage.dateManage.getDateEditor().getUiComponent()).getText();
                String tendk = manage.nameManage.getText();
                int soluong = Integer.parseInt(manage.chassis_noManage.getText());
                String borrow_code = manage.plateManage.getText();
                String mamuon = manage.chassis_noManage.getText();

                manageVehicleInput.setId(Integer.parseInt(id));
                manageVehicleInput.setUser_id(id_user);
                manageVehicleInput.setBook_id(id_vehicle);
                manageVehicleInput.setRegistration_date(date);
                manageVehicleInput.setQuantity(soluong);
                manageVehicleInput.setBook_name(tendk);
                manageVehicleInput.setBorro_code(borrow_code);
                try {
                    Message message = client.updateManageBook(manageVehicleInput);
                    manage.notification(message.getMessage());
                    ArrayList<ManageBook> listManageVehicle = client.getAllManageBook();
                    addDataToTableManageVehicle(listManageVehicle);
                    
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        manage.deleteManage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ManageBook manageVehicle = new ManageBook();
				String id = manage.idManage.getText();
				if (id.equals("")) {
					manage.notification("Vui lòng chọn thông tin");
				} else {
					int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá đăng ký không?", "Xác nhận xoá",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						manageVehicle.setId(Integer.parseInt(id));
						try {
							Message message = client.deleteManageBook(manageVehicle);
                            manage.notification(message.getMessage());
								ArrayList<ManageBook> listManageVehicle = client.getAllManageBook();
								addDataToTableManageVehicle(listManageVehicle);
							
						} catch (Exception ex) {
							throw new RuntimeException(ex);
						}
					}
				}

			}
		});
        	manage.searchManage.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String search = manage.searchManage.getText();
				ArrayList<ManageBook> listManageVehicle = null;
				try {
					listManageVehicle = client.searchManageBook(search);
					addDataToTableManageVehicle(listManageVehicle);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}

			}
		});

    }

    public static int getIdFromString(String str) {
        String[] arr = str.split(" - ");
        return Integer.parseInt(arr[1]);
    }

    public static boolean validateRegister(String email, String password, String name) {
        // check email not empty
        if (email.isEmpty()) {
            register.notification("Email is empty");
            return false;
        }
        // check email format
        if (!validateEmail(email)) {
            register.notification("Email is invalid");
            return false;
        }
        // check password not empty
        if (password.isEmpty()) {
            register.notification("Password is empty");
            return false;
        }
        // check password length
        if (password.length() < 6) {
            register.notification("Password is too short is less than 6 characters");
            return false;
        }
        // check name not empty
        if (name.isEmpty()) {
            register.notification("Name is empty");
            return false;
        }

        return true;
    }

    public static boolean validateEmail(String email) {
        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
    }

    public static void addDataToTableBook(ArrayList<Book> listBook) {
        DefaultTableModel modelBook = new DefaultTableModel();
        Object columns[] = { "ID", " Tên sách ", "Thể loại", "Mô tả ", "số trang", "IBSN", "Ngày xuất bản",
                "Nhà xuất bản" };
        modelBook = new DefaultTableModel(columns, 0);
        for (Book book : listBook) {
            Object[] row = { book.getId(), book.getTen_Sach(), book.getLoai_sach(), book.getMo_ta(), book.getSo_trang(),
                    book.getIbsn(), book.getNgay_xuatban(), book.getNha_xuatban() };
            modelBook.addRow(row);
        }
        manage.tableVehicle.setModel(modelBook);

    }

    public static void addDataToTableUser(ArrayList<Users> listUser) {
        DefaultTableModel modelUser = new DefaultTableModel();
        Object[] columns = { "ID", "Name", "Email", "Password", "Address", "Phone", "Date", "Code", "Role" };
        modelUser = new DefaultTableModel(columns, 0);
        // add data to table
        for (Users user : listUser) {
            Object[] row = { user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAddress(),
                    user.getPhone(), user.getDate(), user.getCode(), user.getRole() };
            modelUser.addRow(row);
        }

        manage.tableUser.setModel(modelUser);
    }

    public static void addDataToComboBoxNameUserManage(ArrayList<Users> listUser) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Users user : listUser) {
            model.addElement(user.getName() + " - " + user.getId());
        }
        manage.nameUserManage.setModel(model);

    }

    public static void addDataToComboBoxNameVehicleManage(ArrayList<Book> listVehicle) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Book book : listVehicle) {

            model.addElement(book.getTen_Sach() + " - " + book.getId());

        }
        manage.nameVehicleManage.setModel(model);
    }

    public static void addDataToTableManageVehicle(ArrayList<ManageBook> manageBooks) {
        DefaultTableModel modelManage = new DefaultTableModel();
        Object columns[] = { "ID", "User ID ", "Date", "Book ID ", "Name Register", "Quantity", "Borrow", "Category" };
        modelManage = new DefaultTableModel(columns, 0);
        // add data to table
        for (ManageBook manageBook : manageBooks) {
            Users user = getUserById(manageBook.getUser_id());
            Book book = getBookbyId(manageBook.getBook_id());
            Object[] row = { manageBook.getId(), user.getName() + "(" + user.getId() + ")",
                    manageBook.getRegistration_date(),
                    manageBook.getBook_id(),
                    manageBook.getBook_name(), manageBook.getQuantity(), manageBook.getBorro_code()
            };
            modelManage.addRow(row);
        }
        makeChart("Tất cả");
        manage.tableManage.setModel(modelManage);
    }

    private static Book getBookbyId(int id) {
        Book book = new Book();
        try {
            book = client.getBookbyId(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return book;
    }

    public static Users getUserById(int id) {
        Users user = null;
        try {
            user = client.getUserById(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return user;
    }

    public static void makeChart(String date) {

        manage.panelChart1.removeAll();
        ChartPanel chartPanel = new ChartPanel(createChart(date));
        chartPanel.setPreferredSize(
                new java.awt.Dimension(manage.panelChart1.getWidth(), manage.panelChart1.getHeight()));

        manage.panelChart1.add(chartPanel);
    }

    public static JFreeChart createChart(String date) {

        String nameChart = "";
        if (date.equals("Tất cả"))
            nameChart = "TỪ TRƯỚC TỚI NAY";
        else
            nameChart = "CỦA NĂM" + date;
        JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ ĐĂNG KÝ " + nameChart, "Tên sách", "Số lượt đăng ký",
                createDataset(date), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public static CategoryDataset createDataset(String date) {
        ArrayList<ManageBook> listVehicleManage = null;
        ArrayList<Book> listVehicle = new ArrayList<>();
        ArrayList<ManageBook> listVehicleManage2 = new ArrayList<>();
        try {
            listVehicleManage = client.getAllManageBook();
            listVehicle = client.getAllBook();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        addDataToDateComboBox(listVehicleManage);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Book vehicle : listVehicle) {
            listVehicleManage2 = new ArrayList<>();
            for (ManageBook manageVehicle : listVehicleManage) {
                if (getYearFromText(manageVehicle.getRegistration_date()).equals(date) || date.equals("Tất cả")) {
                    listVehicleManage2.add(manageVehicle);
                    dataset.addValue(countBook(listVehicleManage2, vehicle.getId()), "Số lượt đăng ký",
                            vehicle.getTen_Sach() + " - " + vehicle.getId());
                }
            }
        }
        return dataset;
    }

    public static int countBook(ArrayList<ManageBook> listVehicle, int vehicleID) {
        int count = 0;

        for (ManageBook manageVehicle : listVehicle) {
            if (manageVehicle.getBook_id() == vehicleID) {
                count++;
            }
        }
        return count;
    }

    public static void addDataToDateComboBox(ArrayList<ManageBook> manageBooks) {
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("Tất cả");
        Vector<String> yearList = new Vector<>();
        for (ManageBook manageBook : manageBooks) {
            String year = getYearFromText(manageBook.getRegistration_date());
            if (!yearList.contains(year)) {
                yearList.add(year);
                boxModel.addElement(year);
            }
        }
        manage.chartYear.setModel(boxModel);
        manage.chartYear.setSelectedIndex(0);

    }

    public static String getYearFromText(String date) {

        String[] dateArr = date.split("-");
        return dateArr[0];

    }
}
