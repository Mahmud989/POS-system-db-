// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.LayoutStyle;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.Cursor;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import javax.swing.plaf.TableUI;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.ComboBoxModel;
import java.time.temporal.TemporalAccessor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JOptionPane;
import product.res.R;
import javax.swing.table.TableModel;
import javax.swing.JTextPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.TreeMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class satisidaresi extends JFrame
{
    static int selectedTypeIndex;
    static int selectedUserIndex;
    static String selectedTypeItem;
    static int selectedIndex;
    static int selectedRow;
    DefaultTableModel dtm;
    Map<String, Double> m;
    static TreeMap<String, String> map;
    private JComboBox<String> Type;
    private JComboBox<String> Users;
    private JButton backButton;
    private JTable dataTable;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JMenuItem jMenuItem1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPopupMenu jPopupMenu1;
    private JScrollPane jScrollPane1;
    private JComboBox<String> langBox;
    private JLabel maxDate;
    private JLabel minDate;
    private JLabel umumiDeyer;
    
    private String[] getTypes() {
        Connection conn = null;
        Statement stm = null;
        try {
            final ArrayList<String> list = new ArrayList<String>();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = (Statement)conn.createStatement();
            final String query = "SELECT seller_name FROM sale where 1 group by `seller_name`;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs = stm.executeQuery("SELECT name FROM users where 1 group by `name`;");
            while (rs.next()) {
                if (list.indexOf(rs.getObject(1)) < 0) {
                    list.add(rs.getString(1));
                }
            }
            final String[] types = new String[list.size() + 1];
            types[0] = "All users";
            for (int i = 0; i < list.size(); ++i) {
                types[i + 1] = list.get(i);
            }
            return types;
        }
        catch (SQLException ex) {
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
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
        }
        return null;
    }
    
    private void loadTabledata() {
        Connection conn = null;
        Statement stm = null;
        try {
            this.dtm = new DefaultTableModel(new String[0][], new String[] { "satici id", "musteri id", "satici adi", "musteri adi", "satis zamani", "mehsul Gostericisi", "umumi qiymet", "Valyuta", "odenis tipi" }) {
                boolean[] canEdit = { false, false, false, false, false, false, false, false, false };
                
                @Override
                public Class getColumnClass(final int columnIndex) {
                    if (columnIndex == 5) {
                        return JTextPane.class;
                    }
                    return String.class;
                }
                
                public int getColumnIndex(final String name) {
                    int r = -1;
                    for (int i = 0; i < this.getColumnCount(); ++i) {
                        if (this.getColumnName(i).equals(name)) {
                            r = i;
                            break;
                        }
                    }
                    return r;
                }
                
                @Override
                public boolean isCellEditable(final int i, final int i1) {
                    return this.canEdit[i1];
                }
                
                public void setColEditable(final int col) {
                    this.canEdit[col] = true;
                }
            };
            this.dataTable.setModel(this.dtm);
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = (Statement)conn.createStatement();
            stm.executeUpdate("set sql_mode='';");
            final String[] dates = this.minDate.getText().split("/");
            final String[] maxdates = this.maxDate.getText().split("/");
            String s1 = "";
            final String s2 = "";
            if (this.Users.getSelectedIndex() != 0) {
                s1 = " and `sale`.`seller_name`='" + this.Users.getSelectedItem() + "'";
            }
            if (this.Type.getSelectedIndex() != 0) {
                s1 = s1 + " and `sale`.`paymentType`='" + this.Type.getSelectedItem() + "'";
            }
            String query = "";
            query = "SELECT *, ANY_VALUE(sale_date) , count(sale_date) as `s` FROM sale where time <='" + maxdates[2] + "-" + maxdates[1] + "-" + String.valueOf(Integer.parseInt(maxdates[0]) + 1) + "' and time >='" + dates[2] + "-" + dates[1] + "-" + dates[0] + "' " + s1 + "  group by seller_id,customer_id,sale_date order by `time` asc;";
            final ResultSet rs = stm.executeQuery(query);
            this.umumiDeyer.setText("0");
            if (rs.next()) {
                double cemi = 0.0;
                rs.previous();
                while (rs.next()) {
                    final Statement s3 = (Statement)conn.createStatement();
                    final ResultSet rs2 = s3.executeQuery("SELECT * FROM product_db.sale where customer_id='" + rs.getString("customer_id") + "' and seller_id='" + rs.getString("seller_id") + "' and sale_date='" + rs.getString("sale_date") + "';");
                    double cem = 0.0;
                    while (rs2.next()) {
                        cem += rs2.getDouble("price") * rs2.getDouble("count");
                    }
                    this.dtm.addRow(new Object[] { rs.getString("seller_id"), rs.getString("customer_id"), rs.getString("seller_name"), rs.getString("customer_name"), rs.getString("sale_date"), "view more", String.format("%.4f", cem), rs.getString("valyuta"), rs.getString("paymentType") });
                    s3.close();
                    cemi += cem;
                }
                this.umumiDeyer.setText(String.format("%.4f", cemi));
            }
            else {
                JOptionPane.showMessageDialog(this, R.ProductNotFound.getValue());
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            }
            catch (SQLException ex4) {}
        }
        catch (SQLException ex2) {
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex2);
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
    
    public satisidaresi() {
        this.m = new TreeMap<String, Double>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setUndecorated(true);
        this.setExtendedState(6);
        this.initComponents();
        final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>() {
            String[] types = satisidaresi.this.getTypes();
            String selection = this.types[0];
            
            @Override
            public String getElementAt(final int index) {
                return this.types[index];
            }
            
            @Override
            public int getSize() {
                return this.types.length;
            }
            
            @Override
            public void setSelectedItem(final Object anItem) {
                this.selection = (String)anItem;
            }
            
            @Override
            public String getSelectedItem() {
                return this.selection;
            }
            
            @Override
            public int getIndexOf(final Object o) {
                return super.getIndexOf(o);
            }
        };
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final LocalDateTime now = LocalDateTime.now();
        final String date = dtf.format(now);
        this.maxDate.setText(date);
        int minMonth = now.getMonthValue() - 1;
        int minYear = now.getYear();
        if (minMonth < 1) {
            minMonth += 12;
            --minYear;
        }
        this.minDate.setText(now.getDayOfMonth() - 1 + "/" + (minMonth + 1) + "/" + minYear);
        this.Users.setModel(comboBoxModel);
        this.dataTable.setUI(new BasicTableUI());
        this.loadTabledata();
    }
    
    public satisidaresi(final TreeMap<String, String> map) {
        this();
        satisidaresi.map = map;
    }
    
    private void initComponents() {
        this.jPopupMenu1 = new JPopupMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jPanel1 = new JPanel();
        this.jLabel2 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.minDate = new JLabel();
        this.maxDate = new JLabel();
        this.Users = new JComboBox<String>();
        this.jLabel1 = new JLabel();
        this.Type = new JComboBox<String>();
        this.jLabel4 = new JLabel();
        this.jPanel2 = new JPanel();
        this.jPanel3 = new JPanel();
        this.jLabel3 = new JLabel();
        this.umumiDeyer = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.dataTable = new JTable();
        this.jButton3 = new JButton();
        this.langBox = new JComboBox<String>();
        this.backButton = new JButton();
        this.jPopupMenu1.setPreferredSize(new Dimension(60, 100));
        this.jMenuItem1.setText("jMenuItem1");
        this.jPopupMenu1.add(this.jMenuItem1);
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel1.setLayout((LayoutManager)new AbsoluteLayout());
        this.jLabel2.setText("satis tarixine gore");
        this.jPanel1.add(this.jLabel2, new AbsoluteConstraints(10, 290, -1, -1));
        this.jButton1.setText("D\u0259yi\u015f");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.jButton1ActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.jButton1, new AbsoluteConstraints(110, 330, -1, -1));
        this.jButton2.setText("D\u0259yi\u015f");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.jButton2ActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.jButton2, new AbsoluteConstraints(110, 370, -1, -1));
        this.minDate.setText("jLabel3");
        this.jPanel1.add(this.minDate, new AbsoluteConstraints(10, 330, -1, -1));
        this.maxDate.setText("jLabel4");
        this.jPanel1.add(this.maxDate, new AbsoluteConstraints(10, 380, -1, -1));
        this.Users.setEditable(true);
        this.Users.setModel(new DefaultComboBoxModel<String>(new String[] { "------" }));
        this.Users.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent evt) {
                satisidaresi.this.UsersItemStateChanged(evt);
            }
        });
        this.Users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.UsersActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.Users, new AbsoluteConstraints(10, 50, 160, -1));
        this.jLabel1.setText("Sat\u0131c\u0131ya g\u00f6r\u0259 axtar\u0131\u015f");
        this.jPanel1.add(this.jLabel1, new AbsoluteConstraints(10, 30, -1, -1));
        this.Type.setEditable(true);
        this.Type.setModel(new DefaultComboBoxModel<String>(new String[] { "B\u00fct\u00fcn n\u00f6vl\u0259r", "N\u0259\u011fd", "Kartla" }));
        this.Type.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent evt) {
                satisidaresi.this.TypeItemStateChanged(evt);
            }
        });
        this.Type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.TypeActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.Type, new AbsoluteConstraints(10, 110, 160, -1));
        this.jLabel4.setText("\u00d6d\u0259ni\u015f n\u00f6v\u00fc");
        this.jPanel1.add(this.jLabel4, new AbsoluteConstraints(10, 90, -1, -1));
        this.jLabel3.setFont(new Font("Tahoma", 0, 18));
        this.jLabel3.setText("Toplam qiymet");
        this.umumiDeyer.setFont(new Font("Tahoma", 0, 18));
        this.umumiDeyer.setText("0");
        final GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3, -2, 239, -2).addGap(18, 18, 18).addComponent(this.umumiDeyer).addContainerGap(-1, 32767)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap(41, 32767).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3, -2, 22, -2).addComponent(this.umumiDeyer)).addContainerGap()));
        this.dataTable.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, { null, null } }, new String[] { "Title 1", "Title 2" }) {
            Class[] types = { Object.class, String.class };
            boolean[] canEdit = { false, false };
            
            @Override
            public Class getColumnClass(final int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.dataTable.setComponentPopupMenu(this.jPopupMenu1);
        this.dataTable.setCursor(new Cursor(0));
        this.dataTable.getTableHeader().setReorderingAllowed(false);
        this.dataTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent evt) {
                satisidaresi.this.dataTableFocusGained(evt);
            }
        });
        this.dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                satisidaresi.this.dataTableMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.dataTable);
        this.dataTable.getColumnModel().getSelectionModel().setSelectionMode(0);
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel3, -1, -1, 32767).addComponent(this.jScrollPane1, -1, 887, 32767));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 468, 32767)));
        this.jButton3.setText("X");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.jButton3ActionPerformed(evt);
            }
        });
        this.langBox.setModel(new DefaultComboBoxModel<String>(new String[] { "AZ", "EN", "RU", "TR" }));
        this.langBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.langBoxActionPerformed(evt);
            }
        });
        this.backButton.setText("<--");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                satisidaresi.this.backButtonActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, 216, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -1, -1, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.backButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.langBox, -2, -1, -2).addGap(44, 44, 44).addComponent(this.jButton3)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.langBox, -2, -1, -2).addComponent(this.backButton).addComponent(this.jButton3)).addGap(12, 12, 12).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel1, -1, -1, 32767))));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        final Calendar njp = new Calendar("AZ");
        JOptionPane.showMessageDialog(this, njp, "Calendar", -1);
        this.minDate.setText(njp.getDate());
        this.loadTabledata();
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        final Calendar njp = new Calendar("AZ");
        JOptionPane.showMessageDialog(this, njp, "Calendar", -1);
        this.maxDate.setText(njp.getDate());
        this.loadTabledata();
    }
    
    private void dataTableFocusGained(final FocusEvent evt) {
    }
    
    private void dataTableMouseClicked(final MouseEvent evt) {
        if (this.dataTable.getSelectedColumn() == 5) {
            if (this.dataTable.getSelectedRow() != satisidaresi.selectedRow) {
                satisidaresi.selectedRow = this.dataTable.getSelectedRow();
                return;
            }
            Connection conn = null;
            Statement stm = null;
            try {
                final JTable table = new JTable();
                final DefaultTableModel model = new DefaultTableModel(new Object[0][], new String[] { "satis nomresi", "barcode", "mehsul adi", "miqdar", "vahid", "qiymet", "umumi qiymet" }) {};
                table.setColumnSelectionAllowed(true);
                table.getTableHeader().setReorderingAllowed(false);
                final JScrollPane js = new JScrollPane();
                js.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() / 2));
                js.setViewportView(table);
                table.getColumnModel().getSelectionModel().setSelectionMode(0);
                table.setModel(model);
                conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
                stm = (Statement)conn.createStatement();
                final Statement s = (Statement)conn.createStatement();
                final ResultSet rs1 = s.executeQuery("SELECT * FROM product_db.sale where customer_id='" + this.dtm.getValueAt(satisidaresi.selectedRow, 1) + "' and seller_id='" + this.dtm.getValueAt(satisidaresi.selectedRow, 0) + "' and sale_date='" + this.dtm.getValueAt(satisidaresi.selectedRow, 4) + "'order by `time` asc;");
                while (rs1.next()) {
                    model.addRow(new Object[] { rs1.getString("id"), rs1.getString("product_barcode"), rs1.getString("product_name"), rs1.getString("count"), rs1.getString("unit"), rs1.getString("price"), String.format("%.2f", rs1.getDouble("price") * rs1.getDouble("count")) });
                }
                s.close();
                JOptionPane.showMessageDialog(null, js, "Information", -1);
                satisidaresi.selectedRow = -1;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (stm != null) {
                        stm.close();
                    }
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        System.exit(0);
    }
    
    private void langBoxActionPerformed(final ActionEvent evt) {
        this.changeLanguage();
    }
    
    private void backButtonActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void UsersActionPerformed(final ActionEvent evt) {
        if (satisidaresi.selectedUserIndex != this.Users.getSelectedIndex() || !satisidaresi.selectedTypeItem.equals(String.valueOf(this.Users.getSelectedItem()))) {
            satisidaresi.selectedUserIndex = this.Users.getSelectedIndex();
            satisidaresi.selectedTypeItem = String.valueOf(this.Users.getSelectedItem());
            this.loadTabledata();
        }
    }
    
    private void UsersItemStateChanged(final ItemEvent evt) {
        this.Users.getEditor().setItem(this.Users.getSelectedItem());
    }
    
    private void TypeItemStateChanged(final ItemEvent evt) {
    }
    
    private void TypeActionPerformed(final ActionEvent evt) {
        if (satisidaresi.selectedTypeIndex != this.Type.getSelectedIndex() || !satisidaresi.selectedTypeItem.equals(String.valueOf(this.Type.getSelectedItem()))) {
            satisidaresi.selectedTypeIndex = this.Type.getSelectedIndex();
            satisidaresi.selectedTypeItem = String.valueOf(this.Users.getSelectedItem());
            this.loadTabledata();
        }
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
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new satisidaresi().setVisible(true);
            }
        });
    }
    
    private void changeLanguage() {
        if (satisidaresi.selectedIndex != this.langBox.getSelectedIndex()) {
            satisidaresi.selectedIndex = this.langBox.getSelectedIndex();
            R.setLanguage(this.langBox.getItemAt(satisidaresi.selectedIndex));
        }
    }
    
    static {
        satisidaresi.selectedTypeIndex = -2;
        satisidaresi.selectedUserIndex = -2;
        satisidaresi.selectedTypeItem = "";
        satisidaresi.selectedIndex = -1;
        satisidaresi.selectedRow = -1;
    }
}
