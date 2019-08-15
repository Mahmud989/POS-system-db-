// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.util.GregorianCalendar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.time.LocalDateTime;
import product.res.R;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;

public class Calendar extends JPanel
{
    static int year;
    static int month;
    static int day;
    static int yearSelectedIndex;
    private JTable Calendar;
    private JLabel ay;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JButton monthMinus;
    private JButton monthPlus;
    private JComboBox<String> yearbox;
    String[] dates;
    
    public Calendar(final String lang) {
        R.setLanguage(lang);
        this.initComponents();
        product.Calendar.month = LocalDateTime.now().getMonthValue();
        product.Calendar.year = LocalDateTime.now().getYear();
        final String[] years = new String[25];
        for (int i = 24; i >= 0; --i) {
            years[24 - i] = "" + (product.Calendar.year - i + 1);
        }
        this.yearbox.setModel(new DefaultComboBoxModel<String>(years));
        this.yearbox.setSelectedIndex(23);
        this.reLoadCalendar();
    }
    
    public static void main(final String[] args) {
        final Calendar njp = new Calendar("AZ");
        JOptionPane.showMessageDialog(null, njp, "Information", -1);
        System.out.println(njp.getDate());
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.Calendar = new JTable();
        this.monthMinus = new JButton();
        this.monthPlus = new JButton();
        this.ay = new JLabel();
        this.jLabel2 = new JLabel();
        this.yearbox = new JComboBox<String>();
        this.setLayout((LayoutManager)new AbsoluteLayout());
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
                product.Calendar.this.CalendarFocusGained(evt);
            }
        });
        this.Calendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                product.Calendar.this.CalendarMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.Calendar);
        this.add(this.jScrollPane1, new AbsoluteConstraints(0, 90, 500, 270));
        this.monthMinus.setText("<-");
        this.monthMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                product.Calendar.this.monthMinusActionPerformed(evt);
            }
        });
        this.add(this.monthMinus, new AbsoluteConstraints(0, 0, 60, 60));
        this.monthPlus.setText("->");
        this.monthPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                product.Calendar.this.monthPlusActionPerformed(evt);
            }
        });
        this.add(this.monthPlus, new AbsoluteConstraints(220, 0, 60, 60));
        this.ay.setFont(new Font("Dialog", 1, 24));
        this.ay.setHorizontalAlignment(0);
        this.ay.setText("Ay");
        this.add(this.ay, new AbsoluteConstraints(60, 20, 160, 30));
        this.jLabel2.setFont(new Font("Dialog", 1, 36));
        this.jLabel2.setText("il");
        this.add(this.jLabel2, new AbsoluteConstraints(360, 10, -1, -1));
        this.yearbox.setModel(new DefaultComboBoxModel<String>(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019" }));
        this.yearbox.setSelectedIndex(7);
        this.yearbox.setAutoscrolls(true);
        this.yearbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                product.Calendar.this.yearboxActionPerformed(evt);
            }
        });
        this.add(this.yearbox, new AbsoluteConstraints(410, 20, -1, -1));
    }
    
    private void monthMinusActionPerformed(final ActionEvent evt) {
        this.monthMinus(1);
        this.reLoadCalendar();
    }
    
    private void monthPlusActionPerformed(final ActionEvent evt) {
        this.monthPlus(1);
        this.reLoadCalendar();
    }
    
    private void CalendarFocusGained(final FocusEvent evt) {
        try {
            this.day();
        }
        catch (Exception ex) {}
    }
    
    private void yearboxActionPerformed(final ActionEvent evt) {
        if (product.Calendar.yearSelectedIndex != this.yearbox.getSelectedIndex()) {
            product.Calendar.yearSelectedIndex = this.yearbox.getSelectedIndex();
            product.Calendar.year = Integer.parseInt(String.valueOf(this.yearbox.getSelectedItem()));
            this.reLoadCalendar();
        }
    }
    
    private void CalendarMouseClicked(final MouseEvent evt) {
        try {
            this.day();
        }
        catch (Exception ex) {}
    }
    
    private void reLoadCalendar() {
        final int nod = new GregorianCalendar(product.Calendar.year, product.Calendar.month, 1).getActualMaximum(5);
        int c = new GregorianCalendar(product.Calendar.year, product.Calendar.month, 1).get(7) - 2;
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
            if (i == product.Calendar.day) {
                this.Calendar.setRowSelectionInterval(r, r);
                this.Calendar.setColumnSelectionInterval(col, col);
            }
            if (col >= 6) {
                ++r;
            }
            this.ay.setText(this.getMonthName(product.Calendar.month));
            this.yearbox.setSelectedItem("" + product.Calendar.year);
        }
    }
    
    private void yearPlus(final int plus) {
        product.Calendar.year += plus;
        if (product.Calendar.year > LocalDateTime.now().getYear() + 1) {
            product.Calendar.year = LocalDateTime.now().getYear() + 1;
        }
    }
    
    private void yearMinus(final int minus) {
        product.Calendar.year -= minus;
        if (product.Calendar.year < Integer.parseInt(this.yearbox.getItemAt(0))) {
            product.Calendar.year = Integer.parseInt(this.yearbox.getItemAt(0));
        }
    }
    
    private void monthMinus(final int minus) {
        product.Calendar.month -= minus;
        if (product.Calendar.month < 1) {
            product.Calendar.month = 12;
            this.yearMinus(1);
        }
    }
    
    private void monthPlus(final int plus) {
        product.Calendar.month += plus;
        if (product.Calendar.month > 12) {
            this.yearPlus(1);
            if (product.Calendar.year == LocalDateTime.now().getYear()) {
                product.Calendar.month = 12;
            }
            else {
                product.Calendar.month = 1;
            }
        }
    }
    
    private void day() {
        product.Calendar.day = (int)this.Calendar.getValueAt(this.Calendar.getSelectedRow(), this.Calendar.getSelectedColumn());
    }
    
    public String getDate() {
        final String date = product.Calendar.day + "/" + product.Calendar.month + "/" + product.Calendar.year;
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
    
    static {
        Calendar.day = 1;
        Calendar.yearSelectedIndex = -1;
    }
}
