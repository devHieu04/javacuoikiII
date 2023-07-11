package Views;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Register extends JFrame {

    private Container contentPane;
    public JTextField emailRegistration;
    public JPasswordField passwordRegistration;
    public JTextField usernameRegistration;
    public JButton submit_btnRegistration;
    public JButton login_btnRegistration;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 536);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        Color myColor = new Color(0, 155, 171);

        JPanel panelTitle = new JPanel();
        JLabel lbTitle = new JLabel("Registration");
        lbTitle.setFont(lbTitle.getFont().deriveFont(26f));
        lbTitle.setForeground(myColor);
        panelTitle.add(lbTitle);
        contentPane.add(panelTitle);

        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new FlowLayout());
        contentPane.add(panelEmail);
        JLabel lbEmail = new JLabel();
        lbEmail.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/QuanLiXe /Client/src/Icons/gmail.png")); // Replace with the actual path to the email icon
        lbEmail.setFont(lbEmail.getFont().deriveFont(16f));
        panelEmail.add(lbEmail);
        emailRegistration = new JTextField(25);
        emailRegistration.setPreferredSize(new Dimension(20, 35));
        emailRegistration.setOpaque(false);
        emailRegistration.setBorder(new RoundedCornerBorder(10));
        panelEmail.add(emailRegistration);

        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new FlowLayout());
        contentPane.add(panelPassword);
        JLabel lbPassword = new JLabel();
        lbPassword.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/QuanLiXe /Client/src/Icons/password.png")); // Replace with the actual path to the password icon
        lbPassword.setFont(lbEmail.getFont().deriveFont(16f));
        panelPassword.add(lbPassword);
        passwordRegistration = new JPasswordField(25);
        passwordRegistration.setPreferredSize(new Dimension(20, 35));
        passwordRegistration.setOpaque(false);
        passwordRegistration.setBorder(new RoundedCornerBorder(10));
        panelPassword.add(passwordRegistration);

        JPanel panelUsername = new JPanel();
        panelUsername.setLayout(new FlowLayout());
        contentPane.add(panelUsername);
        JLabel lbUsername = new JLabel();
        lbUsername.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JavaCuoiki/Quanlithuvien/Client/src/Icons/profile.png")); // Replace with the actual path to the username icon
        lbUsername.setFont(lbEmail.getFont().deriveFont(16f));
        panelUsername.add(lbUsername);
        usernameRegistration = new JTextField(25);
        usernameRegistration.setPreferredSize(new Dimension(20, 35));
        usernameRegistration.setOpaque(false);
        usernameRegistration.setBorder(new RoundedCornerBorder(10));
        panelUsername.add(usernameRegistration);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        submit_btnRegistration = new JButton("Register");
        submit_btnRegistration.setForeground(myColor);
        submit_btnRegistration.setBackground(myColor);
        submit_btnRegistration.setPreferredSize(new Dimension(155, 36));
        panelButton.add(submit_btnRegistration);
        login_btnRegistration = new JButton("Login");
        login_btnRegistration.setForeground(myColor);
        login_btnRegistration.setBackground(myColor);
        login_btnRegistration.setPreferredSize(new Dimension(155, 36));
        panelButton.add(login_btnRegistration);
        contentPane.add(panelButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void notification(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    class RoundedCornerBorder extends LineBorder {
        private int radius;

        public RoundedCornerBorder(int radius) {
            super(new Color(0, 155, 171), 2, true);
            this.radius = radius;
        }

        @Override
        public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            java.awt.Shape shape = new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius);
            g2d.draw(shape);
            g2d.dispose();
        }
    }
}
