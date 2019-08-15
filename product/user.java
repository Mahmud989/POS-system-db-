// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import product.res.R;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class user extends JFrame
{
    DefaultTableModel model;
    ArrayList<String> user_id;
    private JComboBox<String> SearchBy;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JPanel jPanel2;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane1;
    private JButton searchBTN;
    private JTable table;
    private JTextField userInput;
    
    public user() {
        this.user_id = new ArrayList<String>();
        this.initComponents();
        this.model = new DefaultTableModel(new Object[0][], new String[] { "user_id", "name", "password", "position", "online", "photo" });
        this.table.setModel(this.model);
    }
    
    private void initComponents() {
        this.jPanel2 = new JPanel();
        this.userInput = new JTextField();
        this.searchBTN = new JButton();
        this.SearchBy = new JComboBox<String>();
        this.jPanel8 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.table = new JTable();
        this.jPanel9 = new JPanel();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();
        this.jButton7 = new JButton();
        this.setDefaultCloseOperation(3);
        this.searchBTN.setText("Search");
        this.searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                user.this.searchBTNActionPerformed(evt);
            }
        });
        this.SearchBy.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(29, 29, 29).addComponent(this.SearchBy, -2, -1, -2).addGap(18, 18, 18).addComponent(this.userInput, -2, 157, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.searchBTN).addContainerGap(-1, 32767)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(21, 21, 21).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.userInput, -2, 33, -2).addComponent(this.searchBTN).addComponent(this.SearchBy, -2, -1, -2)).addContainerGap(26, 32767)));
        this.table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.table.setColumnSelectionAllowed(true);
        this.table.getTableHeader().setReorderingAllowed(false);
        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                user.this.tableMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.table);
        this.table.getColumnModel().getSelectionModel().setSelectionMode(0);
        this.jButton5.setText("delete user");
        this.jButton6.setText("Add user");
        this.jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                user.this.jButton6ActionPerformed(evt);
            }
        });
        this.jButton7.setText("Save user");
        final GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
        this.jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addGap(23, 23, 23).addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButton5, -1, -1, 32767).addComponent(this.jButton6, -1, -1, 32767).addComponent(this.jButton7, -1, -1, 32767)).addContainerGap(114, 32767)));
        jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton5).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton6).addGap(18, 18, 18).addComponent(this.jButton7).addContainerGap(-1, 32767)));
        final GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
        this.jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addComponent(this.jScrollPane1, -1, 743, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel9, -2, -1, -2)));
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 504, 32767).addComponent(this.jPanel9, -1, -1, 32767));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel8, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel8, -1, -1, 32767)));
        this.pack();
    }
    
    private void searchBTNActionPerformed(final ActionEvent evt) {
        switch (this.SearchBy.getSelectedIndex()) {
            case 0: {
                this.searchById();
                break;
            }
            default: {
                this.searchById();
                break;
            }
        }
    }
    
    private void jButton6ActionPerformed(final ActionEvent evt) {
        this.model.addRow(new Object[0]);
    }
    
    private void tableMouseClicked(final MouseEvent evt) {
    }
    
    void searchById() {
        this.user_id = new ArrayList<String>();
        Connection conn = null;
        Statement stm = null;
        try {
            this.model = new DefaultTableModel(new Object[0][], new String[] { "user_id", "name", "password", "position", "online", "photo" }) {
                @Override
                public boolean isCellEditable(final int i, final int i1) {
                    return i1 != 5;
                }
            };
            this.table.setModel(this.model);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            String query = "";
            query = "SELECT * FROM `product_db`.`users` where `user_id` like '%" + this.userInput.getText().replace("'", "\\'") + "%'";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final int say = 0;
                rs.previous();
                while (rs.next()) {
                    this.user_id.add(rs.getString("id"));
                    this.model.addRow(new Object[] { rs.getString("user_id"), rs.getString("name"), rs.getString("password"), rs.getString("position"), rs.getString("online"), "view photo" });
                }
            }
        }
        catch (ClassNotFoundException ex3) {}
        catch (SQLException ex4) {}
        catch (NumberFormatException ex5) {}
        catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, R.ServerConnectionErr.getValue());
            ex.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            }
            catch (SQLException ex6) {}
        }
    }
    
    void searchByName() {
    }
    
    void searchByPosition() {
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
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new user().setVisible(true);
            }
        });
    }
}
