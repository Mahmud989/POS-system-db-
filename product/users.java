// 
// Decompiled by Procyon v0.5.36
// 

package product;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.awt.Graphics2D;
import java.sql.Blob;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileInputStream;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import java.util.Iterator;
import java.util.Set;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import javax.swing.LayoutStyle;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.Color;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Collections;
import product.res.R;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class users extends JFrame
{
    private JLabel LabelForSearchInpt;
    private JButton backButton;
    private JButton clearSearchInput;
    private JTable dataTable;
    private JButton deleteRow;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;
    private JCheckBox jCheckBox4;
    private JCheckBox jCheckBox5;
    private JCheckBox jCheckBox6;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel6;
    private JScrollPane jScrollPane1;
    private JComboBox<String> langBox;
    private JPanel permissionPanel;
    private JLabel photo;
    private JButton photoChangeBTN;
    private JPanel photoPanel;
    private JTextField productInput;
    private JButton saveBTN;
    private JLabel savedLBL;
    private JButton searchBTN;
    private JButton selectRow;
    static int selectedIndex;
    static int selectedRow;
    DefaultTableModel model;
    ArrayList<String> list;
    Map<Integer, ArrayList<Integer>> m;
    static TreeMap<String, String> map;
    
    public users() {
        this.m = new TreeMap<Integer, ArrayList<Integer>>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setUndecorated(true);
        this.setExtendedState(6);
        this.initComponents();
        R.setLanguage("AZ");
        this.savedLBL.setVisible(false);
        this.list = new ArrayList<String>();
        this.model = new DefaultTableModel(new Object[0][], new String[] { "user id", "Name", "Password", "last sign date", "position", "status", "photo" }) {
            boolean[] canEdit = { true, true, true, true, true, true, false, true, true };
            
            @Override
            public boolean isCellEditable(final int i, final int i1) {
                return this.canEdit[i1];
            }
            
            @Override
            public void fireTableCellUpdated(final int row, final int col) {
                super.fireTableCellUpdated(row, col);
                if (users.this.m.containsKey(row)) {
                    final ArrayList<Integer> l = users.this.m.get(row);
                    if (l.indexOf(col) < 0) {
                        l.add(col);
                        Collections.sort(l);
                        users.this.m.put(row, l);
                    }
                }
                else {
                    final ArrayList<Integer> l = new ArrayList<Integer>();
                    l.add(col);
                    users.this.m.put(row, l);
                }
                System.out.println(users.this.m.entrySet());
            }
            
            public void setColEditable(final int col) {
                this.canEdit[col] = true;
            }
        };
        this.dataTable.setModel(this.model);
        this.setUpPositionColumn(this.dataTable, this.dataTable.getColumnModel().getColumn(4));
        this.setUpStatusColumn(this.dataTable, this.dataTable.getColumnModel().getColumn(5));
    }
    
    public users(final TreeMap<String, String> map) {
        this();
        users.map = map;
    }
    
    private void initComponents() {
        this.photoPanel = new JPanel();
        this.photo = new JLabel();
        this.photoChangeBTN = new JButton();
        this.jButton1 = new JButton();
        this.permissionPanel = new JPanel();
        this.jCheckBox1 = new JCheckBox();
        this.jCheckBox2 = new JCheckBox();
        this.jCheckBox3 = new JCheckBox();
        this.jCheckBox4 = new JCheckBox();
        this.jCheckBox5 = new JCheckBox();
        this.jCheckBox6 = new JCheckBox();
        this.jPanel1 = new JPanel();
        this.jPanel3 = new JPanel();
        this.LabelForSearchInpt = new JLabel();
        this.searchBTN = new JButton();
        this.jPanel6 = new JPanel();
        this.clearSearchInput = new JButton();
        this.productInput = new JTextField();
        this.deleteRow = new JButton();
        this.selectRow = new JButton();
        this.jButton2 = new JButton();
        this.savedLBL = new JLabel();
        this.jPanel2 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.dataTable = new JTable();
        this.saveBTN = new JButton();
        this.langBox = new JComboBox<String>();
        this.backButton = new JButton();
        this.jButton3 = new JButton();
        this.jLabel1 = new JLabel();
        this.photoPanel.setLayout((LayoutManager)new AbsoluteLayout());
        this.photo.setBackground(new Color(153, 153, 153));
        this.photoPanel.add(this.photo, new AbsoluteConstraints(0, 0, 280, 280));
        this.photoChangeBTN.setText("change photo");
        this.photoChangeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.photoChangeBTNActionPerformed(evt);
            }
        });
        this.photoPanel.add(this.photoChangeBTN, new AbsoluteConstraints(290, 250, 100, -1));
        this.jButton1.setText("delete photo");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.jButton1ActionPerformed(evt);
            }
        });
        this.photoPanel.add(this.jButton1, new AbsoluteConstraints(290, 213, 100, 30));
        this.photoPanel.getAccessibleContext().setAccessibleDescription("");
        this.jCheckBox1.setText("Users");
        this.jCheckBox2.setText("satisa nezaret");
        this.jCheckBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.jCheckBox2ActionPerformed(evt);
            }
        });
        this.jCheckBox3.setText("Products");
        this.jCheckBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.jCheckBox3ActionPerformed(evt);
            }
        });
        this.jCheckBox4.setText("jCheckBox1");
        this.jCheckBox5.setText("jCheckBox1");
        this.jCheckBox6.setText("jCheckBox1");
        final GroupLayout permissionPanelLayout = new GroupLayout(this.permissionPanel);
        this.permissionPanel.setLayout(permissionPanelLayout);
        permissionPanelLayout.setHorizontalGroup(permissionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(permissionPanelLayout.createSequentialGroup().addGap(20, 20, 20).addGroup(permissionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jCheckBox1).addComponent(this.jCheckBox5).addComponent(this.jCheckBox6).addComponent(this.jCheckBox4).addComponent(this.jCheckBox2).addComponent(this.jCheckBox3)).addContainerGap(290, 32767)));
        permissionPanelLayout.setVerticalGroup(permissionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(permissionPanelLayout.createSequentialGroup().addGap(34, 34, 34).addComponent(this.jCheckBox1).addGap(18, 18, 18).addComponent(this.jCheckBox3).addGap(29, 29, 29).addComponent(this.jCheckBox2).addGap(18, 18, 18).addComponent(this.jCheckBox4).addGap(18, 18, 18).addComponent(this.jCheckBox5).addGap(18, 18, 18).addComponent(this.jCheckBox6).addContainerGap(225, 32767)));
        this.setDefaultCloseOperation(3);
        this.jPanel3.setBorder(BorderFactory.createBevelBorder(0));
        this.jPanel3.setLayout((LayoutManager)new AbsoluteLayout());
        this.LabelForSearchInpt.setText("Istifadeci adin ve ya ID sin daxil et");
        this.jPanel3.add(this.LabelForSearchInpt, new AbsoluteConstraints(20, 30, -1, -1));
        this.searchBTN.setText("Axtar");
        this.searchBTN.setFocusable(false);
        this.searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.searchBTNActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.searchBTN, new AbsoluteConstraints(440, 20, -1, 30));
        this.jPanel6.setLayout(null);
        this.clearSearchInput.setBackground(new Color(255, 255, 255));
        this.clearSearchInput.setFont(new Font("Dialog", 1, 20));
        this.clearSearchInput.setText("X");
        this.clearSearchInput.setFocusable(false);
        this.clearSearchInput.setMaximumSize(new Dimension(49, 35));
        this.clearSearchInput.setMinimumSize(new Dimension(49, 35));
        this.clearSearchInput.setPreferredSize(new Dimension(49, 35));
        this.clearSearchInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.clearSearchInputActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.clearSearchInput);
        this.clearSearchInput.setBounds(170, 0, 50, 34);
        this.productInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.productInputActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.productInput);
        this.productInput.setBounds(0, 0, 220, 35);
        this.jPanel3.add(this.jPanel6, new AbsoluteConstraints(210, 20, 240, 34));
        this.deleteRow.setText("Setir sil");
        this.deleteRow.setFocusable(false);
        this.deleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.deleteRowActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.deleteRow, new AbsoluteConstraints(640, 10, 110, -1));
        this.selectRow.setText("Setri yadda saxla");
        this.selectRow.setEnabled(false);
        this.selectRow.setFocusable(false);
        this.selectRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.selectRowActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.selectRow, new AbsoluteConstraints(640, 70, 112, -1));
        this.jButton2.setText("Setir elave et");
        this.jButton2.setFocusable(false);
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.jButton2ActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.jButton2, new AbsoluteConstraints(640, 40, 112, -1));
        this.savedLBL.setForeground(new Color(255, 51, 51));
        this.savedLBL.setHorizontalAlignment(0);
        this.savedLBL.setText("changes saved");
        this.savedLBL.setToolTipText("");
        this.savedLBL.setHorizontalTextPosition(0);
        this.savedLBL.setOpaque(true);
        this.jPanel3.add(this.savedLBL, new AbsoluteConstraints(210, 65, 290, 30));
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(64, 64, 64).addComponent(this.jPanel3, -1, 792, 32767).addGap(84, 84, 84)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel3, -1, 101, 32767).addContainerGap()));
        this.dataTable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.dataTable.setRowSelectionAllowed(false);
        this.dataTable.getTableHeader().setReorderingAllowed(false);
        this.dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                users.this.dataTableMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.dataTable);
        this.saveBTN.setText("Yadda saxla");
        this.saveBTN.setFocusable(false);
        this.saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.saveBTNActionPerformed(evt);
            }
        });
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1).addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.saveBTN).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(34, 34, 34).addComponent(this.jScrollPane1, -1, 389, 32767).addGap(18, 18, 18).addComponent(this.saveBTN).addContainerGap()));
        this.langBox.setModel(new DefaultComboBoxModel<String>(new String[] { "AZ", "EN", "RU", "TR" }));
        this.langBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.langBoxActionPerformed(evt);
            }
        });
        this.backButton.setText("<--");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.backButtonActionPerformed(evt);
            }
        });
        this.jButton3.setText("X");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                users.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Dialog", 1, 18));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Istifadeci idaresi");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.jPanel2, -1, -1, 32767).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.backButton).addGap(161, 161, 161).addComponent(this.jLabel1, -2, 403, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.langBox, -2, -1, -2).addGap(44, 44, 44).addComponent(this.jButton3).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.langBox, -2, -1, -2).addComponent(this.backButton).addComponent(this.jButton3)).addComponent(this.jLabel1, -2, 27, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -1, -1, 32767)));
        this.pack();
    }
    
    private void searchBTNActionPerformed(final ActionEvent evt) {
        Connection conn = null;
        Statement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            String query = "";
            final int rowCount = this.model.getRowCount();
            for (int i = this.model.getRowCount(); i > 0; --i) {
                this.model.removeRow(this.model.getRowCount() - 1);
            }
            this.dataTable.setModel(this.model);
            this.list.clear();
            this.m.clear();
            query = "SELECT * FROM `product_db`.`users` where `user_id`='" + this.productInput.getText().replace("'", "\\'") + "' or `name` like '%" + this.productInput.getText().replace("'", "\\'") + "%'order by `id` asc ;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final int say = 0;
                rs.previous();
                final int sum = 0;
                while (rs.next()) {
                    this.model.addRow(new Object[] { rs.getString("user_id"), rs.getString("name"), rs.getString("password"), rs.getString("lastsignDate"), rs.getString("position"), rs.getString("status"), "view photo" });
                    this.list.add(rs.getString("id"));
                }
            }
            else {
                JOptionPane.showMessageDialog(null, R.ProductNotFound.getValue());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
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
            catch (SQLException ex3) {}
        }
    }
    
    private void deleteRowActionPerformed(final ActionEvent evt) {
        if (this.dataTable.getSelectedRow() >= 0) {
            Connection conn = null;
            Statement stm = null;
            try {
                final long l = System.currentTimeMillis();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
                stm = conn.createStatement();
                final StringBuffer query = new StringBuffer();
                for (int i = this.dataTable.getSelectedRows().length; i > 0; --i) {
                    if (this.list.get(this.dataTable.getSelectedRow()).equals("")) {
                        this.list.remove(this.dataTable.getSelectedRow());
                        this.model.removeRow(this.dataTable.getSelectedRow());
                    }
                    else {
                        query.append(" `id`='" + this.list.get(this.dataTable.getSelectedRow()) + "' OR");
                        this.list.remove(this.dataTable.getSelectedRow());
                        this.model.removeRow(this.dataTable.getSelectedRow());
                    }
                }
                if (query.length() > 2) {
                    stm.executeUpdate("DELETE FROM `product_db`.`users` WHERE " + query.substring(0, query.length() - 2) + ";");
                }
                this.savedLBL.setText("Changes saved as " + String.format("%.5f", (System.currentTimeMillis() - l) / 1000.0) + " sec");
                this.savedLBL.setVisible(true);
                final long n;
                EventQueue.invokeLater(() -> {
                    try {
                        Thread.sleep(3000L + (System.currentTimeMillis() - n));
                        this.savedLBL.setVisible(false);
                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                });
            }
            catch (SQLException ex2) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex2);
            }
            finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                try {
                    if (stm != null) {
                        stm.close();
                    }
                }
                catch (SQLException ex4) {}
            }
        }
    }
    
    private void selectRowActionPerformed(final ActionEvent evt) {
    }
    
    private void clearSearchInputActionPerformed(final ActionEvent evt) {
        this.productInput.setText("");
    }
    
    private void productInputActionPerformed(final ActionEvent evt) {
        this.searchBTNActionPerformed(evt);
    }
    
    private void saveBTNActionPerformed(final ActionEvent evt) {
        try {
            this.dataTable.getCellEditor().stopCellEditing();
        }
        catch (Exception ex4) {}
        Connection conn = null;
        Statement stm = null;
        try {
            final long l = System.currentTimeMillis();
            boolean yenile = true;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final Set<Integer> a = this.m.keySet();
            for (final Map.Entry<Integer, ArrayList<Integer>> entry : this.m.entrySet()) {
                final StringBuffer q = new StringBuffer();
                final Integer row = entry.getKey();
                final ArrayList<Integer> value = entry.getValue();
                if (this.list.get(row).equals("")) {
                    q.append("'" + this.dataTable.getValueAt(row, 0) + "'");
                    for (int i = 1; i < this.model.getColumnCount(); ++i) {
                        q.append(", '" + this.dataTable.getValueAt(row, i) + "'");
                    }
                    final String query = "INSERT INTO `product_db`.`users` ( `user_id`, `name`, `password`, `lastsignDate`, `position`, `status`,`photo`)  VALUES (" + q.toString() + ");";
                    final int r = stm.executeUpdate(query);
                    if (r >= 0) {
                        continue;
                    }
                    yenile = false;
                }
                else {
                    for (final int j : value) {
                        switch (j) {
                            case 0: {
                                q.append(", `user_id`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 1: {
                                q.append(", `name`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 2: {
                                q.append(", `password`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 3: {
                                q.append(", `lastsignDate`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 4: {
                                q.append(", `position`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 5: {
                                q.append(", `status`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 6: {
                                q.append(", `photo`='" + this.dataTable.getValueAt(row, j) + "' ");
                                continue;
                            }
                        }
                    }
                    q.deleteCharAt(0);
                    final String query = "UPDATE `product_db`.`users` SET " + q.toString() + " WHERE `id`='" + this.list.get(row) + "';";
                    final int r = stm.executeUpdate(query);
                    if (r >= 0) {
                        continue;
                    }
                    yenile = false;
                }
            }
            if (yenile) {
                this.searchBTNActionPerformed(evt);
            }
            this.savedLBL.setText(String.format("The changes were saved within %.5f seconds", (System.currentTimeMillis() - l) / 1000.0));
            this.savedLBL.setVisible(true);
            final long n;
            EventQueue.invokeLater(() -> {
                try {
                    Thread.sleep(3000L + (System.currentTimeMillis() - n));
                    this.savedLBL.setVisible(false);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            });
        }
        catch (SQLException ex2) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex2);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex3) {
                ex3.printStackTrace();
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            }
            catch (SQLException ex5) {}
        }
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        this.model.addRow(new Object[] { "", "", "", "", "Seller", "offline", "" });
        this.list.add("");
    }
    
    private void langBoxActionPerformed(final ActionEvent evt) {
        this.changeLanguage();
    }
    
    private void backButtonActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        System.exit(0);
    }
    
    private void dataTableMouseClicked(final MouseEvent evt) {
        try {
            if (this.dataTable.getSelectedColumn() == 6) {
                if (this.dataTable.getSelectedRow() == users.selectedRow) {
                    if (!this.list.get(users.selectedRow).equals("")) {
                        this.photo.setIcon(this.loadImage(this.list.get(users.selectedRow), 280, 280));
                        JOptionPane.showMessageDialog(this, this.photoPanel, "", -1);
                    }
                    users.selectedRow = -1;
                }
                else {
                    users.selectedRow = this.dataTable.getSelectedRow();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void photoChangeBTNActionPerformed(final ActionEvent evt) {
        FileInputStream fis = null;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            final JFileChooser chooser = new JFileChooser();
            final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", new String[] { "jpg", "jpeg", "png" });
            chooser.setFileFilter(filter);
            final int returnVal = chooser.showOpenDialog(this);
            if (returnVal == 0) {
                final File image = new File(chooser.getSelectedFile().getAbsolutePath());
                fis = new FileInputStream(image);
                final String sql = "UPDATE `product_db`.`users` SET `photo`=? WHERE `id`='" + this.list.get(users.selectedRow) + "';";
                stm = (PreparedStatement)conn.prepareStatement(sql);
                stm.setBinaryStream(1, (InputStream)fis, (int)image.length());
                stm.executeUpdate();
                this.photo.setIcon(this.loadImage(this.list.get(users.selectedRow), 280, 280));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (FileNotFoundException ex2) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex2);
        }
        finally {
            try {
                conn.close();
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException ex3) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex3);
            }
            catch (SQLException ex4) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex4);
            }
        }
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            final String sql = "UPDATE `product_db`.`users` SET `photo`=? WHERE `id`='" + this.list.get(users.selectedRow) + "';";
            stm = (PreparedStatement)conn.prepareStatement(sql);
            stm.setString(1, "");
            stm.executeUpdate();
            this.photo.setIcon(this.loadImage(this.list.get(users.selectedRow), 280, 280));
        }
        catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException ex2) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
    }
    
    private void jCheckBox3ActionPerformed(final ActionEvent evt) {
    }
    
    private void jCheckBox2ActionPerformed(final ActionEvent evt) {
    }
    
    private ImageIcon loadImage(final String id, final int width, final int height) {
        ImageIcon imageIcon = null;
        Connection conn = null;
        Statement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final ResultSet rs = stm.executeQuery("SELECT * FROM `product_db`.`users` where `id`='" + id + "';");
            if (rs.next()) {
                final Blob blob = rs.getBlob("photo");
                final byte[] b = blob.getBytes(1L, (int)blob.length());
                final BufferedImage img = ImageIO.read(new ByteArrayInputStream(b));
                final BufferedImage bufferedImage = new BufferedImage(width, height, 3);
                final Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.drawImage(img, 0, 0, width, height, null);
                graphics2D.dispose();
                imageIcon = new ImageIcon(bufferedImage);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex2) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex2);
        }
        finally {
            try {
                stm.close();
            }
            catch (SQLException ex3) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex3);
            }
            try {
                conn.close();
            }
            catch (SQLException ex3) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex3);
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
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new users().setVisible(true);
            }
        });
    }
    
    public void setUpPositionColumn(final JTable table, final TableColumn sportColumn) {
        final JComboBox comboBox = new JComboBox();
        comboBox.addItem("Seller");
        comboBox.addItem("Accountant");
        comboBox.addItem("Administrator");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
    
    public void setUpStatusColumn(final JTable table, final TableColumn sportColumn) {
        final JComboBox comboBox = new JComboBox();
        comboBox.addItem("Online");
        comboBox.addItem("Offline");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
    
    private void changeLanguage() {
        if (users.selectedIndex != this.langBox.getSelectedIndex()) {
            users.selectedIndex = this.langBox.getSelectedIndex();
            R.setLanguage(this.langBox.getItemAt(users.selectedIndex));
        }
    }
    
    static {
        users.selectedIndex = -1;
        users.selectedRow = -1;
    }
}
