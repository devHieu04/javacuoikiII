package Views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Login extends JFrame {

    private Container contentPane;
    private JPanel panel;
    private JLabel lbShow;
    public JButton submit_btnLogin;
    public JButton reg_btnLogin;
    public JTextField emailLogin;
    public JPasswordField passwordLogin;
    public JLabel lblSignIn;
    public JLabel lblForgotPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 536);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        // contentPane = new JPanel();
        // contentPane.setBackground(new Color(82, 177, 226));
        // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        // setContentPane(contentPane);
        // contentPane.setLayout(null);
        Color myColor = new Color(0, 155, 171);

        JPanel panelTitle = new JPanel();
        // panelTitle.setLayout(new BorderLayout());
        JLabel lbTitle = new JLabel("Log In");
        lbTitle.setFont(lbTitle.getFont().deriveFont(26f));

        lbTitle.setForeground(myColor);
        panelTitle.add(lbTitle);
        contentPane.add(panelTitle);

        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new FlowLayout());
        contentPane.add(panelEmail);
        JLabel lbEmail = new JLabel();
        lbEmail.setIcon(
                new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/QuanLiXe /Client/src/Icons/gmail.png"));
        lbEmail.setFont(lbEmail.getFont().deriveFont(16f));
        panelEmail.add(lbEmail);
        emailLogin = new JTextField(25);
        emailLogin.setPreferredSize(new Dimension(20, 35));
        emailLogin.setOpaque(false); // Đặt nền trong suốt cho JTextField
        emailLogin.setBorder(new RoundedCornerBorder(10));
       JLabel lbNo= new JLabel(
                new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/QuanLiXe /Client/src/Icons/no.png"));
               // Sử dụng RoundedCornerBorder để bo góc
        panelEmail.add(emailLogin);
         panelEmail.add(lbNo);
        //panelEmail.add(emailLogin);

        JPanel panelPassword = new JPanel();

        panelPassword.setLayout(new FlowLayout());
        contentPane.add(panelPassword);
        JLabel lbPassword = new JLabel();
        lbPassword.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/QuanLiXe /Client/src/Icons/password.png"));
        lbPassword.setFont(lbEmail.getFont().deriveFont(16f));
        panelPassword.add(lbPassword);
        passwordLogin = new JPasswordField(25);
        passwordLogin.setPreferredSize(new Dimension(20, 35));
        passwordLogin.setOpaque(false);
        passwordLogin.setBorder(new RoundedCornerBorder(10));
        lbShow = new JLabel(
                new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/QuanLiXe /Client/src/Icons/hide.png"));
        panelPassword.add(passwordLogin);
        panelPassword.add(lbShow);

       lbPassword.setPreferredSize(lbEmail.getPreferredSize());

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        submit_btnLogin = new JButton("Login");
        submit_btnLogin.setForeground(myColor);
        submit_btnLogin.setBackground(myColor);
        submit_btnLogin.setPreferredSize(new Dimension(155, 36));
        panelButton.add(submit_btnLogin);
        reg_btnLogin = new JButton("Register");
        reg_btnLogin.setForeground(myColor);
        reg_btnLogin.setBackground(myColor);
        reg_btnLogin.setPreferredSize(new Dimension(155, 36));
        panelButton.add(reg_btnLogin);
        contentPane.add(panelButton); 

        // JPanel panelForgot = new JPanel();
        // //panelForgot.setLayout(null);
        // lblForgotPassword= new JLabel("Forgot password?");
        // panelForgot.add(lblForgotPassword);
        // contentPane.add(panelForgot);

        lbShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ex) {
                // Khi nhấp chuột vào JLabel
                // Kiểm tra hiển thị mật khẩu
                if (passwordLogin.getEchoChar() != '\0') {
                    // Nếu đang ẩn, hiển thị mật khẩu
                    passwordLogin.setEchoChar('\0');
                } else {
                    // Nếu đang hiển thị, ẩn mật khẩu
                    passwordLogin.setEchoChar('*');
                }
            }
        });
        // lblForgotPassword = new JLabel("Forgot Password");
        // lblForgotPassword.setForeground(new Color(65, 194, 203));
        // lblForgotPassword.setFont(new Font("Roboto", Font.PLAIN, 15));
        // lblForgotPassword.setBounds(164, 329, 121, 18);
        // panel.add(lblForgotPassword);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void notification(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

}

class RoundedCornerBorder extends LineBorder {
    private int radius;

    public RoundedCornerBorder(int radius) {
        // Color myColor = new Color(0, 155, 171);
        super(new Color(0, 155, 171), 2, true); // Đặt màu viền và độ dày viền
        this.radius = radius;
    }

    @Override
    public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        // Tạo hình chữ nhật bo góc
        java.awt.Shape shape = new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius);
        g2d.draw(shape);
        g2d.dispose();
    }
}

