// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import product.res.R;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import java.text.DateFormat;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.LayoutStyle;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class qaime_panel extends JFrame
{
    DefaultTableModel modelAdd;
    DefaultTableModel modelShow;
    DefaultTableModel modelQaimeGoster;
    static int SelectedRowQaimeGoster;
    private JButton AddBTN;
    private JLabel DaxiledilmeZamani;
    private JButton DeleteRow;
    private JLabel ExistMEssage;
    private JLabel FirmaAdiView;
    private JButton ProductPanelBTN;
    private JLabel QaimeTertibZamani;
    private JTable Qaimeler;
    private JButton SearchByTime;
    private JFormattedTextField addDate;
    private JCheckBox autoFillRows;
    private JButton backButton;
    private JTextField barcode;
    private JTextField buyprice;
    private JTextField code;
    private JTextField count;
    private JTextField currency;
    private JTextField edv;
    private JComboBox<String> firmaAdi;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JTabbedPane jTabbedPane1;
    private JTable jTable1;
    private JTextField maxDate;
    private JTextField minDate;
    private JTextField name;
    private JTextField qaimeNum;
    private JTextField qaime_nomresi;
    private JComboBox<String> qaime_novu;
    private JLabel qaime_zamani;
    private JTable resultNEW;
    private JTextField salePrice;
    private JButton saveBTN;
    private JButton searchBTN;
    private JTable showTable;
    private JLabel toplam;
    private JTextField unit;
    
    public void getFirmaAdi() {
        Connection conn = null;
        Statement stm = null;
        final ArrayList<Object> list = new ArrayList<Object>();
        try {
            this.firmaAdi.removeAllItems();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final ResultSet rs = stm.executeQuery("SELECT * FROM product_db.firma;");
            while (rs.next()) {
                this.firmaAdi.addItem(rs.getString("name"));
                list.add(rs.getString("name"));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
    
    public qaime_panel() {
        this.setUndecorated(true);
        this.setExtendedState(6);
        this.initComponents();
        this.modelAdd = new DefaultTableModel(new Object[0][], new String[] { "barcode", "code", "adi", "alis qiymeti", "miqdar", "vahid", "%", "satis qiymeti", "Mebleg", "Valyuta", "qeyd" }) {
            boolean[] canEdit = { true, true, true, true, true, true, true, true, true, true, true };
            
            @Override
            public void fireTableCellUpdated(final int row, final int column) {
                super.fireTableCellUpdated(row, column);
                if (column == 0 && qaime_panel.this.autoFillRows.isSelected()) {
                    qaime_panel.this.autoFill(row, column);
                }
                else if ((column == 3 || column == 4) && qaime_panel.this.modelAdd.getValueAt(row, 3) != null && qaime_panel.this.modelAdd.getValueAt(row, 4) != null) {
                    qaime_panel.this.modelAdd.setValueAt(String.format("%.4f", Double.parseDouble((String)qaime_panel.this.modelAdd.getValueAt(row, 3)) * Double.parseDouble((String)qaime_panel.this.modelAdd.getValueAt(row, 4))), row, 8);
                }
            }
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        };
        this.modelShow = new DefaultTableModel(new Object[0][], new String[] { "barcode", "code", "adi", "alis qiymeti", "miqdar", "vahid", "%", "satis qiymeti", "Mebleg", "Valyuta", "qeyd" }) {
            boolean[] canEdit = { true, true, true, true, true, true, true, true, true, true, true };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return false;
            }
        };
        this.modelQaimeGoster = new DefaultTableModel(new Object[0][], new String[] { "Firma adi", "Qaim\u0259 n\u00f6mr\u0259si", "n\u00f6v", "T\u0259rtib zamani", "Sistem\u0259 daxil edilm\u0259..", "M\u0259bl\u0259\u011f" }) {
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return false;
            }
        };
        this.getFirmaAdi();
        this.Qaimeler.setModel(this.modelQaimeGoster);
        this.showTable.setModel(this.modelShow);
        this.jTable1.setModel(this.modelAdd);
        this.Qaimeler.getSelectionModel().setSelectionMode(0);
        this.Qaimeler.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent event) {
                if (qaime_panel.this.Qaimeler.getSelectedRow() > -1 && qaime_panel.this.Qaimeler.getSelectedRow() != qaime_panel.SelectedRowQaimeGoster) {
                    qaime_panel.SelectedRowQaimeGoster = qaime_panel.this.Qaimeler.getSelectedRow();
                    qaime_panel.this.SearchQaime(qaime_panel.this.Qaimeler.getValueAt(qaime_panel.this.Qaimeler.getSelectedRow(), 1).toString(), "");
                }
            }
        });
    }
    
    private void initComponents() {
        this.jPanel3 = new JPanel();
        this.jLabel4 = new JLabel();
        this.barcode = new JTextField();
        this.jLabel5 = new JLabel();
        this.code = new JTextField();
        this.jLabel6 = new JLabel();
        this.name = new JTextField();
        this.jLabel7 = new JLabel();
        this.count = new JTextField();
        this.edv = new JTextField();
        this.jLabel8 = new JLabel();
        this.buyprice = new JTextField();
        this.jLabel9 = new JLabel();
        this.salePrice = new JTextField();
        this.jLabel10 = new JLabel();
        this.jLabel11 = new JLabel();
        this.currency = new JTextField();
        this.unit = new JTextField();
        this.jLabel13 = new JLabel();
        this.jTabbedPane1 = new JTabbedPane();
        this.jPanel4 = new JPanel();
        this.jLabel12 = new JLabel();
        this.qaime_nomresi = new JTextField();
        this.searchBTN = new JButton();
        this.qaime_zamani = new JLabel();
        this.minDate = new JTextField();
        this.jScrollPane3 = new JScrollPane();
        this.showTable = new JTable();
        this.jPanel7 = new JPanel();
        this.jLabel14 = new JLabel();
        this.jLabel15 = new JLabel();
        this.jLabel16 = new JLabel();
        this.QaimeTertibZamani = new JLabel();
        this.DaxiledilmeZamani = new JLabel();
        this.toplam = new JLabel();
        this.jLabel17 = new JLabel();
        this.FirmaAdiView = new JLabel();
        this.maxDate = new JTextField();
        this.jScrollPane4 = new JScrollPane();
        this.Qaimeler = new JTable();
        this.SearchByTime = new JButton();
        this.jLabel18 = new JLabel();
        this.jButton2 = new JButton();
        this.jPanel1 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.qaime_novu = new JComboBox<String>();
        this.jPanel5 = new JPanel();
        this.AddBTN = new JButton();
        this.saveBTN = new JButton();
        this.DeleteRow = new JButton();
        this.jPanel6 = new JPanel();
        this.firmaAdi = new JComboBox<String>();
        this.ExistMEssage = new JLabel();
        this.qaimeNum = new JTextField();
        this.addDate = new JFormattedTextField();
        this.jLabel3 = new JLabel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.resultNEW = new JTable();
        this.autoFillRows = new JCheckBox();
        this.ProductPanelBTN = new JButton();
        this.jPanel2 = new JPanel();
        this.backButton = new JButton();
        this.jButton1 = new JButton();
        this.jPanel3.setLayout((LayoutManager)new AbsoluteLayout());
        this.jLabel4.setText("barcode");
        this.jPanel3.add(this.jLabel4, new AbsoluteConstraints(20, 50, -1, -1));
        this.barcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.barcodeActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.barcode, new AbsoluteConstraints(150, 40, 116, -1));
        this.jLabel5.setText("code");
        this.jPanel3.add(this.jLabel5, new AbsoluteConstraints(20, 88, -1, -1));
        this.jPanel3.add(this.code, new AbsoluteConstraints(150, 80, 116, -1));
        this.jLabel6.setText("name");
        this.jPanel3.add(this.jLabel6, new AbsoluteConstraints(20, 119, -1, -1));
        this.jPanel3.add(this.name, new AbsoluteConstraints(150, 110, 116, -1));
        this.jLabel7.setText("elave olunma sayi");
        this.jPanel3.add(this.jLabel7, new AbsoluteConstraints(20, 157, -1, -1));
        this.jPanel3.add(this.count, new AbsoluteConstraints(150, 150, 116, -1));
        this.jPanel3.add(this.edv, new AbsoluteConstraints(150, 330, 116, -1));
        this.jLabel8.setText("alis qiymeti");
        this.jPanel3.add(this.jLabel8, new AbsoluteConstraints(20, 271, -1, -1));
        this.buyprice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.buypriceActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.buyprice, new AbsoluteConstraints(150, 260, 116, -1));
        this.jLabel9.setText("satis qiymeti");
        this.jPanel3.add(this.jLabel9, new AbsoluteConstraints(20, 302, -1, -1));
        this.jPanel3.add(this.salePrice, new AbsoluteConstraints(150, 290, 116, -1));
        this.jLabel10.setText("edv");
        this.jPanel3.add(this.jLabel10, new AbsoluteConstraints(20, 340, -1, -1));
        this.jLabel11.setText("Mezenne");
        this.jPanel3.add(this.jLabel11, new AbsoluteConstraints(20, 233, -1, -1));
        this.jPanel3.add(this.currency, new AbsoluteConstraints(150, 230, 116, -1));
        this.jPanel3.add(this.unit, new AbsoluteConstraints(150, 190, 116, -1));
        this.jLabel13.setText("vahid");
        this.jPanel3.add(this.jLabel13, new AbsoluteConstraints(20, 195, -1, -1));
        this.setDefaultCloseOperation(3);
        this.jTabbedPane1.setTabLayoutPolicy(1);
        this.jTabbedPane1.setCursor(new Cursor(0));
        this.jLabel12.setText("Qaime nomresi");
        this.qaime_nomresi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                qaime_panel.this.qaime_nomresiKeyReleased(evt);
            }
        });
        this.searchBTN.setText("Axtar");
        this.searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.searchBTNActionPerformed(evt);
            }
        });
        this.qaime_zamani.setText("Qaimenin zamani*");
        this.minDate.setEditable(false);
        this.minDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                qaime_panel.this.minDateMouseClicked(evt);
            }
        });
        this.minDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.minDateActionPerformed(evt);
            }
        });
        this.showTable.setModel(new DefaultTableModel(new Object[0][], new String[] { "barcode", "code", "adi", "alis qiymeti", "miqdar", "vahid", "%", "satis qiymeti", "Mebleg", "Valyuta", "qeyd" }) {
            boolean[] canEdit = { false, false, false, false, false, false, false, false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.showTable.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane3.setViewportView(this.showTable);
        this.jLabel14.setText("tertib zamani");
        this.jLabel15.setText("Sistem\u0259 daxil edilm\u0259 zaman\u0131");
        this.jLabel16.setText("Toplam qiym\u0259t");
        this.jLabel17.setText("Firma adi");
        final GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
        this.jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel14, -1, -1, 32767).addComponent(this.jLabel15, -1, 151, 32767).addComponent(this.jLabel16, -1, -1, 32767)).addComponent(this.jLabel17)).addGap(51, 51, 51).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.QaimeTertibZamani, -1, -1, 32767).addComponent(this.DaxiledilmeZamani, -1, 163, 32767).addComponent(this.toplam, -1, 163, 32767).addComponent(this.FirmaAdiView, -1, -1, 32767)).addContainerGap(18, 32767)));
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel17, GroupLayout.Alignment.TRAILING).addComponent(this.FirmaAdiView, GroupLayout.Alignment.TRAILING, -2, 14, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel7Layout.createSequentialGroup().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel14).addComponent(this.QaimeTertibZamani, -2, 14, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel15, -2, 14, -2)).addComponent(this.DaxiledilmeZamani, -2, 14, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.toplam, -1, -1, 32767).addComponent(this.jLabel16))));
        this.maxDate.setEditable(false);
        this.maxDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                qaime_panel.this.maxDateMouseClicked(evt);
            }
        });
        this.maxDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.maxDateActionPerformed(evt);
            }
        });
        this.Qaimeler.setModel(new DefaultTableModel(new Object[0][], new String[] { "Firma adi", "Qaim\u0259 n\u00f6mr\u0259si", "n\u00f6v", "T\u0259rtib zamani", "Sistem\u0259 daxil edilm\u0259..", "M\u0259bl\u0259\u011f" }) {
            boolean[] canEdit = { false, false, false, false, true, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.Qaimeler.setFillsViewportHeight(true);
        this.Qaimeler.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane4.setViewportView(this.Qaimeler);
        this.SearchByTime.setText("Zamana g\u00f6r\u0259 axtar");
        this.SearchByTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.SearchByTimeActionPerformed(evt);
            }
        });
        this.jLabel18.setFont(new Font("Tahoma", 0, 14));
        this.jLabel18.setText("-");
        this.jButton2.setText("T\u0259mizl\u0259");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.jButton2ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
        this.jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3, -1, 1467, 32767).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(27, 27, 27).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.qaime_zamani, -1, 108, 32767).addComponent(this.jLabel12, -1, -1, 32767)).addGap(18, 18, 18).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.qaime_nomresi).addComponent(this.minDate, -1, 110, 32767)).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(46, 46, 46).addComponent(this.searchBTN)).addGroup(jPanel4Layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel18).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.maxDate, -2, 110, -2).addGap(27, 27, 27).addComponent(this.jButton2)))).addGroup(jPanel4Layout.createSequentialGroup().addGap(191, 191, 191).addComponent(this.SearchByTime, -2, 139, -2))).addGap(42, 42, 42).addComponent(this.jPanel7, -2, -1, -2).addContainerGap(-1, 32767)).addComponent(this.jScrollPane4));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel12).addComponent(this.qaime_nomresi, -2, -1, -2).addComponent(this.searchBTN)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.qaime_zamani).addComponent(this.minDate, -2, -1, -2).addComponent(this.maxDate, -1, -1, -2).addComponent(this.jLabel18, -2, 23, -2).addComponent(this.jButton2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.SearchByTime).addGap(7, 7, 7)).addComponent(this.jPanel7, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane4, -2, 146, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane3, -1, 428, 32767).addGap(0, 0, 0)));
        this.jTabbedPane1.addTab("qaimeni goster", this.jPanel4);
        this.jTable1.setModel(new DefaultTableModel(new Object[0][], new String[] { "barcode", "code", "adi", "alis qiymeti", "miqdar", "vahid", "%", "satis qiymeti", "Mebleg", "Valyuta", "qeyd" }) {
            boolean[] canEdit = { true, true, true, true, true, true, true, true, false, true, true };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jTable1.getTableHeader().setReorderingAllowed(false);
        this.jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                qaime_panel.this.jTable1KeyReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.jTable1);
        this.qaime_novu.setModel(new DefaultComboBoxModel<String>(new String[] { "\u0130dxal", "Daxili al\u0131\u015f" }));
        this.AddBTN.setText("Setir elave et");
        this.AddBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.AddBTNActionPerformed(evt);
            }
        });
        this.saveBTN.setText("Saxla");
        this.saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.saveBTNActionPerformed(evt);
            }
        });
        this.DeleteRow.setText("Setri sil");
        this.DeleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.DeleteRowActionPerformed(evt);
            }
        });
        final GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.AddBTN, GroupLayout.Alignment.TRAILING, -1, 163, 32767).addComponent(this.saveBTN, -1, -1, 32767).addComponent(this.DeleteRow, -1, -1, 32767));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.DeleteRow).addGap(18, 18, 18).addComponent(this.AddBTN).addGap(13, 13, 13).addComponent(this.saveBTN).addContainerGap()));
        this.jPanel6.setLayout((LayoutManager)new AbsoluteLayout());
        this.firmaAdi.setEditable(true);
        this.firmaAdi.setModel(new DefaultComboBoxModel<String>(new String[] { "legan", "test", "test" }));
        this.jPanel6.add(this.firmaAdi, new AbsoluteConstraints(160, 60, 143, -1));
        this.ExistMEssage.setText("bu qaime nomresi movcuddur");
        this.jPanel6.add(this.ExistMEssage, new AbsoluteConstraints(98, 0, -1, -1));
        this.qaimeNum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                qaime_panel.this.qaimeNumKeyReleased(evt);
            }
        });
        this.jPanel6.add(this.qaimeNum, new AbsoluteConstraints(160, 20, 143, -1));
        this.addDate.setEditable(false);
        this.addDate.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))));
        this.addDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                qaime_panel.this.addDateMouseClicked(evt);
            }
        });
        this.addDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.addDateActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.addDate, new AbsoluteConstraints(160, 90, 143, -1));
        this.jLabel3.setText("qaimenin tarixi");
        this.jPanel6.add(this.jLabel3, new AbsoluteConstraints(10, 97, 82, -1));
        this.jLabel1.setText("Alis qaimesi");
        this.jPanel6.add(this.jLabel1, new AbsoluteConstraints(10, 26, 82, -1));
        this.jLabel2.setText("Firma adi");
        this.jPanel6.add(this.jLabel2, new AbsoluteConstraints(10, 66, 82, -1));
        this.resultNEW.setModel(new DefaultTableModel(new Object[][] { { "", null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }) {
            boolean[] canEdit = { false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.resultNEW.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane2.setViewportView(this.resultNEW);
        this.autoFillRows.setText("avto daxil etme");
        this.autoFillRows.setFocusPainted(false);
        this.autoFillRows.setSelected(true);
        this.ProductPanelBTN.setText("mehsul panel");
        this.ProductPanelBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.ProductPanelBTNActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(100, 100, 100).addComponent(this.jPanel6, -2, -1, -2).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.qaime_novu, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGap(71, 71, 71).addComponent(this.autoFillRows))).addGap(34, 34, 34).addComponent(this.jPanel5, -2, -1, -2).addGap(18, 18, 18).addComponent(this.ProductPanelBTN, -2, 126, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 553, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel5, -2, -1, -2).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.qaime_novu, -2, -1, -2).addGap(48, 48, 48).addComponent(this.autoFillRows)).addComponent(this.jPanel6, -2, -1, -2)).addGap(19, 19, 19)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.ProductPanelBTN).addGap(18, 18, 18))).addComponent(this.jScrollPane1, -1, 495, 32767))).addGap(0, 0, 0).addComponent(this.jScrollPane2, -2, 64, -2)));
        this.jTabbedPane1.addTab("yeni alis", this.jPanel1);
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1467, 32767));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 697, 32767));
        this.jTabbedPane1.addTab("iade", this.jPanel2);
        this.backButton.setText("<--");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.backButtonActionPerformed(evt);
            }
        });
        this.jButton1.setText("X");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                qaime_panel.this.jButton1ActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTabbedPane1).addGroup(layout.createSequentialGroup().addComponent(this.backButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton1)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jButton1).addGroup(layout.createSequentialGroup().addComponent(this.backButton).addGap(1, 1, 1))).addGap(0, 0, 0).addComponent(this.jTabbedPane1, -1, 725, 32767)));
        this.pack();
    }
    
    private void addDateActionPerformed(final ActionEvent evt) {
    }
    
    private void addDateMouseClicked(final MouseEvent evt) {
        final Calendar njp = new Calendar("AZ");
        JOptionPane.showMessageDialog(this, njp, "Calendar", -1);
        this.addDate.setText(njp.getDate());
    }
    
    private void barcodeActionPerformed(final ActionEvent evt) {
    }
    
    private void buypriceActionPerformed(final ActionEvent evt) {
    }
    
    private void ProductPanelBTNActionPerformed(final ActionEvent evt) {
        final AddProduct addProduct = new AddProduct(this, this.rootPaneCheckingEnabled);
        new products().show();
    }
    
    private void AddBTNActionPerformed(final ActionEvent evt) {
        ((DefaultTableModel)this.jTable1.getModel()).addRow(new Object[0]);
    }
    
    private void saveBTNActionPerformed(final ActionEvent evt) {
        Connection conn = null;
        Statement stm = null;
        try {
            for (int i = this.modelAdd.getRowCount() - 1; i >= 0; --i) {
                for (int j = 0; j < 10; ++j) {
                    if (this.modelAdd.getValueAt(i, j) == null || this.modelAdd.getValueAt(i, j).equals("")) {
                        JOptionPane.showMessageDialog(this, "C\u0259dv\u0259ld\u0259 bo\u015f xana var");
                        return;
                    }
                }
            }
            final String[] dates = this.addDate.getText().split("/");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final String query = "INSERT INTO `product_db`.`muhasibat` (`qaime_nomresi`, `qaime_novu`, `alinan_sirketin_adi`, `mehsullarin_siyahisi`, `qeyd`, `qaime_zamani`, `time`) VALUES ('" + this.qaimeNum.getText() + "', '" + this.qaime_novu.getSelectedItem() + "', '" + this.firmaAdi.getSelectedItem() + "', 'siyahi', 'qeyd', '" + dates[2] + "-" + dates[1] + "-" + dates[0] + "', current_timestamp());";
            String values = "";
            final int b = stm.executeUpdate(query);
            if (b > 0) {
                for (int k = this.modelAdd.getRowCount() - 1; k >= 0; --k) {
                    if (this.modelAdd.getValueAt(k, 0) == null || this.modelAdd.getValueAt(k, 0).equals("")) {
                        this.modelAdd.removeRow(k);
                    }
                }
                final ResultSet rs = stm.executeQuery("SELECT * FROM product_db.muhasibat where `qaime_nomresi`='" + this.qaimeNum.getText() + "' and `qaime_novu`='" + this.qaime_novu.getSelectedItem() + "' and `alinan_sirketin_adi`='" + this.firmaAdi.getSelectedItem() + "'and  `qaime_zamani`='" + dates[2] + "-" + dates[1] + "-" + dates[0] + "' ;");
                if (rs.next()) {
                    final String id = rs.getString("id");
                    rs.close();
                    for (int l = 0; l < this.jTable1.getRowCount(); ++l) {
                        stm.execute("UPDATE `product_db`.`products` SET `stock_count`=`stock_count`+" + this.modelAdd.getValueAt(l, 4) + " WHERE `barcode`='" + this.modelAdd.getValueAt(l, 0) + "' and `product_code`='" + this.modelAdd.getValueAt(l, 1) + "';");
                        values = values + ",('" + id + "','" + this.modelAdd.getValueAt(l, 0) + "','" + this.modelAdd.getValueAt(l, 1) + "','" + this.modelAdd.getValueAt(l, 2) + "','" + this.modelAdd.getValueAt(l, 3) + "','" + this.modelAdd.getValueAt(l, 4) + "','" + this.modelAdd.getValueAt(l, 5) + "','" + this.modelAdd.getValueAt(l, 6) + "','" + this.modelAdd.getValueAt(l, 7) + "','" + this.modelAdd.getValueAt(l, 9) + "','" + this.modelAdd.getValueAt(l, 10) + "')";
                    }
                    final boolean c = stm.execute("INSERT INTO `product_db`.`qaime_mehsul` (`qaime_id`, `barcode`, `code`, `name`, `buy_price`, `value`, `unit`, `comission`, `sale_price`, `currency`, `qeyd`) VALUES " + values.substring(1));
                    if (!c) {
                        for (int m = this.modelAdd.getRowCount() - 1; m >= 0; --m) {
                            this.modelAdd.removeRow(m);
                        }
                        this.qaimeNum.setText("");
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, R.ProductNotFound.getValue());
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
    
    private void SearchQaime(final String QaimeNomresi, final String date) {
        Connection conn = null;
        Statement stm = null;
        try {
            for (int i = this.modelShow.getRowCount() - 1; i >= 0; --i) {
                this.modelShow.removeRow(i);
            }
            this.QaimeTertibZamani.setText("");
            this.DaxiledilmeZamani.setText("");
            this.toplam.setText("");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final String listString = "";
            if (QaimeNomresi.length() > 0) {
                String query = "";
                if (date.length() > 0) {
                    final String[] dates = date.split("/");
                    query = "and `qaime_zamani`='" + dates[2] + "-" + dates[1] + "-" + dates[0] + "' ";
                }
                final ResultSet rs = stm.executeQuery("SELECT * FROM product_db.muhasibat, product_db.qaime_mehsul where muhasibat.qaime_nomresi='" + QaimeNomresi + "' and muhasibat.id=qaime_mehsul.qaime_id  " + query + " ;");
                double cem = 0.0;
                if (rs.next()) {
                    this.FirmaAdiView.setText(rs.getString("alinan_sirketin_adi"));
                    this.QaimeTertibZamani.setText(rs.getString("qaime_zamani"));
                    this.DaxiledilmeZamani.setText(rs.getString("time"));
                    rs.previous();
                    while (rs.next()) {
                        this.modelShow.addRow(new Object[] { rs.getString("barcode"), rs.getString("code"), rs.getString("name"), rs.getString("buy_price"), rs.getString("value"), rs.getString("unit"), rs.getString("comission"), rs.getString("sale_price"), String.format("%.4f", rs.getDouble("buy_price") * rs.getDouble("value")), rs.getString("currency"), rs.getString("qeyd") });
                        cem += rs.getDouble("buy_price") * rs.getDouble("value");
                    }
                    this.toplam.setText(String.format("%.4f", cem));
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "NotFound");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
    
    private void searchBTNActionPerformed(final ActionEvent evt) {
        this.SearchQaime(this.qaime_nomresi.getText(), "");
    }
    
    private void minDateActionPerformed(final ActionEvent evt) {
    }
    
    private void minDateMouseClicked(final MouseEvent evt) {
        final Calendar njp = new Calendar("AZ");
        JOptionPane.showMessageDialog(this, njp, "Calendar", -1);
        this.minDate.setText(njp.getDate());
    }
    
    private void qaime_nomresiKeyReleased(final KeyEvent evt) {
    }
    
    private void qaimeNumKeyReleased(final KeyEvent evt) {
        Connection conn = null;
        Statement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            String query = "";
            final String where = "";
            query = "SELECT * FROM product_db.muhasibat where qaime_nomresi='" + this.qaimeNum.getText() + "' order by `id` asc ;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                this.ExistMEssage.setVisible(true);
                this.saveBTN.setEnabled(false);
            }
            else {
                this.ExistMEssage.setVisible(false);
                this.saveBTN.setEnabled(true);
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
    
    private void DeleteRowActionPerformed(final ActionEvent evt) {
        this.modelAdd.removeRow(this.jTable1.getSelectedRow());
    }
    
    private void jTable1KeyReleased(final KeyEvent evt) {
        if (evt.getKeyCode() == 40 && this.jTable1.getSelectedRow() + 1 == this.jTable1.getRowCount() && ((DefaultTableModel)this.jTable1.getModel()).getValueAt(this.jTable1.getSelectedRow(), 0) != null && !((DefaultTableModel)this.jTable1.getModel()).getValueAt(this.jTable1.getSelectedRow(), 0).equals("")) {
            ((DefaultTableModel)this.jTable1.getModel()).addRow(new Object[0]);
        }
    }
    
    private void maxDateMouseClicked(final MouseEvent evt) {
        final Calendar njp = new Calendar("AZ");
        JOptionPane.showMessageDialog(this, njp, "Calendar", -1);
        this.maxDate.setText(njp.getDate());
    }
    
    private void maxDateActionPerformed(final ActionEvent evt) {
    }
    
    private void SearchByTimeActionPerformed(final ActionEvent evt) {
        Connection conn = null;
        Statement stm = null;
        try {
            for (int i = this.modelQaimeGoster.getRowCount() - 1; i >= 0; --i) {
                this.modelQaimeGoster.removeRow(i);
            }
            final String[] mindates = this.minDate.getText().split("/");
            final String[] maxdates = this.maxDate.getText().split("/");
            this.QaimeTertibZamani.setText("");
            this.DaxiledilmeZamani.setText("");
            this.toplam.setText("");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final String listString = "";
            String min = "'1'='1'";
            String max = "'1'='1'";
            if (mindates.length == 3) {
                min = " qaime_zamani>='" + mindates[2] + "-" + mindates[1] + "-" + mindates[0] + "'";
            }
            if (maxdates.length == 3) {
                max = " qaime_zamani<='" + maxdates[2] + "-" + maxdates[1] + "-" + maxdates[0] + "'";
            }
            final ResultSet rs = stm.executeQuery("SELECT * FROM product_db.muhasibat where  " + min + " and  " + max);
            final double cem = 0.0;
            if (rs.next()) {
                rs.previous();
                final Statement stm2 = conn.createStatement();
                while (rs.next()) {
                    final ResultSet rs2 = stm2.executeQuery("SELECT * FROM product_db.muhasibat, product_db.qaime_mehsul where muhasibat.id='" + rs.getString("id") + "' and muhasibat.id=qaime_mehsul.qaime_id   ;");
                    double cemi = 0.0;
                    while (rs2.next()) {
                        cemi += rs2.getDouble("buy_price") * rs2.getDouble("value");
                    }
                    this.modelQaimeGoster.addRow(new Object[] { rs.getString("alinan_sirketin_adi"), rs.getString("qaime_nomresi"), rs.getString("qaime_novu"), rs.getString("qaime_zamani"), rs.getString("time"), String.format("%.4f", cemi) });
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "NotFound");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
    
    private void backButtonActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        System.exit(0);
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        this.minDate.setText("");
        this.maxDate.setText("");
    }
    
    private void autoFill(final int row, final int column) {
        Connection conn = null;
        Statement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            String query = "";
            String where = "";
            switch (column) {
                case 0: {
                    where = "barcode='" + this.modelAdd.getValueAt(row, column) + "'";
                    break;
                }
                default: {
                    where = "1";
                    break;
                }
            }
            query = "SELECT * FROM `product_db`.`products` where " + where + " order by `id` asc ;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final int say = 0;
                rs.previous();
                final int sum = 0;
                while (rs.next()) {
                    this.modelAdd.setValueAt(rs.getString("product_code"), row, 1);
                    this.modelAdd.setValueAt(rs.getString("name"), row, 2);
                    this.modelAdd.setValueAt(rs.getString("unit"), row, 5);
                    this.modelAdd.setValueAt(rs.getString("commission"), row, 6);
                    this.modelAdd.setValueAt(rs.getString("price"), row, 7);
                    this.modelAdd.setValueAt(rs.getString("currency"), row, 9);
                }
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
            Logger.getLogger(qaime_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(qaime_panel.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(qaime_panel.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(qaime_panel.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new qaime_panel().setVisible(true);
            }
        });
    }
    
    static {
        qaime_panel.SelectedRowQaimeGoster = -1;
    }
}
