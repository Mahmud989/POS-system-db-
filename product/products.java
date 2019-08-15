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
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.BorderFactory;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Collections;
import product.res.R;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class products extends JFrame
{
    DefaultTableModel model;
    ArrayList<String> list;
    Map<Integer, ArrayList<Integer>> m;
    static int selectedIndex;
    static TreeMap<String, String> map;
    private JLabel LabelForSearchInpt;
    private JButton backButton;
    private JButton clearSearchInput;
    private JButton deleteRow;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel6;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JComboBox<String> langBox;
    private JTextField productInput;
    private JButton saveBTN;
    private JLabel savedLBL;
    private JButton searchBTN;
    private JButton selectRow;
    
    public products() {
        this.m = new TreeMap<Integer, ArrayList<Integer>>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setUndecorated(true);
        this.setExtendedState(6);
        this.initComponents();
        R.setLanguage("AZ");
        this.savedLBL.setVisible(false);
        this.list = new ArrayList<String>();
        this.model = new DefaultTableModel(new Object[0][], new String[] { "product_code", "barcode", "type", "desc", "name", "alis_qiymeti", "price", "unit", "%", "currency", "satis miqdari", "Anbar miqdari", "Xetali Mehsul Miqdari" }) {
            boolean[] canEdit = { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };
            
            @Override
            public Class<?> getColumnClass(final int i) {
                return super.getColumnClass(i);
            }
            
            @Override
            public boolean isCellEditable(final int i, final int i1) {
                return this.canEdit[i1];
            }
            
            @Override
            public void fireTableCellUpdated(final int row, final int col) {
                super.fireTableCellUpdated(row, col);
                if (products.this.m.containsKey(row)) {
                    final ArrayList<Integer> l = products.this.m.get(row);
                    if (l.indexOf(col) < 0) {
                        l.add(col);
                        Collections.sort(l);
                        products.this.m.put(row, l);
                    }
                }
                else {
                    final ArrayList<Integer> l = new ArrayList<Integer>();
                    l.add(col);
                    products.this.m.put(row, l);
                }
            }
            
            public void setColEditable(final int col) {
                this.canEdit[col] = true;
            }
        };
        this.jTable1.setModel(this.model);
        this.setUpUnitColumn(this.jTable1, this.jTable1.getColumnModel().getColumn(this.model.findColumn("unit")));
        this.setUpCurrencyColumn(this.jTable1, this.jTable1.getColumnModel().getColumn(this.model.findColumn("currency")));
    }
    
    public products(final TreeMap<String, String> map) {
        this();
        products.map = map;
    }
    
    private void initComponents() {
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
        this.jTable1 = new JTable();
        this.saveBTN = new JButton();
        this.langBox = new JComboBox<String>();
        this.backButton = new JButton();
        this.jButton3 = new JButton();
        this.jLabel1 = new JLabel();
        this.setDefaultCloseOperation(3);
        this.jPanel3.setBorder(BorderFactory.createBevelBorder(0));
        this.jPanel3.setLayout((LayoutManager)new AbsoluteLayout());
        this.LabelForSearchInpt.setText("Mehsulun barkodu ve ya adi");
        this.jPanel3.add(this.LabelForSearchInpt, new AbsoluteConstraints(14, 19, -1, -1));
        this.searchBTN.setText("Axtar");
        this.searchBTN.setFocusable(false);
        this.searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.searchBTNActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.searchBTN, new AbsoluteConstraints(440, 20, -1, -1));
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
                products.this.clearSearchInputActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.clearSearchInput);
        this.clearSearchInput.setBounds(170, 0, 50, 34);
        this.productInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.productInputActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.productInput);
        this.productInput.setBounds(0, 0, 220, 35);
        this.jPanel3.add(this.jPanel6, new AbsoluteConstraints(210, 20, 240, 34));
        this.deleteRow.setText("Setr(ler)i sil");
        this.deleteRow.setFocusable(false);
        this.deleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.deleteRowActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.deleteRow, new AbsoluteConstraints(640, 10, 110, -1));
        this.selectRow.setText("Setri yadda saxla");
        this.selectRow.setFocusable(false);
        this.selectRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.selectRowActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.selectRow, new AbsoluteConstraints(640, 70, 112, -1));
        this.jButton2.setText("Setir elave et");
        this.jButton2.setFocusable(false);
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.jButton2ActionPerformed(evt);
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
        this.jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }) {
            Class[] types = { Object.class, Double.class, String.class, Object.class };
            
            @Override
            public Class getColumnClass(final int columnIndex) {
                return this.types[columnIndex];
            }
        });
        this.jTable1.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane1.setViewportView(this.jTable1);
        this.saveBTN.setText("Yadda saxla");
        this.saveBTN.setFocusable(false);
        this.saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.saveBTNActionPerformed(evt);
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
                products.this.langBoxActionPerformed(evt);
            }
        });
        this.backButton.setText("<--");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.backButtonActionPerformed(evt);
            }
        });
        this.jButton3.setText("X");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                products.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Dialog", 1, 18));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Mehsul idaresi");
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
            this.jTable1.setModel(this.model);
            this.jTable1.setShowGrid(true);
            this.list.clear();
            this.m.clear();
            query = "SELECT * FROM `product_db`.`products` where `barcode`='" + this.productInput.getText().replace("'", "\\'") + "' or `name` like '%" + this.productInput.getText().replace("'", "\\'") + "%'order by `id` asc ;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final int say = 0;
                rs.previous();
                final int sum = 0;
                while (rs.next()) {
                    this.model.addRow(new Object[] { rs.getString("product_code"), rs.getString("barcode"), rs.getString("type"), rs.getString("desc"), rs.getString("name"), rs.getString("alis_qiymeti"), rs.getString("price"), rs.getString("unit"), rs.getString("commission"), rs.getString("currency"), rs.getString("saled_count"), rs.getString("stock_count"), rs.getString("xetali_mehsul_sayi") });
                    this.list.add(rs.getString("id"));
                }
            }
            else {
                JOptionPane.showMessageDialog(null, R.ProductNotFound.getValue());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
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
        if (this.jTable1.getSelectedRow() >= 0) {
            Connection conn = null;
            Statement stm = null;
            try {
                final long l = System.currentTimeMillis();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
                stm = conn.createStatement();
                final StringBuffer query = new StringBuffer();
                for (int i = this.jTable1.getSelectedRows().length; i > 0; --i) {
                    if (this.list.get(this.jTable1.getSelectedRow()).equals("")) {
                        this.list.remove(this.jTable1.getSelectedRow());
                        this.model.removeRow(this.jTable1.getSelectedRow());
                    }
                    else {
                        query.append("OR `id`='" + this.list.get(this.jTable1.getSelectedRow()) + "' ");
                        this.list.remove(this.jTable1.getSelectedRow());
                        this.model.removeRow(this.jTable1.getSelectedRow());
                    }
                }
                if (query.length() > 2) {
                    stm.executeUpdate("DELETE FROM `product_db`.`products` WHERE " + query.substring(2) + ";");
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
                        Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                });
            }
            catch (SQLException ex2) {
                Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex2);
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
            this.jTable1.getCellEditor().stopCellEditing();
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
                    q.append("'" + this.jTable1.getValueAt(row, 0) + "'");
                    for (int i = 1; i < this.model.getColumnCount(); ++i) {
                        q.append(", '" + this.jTable1.getValueAt(row, i) + "'");
                    }
                    final String query = "INSERT INTO `product_db`.`products` ( `product_code`, `barcode, `type`, `desc`, `name`, `alis_qiymeti`,`price`, `unit`, `comission`, `currency`, `saled_count`, `stock_count`,`xetali_mehsul_sayi`) VALUES (" + q.toString() + ");";
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
                                q.append(", `product_code`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 1: {
                                q.append(", `barcode`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 2: {
                                q.append(", `type`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 3: {
                                q.append(", `desc`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 4: {
                                q.append(", `name`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 5: {
                                q.append(", `alis_qiymeti`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 6: {
                                q.append(", `price`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 7: {
                                q.append(", `unit`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 8: {
                                q.append(", `commission`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 9: {
                                q.append(", `currency`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 10: {
                                q.append(", `saled_count`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 11: {
                                q.append(", `stock_count`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                            case 12: {
                                q.append(", `xetali_mehsul_sayi`='" + this.jTable1.getValueAt(row, j) + "' ");
                                continue;
                            }
                        }
                    }
                    q.deleteCharAt(0);
                    final String query = "UPDATE `product_db`.`products` SET " + q.toString() + " WHERE `id`='" + this.list.get(row) + "';";
                    final int r = stm.executeUpdate(query);
                    if (r >= 0) {
                        continue;
                    }
                    yenile = false;
                }
            }
            if (yenile) {}
            this.savedLBL.setText("Changes saved as " + String.format("%.5f", (System.currentTimeMillis() - l) / 1000.0) + " sec");
            this.savedLBL.setVisible(true);
            final long n;
            EventQueue.invokeLater(() -> {
                try {
                    Thread.sleep(3000L + (System.currentTimeMillis() - n));
                    this.savedLBL.setVisible(false);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            });
        }
        catch (SQLException ex2) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex2);
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
        this.model.addRow(new Object[0]);
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
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new products().setVisible(true);
            }
        });
    }
    
    public void setUpCurrencyColumn(final JTable table, final TableColumn sportColumn) {
        final JComboBox comboBox = new JComboBox();
        comboBox.addItem("AZN");
        comboBox.addItem("Dollar");
        comboBox.addItem("Rubl");
        comboBox.addItem("TL");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
    
    public void setUpUnitColumn(final JTable table, final TableColumn sportColumn) {
        final JComboBox comboBox = new JComboBox();
        comboBox.setEditable(true);
        comboBox.addItem("eded");
        comboBox.addItem("kg");
        comboBox.addItem("metr");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
    
    private void changeLanguage() {
        if (products.selectedIndex != this.langBox.getSelectedIndex()) {
            products.selectedIndex = this.langBox.getSelectedIndex();
            R.setLanguage(this.langBox.getItemAt(products.selectedIndex));
        }
    }
    
    static {
        products.selectedIndex = -1;
    }
}
