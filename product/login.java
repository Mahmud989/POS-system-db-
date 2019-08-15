// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import product.res.R;
import java.util.TreeMap;
import java.sql.DriverManager;
import javax.swing.GroupLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class login extends JFrame
{
    static int SelectedIndex;
    private JButton close;
    private JLabel jLabel1;
    private JComboBox<String> langBox;
    private JButton loginBTN;
    private JPanel loginPanel;
    private JPasswordField password;
    private JLabel userLogin;
    private JLabel userPassword;
    private JTextField username;
    
    public login() {
        this.initComponents();
        this.changeLanguage();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10L);
                    login.this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - login.this.loginPanel.getWidth() / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - login.this.loginPanel.getHeight() / 2);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    
    private void initComponents() {
        this.loginPanel = new JPanel();
        this.userLogin = new JLabel();
        this.username = new JTextField();
        this.loginBTN = new JButton();
        this.close = new JButton();
        this.langBox = new JComboBox<String>();
        this.userPassword = new JLabel();
        this.password = new JPasswordField();
        this.jLabel1 = new JLabel();
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(final WindowEvent evt) {
                login.this.formWindowOpened(evt);
            }
        });
        this.loginPanel.setBorder(BorderFactory.createTitledBorder(null, "Login Panel", 2, 2, new Font("Tahoma", 0, 14), new Color(102, 102, 102)));
        this.loginPanel.setLayout((LayoutManager)new AbsoluteLayout());
        this.userLogin.setHorizontalAlignment(4);
        this.userLogin.setText("jLabel1");
        this.loginPanel.add(this.userLogin, new AbsoluteConstraints(20, 50, 150, -1));
        this.loginPanel.add(this.username, new AbsoluteConstraints(180, 50, 143, 28));
        this.loginBTN.setText("Login");
        this.loginBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                login.this.loginBTNActionPerformed(evt);
            }
        });
        this.loginPanel.add(this.loginBTN, new AbsoluteConstraints(290, 170, -1, -1));
        this.close.setText("close");
        this.close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                login.this.closeActionPerformed(evt);
            }
        });
        this.loginPanel.add(this.close, new AbsoluteConstraints(130, 170, -1, -1));
        this.langBox.setModel(new DefaultComboBoxModel<String>(new String[] { "AZ", "EN", "RU", "TR" }));
        this.langBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                login.this.langBoxActionPerformed(evt);
            }
        });
        this.loginPanel.add(this.langBox, new AbsoluteConstraints(360, 10, -1, -1));
        this.userPassword.setHorizontalAlignment(4);
        this.userPassword.setText("jLabel1");
        this.loginPanel.add(this.userPassword, new AbsoluteConstraints(20, 120, 150, -1));
        this.password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                login.this.passwordActionPerformed(evt);
            }
        });
        this.loginPanel.add(this.password, new AbsoluteConstraints(180, 110, 140, 30));
        this.jLabel1.setText("version 1.0.3");
        this.loginPanel.add(this.jLabel1, new AbsoluteConstraints(10, 180, 80, -1));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.loginPanel, -2, -1, -2));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.loginPanel, -2, 213, -2));
        this.pack();
    }
    
    private void closeActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void langBoxActionPerformed(final ActionEvent evt) {
        this.changeLanguage();
    }
    
    private void loginBTNActionPerformed(final ActionEvent evt) {
        Connection conn = null;
        Statement stm = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final String user = this.username.getText().replace("\\", "\\\\");
            final String pass = String.valueOf(this.password.getPassword()).replace("'", "\\'");
            String query = "";
            query = "SELECT * FROM `product_db`.`users` where (`user_id`=('" + user + "') or `name`=('" + user + "') )and `password`='" + pass + "' ;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final TreeMap<String, String> map = new TreeMap<String, String>();
                if (rs.getString("password").equals(pass) && (rs.getString("name").equals(user) || rs.getString("user_id").equals(user))) {
                    map.put("id", rs.getString("id"));
                    map.put("user_id", rs.getString("user_id"));
                    map.put("name", rs.getString("name"));
                    map.put("password", rs.getString("password"));
                    map.put("position", rs.getString("position"));
                    map.put("permission", rs.getString("permission"));
                    if (rs.getString("position").equalsIgnoreCase("Administrator")) {
                        new AdminFrame(map).setVisible(true);
                    }
                    else {
                        new index(map).setVisible(true);
                    }
                    this.dispose();
                }
            }
            else {
                JOptionPane.showMessageDialog(this, R.loginErrorMsg.getValue());
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, R.ServerConnectionErr.getValue());
            ex.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex2) {}
            try {
                if (stm != null) {
                    stm.close();
                }
            }
            catch (SQLException ex3) {}
        }
    }
    
    private void formWindowOpened(final WindowEvent evt) {
    }
    
    private void passwordActionPerformed(final ActionEvent evt) {
        this.loginBTNActionPerformed(evt);
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    
    private void changeLanguage() {
        if (login.SelectedIndex != this.langBox.getSelectedIndex()) {
            login.SelectedIndex = this.langBox.getSelectedIndex();
            R.setLanguage(this.langBox.getItemAt(login.SelectedIndex));
            this.userLogin.setText(R.loginLabel.getValue());
            this.userPassword.setText(R.loginPassword.getValue());
            this.loginBTN.setText(R.loginBTN.getValue());
            this.close.setText(R.closeBTN.getValue());
        }
    }
    
    static {
        login.SelectedIndex = -1;
    }
}
