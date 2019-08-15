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
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.time.temporal.TemporalAccessor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Iterator;
import java.awt.Component;
import javax.swing.JOptionPane;
import product.res.R;
import javax.swing.table.TableModel;
import javax.swing.JTextPane;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
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

public class muhasibat extends JFrame
{
    static int selectedTypeIndex;
    static int selectedPayTypeIndex;
    static String selectedTypeItem;
    static int selectedIndex;
    static int selectedRow;
    DefaultTableModel dtm;
    Map<String, Double> m;
    static TreeMap<String, String> map;
    private JComboBox<String> PayType;
    private JComboBox<String> Type;
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
            final String query = "SELECT `type`,  count(`type`) as `s` FROM products where 1 group by `type`;";
            final ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            final String[] types = new String[list.size() + 1];
            types[0] = "------";
            for (int i = 0; i < list.size(); ++i) {
                types[i + 1] = list.get(i);
            }
            return types;
        }
        catch (SQLException ex2) {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                ex2.printStackTrace();
            }
            Logger.getLogger(muhasibat.class.getName()).log(Level.SEVERE, null, ex2);
            return null;
        }
    }
    
    private void loadTabledata() {
        Connection conn = null;
        Statement stm = null;
        try {
            this.dtm = new DefaultTableModel(new String[0][], new String[] { "barcode", "mehsul adi", "satis zamani", "mehsul qiymeti", "satis qiymeti", "miqdar", "cemi qiymet", "vahid", "odenis tipi" }) {
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
            String s = "";
            if (this.Type.getSelectedIndex() != 0) {
                s = " and (`products`.`type`='" + this.Type.getSelectedItem() + "' or `products`.`product_code`='" + this.Type.getSelectedItem() + "'  or `products`.`barcode`='" + this.Type.getSelectedItem() + "' )";
            }
            String s2 = "";
            if (this.PayType.getSelectedIndex() != 0) {
                s2 = " and `sale`.`paymentType`='" + this.PayType.getSelectedItem() + "'";
            }
            final String query = "SELECT * FROM sale ,products where sale.time <='" + maxdates[2] + "-" + maxdates[1] + "-" + String.valueOf(Integer.parseInt(maxdates[0]) + 1) + "' and sale.time >='" + dates[2] + "-" + dates[1] + "-" + dates[0] + "' and `products`.`barcode`=`sale`.`product_barcode`  " + s + s2 + " order by sale_date asc;";
            final ResultSet rs = stm.executeQuery(query);
            this.m.clear();
            this.umumiDeyer.setText("0");
            if (rs.next()) {
                double cemi = 0.0;
                rs.previous();
                while (rs.next()) {
                    if (this.m.containsKey(rs.getString("product_id"))) {
                        final Double sum = this.m.get(rs.getString("product_id"));
                        this.m.put(rs.getString("product_id"), sum + rs.getDouble("count") * rs.getDouble("sale.price"));
                    }
                    else {
                        this.m.put(rs.getString("product_id"), rs.getDouble("count") * rs.getDouble("sale.price"));
                    }
                    this.dtm.addRow(new Object[] { rs.getString("product_barcode"), rs.getString("product_name"), rs.getString("sale_date"), rs.getString("products.price"), rs.getString("sale.price"), rs.getString("sale.count"), String.format("%.2f", rs.getDouble("count") * rs.getDouble("sale.price")), rs.getString("unit"), rs.getString("paymentType") });
                }
                for (final Map.Entry<String, Double> entry : this.m.entrySet()) {
                    final String key = entry.getKey();
                    final Double value = entry.getValue();
                    cemi += value;
                }
                this.umumiDeyer.setText("" + cemi);
            }
            else {
                JOptionPane.showMessageDialog(this, R.ProductNotFound.getValue());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(muhasibat.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public muhasibat() {
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
        R.setLanguage("AZ");
        final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>() {
            String[] types = muhasibat.this.getTypes();
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
        this.Type.setModel(comboBoxModel);
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
        this.loadTabledata();
    }
    
    public muhasibat(final TreeMap<String, String> map) {
        this();
        muhasibat.map = map;
    }
    
    private void initComponents() {
        this.jPopupMenu1 = new JPopupMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jPanel1 = new JPanel();
        this.Type = new JComboBox<String>();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.minDate = new JLabel();
        this.maxDate = new JLabel();
        this.PayType = new JComboBox<String>();
        this.jLabel4 = new JLabel();
        this.jPanel2 = new JPanel();
        this.jPanel3 = new JPanel();
        this.jLabel3 = new JLabel();
        this.umumiDeyer = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.dataTable = new JTable();
        this.jButton3 = new JButton();
        this.backButton = new JButton();
        this.jPopupMenu1.setPreferredSize(new Dimension(60, 100));
        this.jMenuItem1.setText("jMenuItem1");
        this.jPopupMenu1.add(this.jMenuItem1);
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel1.setLayout((LayoutManager)new AbsoluteLayout());
        this.Type.setEditable(true);
        this.Type.setModel(new DefaultComboBoxModel<String>(new String[] { "------" }));
        this.Type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                muhasibat.this.TypeActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.Type, new AbsoluteConstraints(10, 50, 160, -1));
        this.jLabel1.setText("type :");
        this.jPanel1.add(this.jLabel1, new AbsoluteConstraints(10, 30, -1, -1));
        this.jLabel2.setText("Sat\u0131\u015f tarixin\u0259 g\u00f6r\u0259");
        this.jPanel1.add(this.jLabel2, new AbsoluteConstraints(10, 210, -1, -1));
        this.jButton1.setText("D\u0259yi\u015f");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                muhasibat.this.jButton1ActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.jButton1, new AbsoluteConstraints(110, 250, -1, -1));
        this.jButton2.setText("D\u0259yi\u015f");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                muhasibat.this.jButton2ActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.jButton2, new AbsoluteConstraints(110, 290, -1, -1));
        this.minDate.setText("jLabel3");
        this.jPanel1.add(this.minDate, new AbsoluteConstraints(10, 250, -1, -1));
        this.maxDate.setText("jLabel4");
        this.jPanel1.add(this.maxDate, new AbsoluteConstraints(10, 300, -1, -1));
        this.PayType.setEditable(true);
        this.PayType.setModel(new DefaultComboBoxModel<String>(new String[] { "B\u00fct\u00fcn n\u00f6vl\u0259r", "N\u0259\u011fd", "Kartla" }));
        this.PayType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent evt) {
                muhasibat.this.PayTypeItemStateChanged(evt);
            }
        });
        this.PayType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                muhasibat.this.PayTypeActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.PayType, new AbsoluteConstraints(10, 110, 160, -1));
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
                muhasibat.this.dataTableFocusGained(evt);
            }
        });
        this.dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                muhasibat.this.dataTableMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.dataTable);
        this.dataTable.getColumnModel().getSelectionModel().setSelectionMode(0);
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel3, -1, -1, 32767).addComponent(this.jScrollPane1, -1, 887, 32767));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 459, 32767)));
        this.jButton3.setText("X");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                muhasibat.this.jButton3ActionPerformed(evt);
            }
        });
        this.backButton.setText("<--");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                muhasibat.this.backButtonActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, 216, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -1, -1, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.backButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton3)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.backButton).addComponent(this.jButton3)).addGap(12, 12, 12).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel1, -1, -1, 32767))));
        this.pack();
    }
    
    private void TypeActionPerformed(final ActionEvent evt) {
        if (muhasibat.selectedTypeIndex != this.Type.getSelectedIndex() || !muhasibat.selectedTypeItem.equals(String.valueOf(this.Type.getSelectedItem()))) {
            muhasibat.selectedTypeIndex = this.Type.getSelectedIndex();
            muhasibat.selectedTypeItem = String.valueOf(this.Type.getSelectedItem());
            this.loadTabledata();
        }
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
            if (this.dataTable.getSelectedRow() != muhasibat.selectedRow) {
                muhasibat.selectedRow = this.dataTable.getSelectedRow();
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
                final ResultSet rs1 = s.executeQuery("SELECT * FROM product_db.sale where customer_id='" + this.dtm.getValueAt(muhasibat.selectedRow, 1) + "' and seller_id='" + this.dtm.getValueAt(muhasibat.selectedRow, 0) + "' and sale_date='" + this.dtm.getValueAt(muhasibat.selectedRow, 4) + "'order by `time` asc;");
                while (rs1.next()) {
                    model.addRow(new Object[] { rs1.getString("id"), rs1.getString("product_barcode"), rs1.getString("product_name"), rs1.getString("count"), rs1.getString("unit"), rs1.getString("price"), String.format("%.2f", rs1.getDouble("price") * rs1.getDouble("count")) });
                }
                s.close();
                JOptionPane.showMessageDialog(null, js, "Information", -1);
                muhasibat.selectedRow = -1;
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
    
    private void backButtonActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void PayTypeItemStateChanged(final ItemEvent evt) {
    }
    
    private void PayTypeActionPerformed(final ActionEvent evt) {
        if (muhasibat.selectedPayTypeIndex != this.Type.getSelectedIndex()) {
            muhasibat.selectedPayTypeIndex = this.Type.getSelectedIndex();
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
            Logger.getLogger(muhasibat.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(muhasibat.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(muhasibat.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(muhasibat.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new muhasibat().setVisible(true);
            }
        });
    }
    
    private void changeLanguage() {
    }
    
    static {
        muhasibat.selectedTypeIndex = -2;
        muhasibat.selectedPayTypeIndex = -2;
        muhasibat.selectedTypeItem = "";
        muhasibat.selectedIndex = -1;
        muhasibat.selectedRow = -1;
    }
}
