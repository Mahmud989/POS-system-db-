// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.time.temporal.TemporalAccessor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Frame;
import javax.swing.event.TableModelListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.LayoutStyle;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import javax.swing.GroupLayout;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxEditor;
import java.awt.Component;
import javax.swing.plaf.metal.MetalComboBoxEditor;
import java.util.logging.Level;
import java.util.logging.Logger;
import product.res.R;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.util.TreeMap;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class index extends JFrame
{
    double sum;
    boolean showHint;
    JTextField field;
    static int selectedIndex;
    static TreeMap<String, String> map;
    DefaultTableModel model;
    private JButton BTN0;
    private JButton BTN1;
    private JButton BTN2;
    private JButton BTN3;
    private JButton BTN4;
    private JButton BTN5;
    private JButton BTN6;
    private JButton BTN7;
    private JButton BTN8;
    private JButton BTN9;
    private JLabel LabelForSearchInpt;
    private JButton PayBTN;
    private JButton PrintQaime;
    private JLabel QaliqLBL;
    private JButton backButton;
    private JButton backspace;
    private JButton clearBTN;
    private JButton clearSearchInput;
    private JLabel clientID;
    private JTextField clientgetId;
    private JComboBox<String> clientgetName;
    private JLabel customer_Name;
    private JButton d;
    private JTable dataTable;
    private JButton delete;
    private JButton deleteRow;
    private JButton dotBtn;
    private JButton enter;
    private JButton jButton1;
    private JCheckBox jCheckBox1;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JScrollPane jScrollPane1;
    private JPanel keyboard;
    private JComboBox<String> langBox;
    private JTextField mebleg;
    private JLabel pay_Type;
    private JComboBox<String> paymentType;
    private JComboBox<String> productInput;
    private JLabel qaliq;
    private JLabel qiymet;
    private JButton searchBTN;
    private JButton selectRow;
    private JLabel sellerId;
    private JLabel sellerName;
    private JLabel usergetID;
    private JLabel usergetName;
    
    public void reDrawTable() {
        this.model = new DefaultTableModel(new Object[0][], new String[] { "kod", "barcode", "name", "price", "count", "unit", "%", "sum" }) {
            boolean[] canEdit = { false, false, false, true, true, false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int i, final int i1) {
                return this.canEdit[i1];
            }
            
            @Override
            public void fireTableCellUpdated(final int row, final int col) {
                super.fireTableCellUpdated(row, col);
                if (col == 3 || col == 4) {
                    index.this.dataTable.setValueAt(String.format("%.2f", Double.parseDouble(String.valueOf(index.this.dataTable.getValueAt(row, 3))) * Double.parseDouble(String.valueOf(index.this.dataTable.getValueAt(row, 4)))), row, 7);
                    index.this.computeSum();
                }
            }
            
            public void setColEditable(final int col) {
                this.canEdit[col] = true;
            }
        };
        this.dataTable.setModel(this.model);
    }
    
    public index() {
        this.showHint = true;
        R.setLanguage("AZ");
        this.setExtendedState(6);
        this.initComponents();
        this.reDrawTable();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.productInput.setEditor(new MetalComboBoxEditor() {
            JTextField field = (JTextField)super.getEditorComponent();
            
            @Override
            public Component getEditorComponent() {
                this.field.setSize(index.this.productInput.getWidth() - 1, index.this.productInput.getHeight());
                return this.field;
            }
        });
        (this.field = (JTextField)this.productInput.getEditor().getEditorComponent()).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.searchBTNActionPerformed(evt);
            }
        });
        this.field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                super.keyReleased(e);
                if (index.this.showHint && e.getKeyCode() != 40 && e.getKeyCode() != 38 && e.getKeyCode() != 37 && e.getKeyCode() != 39) {
                    final String value = index.this.field.getText();
                    index.this.productInput.removeAllItems();
                    final List<String> strings = index.this.getTypes(value);
                    for (final String string : strings) {
                        index.this.productInput.addItem(string);
                    }
                    final int maxRowCount = (strings.size() > 8) ? 8 : strings.size();
                    index.this.productInput.setMaximumRowCount(maxRowCount);
                    index.this.productInput.showPopup();
                }
                if (e.getKeyCode() == 10) {
                    index.this.productInput.hidePopup();
                }
            }
        });
    }
    
    public index(final TreeMap<String, String> map) {
        this();
        index.map = map;
        this.usergetID.setText(map.get("user_id"));
        this.usergetName.setText(map.get("name"));
    }
    
    void computeSum() {
        this.sum = 0.0;
        for (int i = 0; i < this.model.getRowCount(); ++i) {
            this.sum += Double.parseDouble(String.valueOf(this.model.getValueAt(i, 7)));
        }
        this.qiymet.setText(String.format("%.2f", this.sum));
    }
    
    private void initComponents() {
        this.selectRow = new JButton();
        this.jCheckBox1 = new JCheckBox();
        this.langBox = new JComboBox<String>();
        this.jPanel1 = new JPanel();
        this.jPanel3 = new JPanel();
        this.LabelForSearchInpt = new JLabel();
        this.searchBTN = new JButton();
        this.jPanel5 = new JPanel();
        this.deleteRow = new JButton();
        this.jPanel6 = new JPanel();
        this.clearSearchInput = new JButton();
        this.productInput = new JComboBox<String>();
        this.jScrollPane1 = new JScrollPane();
        this.dataTable = new JTable();
        this.keyboard = new JPanel();
        this.jButton1 = new JButton();
        this.jPanel4 = new JPanel();
        this.sellerName = new JLabel();
        this.sellerId = new JLabel();
        this.clientID = new JLabel();
        this.clientgetId = new JTextField();
        this.usergetName = new JLabel();
        this.usergetID = new JLabel();
        this.jLabel1 = new JLabel();
        this.qiymet = new JLabel();
        this.PayBTN = new JButton();
        this.customer_Name = new JLabel();
        this.paymentType = new JComboBox<String>();
        this.pay_Type = new JLabel();
        this.jLabel3 = new JLabel();
        this.mebleg = new JTextField();
        this.QaliqLBL = new JLabel();
        this.qaliq = new JLabel();
        this.clientgetName = new JComboBox<String>();
        this.PrintQaime = new JButton();
        this.jPanel2 = new JPanel();
        this.BTN6 = new JButton();
        this.BTN1 = new JButton();
        this.BTN3 = new JButton();
        this.BTN2 = new JButton();
        this.BTN4 = new JButton();
        this.BTN5 = new JButton();
        this.enter = new JButton();
        this.BTN7 = new JButton();
        this.BTN9 = new JButton();
        this.BTN8 = new JButton();
        this.delete = new JButton();
        this.clearBTN = new JButton();
        this.dotBtn = new JButton();
        this.d = new JButton();
        this.BTN0 = new JButton();
        this.backspace = new JButton();
        this.backButton = new JButton();
        this.selectRow.setText("Select Row");
        this.selectRow.setFocusable(false);
        this.selectRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.selectRowActionPerformed(evt);
            }
        });
        this.jCheckBox1.setText("disable hint");
        this.jCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.jCheckBox1ActionPerformed(evt);
            }
        });
        this.setDefaultCloseOperation(3);
        this.setUndecorated(true);
        this.langBox.setModel(new DefaultComboBoxModel<String>(new String[] { "AZ", "EN", "RU", "TR" }));
        this.langBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.langBoxActionPerformed(evt);
            }
        });
        this.jPanel3.setBorder(BorderFactory.createBevelBorder(0));
        this.jPanel3.setLayout((LayoutManager)new AbsoluteLayout());
        this.LabelForSearchInpt.setText("Mehsulun  barkodu ve ya adi");
        this.jPanel3.add(this.LabelForSearchInpt, new AbsoluteConstraints(40, 30, -1, -1));
        this.searchBTN.setText("Axtar");
        this.searchBTN.setFocusable(false);
        this.searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.searchBTNActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.searchBTN, new AbsoluteConstraints(440, 20, -1, 30));
        this.deleteRow.setText("Setiri sil");
        this.deleteRow.setFocusable(false);
        this.deleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.deleteRowActionPerformed(evt);
            }
        });
        final GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addContainerGap(62, 32767).addComponent(this.deleteRow).addGap(19, 19, 19)));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.deleteRow).addContainerGap(36, 32767)));
        this.jPanel3.add(this.jPanel5, new AbsoluteConstraints(600, 10, 150, 70));
        this.jPanel6.setLayout(null);
        this.clearSearchInput.setBackground(new Color(255, 255, 255));
        this.clearSearchInput.setFont(new Font("Dialog", 1, 20));
        this.clearSearchInput.setText("X");
        this.clearSearchInput.setFocusable(false);
        this.clearSearchInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.clearSearchInputActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.clearSearchInput);
        this.clearSearchInput.setBounds(170, 0, 50, 30);
        this.productInput.setEditable(true);
        this.productInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.productInputActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.productInput);
        this.productInput.setBounds(0, 0, 170, 30);
        this.jPanel3.add(this.jPanel6, new AbsoluteConstraints(210, 20, 240, 34));
        this.dataTable.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, { null, null } }, new String[] { "Title 1", "Title 2" }) {
            boolean[] canEdit = { false, true };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.dataTable.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane1.setViewportView(this.dataTable);
        this.keyboard.setBorder(BorderFactory.createEtchedBorder(0));
        final GroupLayout keyboardLayout = new GroupLayout(this.keyboard);
        this.keyboard.setLayout(keyboardLayout);
        keyboardLayout.setHorizontalGroup(keyboardLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
        keyboardLayout.setVerticalGroup(keyboardLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 174, 32767));
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(27, 27, 27).addComponent(this.jPanel3, -1, -1, 32767).addGap(67, 67, 67)).addComponent(this.keyboard, -1, -1, 32767).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel3, -2, 88, -2).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 0, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.keyboard, -2, -1, -2)));
        this.jButton1.setText("X");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.jButton1ActionPerformed(evt);
            }
        });
        this.jPanel4.setBorder(new SoftBevelBorder(0));
        this.jPanel4.setLayout((LayoutManager)new AbsoluteLayout());
        this.sellerName.setText("Satici adi");
        this.jPanel4.add(this.sellerName, new AbsoluteConstraints(13, 14, -1, -1));
        this.sellerId.setText("Satici id");
        this.jPanel4.add(this.sellerId, new AbsoluteConstraints(13, 34, -1, -1));
        this.clientID.setText("Alici id");
        this.jPanel4.add(this.clientID, new AbsoluteConstraints(16, 57, -1, -1));
        this.jPanel4.add(this.clientgetId, new AbsoluteConstraints(109, 54, 150, -1));
        this.usergetName.setText("username");
        this.jPanel4.add(this.usergetName, new AbsoluteConstraints(109, 14, -1, -1));
        this.usergetID.setText("userid");
        this.jPanel4.add(this.usergetID, new AbsoluteConstraints(109, 34, -1, -1));
        this.jLabel1.setText("Umumi qiymet");
        this.jPanel4.add(this.jLabel1, new AbsoluteConstraints(13, 137, -1, -1));
        this.qiymet.setFont(new Font("Dialog", 1, 24));
        this.qiymet.setText("0");
        this.jPanel4.add(this.qiymet, new AbsoluteConstraints(146, 120, 93, 38));
        this.PayBTN.setText("Odenis et");
        this.PayBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.PayBTNActionPerformed(evt);
            }
        });
        this.jPanel4.add(this.PayBTN, new AbsoluteConstraints(210, 330, -1, 37));
        this.customer_Name.setText("Alici adi");
        this.jPanel4.add(this.customer_Name, new AbsoluteConstraints(16, 88, -1, -1));
        this.paymentType.setModel(new DefaultComboBoxModel<String>(new String[] { "N\u0259\u011fd", "Kartla", "-------" }));
        this.jPanel4.add(this.paymentType, new AbsoluteConstraints(150, 290, 67, 25));
        this.pay_Type.setText("Odenis tipi");
        this.jPanel4.add(this.pay_Type, new AbsoluteConstraints(13, 299, 116, -1));
        this.jLabel3.setText("Odenilen mebleg");
        this.jPanel4.add(this.jLabel3, new AbsoluteConstraints(13, 191, -1, -1));
        this.mebleg.setFont(new Font("Dialog", 0, 24));
        this.mebleg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.meblegActionPerformed(evt);
            }
        });
        this.mebleg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                index.this.meblegKeyReleased(evt);
            }
        });
        this.jPanel4.add(this.mebleg, new AbsoluteConstraints(146, 174, 93, -1));
        this.QaliqLBL.setText("Qaliq pul");
        this.jPanel4.add(this.QaliqLBL, new AbsoluteConstraints(13, 244, -1, -1));
        this.qaliq.setFont(new Font("Dialog", 1, 24));
        this.qaliq.setText("0");
        this.jPanel4.add(this.qaliq, new AbsoluteConstraints(146, 230, 93, -1));
        this.clientgetName.setEditable(true);
        this.clientgetName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.clientgetNameActionPerformed(evt);
            }
        });
        this.jPanel4.add(this.clientgetName, new AbsoluteConstraints(109, 85, 150, -1));
        this.PrintQaime.setText("Qaimeni cap et");
        this.PrintQaime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.PrintQaimeActionPerformed(evt);
            }
        });
        this.jPanel4.add(this.PrintQaime, new AbsoluteConstraints(20, 330, -1, 37));
        this.jPanel2.setBorder(new SoftBevelBorder(0));
        this.jPanel2.setLayout((LayoutManager)new AbsoluteLayout());
        this.BTN6.setFont(new Font("Dialog", 1, 18));
        this.BTN6.setText("6");
        this.BTN6.setToolTipText("");
        this.BTN6.setFocusable(false);
        this.BTN6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN6ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN6, new AbsoluteConstraints(200, 50, 100, 40));
        this.BTN1.setFont(new Font("Dialog", 1, 18));
        this.BTN1.setText("1");
        this.BTN1.setToolTipText("");
        this.BTN1.setFocusable(false);
        this.BTN1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN1ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN1, new AbsoluteConstraints(0, 0, 100, 40));
        this.BTN3.setFont(new Font("Dialog", 1, 18));
        this.BTN3.setText("3");
        this.BTN3.setToolTipText("");
        this.BTN3.setFocusable(false);
        this.BTN3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN3ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN3, new AbsoluteConstraints(200, 0, 100, 40));
        this.BTN2.setFont(new Font("Dialog", 1, 18));
        this.BTN2.setText("2");
        this.BTN2.setToolTipText("");
        this.BTN2.setFocusable(false);
        this.BTN2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN2ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN2, new AbsoluteConstraints(100, 0, 100, 40));
        this.BTN4.setFont(new Font("Dialog", 1, 18));
        this.BTN4.setText("4");
        this.BTN4.setToolTipText("");
        this.BTN4.setFocusable(false);
        this.BTN4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN4ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN4, new AbsoluteConstraints(0, 50, 100, 40));
        this.BTN5.setFont(new Font("Dialog", 1, 18));
        this.BTN5.setText("5");
        this.BTN5.setToolTipText("");
        this.BTN5.setFocusable(false);
        this.BTN5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN5ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN5, new AbsoluteConstraints(100, 50, 100, 40));
        this.enter.setText("Daxil et");
        this.enter.setToolTipText("");
        this.enter.setFocusable(false);
        this.enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.enterActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.enter, new AbsoluteConstraints(80, 210, 100, 40));
        this.BTN7.setFont(new Font("Dialog", 1, 18));
        this.BTN7.setText("7");
        this.BTN7.setToolTipText("");
        this.BTN7.setFocusable(false);
        this.BTN7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN7ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN7, new AbsoluteConstraints(0, 100, 100, 40));
        this.BTN9.setFont(new Font("Dialog", 1, 18));
        this.BTN9.setText("9");
        this.BTN9.setToolTipText("");
        this.BTN9.setFocusable(false);
        this.BTN9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN9ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN9, new AbsoluteConstraints(200, 100, 100, 40));
        this.BTN8.setFont(new Font("Dialog", 1, 18));
        this.BTN8.setText("8");
        this.BTN8.setToolTipText("");
        this.BTN8.setFocusable(false);
        this.BTN8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN8ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN8, new AbsoluteConstraints(100, 100, 100, 40));
        this.delete.setText("Sil ->");
        this.delete.setToolTipText("");
        this.delete.setFocusable(false);
        this.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.deleteActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.delete, new AbsoluteConstraints(0, 210, 70, 40));
        this.clearBTN.setText("Temizle");
        this.clearBTN.setToolTipText("");
        this.clearBTN.setFocusable(false);
        this.clearBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.clearBTNActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.clearBTN, new AbsoluteConstraints(100, 270, 100, 40));
        this.dotBtn.setFont(new Font("Dialog", 1, 18));
        this.dotBtn.setText(".");
        this.dotBtn.setToolTipText("");
        this.dotBtn.setFocusable(false);
        this.dotBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.dotBtnActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.dotBtn, new AbsoluteConstraints(200, 150, 100, 40));
        this.d.setFont(new Font("Dialog", 1, 18));
        this.d.setText(",");
        this.d.setToolTipText("");
        this.d.setFocusable(false);
        this.d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.dActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.d, new AbsoluteConstraints(0, 150, 100, 40));
        this.BTN0.setFont(new Font("Dialog", 1, 18));
        this.BTN0.setText("0");
        this.BTN0.setToolTipText("");
        this.BTN0.setFocusable(false);
        this.BTN0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.BTN0ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.BTN0, new AbsoluteConstraints(100, 150, 100, 40));
        this.backspace.setText("<- Sil ");
        this.backspace.setToolTipText("");
        this.backspace.setFocusable(false);
        this.backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.backspaceActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.backspace, new AbsoluteConstraints(190, 210, 110, 40));
        this.backButton.setText("<--");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                index.this.backButtonActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel4, -1, -1, 32767))).addGroup(layout.createSequentialGroup().addComponent(this.backButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.langBox, -2, -1, -2).addGap(43, 43, 43).addComponent(this.jButton1))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jButton1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.langBox, -2, -1, -2).addComponent(this.backButton)).addGap(1, 1, 1))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addGroup(layout.createSequentialGroup().addGap(4, 4, 4).addComponent(this.jPanel4, -1, -1, 32767).addGap(2, 2, 2).addComponent(this.jPanel2, -2, 310, -2)))));
        this.pack();
    }
    
    private void langBoxActionPerformed(final ActionEvent evt) {
        this.changeLanguage();
    }
    
    private void write(final char ch) {
        final Component c = this.getFocusOwner();
        if (c instanceof JTextField) {
            try {
                final int caretPosition = ((JTextField)c).getCaretPosition();
                final String currentText = ((JTextField)c).getText();
                if (caretPosition <= currentText.length()) {
                    String newText = "";
                    if (caretPosition > 0) {
                        newText = currentText.substring(0, caretPosition) + ch + currentText.substring(caretPosition, currentText.length());
                    }
                    else {
                        newText = ch + currentText.substring(0, currentText.length());
                    }
                    ((JTextField)c).setText(newText);
                    ((JTextField)c).requestFocus();
                    ((JTextField)c).setCaretPosition(caretPosition + 1);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void searchBTNActionPerformed(final ActionEvent evt) {
        try {
            this.dataTable.getCellEditor().stopCellEditing();
        }
        catch (Exception ex3) {}
        Connection conn = null;
        Statement stm = null;
        if (this.field.getText().length() > 0) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
                stm = conn.createStatement();
                final String query = "SELECT * FROM `product_db`.`products` where `barcode`='" + this.field.getText().replace("'", "\\'") + "' or `product_code`='" + this.field.getText().replace("'", "\\'") + "' or `name` like '" + this.field.getText().replace("'", "\\'") + "%' ;";
                final ResultSet rs = stm.executeQuery(query);
                if (rs.next()) {
                    final int say = 0;
                    rs.previous();
                    while (rs.next()) {
                        boolean b = true;
                        for (int i = 0; i < this.model.getRowCount(); ++i) {
                            if (this.model.getValueAt(i, 0).equals(rs.getString("product_code"))) {
                                this.model.setValueAt(Double.parseDouble(String.valueOf(this.model.getValueAt(i, 4))) + 1.0, i, 4);
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            this.model.addRow(new Object[] { rs.getString("product_code"), rs.getString("barcode"), rs.getString("name"), rs.getString("price"), 1, rs.getString("unit"), rs.getString("commission"), rs.getString("price") });
                        }
                    }
                    this.computeSum();
                    this.clearBTNActionPerformed(evt);
                }
                else {
                    JOptionPane.showMessageDialog(null, R.ProductNotFound.getValue());
                }
            }
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
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        System.exit(0);
    }
    
    private void enterActionPerformed(final ActionEvent evt) {
        final Component c = this.getFocusOwner();
        if (c instanceof JTextField) {
            ((JTextField)c).getActionListeners()[0].actionPerformed(evt);
        }
    }
    
    private void BTN2ActionPerformed(final ActionEvent evt) {
        this.write('2');
    }
    
    private void backButtonActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void dotBtnActionPerformed(final ActionEvent evt) {
        this.write('.');
    }
    
    private void BTN6ActionPerformed(final ActionEvent evt) {
        this.write('6');
    }
    
    private void BTN1ActionPerformed(final ActionEvent evt) {
        this.write('1');
    }
    
    private void BTN3ActionPerformed(final ActionEvent evt) {
        this.write('3');
    }
    
    private void BTN4ActionPerformed(final ActionEvent evt) {
        this.write('4');
    }
    
    private void BTN5ActionPerformed(final ActionEvent evt) {
        this.write('5');
    }
    
    private void BTN7ActionPerformed(final ActionEvent evt) {
        this.write('7');
    }
    
    private void BTN8ActionPerformed(final ActionEvent evt) {
        this.write('8');
    }
    
    private void BTN9ActionPerformed(final ActionEvent evt) {
        this.write('9');
    }
    
    private void BTN0ActionPerformed(final ActionEvent evt) {
        this.write('0');
    }
    
    private void dActionPerformed(final ActionEvent evt) {
        this.write(',');
    }
    
    private void clearBTNActionPerformed(final ActionEvent evt) {
        final Component c = this.getFocusOwner();
        if (c instanceof JTextField) {
            ((JTextField)c).setText("");
        }
    }
    
    private void deleteActionPerformed(final ActionEvent evt) {
        final Component c = this.getFocusOwner();
        if (c instanceof JTextField) {
            try {
                final int caretPosition = ((JTextField)c).getCaretPosition();
                final String currentText = ((JTextField)c).getText();
                if (caretPosition < currentText.length()) {
                    String newText = "";
                    if (caretPosition > 0) {
                        newText = currentText.substring(0, caretPosition) + currentText.substring(caretPosition + 1, currentText.length());
                    }
                    else {
                        newText = currentText.substring(1, currentText.length());
                    }
                    ((JTextField)c).setText(newText);
                    ((JTextField)c).requestFocus();
                    ((JTextField)c).setCaretPosition(caretPosition);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void backspaceActionPerformed(final ActionEvent evt) {
        final Component c = this.getFocusOwner();
        if (c instanceof JTextField) {
            try {
                final int caretPosition = ((JTextField)c).getCaretPosition();
                if (caretPosition > 0) {
                    final String currentText = ((JTextField)c).getText();
                    final String newText = currentText.substring(0, caretPosition - 1) + currentText.substring(caretPosition, currentText.length());
                    ((JTextField)c).setText(newText);
                    ((JTextField)c).requestFocus();
                    ((JTextField)c).setCaretPosition(caretPosition - 1);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void selectRowActionPerformed(final ActionEvent evt) {
    }
    
    private void deleteRowActionPerformed(final ActionEvent evt) {
        if (this.dataTable.getSelectedRow() >= 0) {
            for (int i = this.dataTable.getSelectedRows().length; i > 0; --i) {
                this.model.removeRow(this.dataTable.getSelectedRow());
            }
            this.computeSum();
        }
    }
    
    private void clearSearchInputActionPerformed(final ActionEvent evt) {
        this.field.setText("");
    }
    
    private void printProducts() {
        final String[] head = { "mehsul adi", "sayi", "vahid", "qiymet", "Mebleg" };
        final String[][] data = new String[this.dataTable.getRowCount()][head.length];
        for (int j = 0; j < this.dataTable.getRowCount(); ++j) {
            data[j][0] = String.valueOf(this.dataTable.getValueAt(j, 2));
            data[j][1] = String.valueOf(this.dataTable.getValueAt(j, 4));
            data[j][2] = String.valueOf(this.dataTable.getValueAt(j, 5));
            data[j][3] = String.valueOf(this.dataTable.getValueAt(j, 3));
            data[j][4] = String.valueOf(this.dataTable.getValueAt(j, 7));
        }
        final TableModel model = new TableModel() {
            String[] header = { "mehsul adi", "sayi", "vahid", "qiymet", "Mebleg" };
            
            @Override
            public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            }
            
            @Override
            public void addTableModelListener(final TableModelListener l) {
            }
            
            @Override
            public void removeTableModelListener(final TableModelListener l) {
            }
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(final int col) {
                return String.class;
            }
            
            @Override
            public int getRowCount() {
                return data.length;
            }
            
            @Override
            public int getColumnCount() {
                return this.header.length;
            }
            
            @Override
            public String getColumnName(final int col) {
                if (this.header.length > col) {
                    return this.header[col];
                }
                throw new AssertionError((Object)"invalid column");
            }
            
            @Override
            public Object getValueAt(final int row, final int col) {
                return data[row][col];
            }
        };
        final JTable table = new JTable(model);
        try {
            new PrintGui(this, true, model).setVisible(true);
        }
        catch (HeadlessException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void PayBTNActionPerformed(final ActionEvent evt) {
        final JCheckBox box = new JCheckBox("Qaim\u0259 \u00e7ap edilsin?", true);
        final int cavab = JOptionPane.showConfirmDialog(null, box, "odenis edilsin?", 2);
        if (cavab == 0) {
            Connection conn = null;
            Statement stm = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
                stm = conn.createStatement();
                final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
                final LocalDateTime now = LocalDateTime.now();
                final String date = dtf.format(now);
                stm.execute("SET @dt =  NOW();");
                for (int i = 0; i < this.model.getRowCount(); ++i) {
                    stm.execute("INSERT INTO `product_db`.`sale` (`product_id`,`valyuta`, `paymentType`, `product_barcode`, `product_name`, `count`, `price`, `unit`, `seller_id`, `seller_name`, `customer_id`, `customer_name`,\n `sale_date`,`time`) select id,currency, '" + this.paymentType.getSelectedItem() + "', '" + this.model.getValueAt(i, 1) + "', '" + this.model.getValueAt(i, 2) + "', '" + this.model.getValueAt(i, 4) + "', '" + this.model.getValueAt(i, 3) + "', '" + this.model.getValueAt(i, 5) + "', '" + this.usergetID.getText() + "', '" + this.usergetName.getText() + "', '" + this.clientID.getText() + "', '" + ((JTextField)this.clientgetName.getEditor().getEditorComponent()).getText() + "', '" + date + "', @dt FROM product_db.products \n where `barcode`='" + this.model.getValueAt(i, 1) + "';");
                    stm.execute("UPDATE `product_db`.`products` SET  `saled_count`=`saled_count`+'" + this.model.getValueAt(i, 4) + "', `stock_count`=`stock_count`-'" + this.model.getValueAt(i, 4) + "' WHERE `barcode`='" + this.model.getValueAt(i, 1) + "' and `product_code`='" + this.model.getValueAt(i, 0) + "';");
                }
                if (box.isSelected()) {
                    this.printProducts();
                }
                for (int i = this.model.getRowCount() - 1; i >= 0; --i) {
                    this.model.removeRow(i);
                }
            }
            catch (ClassNotFoundException ex4) {}
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
                try {
                    conn.close();
                }
                catch (SQLException ex2) {
                    Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
            finally {
                try {
                    conn.close();
                }
                catch (SQLException ex3) {
                    Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex3);
                }
            }
        }
    }
    
    private void meblegActionPerformed(final ActionEvent evt) {
        try {
            double q = 0.0;
            if (!this.qiymet.getText().equalsIgnoreCase("")) {
                q = Double.parseDouble(this.qiymet.getText());
            }
            final double m = Double.parseDouble(this.mebleg.getText());
            this.qaliq.setText(String.format("%.2f", m - q));
        }
        catch (Exception ex) {}
    }
    
    private void jCheckBox1ActionPerformed(final ActionEvent evt) {
        this.showHint = !this.jCheckBox1.isSelected();
    }
    
    private void meblegKeyReleased(final KeyEvent evt) {
        try {
            double m = 0.0;
            double q = 0.0;
            q = Double.parseDouble(this.qiymet.getText());
            if (!this.mebleg.getText().equalsIgnoreCase("")) {
                m = Double.parseDouble(this.mebleg.getText());
            }
            this.qaliq.setText(String.format("%.2f", m - q));
        }
        catch (Exception ex) {}
    }
    
    private void productInputActionPerformed(final ActionEvent evt) {
    }
    
    private void clientgetNameActionPerformed(final ActionEvent evt) {
    }
    
    private void PrintQaimeActionPerformed(final ActionEvent evt) {
        if (this.dataTable.getRowCount() > 0) {
            final String[] head = { "mehsul adi", "sayi", "vahid", "qiymet", "Mebleg" };
            final String[][] data = new String[this.dataTable.getRowCount()][head.length];
            for (int j = 0; j < this.dataTable.getRowCount(); ++j) {
                data[j][0] = String.valueOf(this.dataTable.getValueAt(j, 2));
                data[j][1] = String.valueOf(this.dataTable.getValueAt(j, 4));
                data[j][2] = String.valueOf(this.dataTable.getValueAt(j, 5));
                data[j][3] = String.valueOf(this.dataTable.getValueAt(j, 3));
                data[j][4] = String.valueOf(this.dataTable.getValueAt(j, 7));
            }
            final TableModel model = new TableModel() {
                String[] header = { "mehsul adi", "sayi", "vahid", "qiymet", "Mebleg" };
                
                @Override
                public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
                }
                
                @Override
                public void addTableModelListener(final TableModelListener l) {
                }
                
                @Override
                public void removeTableModelListener(final TableModelListener l) {
                }
                
                @Override
                public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                    return false;
                }
                
                @Override
                public Class<?> getColumnClass(final int col) {
                    return String.class;
                }
                
                @Override
                public int getRowCount() {
                    return data.length;
                }
                
                @Override
                public int getColumnCount() {
                    return this.header.length;
                }
                
                @Override
                public String getColumnName(final int col) {
                    if (this.header.length > col) {
                        return this.header[col];
                    }
                    throw new AssertionError((Object)"invalid column");
                }
                
                @Override
                public Object getValueAt(final int row, final int col) {
                    return data[row][col];
                }
            };
            final JTable table = new JTable(data, head);
            new PrintGui(this, true, model).setVisible(true);
        }
    }
    
    private ArrayList<String> getTypes(final String value) {
        com.mysql.jdbc.Connection conn = null;
        com.mysql.jdbc.Statement stm = null;
        try {
            final ArrayList<String> list = new ArrayList<String>();
            conn = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = (com.mysql.jdbc.Statement)conn.createStatement();
            final String query = "SELECT name FROM products where name like '" + value + "%' order by name;";
            final ResultSet rs = stm.executeQuery(query);
            list.add(value);
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    if (!list.contains(rs.getString(1))) {
                        list.add(rs.getString(1));
                    }
                }
            }
            return list;
        }
        catch (SQLException ex) {
            Logger.getLogger(satisidaresi.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex3) {
            final Exception ex2;
            final Exception ex = ex2;
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new index().setVisible(true));
    }
    
    private void changeLanguage() {
        if (index.selectedIndex != this.langBox.getSelectedIndex()) {
            index.selectedIndex = this.langBox.getSelectedIndex();
            R.setLanguage(this.langBox.getItemAt(index.selectedIndex));
            this.searchBTN.setText(R.searchBTN.getValue());
            this.PayBTN.setText(R.Pay_BTN.getValue());
            this.QaliqLBL.setText(R.Qaliq_LBL.getValue());
            this.delete.setText(R.DeleteRowBTN.getValue());
            this.LabelForSearchInpt.setText(R.LabelForProductSearchInput.getValue());
            this.sellerName.setText(R.seller_name.getValue());
            this.customer_Name.setText(R.customer_name.getValue());
            this.pay_Type.setText(R.PayTypeLBL.getValue());
        }
    }
    
    static {
        index.selectedIndex = -1;
    }
}
