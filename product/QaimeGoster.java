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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JDialog;

public class QaimeGoster extends JDialog
{
    private JScrollPane jScrollPane3;
    private JTable showTable;
    
    public QaimeGoster(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.initComponents();
    }
    
    private void initComponents() {
        this.jScrollPane3 = new JScrollPane();
        this.showTable = new JTable();
        this.setDefaultCloseOperation(2);
        this.showTable.setModel(new DefaultTableModel(new Object[0][], new String[] { "barcode", "code", "adi", "alis qiymeti", "miqdar", "vahid", "%", "satis qiymeti", "Mebleg", "Valyuta", "qeyd" }) {
            boolean[] canEdit = { false, false, false, false, false, false, false, false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.showTable.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane3.setViewportView(this.showTable);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane3, -2, 1140, -2).addGap(0, 0, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane3, -2, 518, -2).addGap(0, 46, 32767)));
        this.pack();
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
            Logger.getLogger(QaimeGoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(QaimeGoster.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(QaimeGoster.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(QaimeGoster.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final QaimeGoster dialog = new QaimeGoster(new JFrame(), true);
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
