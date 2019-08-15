// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.awt.Graphics2D;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.TreeMap;
import javax.swing.JFrame;

public class AdminFrame extends JFrame
{
    static TreeMap<String, String> map;
    static int SelectedIndex;
    private JButton ProductBTN;
    private JButton QaimeBTN;
    private JButton SaleBTN;
    private JButton SaledProductBTN;
    private JLabel id;
    private JLabel idLabel;
    private JButton jButton4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JLabel name;
    private JLabel nameLabel;
    private JButton saleBTN;
    private JButton userBTN;
    
    public AdminFrame(final TreeMap<String, String> map) {
        this();
        AdminFrame.map = map;
        this.name.setText(map.get("name"));
        this.id.setText(map.get("user_id"));
        this.jLabel1.setIcon(this.load());
        if (map.get("permission").indexOf("users") >= 0) {
            this.userBTN.setEnabled(true);
        }
    }
    
    private AdminFrame() {
        this.initComponents();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10L);
                    AdminFrame.this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - AdminFrame.this.getWidth() / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - AdminFrame.this.getHeight() / 2);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.SaleBTN = new JButton();
        this.userBTN = new JButton();
        this.SaledProductBTN = new JButton();
        this.saleBTN = new JButton();
        this.ProductBTN = new JButton();
        this.jButton4 = new JButton();
        this.nameLabel = new JLabel();
        this.name = new JLabel();
        this.idLabel = new JLabel();
        this.id = new JLabel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.QaimeBTN = new JButton();
        this.setDefaultCloseOperation(3);
        this.setUndecorated(true);
        this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
        this.SaleBTN.setText("Satisa nezaret");
        this.SaleBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.SaleBTNActionPerformed(evt);
            }
        });
        this.userBTN.setText("Istifadeci paneli");
        this.userBTN.setEnabled(false);
        this.userBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.userBTNActionPerformed(evt);
            }
        });
        this.SaledProductBTN.setText("Satilan mallar");
        this.SaledProductBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.SaledProductBTNActionPerformed(evt);
            }
        });
        this.saleBTN.setText("Satis ekrani");
        this.saleBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.saleBTNActionPerformed(evt);
            }
        });
        this.ProductBTN.setText("Mehsul idaresi");
        this.ProductBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.ProductBTNActionPerformed(evt);
            }
        });
        this.jButton4.setText("X");
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.jButton4ActionPerformed(evt);
            }
        });
        this.nameLabel.setText("Istifadeci adi");
        this.name.setText("jLabel2");
        this.idLabel.setText("Istifadeci ID");
        this.id.setText("jLabel2");
        this.jLabel2.setText("Istifadeci sekli");
        this.QaimeBTN.setText("Qaime Panel");
        this.QaimeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AdminFrame.this.QaimeBTNActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(22, 22, 22).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.idLabel).addComponent(this.nameLabel)).addGap(27, 27, 27).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.id).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.saleBTN, -2, 130, -2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.name).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton4)))).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel1, -1, -1, 32767).addComponent(this.jLabel2, -2, 150, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 298, 32767).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.SaleBTN, GroupLayout.Alignment.TRAILING, -2, 130, -2).addComponent(this.SaledProductBTN, GroupLayout.Alignment.TRAILING, -2, 130, -2).addComponent(this.userBTN, GroupLayout.Alignment.TRAILING, -2, 130, -2).addComponent(this.QaimeBTN, GroupLayout.Alignment.TRAILING, -2, 130, -2))).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.ProductBTN, -2, 130, -2))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(21, 21, 21).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.nameLabel).addComponent(this.name))).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton4))).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.idLabel).addComponent(this.id)).addComponent(this.saleBTN, -2, 40, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.ProductBTN, -2, 40, -2).addGap(5, 5, 5).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel1, -2, 150, -2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.SaleBTN, -2, 40, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.QaimeBTN, -2, 40, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.SaledProductBTN, -2, 40, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.userBTN, -2, 40, -2))).addGap(38, 38, 38)));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        this.pack();
    }
    
    private void saleBTNActionPerformed(final ActionEvent evt) {
        new index(AdminFrame.map).setVisible(true);
    }
    
    private void ProductBTNActionPerformed(final ActionEvent evt) {
        new products(AdminFrame.map).setVisible(true);
    }
    
    private void userBTNActionPerformed(final ActionEvent evt) {
        new users(AdminFrame.map).setVisible(true);
    }
    
    private void SaleBTNActionPerformed(final ActionEvent evt) {
        new satisidaresi(AdminFrame.map).setVisible(true);
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) {
        System.exit(0);
    }
    
    private void SaledProductBTNActionPerformed(final ActionEvent evt) {
        new muhasibat(AdminFrame.map).setVisible(true);
    }
    
    private void QaimeBTNActionPerformed(final ActionEvent evt) {
        new qaime_panel().setVisible(true);
    }
    
    private ImageIcon load() {
        Connection conn = null;
        Statement stm = null;
        ImageIcon imageIcon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final ResultSet rs = stm.executeQuery("SELECT * FROM `product_db`.`users` where `user_id`='" + AdminFrame.map.get("user_id") + "';");
            if (rs.next()) {
                final Blob blob = rs.getBlob("photo");
                final byte[] b = blob.getBytes(1L, (int)blob.length());
                final BufferedImage img = ImageIO.read(new ByteArrayInputStream(b));
                final BufferedImage bufferedImage = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), 3);
                final Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.drawImage(img, 0, 0, this.jLabel1.getWidth(), this.jLabel1.getHeight(), null);
                graphics2D.dispose();
                imageIcon = new ImageIcon(bufferedImage);
                this.jLabel1.setIcon(imageIcon);
            }
        }
        catch (ClassNotFoundException | SQLException ex5) {
            final Exception ex4;
            final Exception ex = ex4;
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex2) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex2);
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException ex3) {
                Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex3);
            }
        }
        return imageIcon;
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
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminFrame((AdminFrame$1)null).setVisible(true);
            }
        });
    }
    
    static {
        AdminFrame.SelectedIndex = -1;
    }
}
