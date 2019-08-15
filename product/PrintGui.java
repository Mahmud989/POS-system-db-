// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.print.PrinterException;
import javax.swing.JOptionPane;
import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import java.text.MessageFormat;
import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.event.TableModelListener;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Frame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class PrintGui extends JDialog
{
    String[][] data;
    private JCheckBox FitToPageWidth;
    private JTextField FooterText;
    private JTextField HeaderText;
    private JCheckBox Interactive;
    private JCheckBox ShowPrintPage;
    private JCheckBox footer;
    private JCheckBox header;
    private JButton jButton1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    
    public PrintGui(final Frame parent, final boolean modal, final TableModel model1) {
        super(parent, modal);
        this.initComponents();
        this.jTable1.setShowGrid(true);
        this.jTable1.setModel(model1);
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.jPanel1 = new JPanel();
        this.jButton1 = new JButton();
        this.header = new JCheckBox();
        this.HeaderText = new JTextField();
        this.footer = new JCheckBox();
        this.FooterText = new JTextField();
        this.ShowPrintPage = new JCheckBox();
        this.FitToPageWidth = new JCheckBox();
        this.Interactive = new JCheckBox();
        this.setDefaultCloseOperation(2);
        this.setTitle("Print Panel");
        this.jTable1.setModel(new DefaultTableModel(new Object[][] { { "kibrit", "1", " 1 paket/10 eded", "0.4", "0.4" }, { "", null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null } }, new String[] { "mehsul adi", "sayi", "vahid", "qiymet", "Mebleg" }));
        this.jTable1.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane1.setViewportView(this.jTable1);
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "\u00c7ap etm\u0259k", 0, 2));
        this.jButton1.setText("\u00c7ap et");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PrintGui.this.jButton1ActionPerformed(evt);
            }
        });
        this.header.setText("Ba\u015fl\u0131q :");
        this.header.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PrintGui.this.headerActionPerformed(evt);
            }
        });
        this.HeaderText.setEnabled(false);
        this.footer.setText("S\u0259hif\u0259 sonu");
        this.footer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PrintGui.this.footerActionPerformed(evt);
            }
        });
        this.FooterText.setText("S\u0259hif\u0259 {0}");
        this.FooterText.setEnabled(false);
        this.ShowPrintPage.setSelected(true);
        this.ShowPrintPage.setText("Print s\u0259hif\u0259sini g\u00f6st\u0259r");
        this.ShowPrintPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PrintGui.this.ShowPrintPageActionPerformed(evt);
            }
        });
        this.FitToPageWidth.setSelected(true);
        this.FitToPageWidth.setText("C\u0259dv\u0259li \u00e7ap v\u0259r\u0259qin\u0259 uygunla\u015fd\u0131r");
        this.Interactive.setSelected(true);
        this.Interactive.setText("Interactive (\u00c7ap statusunu gost\u0259r)");
        this.Interactive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PrintGui.this.InteractiveActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.footer).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.FooterText, -2, 112, -2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.header).addGap(24, 24, 24).addComponent(this.HeaderText, -2, 112, -2))).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.FitToPageWidth).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton1, -2, 71, -2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.ShowPrintPage).addGap(71, 71, 71).addComponent(this.Interactive).addGap(0, 168, 32767))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.header).addComponent(this.HeaderText, -2, -1, -2).addComponent(this.ShowPrintPage).addComponent(this.Interactive)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, 32767).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.footer).addComponent(this.FooterText, -2, -1, -2).addComponent(this.FitToPageWidth)).addGap(30, 30, 30)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jButton1, -2, 40, -2).addContainerGap()))));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane1, -1, 418, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2).addGap(0, 0, 0)));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        this.printGradesTable(this.jTable1, this.HeaderText.getText(), this.FooterText.getText(), this.FitToPageWidth.isSelected(), this.ShowPrintPage.isSelected(), this.Interactive.isSelected());
    }
    
    private void headerActionPerformed(final ActionEvent evt) {
        this.HeaderText.setEnabled(this.header.isSelected());
    }
    
    private void ShowPrintPageActionPerformed(final ActionEvent evt) {
    }
    
    private void InteractiveActionPerformed(final ActionEvent evt) {
    }
    
    private void footerActionPerformed(final ActionEvent evt) {
        this.FooterText.setEnabled(this.footer.isSelected());
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
            Logger.getLogger(PrintGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(PrintGui.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(PrintGui.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(PrintGui.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                        return 5;
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
                        return "1";
                    }
                };
                final PrintGui dialog = new PrintGui(new JFrame(), true, model);
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
    
    private void printGradesTable(final JTable table, final String Header, final String Footer, final boolean fitWidth, final boolean showPrintDialog, final boolean interactive) {
        MessageFormat header = null;
        if (this.header.isSelected()) {
            header = new MessageFormat(Header);
        }
        MessageFormat footer = null;
        if (this.footer.isSelected()) {
            footer = new MessageFormat(Footer);
        }
        final JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {
            final boolean complete = table.print(mode, header, footer, showPrintDialog, null, interactive, null);
            if (complete) {
                JOptionPane.showMessageDialog(this, "Printing Complete", "Printing Result", 1);
            }
            else {
                JOptionPane.showMessageDialog(this, "Printing Cancelled", "Printing Result", 1);
            }
        }
        catch (PrinterException pe) {
            JOptionPane.showMessageDialog(this, "Printing Failed: " + pe.getMessage(), "Printing Result", 0);
        }
    }
}
