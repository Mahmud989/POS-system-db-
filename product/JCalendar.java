// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.util.GregorianCalendar;
import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Component;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import product.res.R;
import java.time.LocalDateTime;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JDialog;

public class JCalendar extends JDialog
{
    static int year;
    static int month;
    static int day;
    String[] dates;
    static int yearSelectedIndex;
    private JTable Calendar;
    private JButton OKBTN;
    private JLabel ay;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JButton monthMinus;
    private JButton monthPlus;
    private JComboBox<String> yearbox;
    
    public JCalendar(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.initComponents();
        JCalendar.month = LocalDateTime.now().getMonthValue();
        JCalendar.year = LocalDateTime.now().getYear();
        final String[] years = new String[25];
        for (int i = 24; i >= 0; --i) {
            years[24 - i] = "" + (JCalendar.year - i + 1);
        }
        R.setLanguage("AZ");
        this.yearbox.setModel(new DefaultComboBoxModel<String>(years));
        this.yearbox.setSelectedIndex(23);
        this.reLoadCalendar();
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.monthMinus = new JButton();
        this.monthPlus = new JButton();
        this.ay = new JLabel();
        this.jLabel2 = new JLabel();
        this.yearbox = new JComboBox<String>();
        this.jScrollPane1 = new JScrollPane();
        this.Calendar = new JTable();
        this.OKBTN = new JButton();
        this.setDefaultCloseOperation(2);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.jPanel1.setLayout((LayoutManager)new AbsoluteLayout());
        this.monthMinus.setText("<-");
        this.monthMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                JCalendar.this.monthMinusActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.monthMinus, new AbsoluteConstraints(0, 0, 60, 60));
        this.monthPlus.setText("->");
        this.monthPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                JCalendar.this.monthPlusActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.monthPlus, new AbsoluteConstraints(220, 0, 60, 60));
        this.ay.setFont(new Font("Dialog", 1, 24));
        this.ay.setHorizontalAlignment(0);
        this.ay.setText("Ay");
        this.jPanel1.add(this.ay, new AbsoluteConstraints(60, 20, 160, 30));
        this.jLabel2.setFont(new Font("Dialog", 1, 36));
        this.jLabel2.setText("il");
        this.jPanel1.add(this.jLabel2, new AbsoluteConstraints(360, 10, -1, -1));
        this.yearbox.setModel(new DefaultComboBoxModel<String>(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019" }));
        this.yearbox.setSelectedIndex(7);
        this.yearbox.setAutoscrolls(true);
        this.yearbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                JCalendar.this.yearboxActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.yearbox, new AbsoluteConstraints(410, 20, -1, -1));
        this.Calendar.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } }, new String[] { "B.e", "C.A", "Cersenbe", "Cume A.", "Cume", "Senbe", "Bazar" }) {
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
        this.Calendar.getTableHeader().setReorderingAllowed(false);
        this.Calendar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent evt) {
                JCalendar.this.CalendarFocusGained(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.Calendar);
        this.jPanel1.add(this.jScrollPane1, new AbsoluteConstraints(0, 90, 500, 270));
        this.OKBTN.setText("OK");
        this.OKBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                JCalendar.this.OKBTNActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.OKBTN, new AbsoluteConstraints(400, 380, 70, -1));
        this.getContentPane().add(this.jPanel1, new AbsoluteConstraints(0, 0, -1, 420));
        this.pack();
    }
    
    private void monthMinusActionPerformed(final ActionEvent evt) {
        this.monthMinus(1);
        this.reLoadCalendar();
    }
    
    private void monthPlusActionPerformed(final ActionEvent evt) {
        this.monthPlus(1);
        this.reLoadCalendar();
    }
    
    private void yearboxActionPerformed(final ActionEvent evt) {
        if (JCalendar.yearSelectedIndex != this.yearbox.getSelectedIndex()) {
            JCalendar.yearSelectedIndex = this.yearbox.getSelectedIndex();
            JCalendar.year = Integer.parseInt(String.valueOf(this.yearbox.getSelectedItem()));
            this.reLoadCalendar();
        }
    }
    
    private void CalendarFocusGained(final FocusEvent evt) {
        try {
            this.day();
        }
        catch (Exception ex) {}
    }
    
    private void OKBTNActionPerformed(final ActionEvent evt) {
        System.out.println(this.getDate());
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
            Logger.getLogger(JCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(JCalendar.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(JCalendar.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(JCalendar.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JCalendar dialog = new JCalendar(new JFrame(), true);
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
    
    private void yearPlus(final int plus) {
        JCalendar.year += plus;
        if (JCalendar.year > LocalDateTime.now().getYear() + 1) {
            JCalendar.year = LocalDateTime.now().getYear() + 1;
        }
    }
    
    private void yearMinus(final int minus) {
        JCalendar.year -= minus;
        if (JCalendar.year < Integer.parseInt(this.yearbox.getItemAt(0))) {
            JCalendar.year = Integer.parseInt(this.yearbox.getItemAt(0));
        }
    }
    
    private void monthMinus(final int minus) {
        JCalendar.month -= minus;
        if (JCalendar.month < 1) {
            JCalendar.month = 12;
            this.yearMinus(1);
        }
    }
    
    private void monthPlus(final int plus) {
        JCalendar.month += plus;
        if (JCalendar.month > 12) {
            this.yearPlus(1);
            if (JCalendar.year == LocalDateTime.now().getYear()) {
                JCalendar.month = 12;
            }
            else {
                JCalendar.month = 1;
            }
        }
    }
    
    private void day() {
        JCalendar.day = (int)this.Calendar.getValueAt(this.Calendar.getSelectedRow(), this.Calendar.getSelectedColumn());
    }
    
    public String getDate() {
        final String date = JCalendar.day + "/" + JCalendar.month + "/" + JCalendar.year;
        return date;
    }
    
    String getMonthName(final int i) {
        this.dates = R.dates.getValue().split(",");
        switch (i) {
            case 1: {
                final String s = "Yanvar";
                break;
            }
            case 2: {
                final String s = "Fevral";
                break;
            }
            case 3: {
                final String s = "Mart";
                break;
            }
            case 4: {
                final String s = "Aprel";
                break;
            }
            case 5: {
                final String s = "May";
                break;
            }
            case 6: {
                final String s = "Iyun";
                break;
            }
            case 7: {
                final String s = "Iyul";
                break;
            }
            case 8: {
                final String s = "Avqust";
                break;
            }
            case 9: {
                final String s = "Sentyabr";
                break;
            }
            case 10: {
                final String s = "Oktyabr";
                break;
            }
            case 11: {
                final String s = "Noyabr";
                break;
            }
            case 12: {
                final String s = "Dekabr";
                break;
            }
            default: {
                final String s = "undefined";
                break;
            }
        }
        return this.dates[i - 1];
    }
    
    private void reLoadCalendar() {
        final int nod = new GregorianCalendar(JCalendar.year, JCalendar.month, 1).getActualMaximum(5);
        int c = new GregorianCalendar(JCalendar.year, JCalendar.month, 1).get(7) - 2;
        if (c < 0) {
            c += 7;
        }
        this.Calendar.setColumnSelectionAllowed(true);
        this.Calendar.setRowSelectionAllowed(true);
        this.Calendar.setSelectionMode(0);
        this.Calendar.setRowHeight(38);
        final DefaultTableModel dtm = new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } }, new String[] { "B.e", "C.A", "Cersenbe", "Cume A.", "Cume", "Senbe", "Bazar" }) {
            @Override
            public boolean isCellEditable(final int i, final int i1) {
                return false;
            }
            
            @Override
            public void addColumn(final Object o) {
                super.addColumn(o);
            }
        };
        this.Calendar.setModel(dtm);
        int r = 0;
        for (int i = 1; i <= nod; ++i) {
            int col = new Integer((i + c - 2) % 7);
            if (col < 0) {
                col += 7;
            }
            this.Calendar.setValueAt(i, r, col);
            if (i == JCalendar.day) {
                this.Calendar.setRowSelectionInterval(r, r);
                this.Calendar.setColumnSelectionInterval(col, col);
            }
            if (col >= 6) {
                ++r;
            }
            this.ay.setText(this.getMonthName(JCalendar.month));
            this.yearbox.setSelectedItem("" + JCalendar.year);
        }
    }
    
    static {
        JCalendar.day = 1;
        JCalendar.yearSelectedIndex = -1;
    }
}
