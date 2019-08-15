// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.time.LocalDateTime;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

public class NewJFrame extends JFrame
{
    static int year;
    static int month;
    static int day;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    
    public NewJFrame() {
        this.initComponents();
        NewJFrame.month = LocalDateTime.now().getMonthValue();
        NewJFrame.year = LocalDateTime.now().getYear();
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();
        this.jButton7 = new JButton();
        this.setDefaultCloseOperation(3);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } }, new String[] { "B.e", "C.A", "Cersenbe", "Cume A.", "Cume", "Senbe", "Bazar" }) {
            Class[] types = { Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class };
            boolean[] canEdit = { false, false, false, false, false, false, false };
            
            @Override
            public Class getColumnClass(final int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jTable1.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane1.setViewportView(this.jTable1);
        if (this.jTable1.getColumnModel().getColumnCount() > 0) {
            this.jTable1.getColumnModel().getColumn(0).setResizable(false);
            this.jTable1.getColumnModel().getColumn(1).setResizable(false);
            this.jTable1.getColumnModel().getColumn(2).setResizable(false);
            this.jTable1.getColumnModel().getColumn(3).setResizable(false);
            this.jTable1.getColumnModel().getColumn(4).setResizable(false);
            this.jTable1.getColumnModel().getColumn(5).setResizable(false);
            this.jTable1.getColumnModel().getColumn(6).setResizable(false);
        }
        this.getContentPane().add(this.jScrollPane1, new AbsoluteConstraints(0, 110, 500, 250));
        this.jButton1.setText("jButton1");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                NewJFrame.this.jButton1ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton1, new AbsoluteConstraints(380, 410, -1, -1));
        this.jButton2.setText("<-");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                NewJFrame.this.jButton2ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton2, new AbsoluteConstraints(140, 10, -1, -1));
        this.jButton3.setText("->");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                NewJFrame.this.jButton3ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton3, new AbsoluteConstraints(200, 10, -1, -1));
        this.jButton4.setText("<-");
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                NewJFrame.this.jButton4ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton4, new AbsoluteConstraints(140, 70, -1, -1));
        this.jButton5.setText("->");
        this.getContentPane().add(this.jButton5, new AbsoluteConstraints(200, 70, -1, -1));
        this.jButton6.setText("<-");
        this.jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                NewJFrame.this.jButton6ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton6, new AbsoluteConstraints(140, 40, -1, -1));
        this.jButton7.setText("->");
        this.jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                NewJFrame.this.jButton7ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton7, new AbsoluteConstraints(200, 40, -1, -1));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        this.reLoadCalendar();
    }
    
    private void reLoadCalendar() {
        final GregorianCalendar calendar = new GregorianCalendar(NewJFrame.year, NewJFrame.month, 1);
        final int nod = calendar.getActualMaximum(5);
        final int som = calendar.get(7);
        this.jTable1.setColumnSelectionAllowed(true);
        this.jTable1.setRowSelectionAllowed(true);
        this.jTable1.setSelectionMode(0);
        this.jTable1.setRowHeight(38);
        final DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(final int i, final int i1) {
                return false;
            }
            
            @Override
            public void addColumn(final Object o) {
                super.addColumn(o);
            }
        };
        this.jTable1.setModel(dtm);
        dtm.setColumnCount(7);
        dtm.setRowCount(6);
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.jTable1.setValueAt(null, i, j);
            }
        }
        for (int i = 1; i <= nod; ++i) {
            final int row = new Integer((i + som - 2) / 7);
            final int column = (i + som - 2) % 7;
            this.jTable1.setValueAt(i, row, column);
            System.out.println(i + "\t" + column + "\t" + row);
        }
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        this.yearMinus(1);
        this.reLoadCalendar();
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton6ActionPerformed(final ActionEvent evt) {
        this.monthMinus(1);
        this.reLoadCalendar();
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        this.yearPlus(1);
        this.reLoadCalendar();
    }
    
    private void jButton7ActionPerformed(final ActionEvent evt) {
        this.monthPlus(1);
        this.reLoadCalendar();
    }
    
    private void yearPlus(final int plus) {
        NewJFrame.year += plus;
        if (NewJFrame.year > LocalDateTime.now().getYear()) {
            NewJFrame.year = LocalDateTime.now().getYear();
        }
    }
    
    private void yearMinus(final int minus) {
        NewJFrame.year -= minus;
        if (NewJFrame.year < 1998) {
            NewJFrame.year = 1998;
        }
    }
    
    private void monthMinus(final int minus) {
        NewJFrame.month -= minus;
        if (NewJFrame.month < 1) {
            NewJFrame.month = 12;
            this.yearMinus(1);
        }
    }
    
    private void monthPlus(final int plus) {
        NewJFrame.month += plus;
        if (NewJFrame.month > 12) {
            this.yearPlus(1);
            if (NewJFrame.year == LocalDateTime.now().getYear()) {
                NewJFrame.month = 12;
            }
            else {
                NewJFrame.month = 1;
            }
        }
    }
    
    private void day() {
        NewJFrame.day = (int)this.jTable1.getValueAt(this.jTable1.getSelectedRow(), this.jTable1.getSelectedColumn());
    }
    
    public String getDate() {
        this.setVisible(true);
        final String date = NewJFrame.day + "/" + NewJFrame.month + "/" + NewJFrame.year;
        return date;
    }
    
    static {
        NewJFrame.day = 1;
    }
}
