// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class AddProduct extends JDialog
{
    private JButton AddBTN;
    private JTextField barcode;
    private JTextField buyprice;
    private JTextField code;
    private JSpinner count;
    private JTextField currency;
    private JTextField edv;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JTextField name;
    private JTextField salePrice;
    private JTextField type;
    private JTextField unit;
    
    public AddProduct(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.initComponents();
    }
    
    private void initComponents() {
        this.jLabel4 = new JLabel();
        this.barcode = new JTextField();
        this.jLabel5 = new JLabel();
        this.code = new JTextField();
        this.jLabel6 = new JLabel();
        this.name = new JTextField();
        this.jLabel7 = new JLabel();
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
        this.AddBTN = new JButton();
        this.jLabel12 = new JLabel();
        this.type = new JTextField();
        this.count = new JSpinner();
        this.setDefaultCloseOperation(2);
        this.jLabel4.setText("barcode");
        this.barcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AddProduct.this.barcodeActionPerformed(evt);
            }
        });
        this.jLabel5.setText("code");
        this.jLabel6.setText("name");
        this.jLabel7.setText("elave olunma sayi");
        this.jLabel8.setText("alis qiymeti");
        this.buyprice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AddProduct.this.buypriceActionPerformed(evt);
            }
        });
        this.jLabel9.setText("satis qiymeti");
        this.jLabel10.setText("edv");
        this.jLabel11.setText("Mezenne");
        this.jLabel13.setText("vahid");
        this.AddBTN.setText("Add");
        this.AddBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AddProduct.this.AddBTNActionPerformed(evt);
            }
        });
        this.jLabel12.setText("nov");
        this.count.setModel(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel12).addComponent(this.jLabel6).addComponent(this.jLabel7).addComponent(this.jLabel13).addComponent(this.jLabel11).addComponent(this.jLabel8).addComponent(this.jLabel9).addComponent(this.jLabel10)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.AddBTN).addComponent(this.edv, -2, 116, -2)).addGap(25, 25, 25)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.barcode).addComponent(this.code).addComponent(this.type).addComponent(this.name).addComponent(this.unit).addComponent(this.currency).addComponent(this.buyprice).addComponent(this.salePrice).addComponent(this.count, -1, 116, 32767)).addContainerGap(-1, 32767)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.barcode, -2, -1, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.code, -2, -1, -2)).addGap(12, 12, 12).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel12).addComponent(this.type, -2, -1, -2)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(this.name, -2, -1, -2)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel7).addComponent(this.count, -2, -1, -2)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel13).addComponent(this.unit, -2, -1, -2)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel11).addComponent(this.currency, -2, -1, -2)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel8).addComponent(this.buyprice, -2, -1, -2)).addGap(10, 10, 10).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel9).addComponent(this.salePrice, -2, -1, -2)).addGap(10, 10, 10).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel10).addComponent(this.edv, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.AddBTN).addContainerGap(173, 32767)));
        this.pack();
    }
    
    private void barcodeActionPerformed(final ActionEvent evt) {
    }
    
    private void buypriceActionPerformed(final ActionEvent evt) {
    }
    
    private void AddBTNActionPerformed(final ActionEvent evt) {
        Connection conn = null;
        Statement stm = null;
        try {
            final long l = System.currentTimeMillis();
            boolean yenile = true;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = conn.createStatement();
            final String query = "INSERT INTO `product_db`.`products` (`type`, `product_code`, `barcode`, `name`, `desc`, `saled_count`, `unit`, `price`, `lastpriceChange`, `currency`, `commission`, `stock_count`, `alis_qiymeti`, `productscol`) VALUES ('" + this.type.getText() + "', '" + this.code.getText() + "', '" + this.barcode.getText() + "', '" + this.name.getText() + "', '', '0', '" + this.unit.getText() + "', '" + this.salePrice.getText() + "', '', '" + this.currency.getText() + "', '" + this.edv.getText() + "', '" + this.count.getValue() + "', '" + this.buyprice.getText() + "', '');";
            final int r = stm.executeUpdate(query);
            if (r < 0) {
                yenile = false;
            }
            if (!yenile) {}
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
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final AddProduct dialog = new AddProduct(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(final WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
